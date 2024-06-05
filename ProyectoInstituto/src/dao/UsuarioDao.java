
package dao;

import java.util.ArrayList;
import java.util.List;
import objetos.Usuario;


/**
 *
 * @author 
 */
public class UsuarioDao {
    List<Usuario> listUsersInSys = new ArrayList<>();

    public UsuarioDao() {
        
    }
    
    public void addUserInSys(Usuario usr){
        this.listUsersInSys.add(usr);
    }
    
    public void addListUsersInSys(List<Usuario> listUsersInSys){
        this.listUsersInSys.addAll(listUsersInSys);
    }
    
    public List<Usuario> getListUsersInSys(){
        return this.listUsersInSys;
    }
            
   
    public Usuario getUserById(int id){
           return listUsersInSys.stream()
                .filter(x -> x.getId() == id).findFirst().orElse(null);
    }
   
}
