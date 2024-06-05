
package utils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author 
 */
public class Utils {
    
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static JSONObject initData(String nameFile){
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(Constantes.BASE_URL+""+nameFile));
            //System.out.println(jsonObject.toString());
            
            return jsonObject;
        } catch (Exception e) {
            System.err.println("==> No se ha podido cargar el archivo JSON ["+e.getMessage()+"]");
            return null;
        }
    }
    
    public static void writeDataOnJson(String data,String fileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.BASE_URL+""+fileName))) {
            bw.write(data);
            System.out.println("==> Cambios guardados en el archivo: "+fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static String generateNumeroControl(String nombre,
            String fechaNccimiento,
            int idCarrera,
            String id){
        
        String numControl = nombre.substring(0,2).toUpperCase()+""
                + ""+fechaNccimiento.substring(0,2)+""
                + ""+idCarrera+""+id;  
        
        return numControl;
    }
    
    public static String generarRFC(String apellidos,String nombre,String fechaNac) {
        
        LocalDate fechaNacimiento = LocalDate.parse(fechaNac, formatter);
        
        String homonimo = "";
        if (apellidos.length() > 1) {
            homonimo = apellidos.substring(0, 2);
        } else {
            homonimo = apellidos;
        }
        String apellidoMaterno = "";
        String primeraLetraApellidoMaterno="";
        if (apellidos.split(" ").length > 1) {
            apellidoMaterno = apellidos.split(" ")[1]; // Obtener el segundo apellido
            primeraLetraApellidoMaterno = apellidoMaterno.substring(0, 1); // Extraer la primera letra
        }
        nombre = nombre.substring(0, 1); // Extraer la primera letra del nombre

        String fechaNacimientoStr;
        try {
            fechaNacimientoStr = fechaNacimiento.format(DateTimeFormatter.ofPattern("yyMMdd"));
        } catch (NullPointerException e) {
            System.err.println("Error al formatear la fecha de nacimiento: " + e.getMessage());
            return null;
        }


        return homonimo.toUpperCase() +primeraLetraApellidoMaterno.toUpperCase()+nombre.toUpperCase()+ fechaNacimientoStr ;
    }
    
    public static String generarCurp(String apellidos,String nombre,String fechaNac,String estado) {
        
        LocalDate fechaNacimiento = LocalDate.parse(fechaNac, formatter);
        
        String homonimo = apellidos.substring(0, 1);
        String primeraLetraApellidoMaterno = buscarPrimeraVocal(apellidos.split(" ")[1]);
        String nombrePrimeraLetra = nombre.substring(0, 1);

        String fechaNacimientoStr = "";
        try {
            fechaNacimientoStr = fechaNacimiento.format(DateTimeFormatter.ofPattern("yyMMdd"));
        } catch (NullPointerException e) {
            System.err.println("Error al formatear la fecha de nacimiento: " + e.getMessage());
            return null;
        }
        String codigoEntidadFederativa = estado.substring(0, 1);
        Random random = new Random();
        int digitoAleatorio = random.nextInt(10) + 1;
        String curp = homonimo.toUpperCase() + primeraLetraApellidoMaterno.toUpperCase() +
                nombrePrimeraLetra.toUpperCase() + fechaNacimientoStr +
                codigoEntidadFederativa + digitoAleatorio;
        return curp;
    }
    
     private static String buscarPrimeraVocal (String cadena) {
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < cadena.length(); i++) {
            if (vowels.contains(String.valueOf(cadena.charAt(i)))) {
                return String.valueOf(cadena.charAt(i));
            }
        }
        return "";
    }

     public static boolean validateFecha(String fecha){
         try {
             LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);
             return true;
         } catch (Exception e) {
             System.out.println("Fecha no valida");
             return false;
         }
     }
     
     public static boolean isNumeric(String n){
         try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
     }
     
     public static boolean isDouble(String n){
         try {
            Double.parseDouble(n);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
     }
}
