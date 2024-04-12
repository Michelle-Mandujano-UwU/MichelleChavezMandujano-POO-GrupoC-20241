package libreria;
import usuarios.Cliente;
import utils.Rol;
public class Main {

    
    public static void main(String[] args) {
        Menu menu = new Menu();
        //menu.iniciarSesión();     //Contraseña y usuario
        menu.mostrarMenuCliente();
        menu.mostrarMenuAsistente();
        menu.mostrarMenuGerente();
    }
    
}
