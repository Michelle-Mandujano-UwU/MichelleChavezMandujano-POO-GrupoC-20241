
package libreria;

/**
 *
 * @author Mich
 */
public class Libro {
     private String titulo;
    private String fechaLanzamiento;
    private String autor;
    boolean libroDisponible;
    private int IDlibro;
    public static int CODIGO = 100000;

    public Libro(String titulo, String fechaLanzamiento, String authorName, boolean libroDipsonible,int libroID) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.autor = authorName;
        this.libroDisponible = true;
        this.IDlibro = CODIGO;
        CODIGO++;

    }

    public Libro(String titulo, String fechaLanzamiento, String authorName) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.autor = authorName;
        this.IDlibro = CODIGO;
        CODIGO++;
    }

    public Libro(int IDlibro) {
        this.IDlibro = IDlibro;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getIDlibro() {
        return IDlibro;
    }
    public void setIDlibro(int IDlibro) {
        this.IDlibro = IDlibro;
    }
    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String nombre) {
        this.autor = nombre;
    }

    public boolean isLibroDisponible() {
        return libroDisponible;
    }
    public void setLibroDisponible(boolean libroDisponible) {
        this.libroDisponible = libroDisponible;
    }
    public String mostrarLibros(){
        return String.format("Libro: %s, Autor: %s ID: %d", titulo, autor, IDlibro);
    }
    public String mostrarLibrosRentados() {
        return String.format("Libro: %s", titulo);
    }
}
