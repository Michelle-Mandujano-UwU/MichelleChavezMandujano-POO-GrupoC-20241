package tienda;

import productos.Producto;
import java.time.LocalDate;
import java.util.ArrayList;

public class Compras {
    private int cantidadProductos;
    private Cliente cliente;
    private LocalDate fechaCompra;
    private ArrayList<Producto>productos;
    private double total;

    public Compras(Cliente cliente, ArrayList<Producto> productos, double total) {
        this.cliente = cliente;
        this.fechaCompra = LocalDate.now();
        this.productos = new ArrayList<>();
        this.total = 0;
    }
    public Compras (Cliente cliente){
        this.cliente=cliente;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void agregarProductos (Producto producto, int cantidad) {
        productos.add(producto);
        total+=producto.getPrecio() * cantidad;
    }
    
    
    public void mostrarDetalleCompra() {
        System.out.println("Compra realizada por: " + cliente.getNombre());
        System.out.println("Fecha de compra: " + fechaCompra);
        System.out.println("Productos:");
        System.out.println("Total: $" + total);
    }
    
    public double calcularTotal() {
    for (Producto producto : productos) {
        int cantidad = getCantidadProducto(producto);
        total += producto.getPrecio() * cantidad;
    }
    return total;
    }
    
    public int getCantidadProducto(Producto producto) {
        for (Producto productoEnCompra : productos) {
            if (productoEnCompra.equals(producto)) {
                return productoEnCompra.getStock();
            }
        }
        return 0;
    }
    public void eliminarProducto(Producto producto) {
    if (productos.contains(producto)) {
        productos.remove(producto); 
        producto.eliminarStock(getCantidadProducto(producto)); 
        System.out.println("Producto eliminado de la compra.");
    } else {
        System.out.println("El producto no se encuentra en la compra.");
    }
}
    public void mostrarHistorialCompras(ArrayList<Compras> historialCompras) {
        if (historialCompras.isEmpty()) {
            System.out.println("El cliente no tiene compras registradas.");
            return;
        }
        System.out.println("\nHistorial de compras:");
        for (Compras compra : historialCompras) {
            compra.mostrarDetalleCompra();
        }
    }
    
}   
        
