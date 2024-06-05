
package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;
import objetos.Coordinador;
import objetos.Carrera;
import objetos.Profesor;
import objetos.Materia;
import utils.Constantes;

/**
 *
 * @author 
 */
public class CoordinadoresDao {
    List<Coordinador> listCoordinadoresInSys = new ArrayList<>();
    
    public CoordinadoresDao(){
   
    }
    
    public void updateDataCoordinador(Coordinador c,Coordinador cNew){
        int index = listCoordinadoresInSys.indexOf(c);
        listCoordinadoresInSys.remove(index);
        listCoordinadoresInSys.add(cNew);
        
        writeDataJSON();
    }
    
    public Coordinador getCoordinadorById(int idCoordinador){
        return listCoordinadoresInSys.stream()
                .filter(x -> x.getId() == idCoordinador).findFirst().orElse(null);
    }
    
    public List<Coordinador> getListCoordinadoresInSys(){
        return this.listCoordinadoresInSys;
    }
    
     public List<String> getCoordinadoresByMateriaInside(List<Integer> listMaterias) {
         List<String> coordinadores = new ArrayList<>();
         
         for (Coordinador coord : listCoordinadoresInSys) {
             for (Integer idMateria : coord.getMateriasImparte()) {
                 if(listMaterias.contains(idMateria)){
                     coordinadores.add(coord.getFullName());
                     break;
                 }
             }
         }
         
        return coordinadores; 
    } 
    
    public void addCoordinador(Coordinador coordinador){
        listCoordinadoresInSys.add(coordinador);

        writeDataJSON();
    }
    
    public void writeDataJSON(){
        //ESCRIBIR LOS CAMBIOS EN EL ARCHIVO JSON
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Coordinador coordi : listCoordinadoresInSys) {
            jsonArray.add(coordi.toJson());
        }

        jsonObject.put("coordinadores", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_COORDINADORES);
    }

    public void initDataCoordinadores(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonCoordinadores = Utils.initData(Constantes.NAME_FILE_COORDINADORES);
        
        try {
            JSONArray jsonArrayCoordinadores = (JSONArray) jsonCoordinadores.get("coordinadores");
            Iterator it = jsonArrayCoordinadores.iterator();
            
            while(it.hasNext()){
                List<Integer> listMateriasImparte = new ArrayList<>();
                JSONObject jsonCoordinador = (JSONObject) it.next();
                
                JSONArray jsonArrayMatCoordinador = 
                        (JSONArray) jsonCoordinador.get("materiasImparte");
                Iterator itMats = jsonArrayMatCoordinador.iterator();
                
                while(itMats.hasNext()){
                    JSONObject jsonMateriaCoordinador = (JSONObject) itMats.next();
                    
                    listMateriasImparte.add(Integer.parseInt(
                                    jsonMateriaCoordinador.get("id").toString()));
                }
                
                listCoordinadoresInSys.add(new Coordinador
                        (jsonCoordinador,listMateriasImparte));
            }
            
            System.out.println("==> Terminé carga de coordinadores... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDataCoordinador(Coordinador c, 
                List<Materia> listMaterias,Carrera carr){
            String[] columns = {"Nombre","Apellidos","Direccion","Estado","Ciudad","Fecha de nacimiento","Sueldo","RFC","CURP","Carrera","Usuario","Contraseña"};
        
            Object[][] data = new Object[1][columns.length];
         
           /*listMaterias.stream().filter( x-> 
                    cal.getIdMateria().equalsIgnoreCase(x.getId()+"")).findFirst().orElse(null);*/
            
            data[0][0] = c.getNombre();
            data[0][1] = c.getApellidos();
            data[0][2] = c.getDireccion();
           
            data[0][3] = c.getEstado();
            data[0][4] = c.getCiudad();
            data[0][5] = c.getFechaNacimiento();
            data[0][6] = c.getSueldo();
            data[0][7] = c.getRfc();
            data[0][8] = c.getCurp();
            data[0][9] = carr.getNombreCarrera();
            data[0][10] = c.getUsername();
            data[0][11] = c.getPassword();

        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable(); 
    }




}
