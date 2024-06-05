package objetos;

import utils.Constantes;

/**
 *
 * @author 
 */
public class Trabajador extends Usuario{
    public Trabajador(int id,String username, String password, Constantes.Rol rol) {
        super(id,username, password, rol);
    }
    
    public Trabajador(){
        
    }
}
