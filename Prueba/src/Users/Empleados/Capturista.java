package Users.Empleados;
import Sucursales.Sucursal;
import Users.Usuario;
import java.time.LocalDate;
import Users.utils.constantes.Rol;
import java.time.format.DateTimeFormatter;

public class Capturista extends Empleado{
    public Capturista(int idEmpleado, String nombre, String apellidos, LocalDate fechaNacimiento,
                      String ciudad, String estado, String direccion, Sucursal sucursal,
                      double salario, Rol rol, LocalDate fechaInicioTrabajo, String contraseña, String nombreUsuario) {
        super(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad,
                estado, direccion, sucursal, salario, Rol.CAPTURISTA,
                fechaInicioTrabajo,contraseña, nombreUsuario);
    }
    public void setRfc(String rfc) {
        this.rfc = generarRFC();
    }
}
