package Libros.models;
import Libros.LibroAccion;
import Libros.LibroComedia;
import Libros.LibroTerror;
import java.util.ArrayList;

public class LibroModel {

    private ArrayList<LibroTerror> TERROR;
    private ArrayList<LibroComedia> COMEDIA;
    private ArrayList<LibroAccion> ACCION;

    public ArrayList<LibroComedia> getLibrosComedia() {
        return COMEDIA;
    }

    public ArrayList<LibroAccion> getLibrosAccion() {
        return ACCION;
    }

    public ArrayList<LibroTerror> getLibrosTerror() {
        return TERROR;
    }
}
