package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import objetos.Alumno;
import objetos.Calificacion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Constantes;
import utils.Utils;

/**
 *
 * @author 
 */
public class CalificacionDao {
    List<Calificacion> listCalificacionesInSys = new ArrayList<>();
    
    public List<Calificacion> getListCalificacionesInSys() {
        return this.listCalificacionesInSys;
    }
    
    public Calificacion getCalificacionByIdAlumno(String idAlumno){
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdAlumno().equalsIgnoreCase(idAlumno)).findFirst().orElse(null);
    }
    
    public void deleteCalificacionesFromAlumnos(List<Alumno> listAlumnosReprobados){
        for (Alumno alumno : listAlumnosReprobados) {
            List<Calificacion> listCalificacionesOfAlumnoReprobado = getCalificacionesByIdAlumno(alumno.getId());
            
            for (Calificacion calificacion : listCalificacionesOfAlumnoReprobado) {
                int index = listCalificacionesInSys.indexOf(calificacion);
                listCalificacionesInSys.remove(index);
            }
 
        }
        
        writeCalificacionesOnJson();
    }
    
    public List<Calificacion> getCalificacionesByIdAlumno(int idAlumno){
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdAlumno().equalsIgnoreCase(idAlumno+"")).collect(Collectors.toList());
    }
    
     public List<Calificacion> getCalificacionesReprobadasByIdAlumnoAndSemestre(int idAlumno,String semestre){
         
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdAlumno().equalsIgnoreCase(idAlumno+"") &&
                        x.getSemestre().equalsIgnoreCase(semestre) && Double.parseDouble(x.getCalificacion()) < 70.0).collect(Collectors.toList());
        
    }
    
    
    public Calificacion getCalificacionByIdAlumnoAndMateria(String idAlumno,String idMateria){
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdAlumno().equalsIgnoreCase(idAlumno) &&
                            x.getIdMateria().equalsIgnoreCase(idMateria)).findFirst().orElse(null);
    }
    
    public List<Calificacion> getCalificacionesByIdAlumnoAndSemestre(int idAlumno,String semestre){
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdAlumno().equalsIgnoreCase(idAlumno+"") &&
                            x.getSemestre().equalsIgnoreCase(semestre)).collect(Collectors.toList());
    }
    
    public List<Calificacion> getCalificacionesByIdMateriaAndCalificacion(String idMateria,String filtroByCalificacion){
        List<Calificacion> listCalificacionesFinded = new ArrayList<>();
        
        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getIdMateria().equalsIgnoreCase(idMateria) && 
                            Double.parseDouble(x.getCalificacion()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getIdMateria().equalsIgnoreCase(idMateria) && 
                            Double.parseDouble(x.getCalificacion()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getIdMateria().equalsIgnoreCase(idMateria)).collect(Collectors.toList());
                break;
            default:
                
               break;
        } 
        
        return listCalificacionesFinded;
    }
    
    public List<Calificacion> getCalificacionesBySemestreAndMateriaAndCalificacion(String semestre,
            List<Integer> listMateriasImparte,String filtroByCalificacion){
        List<Calificacion> listCalificacionesFinded = new ArrayList<>();
        
        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre) && listMateriasImparte.contains(Integer.parseInt(x.getIdMateria())) &&
                            Double.parseDouble(x.getCalificacion()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre) && listMateriasImparte.contains(Integer.parseInt(x.getIdMateria())) && 
                            Double.parseDouble(x.getCalificacion()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre) &&listMateriasImparte.contains(Integer.parseInt(x.getIdMateria())) ).collect(Collectors.toList());
                break;
            default:
                
               break;
        } 
        
        return listCalificacionesFinded;
    }
    
    public List<Calificacion> getCalificacionesBySemestreCalificacion(String semestre,String filtroByCalificacion){
        List<Calificacion> listCalificacionesFinded = new ArrayList<>();
        
        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre)  &&
                            Double.parseDouble(x.getCalificacion()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre)  && 
                            Double.parseDouble(x.getCalificacion()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre)).collect(Collectors.toList());
                break;
            default:
                
               break;
        } 
        
        return listCalificacionesFinded;
    }
    
    public List<Calificacion> getCalificacionesBySemestreAndCarreraAndCalificacion(String semestre, String idCarrera,String filtroByCalificacion){
        List<Calificacion> listCalificacionesFinded = new ArrayList<>();
        
        switch (filtroByCalificacion) {
            case "1": // APROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre)  && x.getIdCarrera().equalsIgnoreCase(idCarrera) &&
                            Double.parseDouble(x.getCalificacion()) >= 70).collect(Collectors.toList());
                break;
            case "2": // REPROBADOS
                listCalificacionesFinded = listCalificacionesInSys.stream()
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre)  && x.getIdCarrera().equalsIgnoreCase(idCarrera) &&
                            Double.parseDouble(x.getCalificacion()) < 70).collect(Collectors.toList());
                break;
            case "3": //TODOS
                listCalificacionesFinded = listCalificacionesInSys.stream() 
                    .filter(x -> x.getSemestre().equalsIgnoreCase(semestre) && x.getIdCarrera().equalsIgnoreCase(idCarrera) ).collect(Collectors.toList());
                break;
            default:
                
               break;
        } 
        
        return listCalificacionesFinded;
    }
    
    public Calificacion getCalificacionByIdCalificacion(int idCalificacion){
        return listCalificacionesInSys.stream()
                .filter(x -> x.getIdCalificacion() == idCalificacion).findFirst().orElse(null);
    }
    
    
    public void modCalificacion(Calificacion calificacion, String newCalificacion){
        int index = listCalificacionesInSys.indexOf(calificacion);
        listCalificacionesInSys.get(index).setCalificacion(newCalificacion);
        
        writeCalificacionesOnJson();
    }
    
    public void addCalificacion(Calificacion calificacion){
        
        this.listCalificacionesInSys.add(calificacion);
        
        writeCalificacionesOnJson();
    }
    
    public void writeCalificacionesOnJson(){
        //ESCRIBIR LOS CAMBIOS EN EL ARCHIVO JSON
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Calificacion cal : listCalificacionesInSys) {
            jsonArray.add(cal.toJson());
        }

        jsonObject.put("calificaciones", jsonArray);

        Utils.writeDataOnJson(jsonObject.toString(), Constantes.NAME_FILE_CALIFICACIONES);
    }
    
    public void initDataCalificaciones() {
         JSONParser jsonParser = new JSONParser();
         JSONObject jsonCalificaciones = Utils.initData(Constantes.NAME_FILE_CALIFICACIONES);

        try {
            JSONArray jsonArrayCalificaciones = (JSONArray) jsonCalificaciones.get("calificaciones");
            Iterator it = jsonArrayCalificaciones.iterator();

            while (it.hasNext()) {
                JSONObject jsonCalificacion = (JSONObject) it.next();

                listCalificacionesInSys.add(new Calificacion(jsonCalificacion));
            }

            System.out.println("==> Terminé carga de calificaciones... ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
