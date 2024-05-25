package Usuarios;

public class Estudiante {


        private String nombre, apellido, telefono, id; 

        public Estudiante(String nombre, String apellido, String telefono, String id) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getId() {
            return id;
        }

        public String getTelefono() {
            return telefono;
        }


        public String toString() { 
            return String.format("ID: %d \nNombre completo: %s %s \nTeléfono: %s",
                    id, nombre, apellido, telefono);
        }


    /*public String mostrarInfo() {
        return String.format("ID: %d, Nombre completo: %s %s, Teléfono: %s, rol: %s",
        id, nombre, apellido, telefono, rol);
    }*/



}

