
package objetos;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
public class Historial {
    private int idRegistro;
    private int idAlumno;
    private List<Integer> materiasCursadas;
    //private List<Integer> profesoresImpartieron;
    private String profesoresImpartieron;
    private String promedioSemestre;
    private String semestre;

    public Historial(int idRegistro, int idAlumno, List<Integer> materiasCursadas, String profesoresImpartieron, String promedioSemestre, String semestre) {
        this.idRegistro = idRegistro;
        this.idAlumno = idAlumno;
        this.materiasCursadas = materiasCursadas;
        this.profesoresImpartieron = profesoresImpartieron;
        //this.profesoresImpartieron = profesoresImpartieron;
        this.promedioSemestre = promedioSemestre;
        this.semestre = semestre;
    }
    
    
     public Historial(HashMap<String, String> dataHistorial) {
        this.idRegistro = Integer.parseInt(dataHistorial.get("idRegistro"));
        this.idAlumno = Integer.parseInt(dataHistorial.get("idAlumno"));
        this.materiasCursadas = Arrays.stream(dataHistorial.get("materiasCursadas").split(","))
                                    .map(Integer::valueOf) 
                                    .collect(Collectors.toList());
        
        /*this.profesoresImpartieron = Arrays.stream(dataHistorial.get("profesoresImpartieron").split(","))
                                    .map(Integer::valueOf) 
                                    .collect(Collectors.toList());*/
        this.profesoresImpartieron = dataHistorial.get("profesoresImpartieron");
        
        this.promedioSemestre = dataHistorial.get("promedio");
        this.semestre = dataHistorial.get("semestre");
    }
    
    public Historial(JSONObject jsonObject, List<Integer> listMateriasCursadas) {
        this.idRegistro = Integer.parseInt(jsonObject.get("idRegistro").toString());
        this.idAlumno = Integer.parseInt(jsonObject.get("idAlumno").toString());
        this.materiasCursadas = listMateriasCursadas;
        this.profesoresImpartieron = jsonObject.get("profesoresImpartieron").toString();
        //this.profesoresImpartieron = listProfesores;
        this.promedioSemestre = jsonObject.get("promedio").toString();
        this.semestre = jsonObject.get("semestre").toString();
    }
    

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public List<Integer> getMateriasCursadas() {
        return materiasCursadas;
    }

    public void setMateriasCursadas(List<Integer> materiasCursadas) {
        this.materiasCursadas = materiasCursadas;
    }

    /*public List<Integer> getProfesoresImpartieron() {
        return profesoresImpartieron;
    }

    public void setProfesoresImpartieron(List<Integer> profesoresImpartieron) {
        this.profesoresImpartieron = profesoresImpartieron;
    }*/

    public String getProfesoresImpartieron() {
        return profesoresImpartieron;
    }

    public void setProfesoresImpartieron(String profesoresImpartieron) {
        this.profesoresImpartieron = profesoresImpartieron;
    }
    
    

    public String getPromedioSemestre() {
        return promedioSemestre;
    }

    public void setPromedioSemestre(String promedioSemestre) {
        this.promedioSemestre = promedioSemestre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
      public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idRegistro", this.getIdRegistro()+"");
        jsonObject.put("idAlumno", this.getIdAlumno()+"");
        jsonObject.put("promedio", this.getPromedioSemestre());
        jsonObject.put("semestre", this.getSemestre());
        
        JSONArray arrayMateriasCursadas = new JSONArray();

        for (Integer idMateria : materiasCursadas) {
            JSONObject jsonIdMateria = new JSONObject();
            jsonIdMateria.put("id", idMateria + "");
            arrayMateriasCursadas.add(jsonIdMateria);
        }
        
        jsonObject.put("materiasCursadas", arrayMateriasCursadas);
        
        /*JSONArray arrayProfesores = new JSONArray();

        for (Integer idMateria : profesoresImpartieron) {
            JSONObject jsonIdProfesor = new JSONObject();
            jsonIdProfesor.put("id", idMateria + "");
            arrayProfesores.add(jsonIdProfesor);
        }*/
        
        jsonObject.put("profesoresImpartieron", profesoresImpartieron);

        
        
        return jsonObject;
    }

    
}
