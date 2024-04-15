package productos;
import java.time.LocalDate;
import utils.Tipo;

public class Electrodomesticos extends Producto{
    
    private int añosDeGarantia;

    public Electrodomesticos(int añosDeGarantia, String nombre, double precio, LocalDate fechaImportacion, long numeroDeSerie) {
        super(nombre, precio, fechaImportacion, numeroDeSerie, Tipo.ELECTRODOMESTICOS);
        this.añosDeGarantia = añosDeGarantia;
    }
    

    public int getAñosDeGarantia() {
        return añosDeGarantia;
    }

    public void setAñosDeGarantia(int añosDeGarantia) {
        this.añosDeGarantia = añosDeGarantia;
    }
    
    public void mostrarAñosDeGarantia (){
        System.out.println("El producto "+nombre+"tiene "+añosDeGarantia+"años de garantía.");
    }
    
    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Años de garantía del producto: " + añosDeGarantia);
    }
}
