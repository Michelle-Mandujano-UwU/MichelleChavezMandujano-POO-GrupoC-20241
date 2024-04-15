package productos;
import java.time.LocalDate;
import utils.Tipo;


public class Belleza extends Producto {
    private String areaDeAplicacion;
    private String tono;
    private String marca;

    public Belleza(String areaDeAplicacion, String tono, String marca, String nombre, double precio, LocalDate fechaImportacion, long numeroDeSerie) {
        super(nombre, precio, fechaImportacion, numeroDeSerie, Tipo.BELLEZA);
        this.areaDeAplicacion = areaDeAplicacion;
        this.tono = tono;
        this.marca = marca;
    }


    public String getAreaDeAplicacion() {
        return areaDeAplicacion;
    }

    public void setAreaDeAplicacion(String areaDeAplicacion) {
        this.areaDeAplicacion = areaDeAplicacion;
    }

    public String getTono() {
        return tono;
    }

    public void setTono(String tono) {
        this.tono = tono;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Área de aplicación: " + areaDeAplicacion);
        System.out.println("Tono: "+tono);
        System.out.println("Marca: " + marca);
    }
    
    public void mostarAreaDeAplicacion () {
        System.out.println("Área de aplicación: " + areaDeAplicacion);
    }
    
}
