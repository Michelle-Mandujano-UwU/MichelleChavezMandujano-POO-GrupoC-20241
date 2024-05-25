package Libros;
import java.time.LocalDate;
import Libreria.Libreria;
import java.util.ArrayList;
import java.util.Scanner;
import Libros.utils.LibrosUtils;
import Libros.utils.constants.Genero;
import Libros.utils.constants.TerrorSubgenero;

public class LibroTerror extends Libro {

    private TerrorSubgenero subgenero;

    public LibroTerror(String nombre, String autor, String editorial, String fechaPublicacion, double precio, int stock, TerrorSubgenero clasi) {
        super(nombre, autor, editorial, fechaPublicacion, precio, stock, Genero.TERROR);
        this.subgenero = clasi;
    }

    public LibroTerror() {
    }

    public void filtrarLibrosPorStock(int cantidadStock) {
        Libreria.libros.get(Genero.TERROR).stream()
                .filter(libro -> libro.getStock() >= cantidadStock)
                .forEach(libro -> System.out.println(libro.toString()));
    }

    public void filtrarLibrosPorEditorial(String editorial) {
        Libreria.libros.get(Genero.TERROR).stream()
                .filter(libro -> libro.getEditorial().equalsIgnoreCase(editorial))
                .forEach(libro -> System.out.println(libro.toString()));
    }

    public void filtrarLibrosPorTitulo(String palabraClave) {
        Libreria.libros.get(Genero.TERROR).stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains(palabraClave.toLowerCase()))
                .forEach(libro -> System.out.println(libro.toString()));
    }

    @Override
    public String getData() {
        return String.format("%s", super.getData() + "Subgenero: " + subgenero);
    }

    public static void registrarLibro() {
        System.out.println("\n---- Registrar datos común ----");
        ArrayList<String> datosComun = LibrosUtils.registrarLibrosDatosComun();

        String nombre = datosComun.get(0);
        String autor = datosComun.get(1);
        String editorial = datosComun.get(2);
        LocalDate fecha = LocalDate.parse(datosComun.get(3));
        double precio = Double.parseDouble(datosComun.get(4));
        int stock = Integer.parseInt(datosComun.get(5));

        TerrorSubgenero subgeneroInput = null;
        int opcion = 0;

        System.out.println("Ingrese el subgénero del libro: ");
        System.out.println("1. Psicológico");
        System.out.println("2. Monjas");
        if (opcion == 1) {
            subgeneroInput = TerrorSubgenero.PSICOLOGICO;
        } else {
            subgeneroInput = TerrorSubgenero.MONJAS;
        }

        LibroTerror libroTerror = new LibroTerror(nombre, autor, editorial, String.valueOf(fecha), precio, stock, subgeneroInput);

        Libreria.libros.get(Genero.TERROR).add(libroTerror);
    }

    public static void eliminarLibroTerror() {
        Libro libroEliminado = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Eliminar libro de terror ----\n");
        System.out.println("Ingrese el nombre del libro que desea eliminar: ");
        String nombre = scanner.nextLine();
        for (Libro libro : Libreria.libros.get(Genero.TERROR)) {
            if (nombre.equalsIgnoreCase(libro.getNombre())) {
                libroEliminado = libro;
                for (ArrayList<Libro> i : Libreria.libros.values()) {
                    for (Libro libro1 : i) {
                        if (libroEliminado.getId() < libro1.getId()) {
                            libro1.setId();
                        }
                    }
                    return;
                }
            }
        }
        if (libroEliminado == null) {
            System.out.println("\nEste título no pertenece a ningún libro");
        } else {
            Libreria.libros.remove(libroEliminado);
            Libro.setCANTIDAD_LIBROS();
        }
    }

    @Override
    public void filtrarLibrosPorPrecio(double precio) {

        Libreria.libros.get(Genero.TERROR).stream().filter(libro -> libro.getPrecio() > precio).forEach(libro -> System.out.println(libro.toString())); //lo hacemos un objeto de tipo stream
    }

    @Override
    public void filtrarLibroPorInicialAutor(String inicial) {
        Libreria.libros.get(Genero.TERROR).stream().anyMatch(libro -> libro.getAutor().toLowerCase().contains(inicial));
    }
}
