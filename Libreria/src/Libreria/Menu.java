package Libreria;

import java.util.InputMismatchException;
import java.util.Scanner;
import Libros.Libro;
import Libros.LibroAccion;
import Libros.LibroComedia;
import Libros.LibroTerror;
import Usuarios.Asistente;
import Usuarios.Cliente;
import Usuarios.Gerente;
import Usuarios.Usuario;
import Usuarios.utils.Rol;
import utils.UsuarioEnSesion;

public class Menu {

    private Libreria libreria = new Libreria();
    private Scanner leer = new Scanner(System.in);

    public void iniciarSesion() {
        Scanner scanner = new Scanner(System.in);
        boolean datosCorrectos = false;
        do {
            System.out.println("\nBienvenido al sistema de la Biblioteca");
            System.out.println("\nIngrese la palabra SALIR para salir de la Biblioteca");
            System.out.println("\nPara continuar sesión\n");
            System.out.println("Ingresa tu usuario: ");
            String usuario = scanner.nextLine();

            if (usuario.equalsIgnoreCase("salir")) {
                datosCorrectos = false;
            } else {
                System.out.println("Ingresa tu contraseña: ");
                String contrasena = scanner.nextLine();
                Usuario usuarioActual = libreria.verificarInicioSesion(usuario, contrasena);
                if (usuarioActual != null) {

                    datosCorrectos = true;
                    UsuarioEnSesion.obtenerInstancia().setUsuarioActual(usuarioActual);
                    seleccionarMenu();
                } else {
                    System.out.println("\nUsuario o contraseña incorrectos.");
                    datosCorrectos = true;
                }
            }
        } while (datosCorrectos);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void seleccionarMenu() {
        Usuario usuario = UsuarioEnSesion.obtenerInstancia().getUsuarioActual();
        switch (usuario.getRol()) {
            case CLIENTE:
                mostrarMenuCliente(usuario.getNombreUsuario());
            case ASISTENTE:
                mostrarMenuAsistente(usuario.getNombreUsuario());
            case GERENTE:
                mostrarMenuGerente(usuario.getNombreUsuario());
        }
    }

    private void mostrarMenuCliente(String nombreUsuario) {
        int decision = 0;
        do {
            System.out.println("\n\n---- BIENVENIDO CLIENTE ----\n");
            System.out.println(nombreUsuario);
            System.out.println("\n1. Consultar libros");
            System.out.println("2. Consultar rentas");
            System.out.println("3. Mostrar mis datos");
            System.out.println("4. Editar mi información");
            System.out.println("5. Filtrar Libros");
            System.out.println("6. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = leer.nextInt();

            switch (decision) {
                case 1:
                    Libro.mostrarLibros();
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    menuFiltrarLibro();
                    break;

                case 6:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    break;
            }
        } while (decision != 6);
        System.out.println("\nSesión cerrada");
    }

    private void mostrarMenuAsistente(String nombreUsuario) {
        int decision;
        do {
            System.out.println("\n\n---- BIENVENIDO ASISTENTE ----\n");
            System.out.println(nombreUsuario);
            System.out.println("\n1. Consultar libros");
            System.out.println("2. Consultar rentas");
            System.out.println("3. Registrar cliente");
            System.out.println("4. Consultar clientes");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Eliminar libros");
            System.out.println("7. Modificar datos de un cliente");
            System.out.println("8. Modificar datos de un libro");
            System.out.println("9. Agregar libro");
            System.out.println("10. Filtrar libros");
            System.out.println("11. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = leer.nextInt();

            switch (decision) {
                case 1:
                    Libro.mostrarLibros();
                    break;

                case 2:
                    break;

                case 3:
                    Cliente.registrarCliente();
                    break;

                case 4:
                    Cliente.mostrarClientes();
                    break;

                case 5:
                    Libreria.eliminarUsuario(Rol.CLIENTE);
                    break;

                case 6:
                    menuEliminarLibro();
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    menuRegistrarLibro();
                    break;

                case 10:
                    menuFiltrarLibro();
                    break;

                case 11:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    break;
            }
        } while (decision != 11);
        System.out.println("\nSesión cerrada");
    }

    private void menuRegistrarLibro() {
        Scanner scanner = new Scanner(System.in);
        int decision = 0;
        boolean esOpcionIncorrecta = false;

        do {
            System.out.println("\n---- Registrar libro ----\n");
            System.out.println("Ingrese el tipo de libro que deseas registrar");
            System.out.println("\n1. Acción");
            System.out.println("2. Comedia");
            System.out.println("3. Terror");
            System.out.println("4. Salir");

            while (!esOpcionIncorrecta) {
                try {
                    decision = scanner.nextInt();

                    if (decision > 4 || decision < 1) {
                        throw new InputMismatchException();
                    }
                    esOpcionIncorrecta = true;
                } catch (InputMismatchException error) {
                    System.out.println("\nIngresaste un valor incorrecto, intenta de nuevo");
                }
                scanner.nextLine();
            }

            esOpcionIncorrecta = false;

            switch (decision) {

                case 1:
                    System.out.println("\n---- Acción ----");
                    LibroAccion.registrarLibroAccion();
                    break;

                case 2:
                    System.out.println("\n---- Comedia ----");
                    LibroComedia.registrarLibroComedia();
                    break;

                case 3:
                    System.out.println("\n---- Terror ----");
                    LibroTerror.registrarLibro();
                    break;
            }
        } while (decision != 4);
    }

    private void menuEliminarLibro() {
        Scanner scanner = new Scanner(System.in);
        int decision = 0;
        boolean esOpcionIncorrecta = false;

        do {
            System.out.println("\n---- Eliminar libro ----\n");
            System.out.println("Ingrese el tipo de libro que deseas eliminar");
            System.out.println("\n1. Acción");
            System.out.println("2. Comedia");
            System.out.println("3. Terror");
            System.out.println("4. Salir");

            while (!esOpcionIncorrecta) {
                try {
                    decision = scanner.nextInt();

                    if (decision > 4 || decision < 1) {
                        throw new InputMismatchException();
                    }
                    esOpcionIncorrecta = true;
                } catch (InputMismatchException error) {
                    System.out.println("\nIngresaste un valor incorrecto, intenta de nuevo");
                }
                scanner.nextLine();
            }

            esOpcionIncorrecta = false;

            switch (decision) {

                case 1:
                    System.out.println("\n---- Acción ----");
                    LibroAccion.eliminarLibroAccion();
                    break;

                case 2:
                    System.out.println("\n---- Comedia ----");
                    LibroComedia.eliminarLibroComedia();
                    break;

                case 3:
                    System.out.println("\n---- Terror ----");
                    LibroTerror.eliminarLibroTerror();
                    break;
            }
        } while (decision != 4);
    }

    private void menuFiltrarLibro() {
        Scanner scanner = new Scanner(System.in);
        int decision = 0;
        boolean esOpcionIncorrecta = false;

        do {
            System.out.println("\n---- Filtrar libros ----\n");
            System.out.println("Ingrese el tipo de filtración que deseas hacer: ");
            System.out.println("\n1. Filtrar libros por precio");
            System.out.println("2. Filtrar libros por inicial de autor");
            System.out.println("3. Filtrar libros por editorial");
            System.out.println("4. Filtrar libros por título (palabra clave)");
            System.out.println("5. Filtrar libros por stock");
            System.out.println("6. Salir");

            while (!esOpcionIncorrecta) {
                try {
                    decision = scanner.nextInt();

                    if (decision > 4 || decision < 1) {
                        throw new InputMismatchException();
                    }
                    esOpcionIncorrecta = true;
                } catch (InputMismatchException error) {
                    System.out.println("\nIngresaste un valor incorrecto, intenta de nuevo");
                }
                scanner.nextLine();
            }

            esOpcionIncorrecta = false;
            LibroAccion libro = new LibroAccion();
            LibroComedia libroComedia = new LibroComedia();
            LibroTerror libroTerror = new LibroTerror();

            switch (decision) {

                case 1:

                    System.out.println("\nIngrese el género que desea filtrar");
                    System.out.println("\n1. Acción");
                    System.out.println("2. Comedia");
                    System.out.println("3. Terror");
                    decision = scanner.nextInt();

                    switch (decision) {
                        case 1:
                            System.out.println("\nIngrese el precio que desea filtrar: ");
                            double precio = scanner.nextDouble();
                            libro.filtrarLibrosPorPrecio(precio);
                            break;

                        case 2:
                            System.out.println("\nIngrese el precio que desea filtrar: ");
                            precio = scanner.nextDouble();
                            libroComedia.filtrarLibrosPorPrecio(precio);
                            break;

                        case 3:
                            System.out.println("\nIngrese el precio que desea filtrar: ");
                            precio = scanner.nextDouble();
                            libroTerror.filtrarLibrosPorPrecio(precio);
                            break;
                    }
                    break;

                case 2:
                    System.out.println("\nIngrese el género que desea filtrar");
                    System.out.println("\n1. Acción");
                    System.out.println("2. Comedia");
                    System.out.println("3. Terror");
                    decision = scanner.nextInt();

                    switch (decision) {
                        case 1:
                            System.out.println("\nIngrese la inicial que desea filtrar: ");
                            String inicial = scanner.nextLine();
                            libro.filtrarLibroPorInicialAutor(inicial);
                            break;

                        case 2:
                            System.out.println("\nIngrese el precio que desea filtrar: ");
                            inicial = scanner.nextLine();
                            libroComedia.filtrarLibroPorInicialAutor(inicial);
                            break;

                        case 3:
                            System.out.println("\nIngrese el precio que desea filtrar: ");
                            inicial = scanner.nextLine();
                            libroTerror.filtrarLibroPorInicialAutor(inicial);
                            break;
                    }
                    break;

                case 3:
                    System.out.println("\nIngrese el género que desea filtrar");
                    System.out.println("\n1. Acción");
                    System.out.println("2. Comedia");
                    System.out.println("3. Terror");
                    decision = scanner.nextInt();

                    switch (decision) {
                        case 1:
                            System.out.println("\nIngrese la editorial que desea filtrar: ");
                            String editorial = scanner.nextLine();
                            libro.filtrarLibrosPorEditorial(editorial);
                            break;

                        case 2:
                            System.out.println("\nIngrese la editorial que desea filtrar: ");
                            editorial = scanner.nextLine();
                            libroComedia.filtrarLibrosPorEditorial(editorial);
                            break;

                        case 3:
                            System.out.println("\nIngrese la editorial que desea filtrar: ");
                            editorial = scanner.nextLine();
                            libroTerror.filtrarLibrosPorEditorial(editorial);
                            break;
                    }
                    break;

                case 4:
                    System.out.println("\nIngrese el género que desea filtrar");
                    System.out.println("\n1. Acción");
                    System.out.println("2. Comedia");
                    System.out.println("3. Terror");
                    decision = scanner.nextInt();

                    switch (decision) {
                        case 1:
                            System.out.println("\nIngrese la palabra clave que desea filtrar: ");
                            String palabraClave = scanner.nextLine();
                            libro.filtrarLibrosPorTitulo(palabraClave);
                            break;

                        case 2:
                            System.out.println("\nIngrese la palabra clave que desea filtrar: ");
                            palabraClave = scanner.nextLine();
                            libroComedia.filtrarLibrosPorTitulo(palabraClave);
                            break;

                        case 3:
                            System.out.println("\nIngrese la palabra clave que desea filtrar: ");
                            palabraClave = scanner.nextLine();
                            libroTerror.filtrarLibrosPorTitulo(palabraClave);
                            break;
                    }
                    break;

                case 5:
                    System.out.println("\nIngrese el género que desea filtrar");
                    System.out.println("\n1. Acción");
                    System.out.println("2. Comedia");
                    System.out.println("3. Terror");
                    decision = scanner.nextInt();

                    switch (decision) {
                        case 1:
                            System.out.println("\nIngrese el stock que desea filtrar: ");
                            int stock = scanner.nextInt();
                            libro.filtrarLibrosPorStock(stock);
                            ;
                            break;

                        case 2:
                            System.out.println("\nIngrese el stock que desea filtrar: ");
                            stock = scanner.nextInt();
                            libroComedia.filtrarLibrosPorStock(stock);
                            ;
                            break;

                        case 3:
                            System.out.println("\nIngrese el stock que desea filtrar: ");
                            stock = scanner.nextInt();
                            libroTerror.filtrarLibrosPorStock(stock);
                            break;
                    }
                    break;
            }
        } while (decision != 6);
    }

    public void mostrarMenuGerente(String nombreUsuario) {
        int decision;
        do {
            System.out.println("\n\n---- BIENVENIDO GERENTE ----\n");
            System.out.println(nombreUsuario);
            System.out.println("\n1. Consultar libros");
            System.out.println("2. Consultar rentas");
            System.out.println("3. Registrar cliente");
            System.out.println("4. Consultar clientes");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Eliminar libros");
            System.out.println("7. Modificar datos de un cliente");
            System.out.println("8. Modificar datos de un libro");
            System.out.println("9. Registrar asistente");
            System.out.println("10. Consultar asistente");
            System.out.println("11. Registrar libro");
            System.out.println("12. Realizar renta");
            System.out.println("13. Eliminar renta");
            System.out.println("14. Eliminar asistente");
            System.out.println("15. Modificar datos de un asistente");
            System.out.println("16. Registrar gerentes");
            System.out.println("17. Consultar gerentes");
            System.out.println("18. Eliminar gerente");
            System.out.println("19. Filtrar libros");
            System.out.println("20. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = leer.nextInt();

            switch (decision) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    Cliente.registrarCliente();
                    break;

                case 4:
                    Cliente.mostrarClientes();
                    break;

                case 5:
                    Libreria.eliminarUsuario(Rol.CLIENTE);
                    break;

                case 6:
                    menuEliminarLibro();
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    Asistente.registrarAsistente();
                    break;

                case 10:
                    Asistente.mostrarAsistentes();
                    break;

                case 11:
                    menuRegistrarLibro();
                    break;

                case 12:
                    break;

                case 13:
                    break;

                case 14:
                    Libreria.eliminarUsuario(Rol.ASISTENTE);
                    break;

                case 15:
                    break;

                case 16:
                    Gerente.registrarGerente();
                    break;

                case 17:
                    Gerente.mostrarGerentes();
                    break;

                case 18:
                    Libreria.eliminarUsuario(Rol.GERENTE);
                    break;

                case 19:
                    menuFiltrarLibro();
                    break;

                case 20:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    break;

            }
        } while (decision != 20);
        System.out.println("\nSesión cerrada");
    }
}
