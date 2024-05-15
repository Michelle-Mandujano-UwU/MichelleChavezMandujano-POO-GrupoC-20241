package Users.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UsuarioUtils {
    public static ArrayList<String> registarUsuarioComun(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = new ArrayList<>();

        System.out.println("\n---- Registrar Usuario ----");
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();

        System.out.print("------Domicilio------");
        System.out.println("Ingera el ciudad");
        String ciudad = scanner.nextLine();
        System.out.println("Ingera el estado");
        String estado = scanner.nextLine();
        System.out.println("Inserta direccion");
        String direccion = scanner.nextLine();
        String domicilio = ciudad + " " + estado + " " + direccion;
        System.out.println("-------------------");
        System.out.print("Año de nacimiento: ");
        String nacimiento = scanner.nextLine();
        String rfc = null;
        String curp = null;
        System.out.print("Nuevo nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.println("Nueva contraseña");
        String contrasena = scanner.nextLine();


        datosComun.addAll(Arrays.asList(nombre, apellido, domicilio, nacimiento, rfc, curp, usuario, contrasena));
        return datosComun;
    }
}
