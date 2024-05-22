package Users;
import Sucursales.SucursalMadero;
import Users.utils.constantes.Rol;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class AltaCliente {
    private static final String[] LETRAS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final int[] NUMEROS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Random random = new Random();
    SecureRandom secureRandom = new SecureRandom();

    // Generar homoclave
    String homoclave = generarHomoclave(random, secureRandom);

    private static List<Cliente> clientes;

    public AltaCliente() {
        this.clientes = new ArrayList<>();
    }

    public Cliente DarAltaCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.println("*** Alta de clientes ***");
        Random random = new Random();
        int idAleatorio = random.nextInt(1000000000);

        System.out.print("Ingrese su nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese sus apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Ingrese su fecha de nacimiento (DDMMYYYY): ");

        String añoNacimiento = sc.nextLine();


        System.out.print("Ingrese su ciudad: ");
        String ciudad = sc.nextLine();

        System.out.print("Ingrese su estado: ");
        String estado = sc.nextLine();


        System.out.print("Ingrese su CURP: ");
        String curp = sc.nextLine();


        System.out.print("Ingrese su dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Ingrese la sucursal de registro: ");
        String sucursalRegistro = sc.nextLine();

        System.out.print("Crea un nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Crea una contraseña: ");
        String contraseña = sc.nextLine();

        // Obtener fecha de registro actual

        LocalDate fechaRegistro = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaRegistroStr = fechaRegistro.format(formatter);
        String cadena = curp;
        String rfc1 = cadena.substring(0, 10);
        String rfc = rfc1 + homoclave;
         Cliente cliente = new Cliente(nombre,apellidos, añoNacimiento, ciudad, estado, curp, rfc,
                fechaRegistroStr, contraseña, sucursalRegistro, direccion,usuario);
        clientes.add(cliente);

        // Imprimir información del cliente
        System.out.println("\n** Datos del cliente **");

        System.out.println(cliente.toString());

        System.out.println("\n** Cliente dado de alta con éxito! **");
        
        return cliente;
    }


    public static String generarHomoclave(Random random, SecureRandom secureRandom) {


        String segundoCaracter = LETRAS[random.nextInt(LETRAS.length)];

        int primerCaracter = NUMEROS[random.nextInt(NUMEROS.length)];

        int cuartoCaracter = NUMEROS[secureRandom.nextInt(NUMEROS.length)];

        String Homoclaveof = primerCaracter + segundoCaracter + String.format("%01d", cuartoCaracter);

        return Homoclaveof;

    }

    public void mostrarLista() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
        }
        else{
            for (Object elemento : clientes) {
                System.out.println(elemento);
            }
        }
    }

    public void modificarDatosCliente() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el nombre del cliente a modificar: ");
        String nombreABuscar = sc.nextLine();

        Cliente clienteAModificar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreABuscar)) {
                clienteAModificar = cliente;
                break;
            }
        }
        if (clienteAModificar != null) {
            System.out.println("\n** Información actual del cliente **");
            System.out.println(clienteAModificar.toString());

            System.out.println("\n¿Qué datos desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellidos");
            System.out.println("3. Ciudad");
            System.out.println("4. Estado");
            System.out.println("5. Dirección");
            System.out.println("6. Contraseña");
            System.out.println("7. Salir");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    clienteAModificar.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    String nuevosApellidos = sc.nextLine();
                    clienteAModificar.setApellidos(nuevosApellidos);
                    break;
                case 3:
                    System.out.print("Ingrese la nueva ciudad: ");
                    String nuevaCiudad = sc.nextLine();
                    clienteAModificar.setCiudad(nuevaCiudad);
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo estado: ");
                    String nuevoEstado = sc.nextLine();
                    clienteAModificar.setEstado(nuevoEstado);
                    break;
                case 5:
                    System.out.print("Ingrese la nueva dirección: ");
                    String nuevaDireccion = sc.nextLine();
                    clienteAModificar.setDireccion(nuevaDireccion);
                    break;
                case 6:
                    System.out.print("Ingrese la nueva contraseña: ");
                    String nuevaContrasena = sc.nextLine();
                    clienteAModificar.setContraseña(nuevaContrasena);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println("\n** Datos del cliente modificados con éxito! **");
        } else {
            System.out.println("No se encontró ningún cliente con el nombre: " + nombreABuscar);
        }

    }

    public void eliminarCliente(String nombreUsuario) {
        Scanner scanner = new Scanner(System.in);

        Cliente clienteAEliminar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreUsuario)) {
                clienteAEliminar = cliente;
                break;
            }
        }

        if (clienteAEliminar != null) {
            // Display a confirmation message
            System.out.println("\n¿Está seguro de que desea eliminar al cliente con el nombre de usuario: " + nombreUsuario + "?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                clientes.remove(clienteAEliminar);
                System.out.println("\nCliente eliminado con éxito.");
            } else {
                System.out.println("\nEliminación cancelada.");
            }
        } else {
            System.out.println("No se encontró ningún cliente con el nombre de usuario: " + nombreUsuario);
        }
    }
    
}
