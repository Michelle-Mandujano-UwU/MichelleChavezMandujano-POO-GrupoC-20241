package usuarios;
import utils.Rol;
import java.time.LocalDate;
import java.util.ArrayList;
import libreria.Libreria;

public class Cliente extends Usuario {
     private LocalDate fechaRegistro; 
    //retorna la fecha actual
    
    public Cliente (String nombre, String apellido, String telefono, String nombreUsuario, String contrasena) {
        super(nombre, apellido, telefono, Rol.CLIENTE, nombreUsuario, contrasena);
        this.fechaRegistro = LocalDate.now(); //accede a la fecha actual
    }

    
    @Override

    public String toString() { //Aqui  lo estamos sobreescribiendogracias al override y con eso el programa toma primero en cuenta la clase hija que el metodo de la clase padre
        return String.format("%s, Fecha de registro: %s", super.toString(), this.fechaRegistro);
    }
    
    public static void registrarCliente() {
        ArrayList<String> datosComun = Libreria.obtenerDatosComun(Rol.CLIENTE);
        
        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        String telefono = datosComun.get(2);
        String nombreUsuario = datosComun.get(3);
        String contraseña = datosComun.get(4);

        Cliente cliente = new Cliente(nombre, apellido, telefono, nombreUsuario, contraseña);

        if(!Libreria.usuarios.containsKey(Rol.CLIENTE)) {
            Libreria.usuarios.put(Rol.CLIENTE, new ArrayList<Usuario>());   //eso va a entrar cuando la llave no exista, o sea que el hash este vacio, si no lo va a ignorar (en la segunda vez)
        }

        Libreria.usuarios.get(Rol.CLIENTE).add(cliente); 
        System.out.println("\nCliente registrado exitosamente");
    }


    public static void mostrarClientes() {
        if (!Libreria.usuarios.containsKey(Rol.CLIENTE) || Libreria.usuarios.get(Rol.CLIENTE).isEmpty()) {
            System.out.println("\nNo hay clientes registrados aún");
        }
        else {
            System.out.println("\nClientes en la biblioteca");
            int x = 1;
            for(Usuario usuario : Libreria.usuarios.get(Rol.CLIENTE)) { //iterando hashmap cliente
                Cliente cliente = (Cliente) usuario;         //parseo
                System.out.println("\n---- Cliente " + x + " ----\n");
                System.out.println(cliente.toString());
                x++;
            }
        }
    }
}
