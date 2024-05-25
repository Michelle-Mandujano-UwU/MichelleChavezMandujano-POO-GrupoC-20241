package Usuarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Libreria.Libreria;
import Usuarios.utils.EmpleadoUtils;
import Usuarios.utils.Rol;

public class Gerente extends Usuario implements EmpleadoUtils {
    private double sueldo;
    private String rfc, ine, fechaInicio;
    
    public Gerente(String nombre, String apellido, String telefono, double sueldo, String rfc, String ine, String nombreUsuario, String contrasena, String fechaNacimiento) {
        super(nombre, apellido, telefono, Rol.GERENTE, nombreUsuario, contrasena, fechaNacimiento);
        this.sueldo = sueldo;
        this.ine = ine;
        this.rfc = rfc;
        this.fechaInicio = String.valueOf(LocalDate.now());
    }

    @Override

    public String toString() { 
        return String.format("%s \nSueldo: %.2f \nRFC: %s \nINE: %s",
         super.toString(), this.sueldo, this.rfc, this.ine) + "\nFecha de inicio: " + this.fechaInicio;
    }


    public static void registrarGerente() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = Libreria.obtenerDatosComun(Rol.GERENTE);
        
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        String telefono = datosComun.get(2);
        String nombreUsuario = datosComun.get(3);
        String contraseña = datosComun.get(4);
        String fechaNacimiento = datosComun.get(5);
        System.out.print("RFC: ");
        String rfc = scanner.nextLine();
        System.out.print("INE: ");
        String ine = scanner.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = scanner.nextDouble();
        
        Gerente gerente = new Gerente(nombre, apellido, telefono, sueldo, rfc.toUpperCase(), ine.toUpperCase(), nombreUsuario, contraseña, fechaNacimiento);
        if(!Libreria.usuarios.containsKey(Rol.GERENTE)) {
            Libreria.usuarios.put(Rol.GERENTE, new ArrayList<Usuario>());  
        }

        Libreria.usuarios.get(Rol.GERENTE).add(gerente); 
        System.out.println("\nRegistro exitoso");
    }


    public static void mostrarGerentes() {
        if(!Libreria.usuarios.containsKey(Rol.GERENTE) || Libreria.usuarios.get(Rol.GERENTE).isEmpty()) {
            System.out.println("\nNo hay gerentes registrados aún");
        }
        else {
            System.out.println("\nGerentes en la biblioteca");
            int x = 1;
            for(Usuario usuario : Libreria.usuarios.get(Rol.GERENTE)) {
                Gerente gerente = (Gerente) usuario;         
                System.out.println("\n---- Gerente " + x + " ----\n");
                System.out.println(gerente.toString());
                x++;
            }
        }
    }

    @Override
    public void checarEntrada() {
        System.out.println("Checar entrada con huella");
    }

    @Override 
    public void checarSalida() {
        System.out.println("Checar salida con huella");
    }

}
