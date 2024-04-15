package productos;
import java.time.LocalDate;
import utils.Tipo;


public class Limpieza extends Producto {
    
    private String tipo;

    public Limpieza(String tipo, String nombre, double precio, LocalDate fechaImportacion, long numeroDeSerie) {
        super(nombre, precio, fechaImportacion, numeroDeSerie, Tipo.LIMPIEZA);
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
     public void mostrarTipoProducto() {
        System.out.println("Tipo de producto: " + tipo);
    }
     
    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de producto: " + tipo);
    }
    
}
