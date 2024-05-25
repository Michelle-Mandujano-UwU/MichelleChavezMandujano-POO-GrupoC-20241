/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.List;

/**
 *
 * @author Mich
 */
public class Persona{
   private String nombre;
    private int edad;
    private String direccion;
    private String telefono;
    private List<String> hobbies;

    public Persona(String nombre,int edad, String direccion, String telefono, List<String> hobbies ){
       this.nombre= nombre;
       this.edad= edad;
       this.direccion=direccion;
       this.telefono=telefono;
       this.hobbies=hobbies;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    
}