package Libros;
import java.util.ArrayList;
import java.util.Scanner;
import Libreria.Libreria;
import Libros.utils.LibrosUtils;
import Libros.utils.constants.ClasificacionLibro;
import Libros.utils.constants.Genero;

public class LibroComedia extends Libro {

    public LibroComedia(String nombre, String autor, String editorial, String fechaPublicacion, double precio, int stock) {
        super(nombre, autor, editorial, fechaPublicacion, precio, stock, Genero.COMEDIA);
    }

    public LibroComedia() {}

    public static void registrarLibroComedia() {
        boolean band = true;
        String clasificacion;
        Scanner scanner = new Scanner(System.in);
        ClasificacionLibro clasi = null;
        ArrayList<String> datos = LibrosUtils.registrarLibrosDatosComun();
        String titulo = datos.get(0);
        String autor = datos.get(1);
        String editorial = datos.get(2);
        String fechaPublicacion = datos.get(3);
        String precio = datos.get(4);
        String stock = datos.get(5);

        LibroComedia libroComedia = new LibroComedia(titulo, autor, editorial, fechaPublicacion, Double.parseDouble(precio), Integer.parseInt(stock));
        Libreria.libros.get(Genero.COMEDIA).add(libroComedia);
    }

    public void filtrarLibrosPorEditorial(String editorial) {
        Libreria.libros.get(Genero.COMEDIA).stream()
                                .filter(libro -> libro.getEditorial().equalsIgnoreCase(editorial))  
                                   .forEach(libro -> System.out.println(libro.toString()));
    }

    public void filtrarLibrosPorStock(int cantidadStock) {
        Libreria.libros.get(Genero.COMEDIA).stream()
                       .filter(libro -> libro.getStock() >= cantidadStock)
                       .forEach(libro -> System.out.println(libro.toString()));
    }

    public void filtrarLibrosPorTitulo(String palabraClave) {
        Libreria.libros.get(Genero.COMEDIA).stream()
                       .filter(libro -> libro.getTitulo().toLowerCase().contains(palabraClave.toLowerCase()))
                       .forEach(libro -> System.out.println(libro.toString()));
    }

    @Override
    public void filtrarLibrosPorPrecio(double precio) {
        // escomo una cadena de instrucciones para llevar a cabo varios metodos al mismo tiempo
        Libreria.libros.get(Genero.COMEDIA).stream().filter(libro -> libro.getPrecio() > precio).forEach(libro -> System.out.println(libro.toString())); //lo hacemos un objeto de tipo stream
    }

    @Override
    public void filtrarLibroPorInicialAutor(String inicial) {
        Libreria.libros.get(Genero.COMEDIA).stream().anyMatch(libro -> libro.getAutor().toLowerCase().contains(inicial));
    }

    public static void eliminarLibroComedia() {
        Libro libroEliminado = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Eliminar libro de comedia ----\n");
        System.out.println("Ingrese el nombre del libro que desea eliminar: ");
        String nombre = scanner.nextLine();
        for(Libro libro : Libreria.libros.get(Genero.COMEDIA)) {
            if(nombre.equalsIgnoreCase(libro.getNombre())) {
                libroEliminado = libro;
                for(ArrayList<Libro> i : Libreria.libros.values()) {
                    for(Libro libro1 : i) {
                        if(libroEliminado.getId() < libro1.getId()) {
                            libro1.setId();
                        }
                    }
                    return;
                }
            }
        }
        if(libroEliminado == null) {
            System.out.println("\nEste título no pertenece a ningún libro");
        }
        else {
            Libreria.libros.remove(libroEliminado);
            Libro.setCANTIDAD_LIBROS();
        }
    }
}
