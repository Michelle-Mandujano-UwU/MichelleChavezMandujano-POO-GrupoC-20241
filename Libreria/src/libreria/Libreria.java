/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import libreria.Usuarios.Cliente;
import libreria.helpers.Rol;

/**
 *
 * @author Mich
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente cliente = new Cliente("juan","Rivera","4171161560");
        System.out.println(cliente.toString());
    }
    
}
