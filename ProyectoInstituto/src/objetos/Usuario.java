package objetos;

import utils.Constantes;

/**
 *
 * @author
 */
public class Usuario {
    private int id;
    private String username;
    private String password;
    private Constantes.Rol rol;

    public Usuario(int id, String username, String password, Constantes.Rol rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }
    
    public Usuario(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Constantes.Rol getRol() {
        return rol;
    }

    public void setRol(Constantes.Rol rol) {
        this.rol = rol;
    }
    
}
