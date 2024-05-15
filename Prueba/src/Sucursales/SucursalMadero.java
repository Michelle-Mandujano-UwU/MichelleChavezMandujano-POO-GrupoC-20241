package Sucursales;

import Users.Cliente;
import Users.Empleados.Empleado;
import Users.Inversionista;
import Users.Usuario;
import Users.utils.constantes.Rol;
import Users.Empleados.GerenteSucursal;
import java.time.LocalDate;
import java.util.*;
import tarjetas.SolicitudTarjeta;

public class SucursalMadero extends Sucursal{

    private static int contadorIdEmpleado = 1;
    int idGerente = 1;
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static HashMap<Rol, ArrayList<Empleado>> empleados = new HashMap<>();
    static ArrayList<Inversionista> inversionistas = new ArrayList<>();
    
    static ArrayList<SolicitudTarjeta> solicitudesTarjeta = new ArrayList<>();

    GerenteSucursal gerenteMadero = new GerenteSucursal(idGerente,"Diana", "Campos Rosas", LocalDate. of(2005, 10, 24),"Morelia","Michoacán",
            "CARD051024MMNMSNA5","Calle Morelos #456", this, 50000.00,Rol.GERENTE_SUCURSAL,LocalDate.of(2020, 01, 01), "a", "d");
        

    public SucursalMadero(String nombreSucursal, String direccionSucursal) {
        super( "Madero","Av. Madero #123");
        empleados.put(Rol.GERENTE_SUCURSAL, new ArrayList<>());
        empleados.put(Rol.INVERSIONISTA, new ArrayList<>());
        empleados.put(Rol.EMPLEADO, new ArrayList<>());
        empleados.put(Rol.EJECUTIVO_CUENTA, new ArrayList<>());
        empleados.put(Rol.CAPTURISTA, new ArrayList<>());
        empleados.put(Rol.CLIENTE, new ArrayList<>());
        empleados.get(Rol.GERENTE_SUCURSAL).add(gerenteMadero);
        
                this.clientes.add(new Cliente("Jorge", "Perez Perez", "04041996", "Acambaro", "Guanajuato", "12345678912", "123", "04041996", "p", "Madero", "ABC", "a"));

    }

    public SucursalMadero(String nombreSucursal, String direccionSucursal, GerenteSucursal gerenteSucursal) {
        super(nombreSucursal, direccionSucursal);
        this.gerenteMadero = gerenteSucursal;
    }
    
    // public Cliente(String nombre,String apellidos, String añoNacimiento, String ciudad, String estado, String curp, String rfc,
    //String fechaRegistroStr, String contraseña, String sucursalRegistro,String direccion,String username) {

    public GerenteSucursal getGerente() {
        return gerenteMadero;
    }

    @Override
    public String toString() {
        String gerenteInfo = "";
        if (getGerente() != null) {
            gerenteInfo = "Gerente: " + getGerente().getNombre() + " " + getGerente().getApellidos();
        } else {
            gerenteInfo = "La sucursal no tiene un gerente asignado.";
        }
        return "SucursalMadero{" +
                ", nombreSucursal='" + nombreSucursal + '\'' +
                ", direccionSucursal='" + direccionSucursal + '\'' +
                ", " + gerenteInfo +
                '}';
    }

    private static ArrayList<String> historialInversiones = new ArrayList<>();

    public static final HashMap<Rol, ArrayList<Empleado>> getEmpleados() {
        return empleados;
    }


    //    public static final HashMap<Rol, Usuario> usuarios = new HashMap<>();
//    public SucursalMadero(String direccionSucursal, Gerente gerente) {
//        super(direccionSucursal, gerente);
//        this.gerente = gerente;
//    }


    public static void setHistorialInversiones(String registro) {
        historialInversiones.add(registro);
    }
    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public static ArrayList<Inversionista> getInversionistas() {
        return inversionistas;
    }

    public static void modificarDatosInversionista() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el usuario del inversionista a modificar: ");
        String usuarioABuscar = scanner.nextLine();

        Usuario inversionistaAModificar = null;
        for (Usuario usuario : inversionistas){
            if (usuario.getUser().equals(usuarioABuscar)) {
                inversionistaAModificar = usuario;
            }
        }

        if (inversionistaAModificar != null) {
            System.out.println("\n** Información actual del inversionista **");
            System.out.println(inversionistaAModificar.toString());

            // Preguntar al usuario qué datos desea modificar
            System.out.println("\n¿Qué datos desea modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Domicilio");
            System.out.println("4. Año de nacimiento");
            System.out.println("5. Usuario");
            System.out.println("6. Contraseña");
            System.out.println("7. Saldo");
            System.out.println("8. Salir");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    inversionistaAModificar.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    String nuevosApellidos = scanner.nextLine();
                    inversionistaAModificar.setApellido(nuevosApellidos);
                    break;
                case 3:
                    System.out.print("Ingrese la nueva ciudad: ");
                    String nuevaCiudad = scanner.nextLine();
                    System.out.print("Ingrese el nuevo estado: ");
                    String nuevoEstado = scanner.nextLine();
                    System.out.print("Ingrese la nueva dirección: ");
                    String nuevaDireccion = scanner.nextLine();
                    String nuevoDomicilio = nuevaCiudad + " " + nuevoEstado + " " + nuevaDireccion;
                    inversionistaAModificar.setDireccion(nuevoDomicilio);
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo año de nacimiento: ");
                    String nuevoNacimiento = scanner.nextLine();
                    inversionistaAModificar.setNacimiento(nuevoNacimiento);
                    break;
                case 5:
                    System.out.print("Ingrese el nuevo nombre de usuario: ");
                    String nuevoUsuario = scanner.nextLine();
                    inversionistaAModificar.setUser(nuevoUsuario);
                    break;
                case 6:
                    System.out.print("Ingrese la nueva contraseña: ");
                    String nuevaContrasena = scanner.nextLine();
                    inversionistaAModificar.setPassword(nuevaContrasena);
                    break;
                case 7:
                    System.out.print("Ingrese el nuevo saldo: ");
                    double nuevoSaldo = scanner.nextDouble();
                    ((Inversionista)inversionistaAModificar).setSaldo(nuevoSaldo);
                    scanner.nextLine();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            System.out.println("\n** Datos del inversionista modificados con éxito! **");
        } else {
            System.out.println("No se encontró ningún inversionista con el usuario: " + usuarioABuscar);
        }

        scanner.close();
    }
    
    
    /*==> SOLICITUDES DE TARJETAS*/

    public static ArrayList<SolicitudTarjeta> getSolicitudesTarjeta() {
        return solicitudesTarjeta;
    }

    public static void setSolicitudesTarjeta(ArrayList<SolicitudTarjeta> solicitudesTarjeta) {
        SucursalMadero.solicitudesTarjeta = solicitudesTarjeta;
    }
    
    public static void addSolicitudTarjeta(SolicitudTarjeta solicitudTarjeta){
        SucursalMadero.solicitudesTarjeta.add(solicitudTarjeta);
    }
    
    public static void addCliente(Cliente cliente){
           SucursalMadero.clientes.add(cliente);
    }
    
}