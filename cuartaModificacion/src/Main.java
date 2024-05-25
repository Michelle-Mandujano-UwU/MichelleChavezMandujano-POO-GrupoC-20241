import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import Libreria.Menu;
import Usuarios.Asistente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Usuarios.Gerente;
import Usuarios.utils.Rol;

/**
 *
 * @author Mich
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);
        boolean datoValido = false;

        while (!datoValido) {
            try {
                System.out.print("\nIngresa tu edad: ");
                int edad = scanner.nextInt();
                datoValido = true;
            } catch (Exception error) {
                System.out.println("\nOcurrió un error");
                scanner.nextLine();
            }
        }

        try {

            String cadena = null;
            System.out.println(cadena.length());
        } catch (ArithmeticException error) {

            System.out.println(error);
        } catch (RuntimeException error) {
            System.out.println(error);
        } catch (Exception error) {
            System.out.println("Ninguna ocurrió");
        } finally {
            System.out.println("Se ejecutó al final");
        }

        Menu menu = new Menu();
        menu.iniciarSesion();

        LocalDate fecha = LocalDate.of(2000, 8, 10);
        LocalTime hora = LocalTime.now();
        LocalTime hour = LocalTime.of(10, 5);

        LocalDateTime fullfecha = LocalDateTime.of(fecha, hora);
        LocalDateTime x = LocalDateTime.of(2024, 11, 24, 9, 30, 3);

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
        LocalDateTime fechaCompleta = LocalDateTime.now();
        String fechaFormateada = fechaCompleta.format(pattern);
        System.out.println(fechaFormateada);

    }

}
