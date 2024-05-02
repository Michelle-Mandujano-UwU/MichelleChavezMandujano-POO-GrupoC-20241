package usuarios;
import utils.Rol;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import libreria.Libreria;
import utils.EmpleadoUtils;

public class Asistente extends Usuario implements EmpleadoUtils {
     private LocalDate fechaInicio;
    private double sueldo;
    private String rfc;

    public Asistente(String nombre, String apellido, String telefono, double sueldo, String rfc, String nombreUsuario, String contrasena) {
        super(nombre, apellido, telefono, Rol.ASISTENTE, nombreUsuario, contrasena);
        this.fechaInicio = LocalDate.now();
        this.sueldo = sueldo;
        this.rfc = rfc;
    }
    
    @Override

    public String toString() { //Aqui  lo estamos sobreescribiendo gracias al override y con eso el programa toma primero en cuenta la clase hija que el metodo de la clase padre
        return String.format("%s, Fecha de inicio: %s, Sueldo: %.2f, RFC: %s",
         super.toString(), this.fechaInicio, this.sueldo, this.rfc) + ", Fecha de inicio: " + this.fechaInicio;
    }


    public static void registrarAsistente() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> datosComun = Libreria.obtenerDatosComun(Rol.ASISTENTE);

        String nombre = datosComun.get(0);
        String apellido = datosComun.get(1);
        String telefono = datosComun.get(2);
        String nombreUsuario = datosComun.get(3);
        String contraseña = datosComun.get(4);
        System.out.print("RFC: ");
        String rfc = scanner.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = scanner.nextDouble();
        

        Asistente asistente = new Asistente(nombre, apellido, telefono, sueldo, rfc.toUpperCase(), nombreUsuario, contraseña);
        
        if(!Libreria.usuarios.containsKey(Rol.ASISTENTE)) {
            Libreria.usuarios.put(Rol.ASISTENTE, new ArrayList<Usuario>());   //eso va a entrar cuando la llave no exista, o sea que el hash este vacio, si no lo va a ignorar (en la segunda vez)
        }

        Libreria.usuarios.get(Rol.ASISTENTE).add(asistente); //aqui lo agrega en un lista especifica de clientes
        System.out.println("\nRegistro exitoso");
    }


    public static void mostrarAsistentes() {
        if(!Libreria.usuarios.containsKey(Rol.ASISTENTE) || Libreria.usuarios.get(Rol.ASISTENTE).isEmpty()) {
            System.out.println("\nNo hay asistentes registrados aún");
        }
        else {
            System.out.println("\nAsistentes en la biblioteca");
            int x = 1;
            for(Usuario usuario : Libreria.usuarios.get(Rol.ASISTENTE)) {
                Asistente asistente = (Asistente) usuario;         //parseo
                System.out.println("\n---- Asistente " + x + " ----\n");
                System.out.println(asistente.toString());
                x++;
            }
        }
    }
     public void checarEntrada(){
       System.out.println("Checar entrada con huella");
   }
   public void checarSalida(){
       System.out.println("Checar entrada con huella");
   }
}
