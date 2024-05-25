package Libros.LibrosSerializer;
import Libreria.Libreria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class LibroSerializer {

    public static void serialize() {
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("libros.json"));
            json.toJson(Libreria.libros, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
