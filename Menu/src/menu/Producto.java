
package menu;


public class Producto {
   private String nombre;
    private float precio;
    private int stock = 1;

    public Producto(String nombre, float precio, int stock){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public Producto(String nombre, float precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre != null) {
            this.nombre = nombre;
        }else{
            System.out.println("El nombre no puede estar vacio");
         }
        
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        if (precio >= 0) {
            this.precio = precio;
        }else{
           System.out.println("El precio no puede ser negativo");
        }
    }
    
    
    
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
        else{
            System.out.println("El stock no puede ser negativo");
         }
    }

    
    

    public void aumentarStock(int cantidad){
        if (cantidad > 0) {
            this.stock += cantidad;
        }else{
            System.out.println("La cantidad debe ser mayor a 0");
        }
    }

    
    public void reducirStock(int cantidad){
        if (cantidad > 0) {
            if (cantidad <= this.stock) {
                this.stock -= cantidad;
            }else{
                System.out.println("La cantidad es mas grande que el stock existente");
            }
        }else{
            System.out.println("La cantidad debe ser mayor a 0");
        }
    } 
}
