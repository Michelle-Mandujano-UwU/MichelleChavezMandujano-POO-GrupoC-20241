package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objetos.Alumno;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;
import objetos.Profesor;
import utils.Constantes;
import java.util.stream.Collectors;
import objetos.Carrera;
import objetos.Coordinador;
import objetos.Materia;

/**
 *
 * @author 
 */
public class ProfesorDao {
    List<Profesor> listProfesoresInSys = new ArrayList<Profesor>();
    MateriasDao materiasDao = null;
    
     public ProfesorDao(MateriasDao materiasDao){
        this.materiasDao = materiasDao;
    }
     
    public void updateDataProfesor(Profesor c,Profesor cNew){
        int index = listProfesoresInSys.indexOf(c);
        listProfesoresInSys.remove(index);
        listProfesoresInSys.add(cNew);
        
        writeDataJSON();
    }
    
    public List<Profesor> getListProfesoresInSys(){
        return this.listProfesoresInSys;
    }
    
     public List<String> getProfesoresByMateriaInside(List<Integer> listMaterias) {
         List<String> profesores = new ArrayList<>();
         
         for (Profesor profesor : listProfesoresInSys) {
             for (Integer idMateria : profesor.getMateriasImparte()) {
                 if(listMaterias.contains(idMateria)){
                     profesores.add(profesor.getFullName());
                     break;
                 }
             }
         }
         
        return profesores; 
    } 
    
    public Profesor getProfesorById(int id) {
        return listProfesoresInSys.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    
    public void addProfesor(Profesor profesor){
        listProfesoresInSys.add(profesor);
        writeDataJSON();
    }
    
    public void writeDataJSON(){
         //ESCRIBIR LOS CAMBIOS EN EL ARCHIVO JSON
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Profesor profe : listProfesoresInSys) {
            jsonArray.add(profe.toJson());
        }

        jsonObject.put("profesores", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_PROFESORES);
    }
    
    public void initDataProfesores() {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonProfesores = Utils.initData(Constantes.NAME_FILE_PROFESORES);

        try {
            JSONArray jsonArrayProfesores = (JSONArray) jsonProfesores.get("profesores");
            Iterator it = jsonArrayProfesores.iterator();

            while (it.hasNext()) {
                JSONObject jsonProfesor = (JSONObject) it.next();
                List<Integer> listMateriasImparte = new ArrayList<>();
                
                JSONArray jsonArrayMatsProfesores = (JSONArray) jsonProfesor.get("materiasImparte");
                Iterator itMats = jsonArrayMatsProfesores.iterator();
                
                while (itMats.hasNext()) {
                    JSONObject jsonMateriaProfesor = (JSONObject) itMats.next();
                    listMateriasImparte.add(
                            Integer.parseInt(
                                    jsonMateriaProfesor.get("id").toString()));
                }
                
                List<Integer> listGruposAsignados = new ArrayList<>();
                
                JSONArray jsonArrayGruposAsignados = (JSONArray) jsonProfesor.get("gruposAsignados");
                Iterator itGrupos = jsonArrayGruposAsignados.iterator();
                
                while (itGrupos.hasNext()) {
                    JSONObject jsonGrupoProfesor = (JSONObject) itGrupos.next();
                    listGruposAsignados.add(
                            Integer.parseInt(
                                    jsonGrupoProfesor.get("id").toString()));
                }
                
                listProfesoresInSys.add(new Profesor(jsonProfesor,listMateriasImparte,listGruposAsignados));
            }

            System.out.println("==> Terminé carga de profesores... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void printDataProfessor(Profesor p, 
                List<Materia> listMaterias){
            String[] columns = {"Nombre","Apellidos","Direccion","Estado","Ciudad","Fecha de nacimiento","Sueldo","RFC","CURP","Usuario","Contraseña"};
        
            Object[][] data = new Object[1][columns.length];
            
            data[0][0] = p.getNombre();
            data[0][1] = p.getApellidos();
            data[0][2] = p.getDireccion();
           
            data[0][3] = p.getEstado();
            data[0][4] = p.getCiudad();
            data[0][5] = p.getFechaNacimiento();
            data[0][6] = p.getSueldo();
            data[0][7] = p.getRfc();
            data[0][8] = p.getCurp();
            data[0][9] = p.getUsername();
            data[0][10] = p.getPassword();

        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable(); 
    }
}
