package objetos;

import java.util.HashMap;
import org.json.simple.JSONObject;
import utils.Constantes;
import utils.Utils;

/**
 *
 * @author
 */
public class Alumno extends Usuario {

    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String ciudad;
    private String estado;
    private String curp;
    private String direccion;
    private String fechaRegistro;

    private int idGrupo;
    private int idCarrera;

    private String semestre;
    private String promedio;
    private String numeroControl;

    public Alumno(int id, String nombre, String apellidos, String fechaNacimiento,
            String ciudad, String estado, String direccion,
            String fechaRegistro, String promedio, String numeroControl,
            String username, String password, int idCarrera, int idGrupo) {

        super(id, username, password, Constantes.Rol.ALUMNO);
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.curp = Utils.generarCurp(this.apellidos,this.nombre, this.fechaNacimiento, this.estado);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.semestre = "1";
        this.promedio = promedio;
        this.numeroControl = numeroControl;

        this.idCarrera = idCarrera;
        this.idGrupo = idGrupo;
    }

    public Alumno(JSONObject jsonObject, int isJson) {
        super(Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("username").toString(),
                jsonObject.get("password").toString(),
                Constantes.Rol.ALUMNO);

        this.nombre = jsonObject.get("nombre").toString();
        this.apellidos = jsonObject.get("apellidos").toString();
        this.fechaNacimiento = jsonObject.get("fechaNacimiento").toString();
        this.ciudad = jsonObject.get("ciudad").toString();
        this.estado = jsonObject.get("estado").toString();
        this.curp = jsonObject.get("curp").toString();
        this.direccion = jsonObject.get("direccion").toString();
        this.fechaRegistro = jsonObject.get("fechaRegistro").toString();
        this.semestre = jsonObject.get("semestre").toString();
        this.promedio = jsonObject.get("promedio").toString();
        this.numeroControl = jsonObject.get("numeroControl").toString();

        this.idCarrera = Integer.parseInt(jsonObject.get("idCarrera").toString());
        this.idGrupo = Integer.parseInt(jsonObject.get("idGrupo").toString());
    }

    public Alumno(HashMap<String, String> dataAlumno) {
        super(Integer.parseInt(dataAlumno.get("id")),
                dataAlumno.get("username"),
                dataAlumno.get("password"),
                Constantes.Rol.ALUMNO);

        this.nombre = dataAlumno.get("nombre");
        this.apellidos = dataAlumno.get("apellidos");
        this.fechaNacimiento = dataAlumno.get("fechaNacimiento");
        this.ciudad = dataAlumno.get("ciudad");
        this.estado = dataAlumno.get("estado");
        this.direccion = dataAlumno.get("direccion");
        this.fechaRegistro = dataAlumno.get("fechaRegistro");
        this.semestre = "1";
        this.promedio = "0";
        this.curp = Utils.generarCurp(this.apellidos,this.nombre, this.fechaNacimiento, this.estado);
        this.idCarrera = Integer.parseInt(dataAlumno.get("idCarrera").toString());
        this.idGrupo = Integer.parseInt(dataAlumno.get("idGrupo").toString());
        this.numeroControl = Utils.generateNumeroControl(nombre, fechaNacimiento, this.idCarrera, dataAlumno.get("id"));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getFullName() {
        return this.nombre + " " + this.apellidos;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + ", estado=" + estado + ", curp=" + curp + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", idGrupo=" + idGrupo + ", idCarrera=" + idCarrera + ", promedio=" + promedio + ", numeroControl=" + numeroControl + '}';
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.getId()+"");
        jsonObject.put("username", this.getUsername());
        jsonObject.put("password", this.getPassword());
        jsonObject.put("nombre", nombre);
        jsonObject.put("apellidos", apellidos);
        jsonObject.put("fechaNacimiento", fechaNacimiento);
        jsonObject.put("ciudad", ciudad);
        jsonObject.put("estado", estado);
        jsonObject.put("curp", curp);
        jsonObject.put("direccion", direccion);
        jsonObject.put("fechaRegistro", fechaRegistro);
        jsonObject.put("idCarrera", idCarrera+"");
        jsonObject.put("semestre", semestre);
        jsonObject.put("idGrupo", idGrupo+"");
        jsonObject.put("promedio", promedio);
        jsonObject.put("numeroControl", numeroControl);
        
        
        return jsonObject;
    }

}
