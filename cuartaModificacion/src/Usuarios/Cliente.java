package Usuarios;
import java.time.LocalDate;
import java.util.ArrayList;
import Libreria.Libreria;
import Usuarios.utils.Rol;

public class Cliente extends Usuario {

    private LocalDate fechaRegistro;

    public Cliente(String nombre, String apellido, String telefono, String nombreUsuario, String contrasena, String fechaNacimiento) {
        super(nombre, apellido, telefono, Rol.CLIENTE, nombreUsuario, contrasena, fechaNacimiento);
        this.fechaRegistro = LocalDate.now();
    }

    @Override

    public String toString() {
        return String.format("%s \nFecha de registro: %s", super.toString(), this.fechaRegistro);
    }

    public static void registrarCliente() {
        ArrayList<String> datosComun = Libreria.obtenerDatosComun(Rol.CLIENTE);

        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        String telefono = datosComun.get(2);
        String nombreUsuario = datosComun.get(3);
        String contraseña = datosComun.get(4);
        String fechaNacimiento = datosComun.get(5);

        Cliente cliente = new Cliente(nombre, apellido, telefono, nombreUsuario, contraseña, fechaNacimiento);

        if (!Libreria.usuarios.containsKey(Rol.CLIENTE)) {
            Libreria.usuarios.put(Rol.CLIENTE, new ArrayList<Usuario>());
        }

        Libreria.usuarios.get(Rol.CLIENTE).add(cliente);
        System.out.println("\nCliente registrado exitosamente");
    }

    public static void mostrarClientes() {
        if (!Libreria.usuarios.containsKey(Rol.CLIENTE) || Libreria.usuarios.get(Rol.CLIENTE).isEmpty()) {
            System.out.println("\nNo hay clientes registrados aún");
        } else {
            System.out.println("\nClientes en la biblioteca");
            int x = 1;
            for (Usuario usuario : Libreria.usuarios.get(Rol.CLIENTE)) {
                Cliente cliente = (Cliente) usuario;
                System.out.println("\n---- Cliente " + x + " ----\n");
                System.out.println(cliente.toString());
                x++;
            }
        }
    }
}
