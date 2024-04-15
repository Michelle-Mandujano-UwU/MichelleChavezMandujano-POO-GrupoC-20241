package productos;
import java.time.LocalDate;
import utils.Tipo;

public class Alimento extends Producto {
    
    private LocalDate fechaCaducidad;
    private String categoria;

    public Alimento(LocalDate fechaCaducidad, String categoria, String nombre, double precio, LocalDate fechaImportacion, long numeroDeSerie) {
        super(nombre, precio, fechaImportacion, numeroDeSerie, Tipo.ALIMENTO);
        this.fechaCaducidad = fechaCaducidad;
        this.categoria = categoria;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void mostarDatos(){
        super.mostrarDatos();
        System.out.println("Fecha de caducidad del producto; "+fechaCaducidad);
        System.out.println("Categoría: "+categoria);
    }
    
    public void mostrarFechaCaducidad() {
        System.out.println("Fecha de Caducidad: " + fechaCaducidad);
    }

    public void mostrarCategoria() {
        System.out.println("Categoría: " + categoria);
    }
}
