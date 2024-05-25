package Libros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Libreria.Libreria;
import Libros.utils.constants.Genero;

public abstract class Libro {

    //se filtraran los libros que tengan un precio arriba de parametro
    //se filtraran los libros que tengan un autor que empiece con la letra parametro
    private static int CANTIDAD_LIBROS = 1;
    private String nombre, autor, editorial, fechaPublicacion;
    private double precio;
    private int id, stock;
    private Genero genero;

    public Libro() {
    }

    public Libro(String nombre, String autor, String editorial, String fechaPublicacion, double precio, int stock, Genero gen) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.genero = gen;
        this.editorial = editorial;
        this.id = CANTIDAD_LIBROS;
        CANTIDAD_LIBROS++;
    }

    public String getData() {
        return String.format("Título: %s \nAutor: %s \nGénero: %s \nID: %d \nEditorial: %s \nPrecio: %.2f \nStock: %d", nombre, autor, genero, id, editorial, precio, stock + "\nFecha de publicación: " + fechaPublicacion);
    }

    public String getEditorial() {
        return editorial;
    }

    protected abstract void filtrarLibrosPorPrecio(double precio);

    protected abstract void filtrarLibroPorInicialAutor(String inicial);

    protected abstract void filtrarLibrosPorEditorial(String editorial);

    protected abstract void filtrarLibrosPorTitulo(String palabraClave);

    protected abstract void filtrarLibrosPorStock(int cantidadStock);

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id--;
    }

    public static void setCANTIDAD_LIBROS() {
        CANTIDAD_LIBROS--;
    }

    public String getAutor() {
        return autor;
    }

    public String getNombre() {
        return nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getTitulo() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public static void mostrarLibros() {
        int x = 1;
        for (ArrayList<Libro> i : Libreria.libros.values()) {
            for (Libro libro : i) {
                System.out.println("\n---- Libro " + x + " ----\n");
                if (libro.getGenero() == Genero.TERROR) {
                    System.out.println(libro.getData());
                } else if (libro.getGenero() == Genero.ACCION) {
                    System.out.println(libro.getData());
                } else {
                    System.out.println(libro.getData());
                }
                x++;
            }
        }
    }
}
