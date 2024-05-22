package utils;
import Users.Empleados.Empleado;
import Users.Usuario;

public class UsuarioEnSesion {

    private static UsuarioEnSesion instancia;
    private Empleado usuarioActual;

    private UsuarioEnSesion() {
    }

    public static UsuarioEnSesion obtenerInstancia() {
        if(instancia == null) {
            instancia = new UsuarioEnSesion();
        }

        return instancia;
    }

    public Empleado getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Empleado usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean hayUsuarioEnSesion() {
        return usuarioActual != null;
    }

    public void cerrarSesion() {
        instancia = null;
        usuarioActual = null;
    }
}