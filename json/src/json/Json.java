
package json;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
/**
 *
 * @author Mich
 */
public class Json {
/*En Java, un archivo JSON (JavaScript Object Notation) es un formato de datos ligero y basado en texto que se utiliza para el intercambio de datos. Es similar a XML, pero es más fácil de leer y escribir. Los archivos JSON se utilizan a menudo para almacenar y transportar datos entre aplicaciones, especialmente en aplicaciones web.

Estructura de un archivo JSON

Un archivo JSON está formado por pares de clave-valor. Las claves son cadenas de texto y los valores pueden ser de varios tipos, incluyendo:

Cadenas de texto
Números
Booleanos
Arrays
Objetos
Los pares de clave-valor se separan por comas y se encierran en llaves {}. Los valores de cadena de texto se encierran en comillas dobles " " y los valores numéricos no necesitan comillas. Los arrays se encierran en corchetes [] y los objetos se encierran en llaves {}.*/
   
    public static void main(String[] args) {
       // Crear un objeto ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // Crear un objeto Persona
        Persona persona = new Persona("Juan Perez", 30, "Calle Mayor 123", "+52 55 12345678", Arrays.asList("Leer", "Escribir", "Viajar"));

        // Convertir el objeto Persona a JSON
        String jsonString = mapper.writeValueAsString(persona);
        System.out.println("JSON String: " + jsonString);

        // Leer el archivo JSON y convertirlo en un objeto Persona
        Persona personaLeida = mapper.readValue(jsonString, Persona.class);
        System.out.println("Persona leída: " + personaLeida);
    }
    
    
} 
   