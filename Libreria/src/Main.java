import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Libreria.Libreria;
import  Libreria.Menu;
import Usuarios.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
            menu.iniciarSesion();
            Libreria.guardarEnJSON();
    }
    
}
