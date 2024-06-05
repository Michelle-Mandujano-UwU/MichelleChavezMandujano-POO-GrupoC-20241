
package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import objetos.Alumno;
import objetos.Carrera;
import objetos.Grupo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Constantes;
import utils.Utils;

/**
 *
 * @author 
 */
public class GruposDao {
    List<Grupo> listGruposInSys = new ArrayList<Grupo>();

    public List<Grupo> getListGruposInSys(){
        return this.listGruposInSys;
    }
    
    public void avanzarSemestreGrupo(List<Integer> listNewMaterias, Grupo grupo,String semestre){
        int index = listGruposInSys.indexOf(grupo);
        
        listGruposInSys.get(index).setSemestre(semestre);
        listGruposInSys.get(index).setMaterias(listNewMaterias);
        
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Grupo grp : listGruposInSys) {
            jsonArray.add(grp.toJson());
        }

        jsonObject.put("grupos", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_GRUPOS);
    }
    
    public Grupo getGrupoById(int id) {
        return listGruposInSys.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    
    public List<Grupo> getGruposByIdCarrera(int idCarrera){
        return listGruposInSys.stream()
                .filter(x -> x.getIdCarrera()== idCarrera).collect(Collectors.toList());
    }
    
    public Grupo getGrupoByIdCarreraAndSemestreAndTipoGrupo(int idCarrera,int idSemestre,String tipoGrupo){
        return listGruposInSys.stream()
                .filter(x -> x.getIdCarrera()== idCarrera && 
                        x.getSemestre().equalsIgnoreCase(idSemestre+"") &&
                        x.getTipoGrupo().equalsIgnoreCase(tipoGrupo)).findFirst().orElse(null);
    }
    
    public List<Grupo> getGruposByMateriaInside(int idMateria){
        return listGruposInSys.stream()
                .filter(x -> x.getMaterias().contains(idMateria)).collect(Collectors.toList());
    }
    
    public List<Grupo> getGruposByIdGrupoInside(List<Integer> gruposAsignados){
        return listGruposInSys.stream()
                .filter(x -> gruposAsignados.contains(x.getId())).collect(Collectors.toList());
    }
    
    
    
    public void addGrupo(Grupo grupo){
        listGruposInSys.add(grupo);

        //ESCRIBIR LOS CAMBIOS EN EL ARCHIVO JSON
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Grupo grp : listGruposInSys) {
            jsonArray.add(grp.toJson());
        }

        jsonObject.put("grupos", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_GRUPOS);
    }

    public void initDataGrupos() {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonGrupos = Utils.initData(Constantes.NAME_FILE_GRUPOS);

        try {
            JSONArray jsonArrayGrupos = (JSONArray) jsonGrupos.get("grupos");
            Iterator it = jsonArrayGrupos.iterator();
            
 

            while (it.hasNext()) {
                JSONObject jsonGrupo = (JSONObject) it.next();
                List<Integer> listMaterias = new ArrayList<>();
                List<Integer> listAlumnos = new ArrayList<>();
                
                //OBTENIENDO MATERIAS
                JSONArray jsonArrayMateriasGrupo = (JSONArray) jsonGrupo.get("materias");
                Iterator itMats = jsonArrayMateriasGrupo.iterator();
                
                while (itMats.hasNext()) {
                    JSONObject jsonMateria = (JSONObject) itMats.next();
                    listMaterias.add(
                            Integer.parseInt(
                                    jsonMateria.get("id").toString()));
                }
                
                //OBTENIENDO ALUMNOS
                /*JSONArray jsonArrayAlumnosGrupo = (JSONArray) jsonGrupo.get("alumnos");
                Iterator itLAlums = jsonArrayAlumnosGrupo.iterator();
                
                while (itLAlums.hasNext()) {
                    JSONObject jsonAlumno = (JSONObject) itLAlums.next();
                    listAlumnos.add(
                            Integer.parseInt(
                                    jsonAlumno.get("id").toString()));
                }*/

                listGruposInSys.add(new Grupo(jsonGrupo,listMaterias));
            }

            System.out.println("==> Terminé carga de grupos... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Integer> printGruposByIdCarrera(int idCarrera){
        String[] columns = {"ID","Tipo Grupo","Semestre"};
        List<Grupo> listGruposByIdCarrera = getGruposByIdCarrera(idCarrera);
        Object[][] data = new Object[listGruposByIdCarrera.size()][columns.length];
        
        for (int i = 0;i< listGruposByIdCarrera.size();i++) {
            
            Grupo grupo = listGruposByIdCarrera.get(i);
            
            data[i][0] = grupo.getId();
            data[i][1] = grupo.getTipoGrupo();
            data[i][2] = grupo.getSemestre();
        }
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();  
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Grupo grupo : listGruposByIdCarrera) {
            listValidsId.add(grupo.getId());
        }
        
        return listValidsId;
    }
    
    public List<Integer> printAllGrupos(List<Carrera> listCarrerasInSystem){
         String[] columns = {"ID","Carrera","Tipo Grupo","Semestre"};
         Object[][] data = new Object[listGruposInSys.size()][columns.length];
        
        for (int i = 0;i< listGruposInSys.size();i++) {
            
            Grupo grupo = listGruposInSys.get(i);
            
            data[i][0] = grupo.getId();
            data[i][1] = listCarrerasInSystem.stream().
                    filter( x -> x.getId() == grupo.getIdCarrera())
                        .findFirst().get().getNombreCarrera();
            data[i][2] = grupo.getTipoGrupo();
            data[i][3] = grupo.getSemestre();
        }
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Grupo grupo : listGruposInSys) {
            listValidsId.add(grupo.getId());
        }
        
        return listValidsId;
    }

    public List<Integer> printGruposByMateriaInside(int idMateria){
         String[] columns = {"ID","Tipo Grupo","Semestre"};
         List<Grupo> listGruposByMateriaInside = getGruposByMateriaInside(idMateria);
         Object[][] data = new Object[listGruposByMateriaInside.size()][columns.length];
        
        for (int i = 0;i< listGruposByMateriaInside.size();i++) {
            
            Grupo grupo = listGruposByMateriaInside.get(i);
            
            data[i][0] = grupo.getId();
            data[i][1] = grupo.getTipoGrupo();
            data[i][2] = grupo.getSemestre();
        }
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Grupo grupo : listGruposByMateriaInside) {
            listValidsId.add(grupo.getId());
        }
        
        return listValidsId;
    }

    public List<Integer> printGruposByIdGrupoInside(List<Integer> gruposAsignados){
         String[] columns = {"ID","Tipo Grupo","Semestre"};
         List<Grupo> listGruposByIdGrupoAsignado = getGruposByIdGrupoInside(gruposAsignados);
         Object[][] data = new Object[listGruposByIdGrupoAsignado.size()][columns.length];
        
        for (int i = 0;i< listGruposByIdGrupoAsignado.size();i++) {
            
            Grupo grupo = listGruposByIdGrupoAsignado.get(i);
            
            data[i][0] = grupo.getId();
            data[i][1] = grupo.getTipoGrupo();
            data[i][2] = grupo.getSemestre();
        }
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Grupo grupo : listGruposByIdGrupoAsignado) {
            listValidsId.add(grupo.getId());
        }
        
        return listValidsId;
    }
    
}
