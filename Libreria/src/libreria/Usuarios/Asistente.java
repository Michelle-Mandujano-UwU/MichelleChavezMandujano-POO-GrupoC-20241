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
public class Asistente extends Usuario{
    private LocalDate fechaInicio;
    private double sueldo;
    private String RFC;
    public Asistente(String nombre,String apellido,String telefono,double sueldo,String RFC) {
        super(nombre,apellido,telefono,Rol.ASISTENTE);//para tomarlos
        this.fechaInicio = LocalDate.now();
        this.sueldo=sueldo;
        this.RFC=RFC;
        
    }
    public String toString(){
        return String.format("%s,Sueldo: %f,RFC: %s, fecha inicio: %s",super.toString(),sueldo,RFC,fechaInicio);
    }
    
}
