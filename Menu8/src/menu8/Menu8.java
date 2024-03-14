
package menu8;

import java.util.Scanner;


public class Menu8 {

    
    public static void main(String[] args) {
        Librería libreria = new Librería();
    Scanner leer = new Scanner(System.in);

    int opcion;
        do {
            System.out.println("Elije una opcion");
            System.out.println("1- Registrar usuario");
            System.out.println("2- Registrar libro");
            System.out.println("3- Rentar libro");
            System.out.println("4- Listar usuarios");
            System.out.println("5- Listar libros");
            System.out.println("6- Listar libros no rentados");
            System.out.println("7- Listar libros rentados");
            System.out.println("8- Salir");

            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    libreria.agregarUsuario();
                    break;
                case 2:
                    libreria.agregarLibro();
                    break;
                case 3:
                    leer.nextLine();
                    System.out.println("Nombre del usuario: ");
                    String nombre = leer.nextLine();
                    System.out.println("Ingrese título del libro:");
                    String título = leer.nextLine();
                    Usuarios usuario = libreria.getUsuarioPorNombre(nombre);
                    Libros libro = libreria.getLibroPorTítulo(título);
                    libreria.rentarLibro(usuario, libro);
                    break;
                case 4:
                    libreria.listarUsuarios();
                    break;
                case 5:
                    libreria.listarLibros();
                    break;
                case 6:
                    libreria.librosNoRentados();
                    break;
                case 7:
                    libreria.librosRentados();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 8);
    }
    }
    

