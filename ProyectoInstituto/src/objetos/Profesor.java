package objetos;

import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;
import utils.Constantes;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import utils.Utils;
/**
 *
 * @author 
 */
public class Profesor extends Trabajador{
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String ciudad;
    private String estado;
    private String curp;
    private String direccion;
    private String fechaRegistro;
    private String rfc;
    private List<Integer> gruposAsignados;
    private List<Integer> materiasImparte;
    private String sueldo;
    //private String numeroControl;

    public Profesor(int id,String nombre, String apellidos, 
            String fechaNacimiento, String ciudad, String estado, 
            String curp, String direccion, String fechaRegistro, 
            String rfc, String sueldo,String numeroControl, 
            String username, String password, List<Integer> materiasImparte,
            List<Integer> gruposAsignados) {
        
        super(id,username,password, Constantes.Rol.TRABAJADOR_PROFESOR);
        
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.curp = curp;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.rfc = rfc;
        this.materiasImparte = materiasImparte;
        this.sueldo = sueldo;
        this.gruposAsignados = gruposAsignados;
        //this.numeroControl = numeroControl;
    }
    
    public Profesor(JSONObject jsonObject, 
            List<Integer> materiasImparte,
            List<Integer> gruposAsignados){
           super(Integer.parseInt(jsonObject.get("id").toString()),
                   jsonObject.get("username").toString(),
                   jsonObject.get("password").toString(), 
                   Constantes.Rol.TRABAJADOR_PROFESOR);
           
        this.nombre = jsonObject.get("nombre").toString();
        this.apellidos = jsonObject.get("apellidos").toString();
        this.fechaNacimiento = jsonObject.get("fechaNacimiento").toString();
        this.ciudad = jsonObject.get("ciudad").toString();
        this.estado = jsonObject.get("estado").toString();
        this.curp = jsonObject.get("curp").toString();
        this.direccion = jsonObject.get("direccion").toString();
        this.fechaRegistro = jsonObject.get("fechaRegistro").toString();
        this.rfc = jsonObject.get("rfc").toString();
        this.sueldo = jsonObject.get("sueldo").toString();
        //this.numeroControl = jsonObject.get("numeroControl").toString();
        this.gruposAsignados = gruposAsignados;
        this.materiasImparte = materiasImparte;
    
    }

    public Profesor(HashMap<String,String> dataProfesor){
        super(Integer.parseInt(dataProfesor.get("id")),
                   dataProfesor.get("username"),
                   dataProfesor.get("password"), 
                   Constantes.Rol.TRABAJADOR_PROFESOR);
        
        this.nombre = dataProfesor.get("nombre");
        this.apellidos = dataProfesor.get("apellidos");
        this.fechaNacimiento = dataProfesor.get("fechaNacimiento");
        this.ciudad = dataProfesor.get("ciudad");
        this.estado = dataProfesor.get("estado");
        this.direccion = dataProfesor.get("direccion");
        this.fechaRegistro = dataProfesor.get("fechaRegistro");
        this.materiasImparte = Arrays.stream(dataProfesor.get("materias").split(","))
                                    .map(Integer::valueOf) 
                                    .collect(Collectors.toList());
        this.gruposAsignados = Arrays.stream(dataProfesor.get("gruposAsignados").split(","))
                                    .map(Integer::valueOf) 
                                    .collect(Collectors.toList());
        this.sueldo = dataProfesor.get("sueldo");
        /*this.numeroControl = 
                Utils.generateNumeroControl(nombre, anioNacimiento, "Carrera", 
                        dataProfesor.get("id"));*/
        this.rfc = Utils.generarRFC(this.apellidos,this.nombre, this.fechaNacimiento);
        this.curp = Utils.generarCurp(this.apellidos, this.nombre, this.fechaNacimiento, this.estado);
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

    public List<Integer> getMateriasImparte() {
        return materiasImparte;
    }

    public void setMateriasImparte(List<Integer> materiasImparte) {
        this.materiasImparte = materiasImparte;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }


    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    /*public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }*/

    public List<Integer> getGruposAsignados() {
        return gruposAsignados;
    }

    public void setGruposAsignados(List<Integer> gruposAsignados) {
        this.gruposAsignados = gruposAsignados;
    }
    
    
    
    public String getFullName(){
        return this.nombre + " "+this.apellidos;
    }

    @Override
    public String toString() {
        return "Profesor{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + ", estado=" + estado + ", curp=" + curp + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", rfc=" + rfc + ", materiasImparte=" + materiasImparte + ", sueldo=" + sueldo + '}';
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.getId() + "");
        jsonObject.put("nombre", nombre);
        jsonObject.put("apellidos", apellidos);
        jsonObject.put("fechaNacimiento", fechaNacimiento);
        jsonObject.put("ciudad", ciudad);
        jsonObject.put("estado", estado);
        jsonObject.put("curp", curp);
        jsonObject.put("direccion", direccion);
        jsonObject.put("fechaRegistro", fechaRegistro);
        jsonObject.put("rfc", rfc);
        jsonObject.put("sueldo", sueldo);
        jsonObject.put("username", this.getUsername());
        jsonObject.put("password", this.getPassword());

        JSONArray arrayMaterias = new JSONArray();

        for (Integer idMateria : materiasImparte) {
            JSONObject jsonIdMateria = new JSONObject();
            jsonIdMateria.put("id", idMateria + "");
            arrayMaterias.add(jsonIdMateria);
        }
        
        jsonObject.put("materiasImparte", arrayMaterias);
        
        JSONArray arrayGrupos = new JSONArray();

        for (Integer idGrupo : gruposAsignados) {
            JSONObject jsonIdGrupo= new JSONObject();
            jsonIdGrupo.put("id", idGrupo + "");
            arrayGrupos.add(jsonIdGrupo);
        }
        
        jsonObject.put("gruposAsignados", arrayGrupos);

        return jsonObject;
    }
   
    
    
}
