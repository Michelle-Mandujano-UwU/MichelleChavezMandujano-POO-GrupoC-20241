package menu8;
import java.util.Random;
import java.util.ArrayList;
public class Usuarios {
    
    Random random = new Random();
    
    private String nombre;
    private int id;
    private int edad;
    private ArrayList<Libros> librosRentados;
    
    public Usuarios(String nombre, int edad) {
        this.librosRentados = new ArrayList<>();
        this.nombre = nombre;
        this.id = random.nextInt(1000)+1;
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Libros> getLibrosRentados() {
        return librosRentados;
    }

    public void setLibrosRentados(ArrayList<Libros> librosRentados) {
        this.librosRentados = librosRentados;
    }
    
    public void addLibroRentado(Libros libro) {
        librosRentados.add(libro);
    }

    public void removeLibroRentado(Libros libro) {
        librosRentados.remove(libro);
    }
    
    /*public String getInformacion (){
        return String.format("Nombre: %s, ID: %d, Edad: %d", nombre, id, edad);
    }*/

    public String getInformacion() {
        return String.format("Nombre: %s, ID: %d, Edad: %d", nombre, id, edad);
    }
}
