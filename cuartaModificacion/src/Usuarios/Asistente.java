package Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Libreria.Libreria;
import Usuarios.utils.EmpleadoUtils;
import Usuarios.utils.Rol;

public class Asistente extends Usuario implements EmpleadoUtils {

    private LocalDate fechaInicio;
    private double sueldo;
    private String rfc;

    public Asistente(String nombre, String apellido, String telefono, double sueldo, String rfc, String nombreUsuario, String contrasena, String fechaNacimiento) {
        super(nombre, apellido, telefono, Rol.ASISTENTE, nombreUsuario, contrasena, fechaNacimiento);
        this.fechaInicio = LocalDate.now();
        this.sueldo = sueldo;
        this.rfc = rfc;
    }

    @Override

    public String toString() {
        return String.format("%s \nFecha de inicio: %s \nSueldo: %.2f \nRFC: %s",
                super.toString(), this.fechaInicio, this.sueldo, this.rfc) + "\nFecha de inicio: " + this.fechaInicio;
    }

    public static void registrarAsistente() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = Libreria.obtenerDatosComun(Rol.ASISTENTE);

        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        String telefono = datosComun.get(2);
        String nombreUsuario = datosComun.get(3);
        String contraseña = datosComun.get(4);
        String fechaNacimiento = datosComun.get(5);
        System.out.print("RFC: ");
        String rfc = scanner.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = scanner.nextDouble();

        Asistente asistente = new Asistente(nombre, apellido, telefono, sueldo, rfc.toUpperCase(), nombreUsuario, contraseña, fechaNacimiento);

        if (!Libreria.usuarios.containsKey(Rol.ASISTENTE)) {
            Libreria.usuarios.put(Rol.ASISTENTE, new ArrayList<Usuario>());
        }

        Libreria.usuarios.get(Rol.ASISTENTE).add(asistente);
        System.out.println("\nRegistro exitoso");
    }

    public static void mostrarAsistentes() {
        if (!Libreria.usuarios.containsKey(Rol.ASISTENTE) || Libreria.usuarios.get(Rol.ASISTENTE).isEmpty()) {
            System.out.println("\nNo hay asistentes registrados aún");
        } else {
            System.out.println("\nAsistentes en la biblioteca");
            int x = 1;
            for (Usuario usuario : Libreria.usuarios.get(Rol.ASISTENTE)) {
                Asistente asistente = (Asistente) usuario;         //parseo
                System.out.println("\n---- Asistente " + x + " ----\n");
                System.out.println(asistente.toString());
                x++;
            }
        }
    }

    @Override
    public void checarEntrada() {
        System.out.println("Checar entrada por correo");
    }

    @Override
    public void checarSalida() {
        System.out.println("Checar salida por correo");
    }
}
