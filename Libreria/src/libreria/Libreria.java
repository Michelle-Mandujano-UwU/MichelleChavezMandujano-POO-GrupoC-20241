package libreria;
import java.util.ArrayList;
import usuarios.Usuario;

public class Libreria {
    
    //@SuppressWarnings("rawTypes")
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    public Usuario verificarInicioSesión (String nombreUsuario, String contraseña){
        for(Usuario usuario : usuarios){
            if(usuario.getNombreUsuario().equals(nombreUsuario)){
                if(usuario.getContraseña().equals(contraseña)) {
                    return usuario;
                }
                return null;
            }
        }
        return null;
    }
    
    
}
