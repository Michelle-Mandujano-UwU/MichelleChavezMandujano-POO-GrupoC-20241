package Usuarios.Serializer;
import java.io.BufferedReader;
import java.io.FileReader;
import Libreria.Libreria;
import Usuarios.utils.Rol;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import Usuarios.Usuario;
import Usuarios.models.UsuarioModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class UsuarioDeserializer {

    public static void deserialize() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("usuarios.json"));
            Gson gson = new Gson();
            UsuarioModel usuarioModel = gson.fromJson(reader, UsuarioModel.class);
            ArrayList<Usuario> gerentes;
            ArrayList<Usuario> asistentes;
            ArrayList<Usuario> clientes;

            if(usuarioModel == null) {
                System.out.println("El archivo JSON no pudo ser deserializado a UsuarioModel");
                return;
            }

            if(usuarioModel.getGerentes() != null) {
                gerentes = new ArrayList<>(usuarioModel.getGerentes());
                Libreria.usuarios.put(Rol.GERENTE, gerentes);
            }
            else {
                Libreria.usuarios.put(Rol.GERENTE, new ArrayList<>());
            }

            if(usuarioModel.getAsistentes() != null) {
                asistentes = new ArrayList<>(usuarioModel.getAsistentes());
                Libreria.usuarios.put(Rol.ASISTENTE, asistentes);
            }
            else {
                Libreria.usuarios.put(Rol.ASISTENTE, new ArrayList<>());
            }

            if(usuarioModel.getClientes() != null) {
                clientes = new ArrayList<>(usuarioModel.getClientes());
                Libreria.usuarios.put(Rol.CLIENTE, clientes);
            }
            else {
                Libreria.usuarios.put(Rol.CLIENTE, new ArrayList<>());
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
