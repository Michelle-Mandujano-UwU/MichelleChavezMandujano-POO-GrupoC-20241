package tienda;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import productos.Producto;
import java.time.LocalDate;
import java.util.Random;
import utils.Tipo;

public class Tienda {
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Compras> compras;
    private String contrasena;

    public Tienda(String contrasena) {
        this.productos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.compras = new ArrayList<>();
        this.contrasena = contrasena;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Compras> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compras> compras) {
        this.compras = compras;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            System.out.print("Ingresa la contraseña para acceder: ");//acceso
            String contraseñaIngresada = scanner.nextLine();
            
            if (!contraseñaIngresada.equals("12345")) {
                System.out.println("Contraseña incorrecta");
                return;
            }
            
            Tienda tienda = new Tienda("contraseña");
            
            ////////////////////////////////////////////////////////////// 
            int opcion;
            do {
                System.out.println("\nTienda Online / Menú Principal");
                System.out.println("0. Salir");
                System.out.println("1. Gestionar productos");
                System.out.println("2. Gestionar clientes");
                System.out.println("3. Realizar compra");
                System.out.println("4. Ver historial de compras");
                System.out.print("Seleccione una opción: ");
                
                opcion = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (opcion) {
                    case 1:
                        gestionarProductos(tienda, scanner);
                        break;
                    case 2:
                        gestionarClientes(tienda, scanner);
                        break;
                    case 3:
                        realizarCompra(tienda, scanner);
                        break;
                    case 4:
                        
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        }
    
    
/////////////////////////////////////////////////////////////////////////////////////
    
    
    private static void gestionarProductos(Tienda tienda, Scanner scanner) {
        int opcionProducto;
        do {
            System.out.println("\nGestión de Productos");
            System.out.println("0. Regresar al menú principal");
            System.out.println("1. Registrar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Ver lista de productos");
            System.out.print("Seleccione una opción: ");

            opcionProducto = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcionProducto) {
                case 1:
                    registrarProducto(tienda, scanner);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del prodcuto que desea eliminar: ");
                    String producoEliminar= scanner.nextLine();
                    
                    break;
                case 3:
//                    mostrarListaProductos(tienda);
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionProducto != 0);
    }

//////////////////////////////////////////////////////////////////////////
    
    
    
    private static void registrarProducto(Tienda tienda, Scanner scanner) {
        System.out.println("\nRegistro de Producto");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Fecha de importación (dd/mm/aaaa): ");
        String fechaImportacionStr = scanner.nextLine();
        LocalDate fechaImportacion = LocalDate.parse(fechaImportacionStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Número de serie: ");
        long numeroSerie = scanner.nextLong();
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Electrodomésticos");
        System.out.println("2. Limpieza");
        System.out.println("3. Belleza");
        System.out.println("4. Alimento");

        int opcionTipoProducto;
         
        do {
            opcionTipoProducto = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcionTipoProducto) {
                case 1:
                    System.out.println("Ingrese el nombre del electrodoméstico:");
                    String nombreElectrodomestico = scanner.nextLine();
                    System.out.println("Ingrese el precio del electrodoméstico:");
                    double precioElectrodomestico = scanner.nextDouble();
                    System.out.println("Ingrese la fecha de importación del electrodoméstico (Formato: YYYY-MM-DD):");
                    String fechaImportacionString = scanner.nextLine();
                    LocalDate fechaImportacionElectrodomestico = LocalDate.parse(fechaImportacionString);
                    System.out.println("Ingrese el número de serie del electrodoméstico:");
                    long numeroSerieElectrodomestico = scanner.nextLong();
                    Producto electrodomestico = new Producto(nombreElectrodomestico, precioElectrodomestico, fechaImportacionElectrodomestico, numeroSerieElectrodomestico, Tipo.ELECTRODOMESTICOS);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre:");
                    String nombreLimpieza = scanner.nextLine();
                    System.out.println("Ingrese el precio:");
                    double precioLimpieza = scanner.nextDouble();
                    System.out.println("Ingrese la fecha de importación del producto (Formato: YYYY-MM-DD):");
                    String fechaImportacionStringLimpieza = scanner.nextLine();
                    LocalDate fechaImportacionLimpieza = LocalDate.parse(fechaImportacionStringLimpieza);
                    System.out.println("Ingrese el número de serie del producto:");
                    long numeroSerieLimpieza = scanner.nextLong();
                    Producto limpieza = new Producto(nombreLimpieza, precioLimpieza, fechaImportacionLimpieza, numeroSerieLimpieza, Tipo.LIMPIEZA);
                    break;
                case 3:
                    System.out.println("Ingrese el nombre:");
                    String nombreBelleza = scanner.nextLine();
                    System.out.println("Ingrese el precio:");
                    double precioBelleza = scanner.nextDouble();
                    System.out.println("Ingrese la fecha de importación del producto (Formato: YYYY-MM-DD):");
                    String fechaImportacionStringBelleza = scanner.nextLine();
                    LocalDate fechaImportacionBelleza = LocalDate.parse(fechaImportacionStringBelleza);
                    System.out.println("Ingrese el número de serie del producto:");
                    long numeroSerieBelleza = scanner.nextLong();
                    Producto belleza = new Producto(nombreBelleza, precioBelleza, fechaImportacionBelleza, numeroSerieBelleza, Tipo.BELLEZA);
                   
                    break;
                case 4:
                    System.out.println("Ingrese el nombre:");
                    String nombreAlimento = scanner.nextLine();
                    System.out.println("Ingrese el precio:");
                    double precioAlimento = scanner.nextDouble();
                    System.out.println("Ingrese la fecha de importación del producto (Formato: YYYY-MM-DD):");
                    String fechaImportacionStringAlimento = scanner.nextLine();
                    LocalDate fechaImportacionAlimento = LocalDate.parse(fechaImportacionStringAlimento);
                    System.out.println("Ingrese el número de serie del producto:");
                    long numeroSerieAlimento= scanner.nextLong();
                    Producto Alimento = new Producto(nombreAlimento, precioAlimento, fechaImportacionAlimento, numeroSerieAlimento, Tipo.ALIMENTO);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcionTipoProducto < 1 || opcionTipoProducto > 4); 
    
    }
 ////////////////////////////////////////////////////////////////////////   
    
     public void eliminarProducto(Producto producto) {
        if (existeProductoEnCompras(producto)) {
            System.out.println("No se puede eliminar el producto. Hay compras asociadas.");
        } else {
            productos.remove(producto);
            System.out.println("Producto eliminado exitosamente");
        }
    }
//////////////////////////////////////////////////////////////////////
     
    private boolean existeProductoEnCompras(Producto producto) {
        for (Compras compra : compras) {
            for (Producto productoCompra : compra.getProductos()) {
                if (productoCompra.equals(producto)) {
                    return true;
                }
            }
        }
        return false;
    }

 //////////////////////////////////////////////////////////////////////////
    
    private static void gestionarClientes(Tienda tienda, Scanner scanner) {
    int opcionCliente;
    do {
        System.out.println("\nGestión de Clientes");
        System.out.println("0. Regresar al menú principal");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Eliminar cliente");
        System.out.println("3. Ver lista de clientes");
        System.out.print("Seleccione una opción: ");

        opcionCliente = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcionCliente) {
            case 1:
                registrarCliente(tienda, scanner);
                break;
            case 2:
                eliminarCliente(tienda, scanner);
                break;
            case 3:
                verListaClientes(tienda);
                break;
            case 0:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("Opción no válida.");
            }
        } while (opcionCliente != 0);
    }
  ////////////////////////////////////////////////////////////////////////////
    
    private static void registrarCliente(Tienda tienda, Scanner scanner) {
    Random random =new Random();
    System.out.println("\nRegistro de Cliente");
    System.out.print("Nombre del cliente: ");
    int id =0;
    id+=1;
    String nombre = scanner.nextLine();

    Cliente cliente = new Cliente(nombre);

    tienda.getClientes().add(cliente);

    System.out.println("Cliente registrado");
    }
    ////////////////////////////////////////////////////////////////////////
    
    
    private static void eliminarCliente(Tienda tienda, Scanner scanner) {
    if (tienda.getClientes().isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
        }
    System.out.println("\nLista de Clientes:");
    for (int i = 0; i < tienda.getClientes().size(); i++) {
        Cliente cliente = tienda.getClientes().get(i);
        System.out.println((i + 1) + ". " + cliente.getNombre());
        }

    System.out.print("Seleccione el número del cliente que desea eliminar: ");
    int numeroCliente = scanner.nextInt();
    scanner.nextLine(); 
    if (numeroCliente < 1 || numeroCliente > tienda.getClientes().size()) {
        System.out.println("Número de cliente no válido.");
        return;
        }
    Cliente cliente = tienda.getClientes().get(numeroCliente - 1);
    if (!cliente.getCompras().isEmpty()) {
        System.out.println("El cliente tiene compras asociadas. No se puede eliminar.");
        return;
        }
    tienda.getClientes().remove(cliente);

    System.out.println("Cliente eliminado!");
    }
    
   ///////////////////////////////////////////////////////////////////////////////
    
    
    private static void verListaClientes(Tienda tienda) {
    if (tienda.getClientes().isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
    }
    System.out.println("\nLista de Clientes:");
    for (Cliente cliente : tienda.getClientes()) {
        System.out.println("- " + cliente.getNombre());
        }
    }

    
    
    ////////////////////////////////////////////////////////////////////////////
    
    private static void realizarCompra(Tienda tienda, Scanner scanner) {
    if (tienda.getClientes().isEmpty()) {
        System.out.println("No hay clientes registrados");
        return;
    }
    if (tienda.getProductos().isEmpty()) {
        System.out.println("No hay productos disponibles ");
        return;
    }
    System.out.print("Ingrese su nombre: ");
    String nombreCliente = scanner.nextLine();

    Cliente cliente = buscarClientePorNombre(tienda, nombreCliente);
    if (cliente == null) {
        System.out.println("No se encontró un cliente con ese nombre. Intente de nuevo.");
        return;
    }
    Compras compra = new Compras(cliente);

    int opcionProducto;
    boolean continuar = true;
    do {
        System.out.print("Seleccione el número del producto que desea agregar a la compra: ");
        opcionProducto = scanner.nextInt();
        scanner.nextLine(); 
        if (opcionProducto < 1 || opcionProducto > tienda.getProductos().size()) {
            System.out.println("Número de producto no válido.");
            continue;
        }
        Producto producto = tienda.getProductos().get(opcionProducto - 1);

        System.out.print("Ingrese la cantidad deseada: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); 

        if (producto.getStock() < cantidad) {
            System.out.println("No hay suficientes unidades en stock.");
            continue;
        }
        compra.agregarProductos(producto, cantidad);
        producto.eliminarStock(cantidad);
        compra.setTotal(compra.calcularTotal());
        compra.mostrarDetalleCompra();
        tienda.getCompras().add(compra);
        cliente.agregarCompra(compra);

        System.out.println("Producto agregado. Desea continuar? (S/N)");
        String ans = scanner.nextLine();
        
        if (ans.equals("N")){
            continuar= false;
        }
        
    } while (continuar); 
    }
///////////////////////////////////////////////////////////////////////////////////////


    private static Cliente buscarClientePorNombre(Tienda tienda, String nombreCliente) {
        for (Cliente cliente : tienda.getClientes()) {
            if (cliente.getNombre().equalsIgnoreCase(nombreCliente)) {
                return cliente;
            }
        }
    return null;
    }
    
}
 