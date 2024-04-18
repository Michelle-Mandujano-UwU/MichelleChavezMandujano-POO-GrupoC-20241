package usuarios;
import utils.Rol;
import java.time.LocalDate;

public class Asistente extends Usuario {
    private LocalDate fechaInicio;
    private Double sueldo;
    private String RFC;
    private String INE;

    public Asistente(String nombre, String apellido, String telefono, String nombreUsuario, String contraseña,
                     Double sueldo, String RFC, String INE) {
        super(nombre, apellido, telefono, Rol.ASISTENTE, nombreUsuario, contraseña);
        this.fechaInicio = LocalDate.now();
        this.sueldo = sueldo;
        this.RFC = RFC;
        this.INE = INE;
    }

    @Override
    public String toString() {
        return String.format("%s, Fecha de ingreso: %s, Sueldo: %.3f, RFC: %s, INE: %s, ",
                super.toString(), fechaInicio, sueldo, RFC, INE);
    }
}
