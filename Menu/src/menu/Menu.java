
package menu;


public class Menu {

    
    public static void main(String[] args) {
        Producto producto1 = new Producto("Paleta de hielo", 18);
        Producto producto2 = new Producto("Galletas", 15f, 30);

        System.out.println(producto1.getNombre());
        System.out.println(producto1.getPrecio());
        System.out.println(producto1.getStock());

        System.out.println("\n");
        
        System.out.println(producto2.getNombre());
        System.out.println(producto2.getPrecio());
        System.out.println(producto2.getStock());

        System.out.println("\n");
        
        System.out.println("Productos existentes: ");
        System.out.println(producto1.getStock());
        System.out.println("En tienda aumento producto a: ");
        producto1.aumentarStock(8);
        System.out.println(producto1.getStock());
        System.out.println("En tienda se tenia: ");
        System.out.println(producto1.getStock());
        producto1.reducirStock(8);
        System.out.println("En tienda se redujo el producto a: ");
        System.out.println(producto1.getStock());
    
    }
    
}
