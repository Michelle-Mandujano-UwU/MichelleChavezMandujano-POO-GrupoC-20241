package objetos;

import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 *
 * @author
 */
public class Graduacion {

    private int idRegistro;
    private int idCarrera;
    private int idAlumno;
    private String fechaGraduacion;
    private String generacion;
    private String promedio;

    public Graduacion(int idRegistro, int idCarrera, int idAlumno, String fechaGraduacion, String generacion, String promedio) {
        this.idRegistro = idRegistro;
        this.idCarrera = idCarrera;
        this.idAlumno = idAlumno;
        this.fechaGraduacion = fechaGraduacion;
        this.generacion = generacion;
        this.promedio = promedio;
    }

    public Graduacion(JSONObject jsonGraduacion) {
        this.idRegistro = Integer.parseInt(jsonGraduacion.get("idRegistro").toString());
        this.idCarrera = Integer.parseInt(jsonGraduacion.get("idCarrera").toString());
        this.idAlumno = Integer.parseInt(jsonGraduacion.get("idAlumno").toString());
        this.fechaGraduacion = jsonGraduacion.get("fechaGraduacion").toString();
        this.generacion = jsonGraduacion.get("generacion").toString();
        this.promedio = jsonGraduacion.get("promedio").toString();
    }

    public Graduacion(HashMap<String, String> mapGraduacion, int opt) {
        this.idRegistro = Integer.parseInt(mapGraduacion.get("idRegistro"));
        this.idCarrera = Integer.parseInt(mapGraduacion.get("idCarrera"));
        this.idAlumno = Integer.parseInt(mapGraduacion.get("idAlumno"));
        this.fechaGraduacion = mapGraduacion.get("fechaGraduacion");
        this.generacion = mapGraduacion.get("generacion");
        this.promedio = mapGraduacion.get("promedio");
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(String fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Graduacion{" + "idRegistro=" + idRegistro + ", idCarrera=" + idCarrera + ", idAlumno=" + idAlumno + ", fechaGraduacion=" + fechaGraduacion + ", generacion=" + generacion + ", promedio=" + promedio + '}';
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("idRegistro", idRegistro + "");
        jsonObject.put("idCarrera", idCarrera + "");
        jsonObject.put("idAlumno", idAlumno + "");
        jsonObject.put("fechaGraduacion", fechaGraduacion);
        jsonObject.put("generacion", generacion);
        jsonObject.put("promedio", promedio);

        return jsonObject;
    }

}
