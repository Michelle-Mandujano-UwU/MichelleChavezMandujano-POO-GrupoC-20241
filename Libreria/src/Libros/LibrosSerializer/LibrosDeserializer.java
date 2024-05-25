package Libros.LibrosSerializer;
import java.io.BufferedReader;
import java.io.FileReader;
import Libreria.Libreria;
import Libros.Libro;
import Libros.models.LibroModel;
import Libros.utils.constants.Genero;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class LibrosDeserializer {

    public static void deserialize() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("libros.json"));
            Gson gson = new Gson();
            LibroModel librosModel = gson.fromJson(reader, LibroModel.class);

            ArrayList<Libro> librosTerror;
            ArrayList<Libro> librosComedia;
            ArrayList<Libro> librosAccion;


            if(librosModel == null) {
                System.out.println("El archivo JSON no pudo ser deserializado a LibroModel");
                return;
            }

            if(librosModel.getLibrosComedia() != null) {
                librosComedia = new ArrayList<>(librosModel.getLibrosComedia());
                Libreria.libros.put(Genero.COMEDIA, librosComedia);
            }
            else {
                Libreria.libros.put(Genero.COMEDIA, new ArrayList<>());
            }

            if(librosModel.getLibrosAccion() != null) {
                librosAccion = new ArrayList<>(librosModel.getLibrosAccion());
                Libreria.libros.put(Genero.ACCION, librosAccion);
            }
            else {
                Libreria.libros.put(Genero.ACCION, new ArrayList<>());
            }

            if(librosModel.getLibrosTerror() != null) {
                librosTerror = new ArrayList<>(librosModel.getLibrosTerror());
                Libreria.libros.put(Genero.TERROR, librosTerror);
            }
            else {
                Libreria.libros.put(Genero.TERROR, new ArrayList<>());
            }


        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (JsonSyntaxException e) {
            System.out.println(e);
        } catch (JsonParseException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
