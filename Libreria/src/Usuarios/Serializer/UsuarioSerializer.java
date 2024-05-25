package Usuarios.Serializer;
import Libreria.Libreria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class UsuarioSerializer {

    public static void serialize() {
        Gson json = new GsonBuilder().setPrettyPrinting().create();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.json"));
            json.toJson(Libreria.usuarios, writer);
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
