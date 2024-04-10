/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.Usuarios;

import java.time.LocalDate;
import libreria.Usuarios.Usuario;
import libreria.helpers.Rol;

/**
 *
 * @author Mich
 */
public class Gerente extends Usuario {
    
    private double sueldo;
    private String RFC;
    private String INE;

    public Gerente(String nombre,String apellido,String telefono,double sueldo,String RFC,String INE) {
        super(nombre,apellido,telefono,Rol.GERENTE);//para tomarlos
        this.RFC=RFC;
        this.sueldo=sueldo;
        this.INE=INE;
}

    @Override
    public String toString(){
        return String.format("%s, Sueldo: %.3f, RFC: %s, INE: %s "+
                super.toString(), sueldo, RFC, INE);
    }
    }

