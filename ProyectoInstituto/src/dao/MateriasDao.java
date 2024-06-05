package dao;

import dnl.utils.text.table.TextTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import objetos.Carrera;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.stream.Collectors;
import objetos.Grupo;
import org.json.simple.parser.JSONParser;
import utils.Utils;
import objetos.Materia;

/**
 *
 * @author 
 */
public class MateriasDao {
    List<Materia> listMateriasInSystem = new ArrayList<>();
    CarrerasDao carrerasDao = null;
    
    public MateriasDao(){
    }
    
    public List<Materia> getListMateriasInSys() {
        return this.listMateriasInSystem;
    }
    
    public void initDataMaterias(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonMaterias = Utils.initData("materias.json");
        
        try {
            JSONArray jsonArrayMaterias = (JSONArray) jsonMaterias.get("materias");
            Iterator it = jsonArrayMaterias.iterator();
            
            while(it.hasNext()){
                JSONObject jsonMateria = (JSONObject) it.next();
                listMateriasInSystem.add(new Materia(jsonMateria));
            }
            
            System.out.println("==> Terminé carga de materias... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Materia getMateriaById(int id){
        return listMateriasInSystem.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }
    
    public List<Materia> getMateriasInsideProfesor(List<Integer> listMaterias){
        
        
        return listMateriasInSystem.stream()
                .filter(x -> listMaterias.contains(x.getId())).collect(Collectors.toList());
    }
 
    public List<Materia> getMateriasByIdCarrera(int idCarrera){
        return listMateriasInSystem.stream()
                .filter(x -> x.getIdCarrera()== idCarrera).collect(Collectors.toList());
    }

    
    public List<Materia> getMateriasByIdCarreraAndSemestre(int idCarrera,String semestre){
        return listMateriasInSystem.stream()
                .filter(x -> x.getIdCarrera()== idCarrera && x.getSemestre().equalsIgnoreCase(semestre)).collect(Collectors.toList());
    }
    
    public List<Integer> printMaterias(List<Carrera> listCarrerasInSystem){
        String[] columns = {"ID","Carrera","Materia","Semestre"};
        Object[][] data = new Object[listMateriasInSystem.size()][columns.length];
                 
        for (int i = 0;i< listMateriasInSystem.size();i++) {
            
            Materia materia = listMateriasInSystem.get(i);
            
            Carrera c = listCarrerasInSystem.stream()
                .filter(x -> x.getId() == materia.getIdCarrera()).
                    findFirst().orElse(null);
            
            data[i][0] = materia.getId();
            data[i][1] = c.getNombreCarrera();
            data[i][2] = materia.getNombreMateria();
            data[i][3] = materia.getSemestre();
        }
        
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();     
        
        
        List<Integer> listValidsId = new ArrayList<>();
        for (Materia materia : listMateriasInSystem) {
            listValidsId.add(materia.getId());
        }
        
        return listValidsId;
    }
    
    public List<Integer> printMateriasByIdCarrera(int idCarrera){
        String[] columns = {"ID","Materia","Semestre"};
        List<Materia> listMateriasByIdCarrera = getMateriasByIdCarrera(idCarrera);
        Object[][] data = new Object[listMateriasByIdCarrera.size()][columns.length];
                 
        for (int i = 0;i< listMateriasByIdCarrera.size();i++) {
            
            Materia materia = listMateriasByIdCarrera.get(i);
            
            data[i][0] = materia.getId();
            data[i][1] = materia.getNombreMateria();
            data[i][2] = materia.getSemestre();
        }
        
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable();  
        
        List<Integer> listValidsId = new ArrayList<>();
            for (Materia materia : listMateriasByIdCarrera) {
                listValidsId.add(materia.getId());
            }

            return listValidsId;
    }

    public List<Integer> printMateriasByProfesorImparte(List<Integer> listMaterias){
        String[] columns = {"ID","Materia","Semestre"};
        List<Materia> listMateriasInsideProfesor = getMateriasInsideProfesor(listMaterias);
        
        Object[][] data = new Object[listMateriasInsideProfesor.size()][columns.length];
                 
        for (int i = 0;i< listMateriasInsideProfesor.size();i++) {
            
            Materia materia = listMateriasInsideProfesor.get(i);
            
            data[i][0] = materia.getId();
            data[i][1] = materia.getNombreMateria();
            data[i][2] = materia.getSemestre();
        }
        
        TextTable tt = new TextTable(columns,data);                                                         
        tt.printTable(); 
        
         List<Integer> listValidsId = new ArrayList<>();
            for (Materia materia : listMateriasInsideProfesor) {
                listValidsId.add(materia.getId());
            }

            return listValidsId;
    }
    
    
}
