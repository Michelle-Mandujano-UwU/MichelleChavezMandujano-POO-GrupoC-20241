package libreria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import usuarios.*;
import utils.Rol;

public class Libreria {
    
       //@SuppressWarnings("rawTypes") //sirve para poder meter lo que quieras a un arrayList
    
    
    private ArrayList<Libro> libros = new ArrayList<Libro>();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Usuario> usuariosTotales = new ArrayList<Usuario>();
     private HashMap<Rol, Object> listaUsuarios1 = new HashMap<>();
    private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    Scanner leer = new Scanner(System.in);

    
    public Usuario verificarInicioSesion(String nombreUsuario, String contraseña) {
        for (Object usuario : listaUsuarios1.values()) {
            if (((Usuario) usuario).getNombre().equals(nombreUsuario)) {
                if (((Usuario) usuario).getContrasena().equals(contraseña)) {
                    return ((Usuario) usuario);
                }
                return null;
            }
        }
        return null;
    }

      public void registrarLibro() {
        System.out.println("Ingresa el título: ");
        String tittle = leer.nextLine();
        System.out.println("Ingresa la fecha de lanzamiento: ");
        String fechaLanzamiento = leer.nextLine();
        System.out.println("Ingresa el nombre del autor: ");
        String authorName = leer.nextLine();
        Libro newBook = new Libro(tittle, fechaLanzamiento, authorName);
        System.out.println("Código de barras: " + newBook.getIDlibro());
        libros.add(newBook);
    }
    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---- Registrar Cliente ----");
        System.out.println("\nIngresa los siguientes datos para continuar con el registro: ");
        System.out.print("\nNombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        String telefono = registrarTelefonoUsuario();
        String nombreUsuario = registrarNombreUsuario();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        Cliente newCliente = new Cliente(nombre, apellido, telefono, nombreUsuario, contraseña);
        listaUsuarios.add(newCliente);
        listaUsuarios1.put(Rol.CLIENTE, newCliente);

        System.out.println("Usuario registrado con rol de CLIENTE y con id " + newCliente.getId());
    }

   public void mostrarClientes() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("\nNo hay cliente registrados");
        } else {
            System.out.println("\n**Lista de Clientes**");

            for (Usuario usuario : listaUsuarios) {
                if (usuario.getRol() == Rol.CLIENTE) {
                    Cliente cliente = (Cliente) usuario;
                    System.out.println(cliente.toString());
                }
            }
        }
    }
        /*System.out.println("\nClientes en la biblioteca");
        int x = 1;
        for(Usuario i : listaUsuarios) {
            if(i.getRol() == Rol.CLIENTE) {
                Cliente cliente = (Cliente) i;         //parseo
                System.out.println("\n---- Cliente " + x + " ----\n");
                System.out.println(cliente.toString());
                x++;
            }
        }*/
    

    private String registrarTelefonoUsuario() {
        boolean telefonoExistente = true;

        String telefono = "";
        do {
            System.out.print("Ingresa tu teléfono: ");
            telefono = leer.nextLine();

            telefonoExistente = false;
            for (Object usuario : listaUsuarios1.values()) {
                if (((Usuario)usuario).getTelefono().equals(telefono)) {
                    telefonoExistente = true;
                    break;
                }
            }

            if (telefonoExistente) {
                System.out.println("\nEl teléfono ya se encuentra registrado. Intenta de nuevo");
            }
        } while (telefonoExistente);

        return telefono;
    }


    public String registrarNombreUsuario() {
         
        boolean user = true;

        String nombreUsuario = "";
        do {
            System.out.print("Nombre de usuario: ");
            nombreUsuario = leer.nextLine();

            user = false;
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getTelefono().equals(nombreUsuario)) {
                    user = true;
                    break;
                }
            }

            if (user) {
                System.out.println("\nYa existe un registro con ese nombre de usuario. Intenta de nuevo");
            }
        } while (user);

        return nombreUsuario;
    

    }
     public void registrarGerente() {
        Rol rol = UsuarioEnSesion.obtenerInstancia().getUsuarioActual().getRol();

        if (rol == Rol.GERENTE) {
            System.out.println("\n***Registro de gerente**");

            System.out.print("\nIngresa el nombre: ");
            String nombre = leer.nextLine();

            System.out.print("Ingresa el apellido: ");
            String apellido = leer.nextLine();

            System.out.print("Ingresa el sueldo: ");
            double sueldo = leer.nextDouble();

            System.out.print("Ingresa la INE: ");
            String INE = leer.nextLine();

            String telefono = registrarTelefonoUsuario();
            String nombreUsuario = registrarNombreUsuario();

            System.out.print("Contraseña: ");
            String clave = leer.nextLine();

            Gerente newAdmin = new Gerente(nombre, apellido, telefono, nombreUsuario, nombreUsuario, sueldo, clave,
                    INE);
            listaUsuarios.add(newAdmin);

            System.out.println("Usuario registrado con rol de ADMINISTRADOR y con id " + newAdmin.getId());
        } else {
            System.out.println("\nNo eres un gerente, no puedes registrar a otro gerente");
            return;
        }

    }

    public void registrarAsistente() {

        Rol rol = UsuarioEnSesion.obtenerInstancia().getUsuarioActual().getRol();

        if (rol == Rol.GERENTE) {
            System.out.println("\n***Registro de asistente***");

            System.out.print("\nIngresa el nombre: ");
            String nombre = leer.nextLine();

            System.out.print("Ingresa el apellido: ");
            String apellido = leer.nextLine();

            System.out.print("Ingresa el sueldo: ");
            double sueldo = leer.nextDouble();

            System.out.print("Ingresa el RFC: ");
            String RFC = leer.nextLine();

            System.out.print("Ingresa la INE: ");
            String INE = leer.nextLine();

            String telefono = registrarTelefonoUsuario();
            String nombreUsuario = registrarNombreUsuario();

            System.out.print("Contraseña: ");
            String clave = leer.nextLine();

            Asistente newAsistente = new Asistente(nombre, apellido, telefono, nombreUsuario, clave, sueldo, RFC, INE);
            listaUsuarios.add(newAsistente);

            System.out.println("Usuario registrado con rol de ASISTENTE y con id " + newAsistente.getId());
        } else {
            System.out.println("\nNo eres un gerente, no puedes registrar a un asistente");
            return;
        }

    }
    public void mostrarGerentes() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("\nNo hay gerentes registrados");
        } else {
            System.out.println("\n**Lista de Gerentes**");
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getRol() == Rol.GERENTE) {
                    Gerente gerente = (Gerente) usuario;
                    System.out.println(gerente.toString());
                }
            }
        }
    }

    public void mostrarAsistentes() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("\nNo hay asistentes registrados");
        } else {
            System.out.println("\n**Lista de Asistentes**");
            for (Usuario usuario : listaUsuarios) {
                if (usuario.getRol() == Rol.ASISTENTE) {
                    Asistente asistente = (Asistente) usuario;
                    System.out.println(asistente.toString());
                }
            }
        }
    }
    public void eliminarClientes() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n**Eliminación de clientes");

        System.out.print("\nIngrese el nombre de usuario del cliente a eliminar: ");
        String nombreUsuario = scanner.nextLine();

        int indiceEliminado = 0;

        for (Usuario cliente : listaUsuarios) {
            if (cliente.getNombreUsuario().equals(nombreUsuario)) {
                listaUsuarios.remove(indiceEliminado);
                System.out.println("\nCliente eliminado con éxito");
                return;
            }
            indiceEliminado++;
        }
        System.out.println("\nEl cliente no existe en sistema");

    }

    public void eliminarAsistente() {
        Scanner scanner = new Scanner(System.in);

        Rol rol = UsuarioEnSesion.obtenerInstancia().getUsuarioActual().getRol();

        if (rol == Rol.GERENTE) {
            System.out.println("\n**Eliminación de asistente");

            System.out.print("\nIngrese el nombre de usuario del asistente a eliminar: ");
            String nombreUsuario = scanner.nextLine();

            int indiceEliminado = 0;

            for (Usuario asistente : listaUsuarios) {
                if (asistente.getNombreUsuario().equals(nombreUsuario)) {
                    listaUsuarios.remove(indiceEliminado);
                    System.out.println("\nAsistente eliminado con éxito");
                    return;
                }
                indiceEliminado++;
            }
            System.out.println("\nEl asistente no existe en sistema");

        } else {
            System.out.println("\nNo eres un gerente no puede eliminar un asistente");
            return;
        }

    }

    public void eliminarGerente() {
        Scanner scanner = new Scanner(System.in);

        Rol rol = UsuarioEnSesion.obtenerInstancia().getUsuarioActual().getRol();

        if (rol == Rol.GERENTE) {
            System.out.println("\n**Eliminación de gerente");

            System.out.print("\nIngrese el nombre de usuario del gerente a eliminar: ");
            String nombreUsuario = scanner.nextLine();

            int indiceEliminado = 0;

            for (Usuario gerente : listaUsuarios) {
                if (gerente.getNombreUsuario().equals(nombreUsuario)) {
                    listaUsuarios.remove(indiceEliminado);
                    System.out.println("\nGerente eliminado con éxito");
                    return;
                }
                indiceEliminado++;
            }
            System.out.println("\nEl gerente no existe en sistema");

        } else {
            System.out.println("\nNo eres un gerente, no puedes eliminar a otro gerente");
            return;
        }

    }

    public void mostrarLibros() {
        for (Libro buscarLibro : libros) {
            System.out.println(buscarLibro.mostrarLibros());
        }
    }

}

