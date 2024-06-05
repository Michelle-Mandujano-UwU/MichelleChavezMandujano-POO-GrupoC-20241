
package objetos;

import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
public class Calificacion {
    
    private int idCalificacion;
    private String idMateria;
    private String idAlumno;
    private String calificacion;
    private String semestre;
    private String idCarrera;

    public Calificacion(int idCalificacion,String idMateria, String idAlumno, String calificacion, String semestre,String idCarrera) {
        this.idCalificacion = idCalificacion;
        this.idMateria = idMateria;
        this.idAlumno = idAlumno;
        this.calificacion = calificacion;
        this.semestre = semestre;
        this.idCarrera = idCarrera;
    }
    
     public Calificacion(JSONObject jsonCalificacion) {
        this.idCalificacion = Integer.parseInt(jsonCalificacion.get("idCalificacion").toString());
        this.idMateria = jsonCalificacion.get("idMateria").toString();
        this.idAlumno = jsonCalificacion.get("idAlumno").toString();
        this.calificacion = jsonCalificacion.get("calificacion").toString();
        this.semestre = jsonCalificacion.get("semestre").toString();
        this.idCarrera = jsonCalificacion.get("idCarrera").toString();
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
    
    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }
    
    

    @Override
    public String toString() {
        return "Calificacion{" + "idMateria=" + idMateria + ", idAlumno=" + idAlumno + ", calificacion=" + calificacion + ", semestre=" + semestre + '}';
    }
    
    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("idCalificacion", idCalificacion+"");
        jsonObject.put("idMateria", idMateria);
        jsonObject.put("idAlumno", idAlumno);
        jsonObject.put("calificacion", calificacion);
        jsonObject.put("semestre", semestre);
        jsonObject.put("idCarrera", idCarrera);
        return jsonObject;
    }
    
     
}
