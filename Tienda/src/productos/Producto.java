package productos;
import java.time.LocalDate;
import utils.Tipo;

public class Producto {
    
    protected String nombre;
    protected double precio;
    protected LocalDate fechaImportacion;
    protected long numeroDeSerie;
    protected int stock;
    protected Tipo tipoProducto;

    public Producto(String nombre, double precio, LocalDate fechaImportacion, long numeroDeSerie, Tipo tipoproducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaImportacion = fechaImportacion;
        this.numeroDeSerie = numeroDeSerie;
        this.tipoProducto = tipoProducto;
        this.stock= 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    public LocalDate getFechaImportacion() {
        return fechaImportacion;
    }

    public void setFechaImportacion(LocalDate fechaImportacion) {
        this.fechaImportacion = fechaImportacion;
    }

    public Tipo getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(Tipo tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    

    public long getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(long numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }
    
    
    
    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
            System.out.println("Se han agregado " + cantidad + " unidades de " + this.nombre + " al stock.");
        } else {
            System.out.println("La cantidad a agregar debe ser mayor a 0.");
        }
    }

    public void eliminarStock(int cantidad) {
        if (cantidad > 0 && this.stock >= cantidad) {
            this.stock -= cantidad;
            System.out.println("Se han eliminado " + cantidad + " unidades ");
        } else {
            System.out.println("No se puede eliminar esa cantidad. El stock actual es de " + this.stock);
        }
    }
    
    public void mostrarDatos() {
        System.out.println("\nNombre: " + this.nombre);
        System.out.println("Precio: $" + this.precio);
        System.out.println("Fecha de importación: "+this.fechaImportacion);
        System.out.println("Número de serie: " + this.numeroDeSerie);
        System.out.println("Stock disponible: " + this.stock);
    }
}
