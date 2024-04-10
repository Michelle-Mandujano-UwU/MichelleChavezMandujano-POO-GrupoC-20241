/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.Usuarios;

import libreria.helpers.Rol;

/**
 *
 * @author Mich
 */
public class Usuario {
    private String nombre,apellido,telefono;
    private static int CANTIDAD_USUARIO=1;
    private int id;
    private Rol rol;
    

    public Usuario(String nombre, String apellido, String telefono, Rol rol) {
        this.id=CANTIDAD_USUARIO;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.rol = rol;
        CANTIDAD_USUARIO++;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public static int getCANTIDAD_USUARIO() {
        return CANTIDAD_USUARIO;
    }

    public static void setCANTIDAD_USUARIO(int CANTIDAD_USUARIO) {
        Usuario.CANTIDAD_USUARIO = CANTIDAD_USUARIO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    public String toString(){
        return String.format("id= %d, nombre completo= %s, telefono= %s, rol= %s",
                id,nombre,apellido,telefono,rol);
        
    }
    
    
}
