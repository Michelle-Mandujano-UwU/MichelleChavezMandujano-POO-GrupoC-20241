package Libreria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import Libros.Libro;
import Libros.LibrosSerializer.LibroSerializer;
import Libros.LibrosSerializer.LibrosDeserializer;
import Libros.utils.constants.Genero;
import Usuarios.*;
import Usuarios.Serializer.UsuarioDeserializer;
import Usuarios.Serializer.UsuarioSerializer;
import Usuarios.utils.Rol;

public class Libreria {

    public static final HashMap<Rol, ArrayList<Usuario>> usuarios = new HashMap<Rol, ArrayList<Usuario>>();
    public static final HashMap<Genero, ArrayList<Libro>> libros = new HashMap<Genero, ArrayList<Libro>>();

    private static String usuarioEnSesion;

    public Libreria(boolean band) {
        if (band) {
            inicializarhashmap();
        }
    }

    private void inicializarhashmap() {
        usuarios.put(Rol.CLIENTE, new ArrayList<Usuario>());
        usuarios.put(Rol.ASISTENTE, new ArrayList<Usuario>());
        usuarios.put(Rol.GERENTE, new ArrayList<Usuario>());
        libros.put(Genero.ACCION, new ArrayList<Libro>());
        libros.put(Genero.TERROR, new ArrayList<Libro>());
        libros.put(Genero.COMEDIA, new ArrayList<Libro>());

        Gerente gerente = new Gerente("Michelle", "Chavez", "4171161560", 80000, "hfbdfhbjdh", "GFHBGFD", "michelle21", "21Diciembre", String.valueOf(LocalDate.of(2000, 10, 28)));
        Asistente asistente = new Asistente("Diana", "Campos", "4433210990", 18000, "DL1234123", "Diana12", "michelle21", String.valueOf(LocalDate.of(1982, 04, 30)));
        usuarios.get(Rol.ASISTENTE).add(asistente);
        usuarios.get(Rol.GERENTE).add(gerente);

    }

    Usuario verificarInicioSesion(String nombreUsuario, String contrasena) {
        for (Rol key : usuarios.keySet()) {
            for (Usuario usuario : usuarios.get(key)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    if (usuario.getContrasena().equals(contrasena)) {
                        usuarioEnSesion = usuario.getNombreUsuario();
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    public static ArrayList<String> obtenerDatosComun(Rol rol) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = new ArrayList<String>();
        String rolUsuario = rol == Rol.CLIENTE ? "Cliente" : rol == Rol.ASISTENTE ? "Asistente" : "Gerente"; //operador ternario, es lo mismo que un if y es para no tantas condiciones

        System.out.println(String.format("\nBienvenido al registro del %s", rolUsuario));
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("\n---- Fecha de nacimiento (YYYY/MM/dd) ----\n");
        System.out.print("\nAño: ");
        int año = scanner.nextInt();
        System.out.print("Mes: ");
        int mes = scanner.nextInt();
        System.out.print("Día: ");
        int dia = scanner.nextInt();
        LocalDate fechaNacimiento = LocalDate.of(año, mes, dia);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String fechaFormateada = fechaNacimiento.format(pattern);
        String telefono = registrarTelefonoUsuario();
        String nombreUsuario = registrarNombreUsuario();
        scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        datosComun.addAll(Arrays.asList(nombre, apellido, telefono, nombreUsuario, contraseña, fechaFormateada)); //te agrega todos estos datos de un jalon
        return datosComun;
    }

    public static void eliminarUsuario(Rol rol) {
        Scanner scanner = new Scanner(System.in);
        boolean band = false;
        System.out.println("\n---- Eliminar usuario ----\n");
        System.out.print("Ingrese el nombre de usuario que desea eliminar: ");
        String nombreUsuario = scanner.nextLine();
        String rolUsuario = rol == Rol.CLIENTE ? "Cliente" : rol == Rol.ASISTENTE ? "Asistente" : "Gerente"; //operador ternario, es lo mismo que un if y es para no tantas condiciones

        if (nombreUsuario.equals(usuarioEnSesion)) {
            System.out.println("\nNo puedes eliminarte a ti mismo");
            band = true;
        } else if (usuarios.containsKey(rol)) {
            Usuario usuarioEliminado = null;
            for (Usuario usuario : usuarios.get(rol)) {
                if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                    usuarioEliminado = usuario;
                    int y = usuarioEliminado.getId();
                    usuarioEliminado.setCANTIDAD_USUARIOS();
                    band = true;
                    for (Rol key : usuarios.keySet()) {
                        for (Usuario i : usuarios.get(key)) {
                            if (i.getId() > y) {
                                i.setId();
                            }
                        }
                    }
                }
            }
            if (band) {
                usuarios.get(rol).remove(usuarioEliminado);
                System.out.println("\nUsuario eliminado");
            }
        }
        if (band == false) {
            System.out.println(String.format("\nEste nombre no pertenece a ningún %s", rolUsuario));
        }
    }

    public static String registrarTelefonoUsuario() {
        Scanner scanner = new Scanner(System.in);
        boolean telefonoExistente = true;
        String telefono = "";

        do {
            System.out.println("\nIngresa el telefono: ");
            telefono = scanner.nextLine();
            telefonoExistente = false;
            for (Rol key : usuarios.keySet()) {
                for (Usuario usuario : usuarios.get(key)) {
                    if (usuario.getTelefono().equals(telefono)) {
                        telefonoExistente = true;
                        break;
                    }
                }
            }
            if (telefonoExistente) {
                System.out.println("\nEl telefono ya se encuentra registrado. Intenta de nuevo.");
            }
        } while (telefonoExistente);
        return telefono;
    }

    public static String registrarNombreUsuario() {
        Scanner scanner = new Scanner(System.in);
        String nombreUsuario = "";
        boolean nombreUsuarioExistente = true;
        do {
            System.out.println("\nIngresa el nombre de usuario: ");
            nombreUsuario = scanner.nextLine();
            nombreUsuarioExistente = false;
            for (Rol key : usuarios.keySet()) {
                for (Usuario usuario : usuarios.get(key)) {
                    if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                        nombreUsuarioExistente = true;
                        break;
                    }
                }
            }
            if (nombreUsuarioExistente) {
                System.out.println("\nYa existe un registro con ese nombre de usuario. Intenta de nuevo.");
            }
        } while (nombreUsuarioExistente);
        return nombreUsuario;
    }

    public static void guardarEnJSON() {
        UsuarioSerializer.serialize();
        LibroSerializer.serialize();
    }

    public static void leerJSON() {
        UsuarioDeserializer.deserialize();
        LibrosDeserializer.deserialize();
    }
}
