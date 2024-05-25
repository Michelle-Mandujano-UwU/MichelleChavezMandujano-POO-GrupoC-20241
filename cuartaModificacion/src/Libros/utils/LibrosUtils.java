package Libros.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LibrosUtils {
    
    
    public static ArrayList<String> registrarLibrosDatosComun() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = new ArrayList<>();  
        
        System.out.println("\nNombre: ");
        String nombre = scanner.nextLine();
         
        System.out.println("\nAutor: ");
        String autor = scanner.nextLine();
         
        System.out.println("\nEditorial: ");
        String editorial = scanner.nextLine();
   
        System.out.println("Día de publicacion: ");
        int dia = scanner.nextInt();
         
        System.out.println("\nMes de publicación: ");
        int mes = scanner.nextInt();
         
        System.out.println("\nAño de publicación: ");
        int año = scanner.nextInt();
   
        LocalDate fecha = LocalDate.of(año, mes, dia);
        System.out.println("\nPrecio: ");
        double precio = scanner.nextDouble();
   
       System.out.println("Stock: ");
       int stock = scanner.nextInt();
   
       datosComun.addAll(Arrays.asList(nombre, autor, editorial, String.valueOf(fecha), String.valueOf(precio), String.valueOf(stock)));
       return datosComun;
    }
}

