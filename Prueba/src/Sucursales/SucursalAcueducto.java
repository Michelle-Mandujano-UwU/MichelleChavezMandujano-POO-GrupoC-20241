package Sucursales;

import Users.Empleados.Empleado;
import Users.Empleados.GerenteSucursal;
import Users.Inversionista;
import Users.Usuario;
import Users.utils.constantes.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SucursalAcueducto extends Sucursal{

    private static int contadorIdEmpleado = 1;
    int idGerente = 1;
    static HashMap<Rol, ArrayList<Empleado>> empleados = new HashMap<>();
    static HashMap<Rol, ArrayList<Inversionista>> inversionistas = new HashMap<>();

    GerenteSucursal gerenteAcueducto = new GerenteSucursal(idGerente,"Diana", "Campos Rosas", LocalDate. of(2005, 10, 24),"Morelia","Michoacán",
            "CARD051024MMNMSNA5","Calle Morelos #456", this, 50000.00,Rol.GERENTE_SUCURSAL,LocalDate.of(2020, 01, 01), "a", "e");

    public SucursalAcueducto(String nombreSucursal, String direccionSucursal) {
        super( "Madero","Av. Madero #123");
        empleados.put(Rol.GERENTE_SUCURSAL, new ArrayList<>());
        empleados.put(Rol.INVERSIONISTA, new ArrayList<>());
        empleados.put(Rol.EMPLEADO, new ArrayList<>());
        empleados.put(Rol.EJECUTIVO_CUENTA, new ArrayList<>());
        empleados.put(Rol.CAPTURISTA, new ArrayList<>());
        empleados.put(Rol.CLIENTE, new ArrayList<>());
        empleados.get(Rol.GERENTE_SUCURSAL).add(gerenteAcueducto);
    }

    public SucursalAcueducto(String nombreSucursal, String direccionSucursal, GerenteSucursal gerenteSucursal) {
        super(nombreSucursal, direccionSucursal);
        this.gerenteAcueducto = gerenteSucursal;
    }

    public GerenteSucursal getGerente() {
        return gerenteAcueducto;
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

    public void modificarDatosInversionista() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el usuario del inversionista a modificar: ");
        String usuarioABuscar = scanner.nextLine();

        Usuario inversionistaAModificar = null;
        for (Usuario usuario : inversionistas.get(Rol.INVERSIONISTA)){
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
}