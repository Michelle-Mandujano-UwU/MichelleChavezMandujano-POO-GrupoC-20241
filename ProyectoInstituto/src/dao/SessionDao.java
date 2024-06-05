
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import objetos.Usuario;

/**
 *
 * @author 
 */
public class SessionDao {
    List<Usuario> listUsersInSys = new ArrayList<>();
    
    Usuario usr = null;
    
    public SessionDao(){
      
    }
    public Usuario getUserOnSession(){
        return this.usr;
    }
    
    public void addListUsrInSystem(List<Usuario> listUsersInSys){
        this.listUsersInSys = listUsersInSys;
    }
    
    public boolean validateLogin(String username, String password){
        
        usr = listUsersInSys.stream()
                .filter(x -> x.getPassword().equalsIgnoreCase(password) 
                            && x.getUsername().equalsIgnoreCase(username))
                                    .findFirst().orElse(null);
       
 
        return usr != null ? true : false;
    } 
}
