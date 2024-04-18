package usuarios;
import utils.Rol;
import java.time.LocalDate;

public class Cliente extends Usuario {
     private LocalDate fechaRegistro; //Este tipo de datos manejan fechas (local)
    //retorna la fecha de hoy 2024-04-09
    
    public Cliente (String nombre, String apellido, String telefono, String nombreUsuario, String contrasena) {
        super(nombre, apellido, telefono, Rol.CLIENTE, nombreUsuario, contrasena);
        this.fechaRegistro = LocalDate.now(); //accede a la fecha actual
    }

    //toString() obtiene la direccion de memoria
    @Override//existe en la clase padre, pero la estamos sobreescribiendo en la clase hija

    public String toString() { //Aqui  lo estamos sobreescribiendogracias al override y con eso el programa toma primero en cuenta la clase hija que el metodo de la clase padre
        return String.format("%s, Fecha de registro: %s", super.toString(), this.fechaRegistro);
    }
    //tambien podemos acer esto con get
//    @Override //Indica que se está sobre escribiendo un método
                
}
