package menu8;
import java.util.ArrayList;
import java.util.Scanner;

public class Librería {
    
    static Scanner leer= new Scanner (System.in);
 
    private ArrayList<Usuarios> usuarios = new ArrayList<> ();
    private ArrayList<Libros> libros = new ArrayList<>();

    public ArrayList<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Libros> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libros> libros) {
        this.libros = libros;
    }
    
    public void agregarUsuario() {
        leer.nextLine();
        System.out.println("Nombre: ");
        String nombre = leer.nextLine();
        System.out.println("Edad: ");
        int edad= leer.nextInt();
        Usuarios usuario = new Usuarios(nombre, edad);
        usuarios.add(usuario);
        System.out.println("Usuario agregado correctamente");
    }
    
    //Método para listar todos los usuarios registardos:
    public void listarUsuarios(){
        System.out.println("-----Usuarios----");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registardos.");
        }
        else {
            for (Usuarios usuario : usuarios) {
                System.out.println(usuario.getInformacion());
            }
        }
    }
    
    public void agregarLibro() {
        leer.nextLine();
        System.out.println("Título: ");
        String título = leer.nextLine();
        System.out.println("Autor: ");
        String autor = leer.nextLine();
        System.out.println("Editorial: ");
        String editorial = leer.nextLine();
        System.out.println("Año de publicación: ");
        int año = leer.nextInt();
        Libros libro = new Libros (título, autor, editorial, año);
        libros.add(libro);
        System.out.println("Libro agregado correctamente");
    }
    
    
     public void listarLibros (){
         System.out.println("-----Libros-----");
         if (libros.isEmpty()) {
             System.out.println("No hay libros registrados.");
         } else {
             for (Libros libro : libros) {
                 System.out.println(libro.getInformacion());
             }
         }
     }
      public void rentarLibro(Usuarios usuario, Libros libro) {
        if (!usuarios.contains(usuario)) {
        System.out.println("El usuario " + usuario.getNombre() + " no está registrado.");
        return;}
        
        usuario.addLibroRentado(libro);
        System.out.println("Libro rentado exitosamente a " + usuario.getNombre());
        
        libro.setRentado(true);
    }

    public void devolverLibro(Usuarios usuario, Libros libro) {
       usuario.removeLibroRentado(libro);
        System.out.println("Libro devuelto exitosamente por " + usuario.getNombre());
    }
    
    public Usuarios getUsuarioPorNombre(String nombre) {
    for (Usuarios usuario : usuarios) {
        if (usuario.getNombre().equals(nombre)) {
            return usuario;
        }
    }
    return null;
}
     public Libros getLibroPorTítulo(String título) {
    for (Libros libro : libros) {
        if (libro.getTítulo().equals(título)) {
            return libro;
        }
    }
    return null;
}
    public void librosNoRentados() {
    ArrayList<Libros> librosNoRentados = new ArrayList<>();
    for (Libros libro : libros) {
        boolean encontrado = false;
        for (Usuarios usuario : usuarios) {
            if (usuario.getLibrosRentados().contains(libro)) {
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            librosNoRentados.add(libro);
        }
    }

    System.out.println("----Libros no rentados----");
    for (Libros libro : librosNoRentados) {
        System.out.println(libro.getTítulo());
        }
    }
    
    public void librosRentados() {
    ArrayList<Libros> librosRentados = new ArrayList<>();
    for (Libros libro : libros) {
        boolean encontrado = false;
        for (Usuarios usuario : usuarios) {
            if (usuario.getLibrosRentados().contains(libro)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            librosRentados.add(libro);
        }
    }
    System.out.println("------Libros rentados----");
    for (Libros libro : librosRentados) {
        System.out.println(libro.getTítulo());
        }
    }
    
}

