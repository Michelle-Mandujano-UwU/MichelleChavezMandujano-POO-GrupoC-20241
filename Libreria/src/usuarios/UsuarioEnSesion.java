
package usuarios;

/**
 *
 * @author Mich
 */
public class UsuarioEnSesion {
     //realizar el registro cliente, libros y consulta de clientes y libros

    /*
     Iniciar sesion
        validamos sus datos
            1. si toso sale correcto
                Mandar llamr a usuarioSesion
                UsuarioEnSesion.setUsuarioActual(usuario)
            2. Sino devolvemos un error


        Editar datos
            usuario = suarioensesion.obtenerinstancia()
     */

    private static UsuarioEnSesion instancia;
    private Usuario usuarioActual;

    private UsuarioEnSesion() {
    }

    public static UsuarioEnSesion obtenerInstancia() {
        if(instancia == null) {
            instancia = new UsuarioEnSesion();
        }

        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
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
