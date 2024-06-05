package objetos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Constantes;
import utils.Utils;

/**
 *
 * @author
 */
public class Coordinador extends Trabajador {

    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String ciudad;
    private String estado;
    private String curp;
    private String direccion;
    private String fechaRegistro;
    private String rfc;
    private List<Integer> materiasImparte;
    private String sueldo;
    private int idCarrera;

    public Coordinador(int id,int idCarrera, String nombre, String apellidos, String fechaNacimiento,
            String ciudad, String estado, String direccion,
            String fechaRegistro, String rfc, String materiasImparte,
            String sueldo, String username, String password) {
        super(id, username, password, Constantes.Rol.TRABAJADOR_COORDINADOR);

        this.idCarrera = idCarrera;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.curp = Utils.generarCurp(this.apellidos, this.nombre, this.fechaNacimiento, this.estado);
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.rfc = rfc;
        this.materiasImparte = new ArrayList<>();
        this.sueldo = sueldo;
    }

    public Coordinador(JSONObject jsonObject, List<Integer> listMaterias) {
        super(
                Integer.parseInt(jsonObject.get("id").toString()),
                jsonObject.get("username").toString(),
                jsonObject.get("password").toString(),
                Constantes.Rol.TRABAJADOR_COORDINADOR);

        this.idCarrera = Integer.parseInt(jsonObject.get("idCarrera").toString());
        this.nombre = jsonObject.get("nombre").toString();
        this.apellidos = jsonObject.get("apellidos").toString();
        this.fechaNacimiento = jsonObject.get("fechaNacimiento").toString();
        this.ciudad = jsonObject.get("ciudad").toString();
        this.estado = jsonObject.get("estado").toString();
        this.curp = jsonObject.get("curp").toString();
        this.direccion = jsonObject.get("direccion").toString();
        this.fechaRegistro = jsonObject.get("fechaRegistro").toString();
        this.rfc = jsonObject.get("rfc").toString();
        this.materiasImparte = listMaterias;
        this.sueldo = jsonObject.get("sueldo").toString();

    }

    public Coordinador(HashMap<String, String> dataCoordinador) {
        super(
                Integer.parseInt(dataCoordinador.get("id")),
                dataCoordinador.get("username"),
                dataCoordinador.get("password"),
                Constantes.Rol.TRABAJADOR_COORDINADOR);

        this.idCarrera = Integer.parseInt(dataCoordinador.get("idCarrera"));
        this.nombre = dataCoordinador.get("nombre");
        this.apellidos = dataCoordinador.get("apellidos");
        this.fechaNacimiento = dataCoordinador.get("fechaNacimiento");
        this.ciudad = dataCoordinador.get("ciudad");
        this.estado = dataCoordinador.get("estado");
        this.direccion = dataCoordinador.get("direccion");
        this.fechaRegistro = dataCoordinador.get("fechaRegistro");
       
        this.rfc = Utils.generarRFC(this.apellidos,this.nombre, this.fechaNacimiento);
        this.curp = Utils.generarCurp(this.apellidos, this.nombre, this.fechaNacimiento, this.estado);
       
        this.materiasImparte = Arrays.stream(dataCoordinador.get("materias").split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        this.sueldo = dataCoordinador.get("sueldo").toString();

    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
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

    public String getFullName() {
        return this.nombre + " " + this.apellidos;
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
    
    

    @Override
    public String toString() {
        return "Coordinador{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + ", estado=" + estado + ", curp=" + curp + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", rfc=" + rfc + ", materiasImparte=" + materiasImparte + ", sueldo=" + sueldo + '}';
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("idCarrera", idCarrera+"");
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

        return jsonObject;
    }

}
