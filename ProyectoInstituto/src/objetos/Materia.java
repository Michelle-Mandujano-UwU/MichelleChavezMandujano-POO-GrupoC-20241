package objetos;

import org.json.simple.JSONObject;

/**
 *
 * @author 
 */
public class Materia {
    private int id;
    private String nombreMateria;
    private String semestre;
    private int idCarrera;

    public Materia(int id, String nombreMateria, String semestre, int idCarrera) {
        this.id = id;
        this.nombreMateria = nombreMateria;
        this.semestre = semestre;
        this.idCarrera = idCarrera;
    }
    
    public Materia(JSONObject jsonObject){
        this.id = Integer.parseInt(jsonObject.get("id").toString());
        this.nombreMateria = jsonObject.get("nombreMateria").toString();
        this.semestre = jsonObject.get("semestre").toString();
        this.idCarrera = Integer.parseInt(jsonObject.get("idCarrera").toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    
    
    
}

