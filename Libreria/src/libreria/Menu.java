package libreria;
import java.util.Scanner;
import usuarios.Asistente;
import usuarios.Cliente;
import usuarios.Gerente;
import usuarios.Usuario;
import usuarios.UsuarioEnSesion;
import utils.Rol;


public class Menu {
    private Libreria libreria = new Libreria();
    private Scanner leer = new Scanner(System.in);

    public void iniciarSesion() { //al hacerlo estático podemos llamar este metodo sin necesidad de crear un objeto
        Scanner scanner = new Scanner(System.in);
        boolean datosCorrectos = false;
        do {
            System.out.println("Bienvenido al sistema de la Biblioteca");
            System.out.println("Para continuar sesión\n");
            System.out.println("Ingresa tu usuario: ");
            String usuario = scanner.nextLine();

            System.out.println("Ingresa tu contraseña: ");
            String contrasena = scanner.nextLine();
            Usuario usuarioActual = libreria.verificarInicioSesion(usuario, contrasena);
            if(usuarioActual != null) {
               
                datosCorrectos = true;
                UsuarioEnSesion.obtenerInstancia().setUsuarioActual(usuarioActual);
                seleccionarMenu();
            }
            else {
                System.out.println("\nUsuario o contraseña incorrectos.");
                datosCorrectos = true;
            }
        }
        while(datosCorrectos == true);
    }



    //funcion lambda
    private void seleccionarMenu() {
        Usuario usuario = UsuarioEnSesion.obtenerInstancia().getUsuarioActual();
        switch (usuario.getRol()) {
            case CLIENTE: mostrarMenuCliente(usuario.getNombreUsuario());
            case ASISTENTE: mostrarMenuAsistente(usuario.getNombreUsuario());
            case GERENTE: mostrarMenuGerente(usuario.getNombreUsuario());
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
            System.out.println("5. Cerrar sesión");
            System.out.println("\nIngrese opción: ");
            decision = leer.nextInt();

            switch(decision) {
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
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 5);
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
            System.out.println("10. Cerrar sesión");
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
                    break;
                
                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    Libro.registrarLibro();
                    break;

                case 10:
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    iniciarSesion();
                    break;
            }
        }
        while(decision != 10);
        System.out.println("\nSesión cerrada");
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
            System.out.println("19. Cerrar sesión");
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
                    Libro.registrarLibro();
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
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    iniciarSesion();
                    break;

                
            }
        }
        while(decision != 19);   
        System.out.println("\nSesión cerrada");  
    }
}
