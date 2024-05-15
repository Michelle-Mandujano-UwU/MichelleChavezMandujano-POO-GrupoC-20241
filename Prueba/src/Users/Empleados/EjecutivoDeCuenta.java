package Users.Empleados;
import Users.utils.constantes.Rol;
import java.time.LocalDate;
import Sucursales.*;
import tarjetas.SolicitudTarjeta;

public class EjecutivoDeCuenta extends Empleado {

    /*private List<SolicitudTarjeta> solicitud;

    public EjecutivoDeCuenta() {
        this.requests = new ArrayList<>();
    }*/

    public EjecutivoDeCuenta(int idEmpleado, String nombre, String apellidos, LocalDate fechaNacimiento,
                             String ciudad, String estado, String direccion, Sucursal sucursal,
                             double salario, Rol rol, LocalDate fechaInicioTrabajo, String contraseña, String nombreUsuario) {
        super(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion,
                sucursal, salario, Rol.EJECUTIVO_CUENTA, fechaInicioTrabajo, contraseña, nombreUsuario);
    }

    public void setRfc(String rfc) {
        this.rfc = generarRFC();
    }

    public void setCURP(String CURP) {
        this.CURP = generarCurp();
    }

}
