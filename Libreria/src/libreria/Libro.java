
package libreria;

import java.util.Scanner;

/**
 *
 * @author Mich
 */
public class Libro {
     private static int CANTIDAD_LIBROS = 1;
    private String titulo, autor, genero, idioma;
    private double precio;
    private int id;

    public Libro(String titulo, String autor, String genero, String idioma, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.idioma = idioma;
        this.precio = precio;
        this.id = CANTIDAD_LIBROS;
        CANTIDAD_LIBROS++;
    }

    public String getData() {
        return String.format("Título: %s \nAutor: %s \nGénero: %s \nID: %d \nIdioma: %s \nPrecio: %.2f", titulo, autor, genero, id, idioma, precio);
    }


    public static void registrarLibro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Registrar libro ----");
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nTítulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Idioma: ");
        String idioma = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();

        Libro libro = new Libro(titulo, autor, genero, idioma, precio);
        Libreria.libros.add(libro);
        System.out.println("\nRegistro exitoso");
    }


    public static void mostrarLibros() {
        if(Libreria.libros.isEmpty()) {
            System.out.println("\nNo hay libros registrados aún");
        }
        else {
            System.out.println("\nLibros en la biblioteca");
            int x = 1;
            for(Libro i : Libreria.libros) {
                System.out.println("\n---- Libro " + x + " ----\n");
                System.out.println(i.getData());
                x++;
            }
        }
    }

}
