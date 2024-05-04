
package excepciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Mich
 */
public class Excepciones {

   
    public static void main(String[] args) {
        
        
     ///////ArithmeticException///////////////////////////////////////////////
        try {
            int resultado = 10 / 0;
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede dividir por cero");
        }
        
   ///////NullPointerException//////////////////////////////////////////////
        String nombre = null;

        try {
            System.out.println("Longitud del nombre: " + nombre.length());
        } catch (NullPointerException e) {
            System.out.println("Error: El objeto es nulo");
        }


    ///////ArrayIndexOutOfBoundsException/////////////////////////////////
        int[] numeros = new int[5];

        try {
            System.out.println("Valor en la posición 10: " + numeros[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Índice fuera de rango");
        }


    //////FileNotFoundException///////////////////////////
        File archivo = new File("archivo.txt");

        try {
            Scanner lector = new Scanner(archivo);
            System.out.println("Primera línea del archivo: " + lector.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Error: Archivo no encontrado");
        }


    //////IOException//////////////////////////////////////////////////////
        try {
            FileReader lector = new FileReader("archivo.txt");
            BufferedReader br = new BufferedReader(lector);
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error: Problema de entrada/salida");
        }
    
    
        
        
        
    ///////IllegalArgumentException/////////////////////////////////////
        try {
            Integer.parseInt("abc");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: El argumento no es un número entero");
        }
       
        
    //////IllegalStateException/////////////////////////////////////////
        List<String> lista = new ArrayList<>();
        lista.add("Elemento 1");

        try {
            Iterator<String> it = lista.iterator();

            while (it.hasNext()) {
                it.remove();
                lista.add("Elemento nuevo");
            }
        } catch (IllegalStateException e) {
            System.out.println("Error: Modificar la lista durante la iteración");
        }
        
        
    ///////SecurityException////////////////////////////////////////////////////////////
        try {
            System.setProperty("propiedad.sensible", "valor");
        } catch (SecurityException e) {
            System.out.println("Error: Falta permiso para acceder a la propiedad");
        }
    
        
    ///////UnsupportedOperationException//////////////////////////////////////////////////////////
        Set<String> conjunto = new HashSet<>();
        conjunto.add("Elemento 1");
        conjunto.add("Elemento 2");

        try {
            conjunto.remove("Elemento 3");
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: La operación no está soportada para este tipo de conjunto");
        }
        
        
    ////////ClassCastException///////////////
        Object objeto = new String("Hola");

        try {
            Integer numero = (Integer) objeto;
            System.out.println("Número: " + numero);
        } catch (ClassCastException e) {
            System.out.println("Error: No se puede convertir el objeto a un número entero");
        }


  

        
        
        
        
        
        
        
        
    }
   
    
    
    
}
