package tienda;
import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private int idCliente=0;
    private ArrayList<Compras> compras;

        public Cliente(String nombre) {
            this.nombre = nombre;
            this.idCliente =++idCliente;
            this.compras = new ArrayList<>();
        }

    public void agregarCompra(Compras compra) {
        compras.add(compra);
    }

    public void mostrarHistorialCompras() {
        if (compras.isEmpty()) {
            System.out.println("El cliente " + nombre + " no tiene compras registradas.");
        } else {
            System.out.println("Historial de compras de " + nombre + ":");
            for (Compras compra : compras) {
                compra.mostrarDetalleCompra();
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente=idCliente;
    }

    public ArrayList<Compras> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compras> compras) {
        this.compras = compras;
    }
    
    
}


