package objetos;

import java.util.ArrayList;
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
public class Grupo {

    private int id;
    //private List<Integer> alumnos;
    private String tipoGrupo;
    private int idCarrera;
    private List<Integer> materias;
    private String semestre;

    public Grupo(int id,
            String tipoGrupo,
            int idCarrera,
            List<Integer> materias,
            String semestre) {

        this.id = id;
        //this.alumnos = alumnos;
        this.tipoGrupo = tipoGrupo;
        this.idCarrera = idCarrera;
        this.materias = materias;
        this.semestre = semestre;
    }

    public Grupo(JSONObject jsonObject, List<Integer> materias) {
        this.id = Integer.parseInt(jsonObject.get("id").toString());
        this.idCarrera = Integer.parseInt(jsonObject.get("idCarrera").toString());
        //this.alumnos = alumnos;
        this.materias = materias;
        this.semestre = jsonObject.get("semestre").toString();
        this.tipoGrupo = jsonObject.get("tipoGrupo").toString();
    }

    public Grupo(HashMap<String, String> dataGrupo) {
        this.id = Integer.parseInt(dataGrupo.get("id"));
        this.idCarrera = Integer.parseInt(dataGrupo.get("idCarrera"));
        //this.alumnos = new ArrayList<>();
        this.materias = Arrays.stream(dataGrupo.get("materias").split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());;
        this.semestre = dataGrupo.get("semestre");
        this.tipoGrupo = dataGrupo.get("tipoGrupo");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public List<Integer> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Integer> alumnos) {
        this.alumnos = alumnos;
    }*/

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public List<Integer> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Integer> materias) {
        this.materias = materias;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id+"");
        jsonObject.put("tipoGrupo", tipoGrupo);
        //jsonObject.put("alumnos", this);
        jsonObject.put("semestre", semestre);
        jsonObject.put("idCarrera", idCarrera+"");
                                        
        JSONArray arrayMaterias = new JSONArray();

        for (Integer idMateria : materias) {
            JSONObject jsonIdMateria = new JSONObject();
            jsonIdMateria.put("id", idMateria + "");
            arrayMaterias.add(jsonIdMateria);
        }
        jsonObject.put("materias", arrayMaterias);
        
        JSONArray arrayAlumnos = new JSONArray();

        /*for (Integer idAlumno : alumnos) {
            JSONObject jsonIdAlumno = new JSONObject();
            jsonIdAlumno.put("id", idAlumno + "");
            arrayAlumnos.add(jsonIdAlumno);
        }
        jsonObject.put("alumnos", arrayAlumnos);*/
        
        return jsonObject;
    }

}
