/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.Usuarios;

import java.time.LocalDate;
import libreria.helpers.Rol;

/**
 *
 * @author Mich
 */
public class Cliente extends Usuario { //para heredar se usa extends
  private LocalDate fechaCliente;

    public Cliente(String nombre,String apellido,String telefono) {
        super(nombre,apellido,telefono,Rol.CLIENTE);//para tomarlos
        this.fechaCliente = LocalDate.now();
        
    }
    public String toString(){
        return String.format("%s,fecha registro:%s",super.toString(),fechaCliente);
    }
    /*
    public String mostrarInfo(){
      return String.format("%s,fecha registro:%s",super.mostrarInfo(),fechaCliente);
        
    }*/
  
}
