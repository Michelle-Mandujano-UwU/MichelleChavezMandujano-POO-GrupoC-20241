package Users;
import Users.utils.constantes.Rol;

import java.util.ArrayList;
import tarjetas.TarjetasDebito;
import tarjetas.SolicitudTarjeta;
import tarjetas.*;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Cliente {

    private String nombre;
     private String apellidos;
    private String añoNacimiento;
    private String ciudad;
    private String estado;
    private String rfc;
    private String curp;
    private String direccion;
    private String username;
    private String contraseña;
    private String sucursalRegistro;
    private String fechaRegistroStr;
    public ArrayList<SolicitudTarjeta> solicitudesTarjeta = new ArrayList<SolicitudTarjeta>();
    private TarjetasDebito tarjetaDebito;

     public Cliente(String nombre,String apellidos, String añoNacimiento, String ciudad, String estado, String curp, String rfc,
                String fechaRegistroStr, String contraseña, String sucursalRegistro,String direccion,String username) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.añoNacimiento = añoNacimiento;
        this.curp = curp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.rfc = rfc;
        this.contraseña= contraseña;
        this.direccion = direccion;
        this.fechaRegistroStr = fechaRegistroStr;
        this.sucursalRegistro = sucursalRegistro;
        this.username = username;
        int longitud = 17;
        String cadenaAleatoria = "";

        Random random = new Random(); 
        for (int i = 0; i < longitud; i++) {
            int numeroAleatorio = random.nextInt(10); 
            cadenaAleatoria += String.valueOf(numeroAleatorio); 
        }

        
        this.tarjetaDebito = new TarjetasDebito(cadenaAleatoria, "123", 0.0, "447", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

     
     
     
     
     
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFechaRegistroStr() {
        return fechaRegistroStr;
    }

    public void setFechaRegistroStr(String fechaRegistroStr) {
        this.fechaRegistroStr = fechaRegistroStr;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(String añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSucursalRegistro() {
        return sucursalRegistro;
    }

    public void setSucursalRegistro(String sucursalRegistro) {
        this.sucursalRegistro = sucursalRegistro;
    }
    
    


    public String toString() {
        return "Cliente{Nombre=" + nombre
                + "apellidos='" + apellidos + '\''
                + ", añoNacimiento=" + añoNacimiento
                + ", ciudad='" + ciudad + '\''
                + ", estado='" + estado + '\'' + ", curp='" + curp + '\''
                + ", rfc='" + rfc + '\''
                + ", Fecha de registro='" + fechaRegistroStr + '\'' + ", contraseña='" + contraseña + '\''
                + ", sucursalRegistro='" + sucursalRegistro + '\'' + ", direccion='" + direccion + '\''
                + +'}';
    }
   

    /*public Cliente(String nombre, String apellido, String domicilio, String nacimiento, String rfc, String curp, String user, String password, String fechaRegistroStr, int id) {
        super(nombre, apellido, domicilio, nacimiento, rfc, curp, user, password, Rol.CLIENTE);
        this.fechaRegistroStr = fechaRegistroStr;
        this.id = id;
    }

    public Cliente(int id, String nombre, String apellido, String domicilio, String nacimiento, String rfc, String curp, String user, String password, String fechaRegistroStr) {
        super(nombre, apellido, domicilio, nacimiento, rfc, curp, user, password, Rol.CLIENTE);
        this.fechaRegistroStr = fechaRegistroStr;
        this.id = id;
    }
*/

    public TarjetasDebito getTarjetaDebito() {
        return tarjetaDebito;
    }
    
    
    
    public void setTarjetaDebito(TarjetasDebito tarjetaDebito) {
        this.tarjetaDebito = tarjetaDebito;
    }

    public void solicitarTarjetaDeCredito(TipoTarjeta tipoTarjeta) {
        if (tarjetaDebito != null) {
            double saldoActual = tarjetaDebito.getCantidad();
            double saldoMinimoRequerido = 0;

            switch (tipoTarjeta) {
                case SIMPLICITY:
                    saldoMinimoRequerido = 50000;
                    break;
                case PLATINO:
                    saldoMinimoRequerido = 100000;
                    break;
                case ORO:
                    saldoMinimoRequerido = 200000;
                    break;
            }

            if (saldoActual >= saldoMinimoRequerido) {
                SolicitudTarjeta solicitud = new SolicitudTarjeta(this, tipoTarjeta,0);
                solicitudesTarjeta.add(solicitud);
                System.out.println("Solicitud de tarjeta de crédito " + tipoTarjeta + " enviada correctamente.");
                solicitud.setEstado(EstadoSolicitud.EN_PROCESO);
            } else {
                System.out.println("No tienes suficiente saldo para solicitar una tarjeta de crédito " + tipoTarjeta + ".");
            }
        } else {
            System.out.println("Debes tener una tarjeta de débito para solicitar una tarjeta de crédito.");
        }
    }

    /*public String getEstadoString() {
        switch (estado) {
            case EN_PROCESO:
                return "En proceso";
            case APROBADA:
                return "Aprobada";
            case RECHAZADA:
                return "Rechazada";
            default:
                return "Estado desconocido";
        }
    }*/
}

//package Users;
//
//import Users.utils.constantes.Rol;
//import tarjetas.SolicitudTarjeta;
//import tarjetas.TarjetasDebito;
//import tarjetas.TipoTarjeta;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.ArrayList;
//
//public class Cliente extends Usuario {
//    private final String fechaRegistroStr;
//    private int id;
//    private ArrayList<SolicitudTarjeta> solicitudesTarjeta = new ArrayList<>();
//    private TarjetasDebito tarjetaDebito;
//
//    String nombre;
//    String apellido;
//
//
//
//    public Cliente(String nombre, String apellido, String domicilio, String nacimiento, String rfc, String curp, String user, String password, String fechaRegistroStr, int id) {
//        super(nombre, apellido, domicilio, nacimiento, rfc, curp, user, password, Rol.CLIENTE);
//        this.fechaRegistroStr = fechaRegistroStr;
//        this.id = id;
//    }
//
//    public Cliente(int id, String nombre, String apellido, String domicilio, String nacimiento, String rfc, String curp, String user, String password, String fechaRegistroStr) {
//        super(nombre, apellido, domicilio, nacimiento, rfc, curp, user, password, Rol.CLIENTE);
//        this.fechaRegistroStr = fechaRegistroStr;
//        this.id = id;
//    }
//
//    public void setTarjetaDebito(TarjetasDebito tarjetaDebito) {
//        this.tarjetaDebito = tarjetaDebito;
//    }
//
//    public void solicitarTarjetaDeCredito(TipoTarjeta tipoTarjeta) {
//        if (tarjetaDebito != null) {
//            double saldoActual = tarjetaDebito.getCantidad();
//            double saldoMinimoRequerido = 0;
//
//            switch (tipoTarjeta) {
//                case SIMPLICITY:
//                    saldoMinimoRequerido = 50000;
//                    break;
//                case PLATINO:
//                    saldoMinimoRequerido = 100000;
//                    break;
//                case ORO:
//                    saldoMinimoRequerido = 200000;
//                    break;
//            }
//
//            if (saldoActual >= saldoMinimoRequerido) {
//                SolicitudTarjeta solicitud = new SolicitudTarjeta(this, tipoTarjeta);
//                solicitudesTarjeta.add(solicitud);
//                System.out.println("Solicitud de tarjeta de crédito " + tipoTarjeta + " enviada correctamente.");
//            } else {
//                System.out.println("No tienes suficiente saldo para solicitar una tarjeta de crédito " + tipoTarjeta + ".");
//            }
//        } else {
//            System.out.println("Debes tener una tarjeta de débito para solicitar una tarjeta de crédito.");
//        }
//    }
//
//
//    public ArrayList<SolicitudTarjeta> getSolicitudesTarjeta() {
//        return solicitudesTarjeta;
//    }
//
//
//}

//public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getContraseña() {
//        return contraseña;
//    }
//
//    public void setContraseña(String contraseña) {
//        this.contraseña = contraseña;
//    }
//
//    public String getFechaRegistroStr() {
//        return fechaRegistroStr;
//    }
//
//    public void setFechaRegistroStr(String fechaRegistroStr) {
//        this.fechaRegistroStr = fechaRegistroStr;
//    }
//
//    public String getApellidos() {
//        return apellidos;
//    }
//
//    public void setApellidos(String apellidos) {
//        this.apellidos = apellidos;
//    }
//
//    public int getAñoNacimiento() {
//        return añoNacimiento;
//    }
//
//    public void setAñoNacimiento(int añoNacimiento) {
//        this.añoNacimiento = añoNacimiento;
//    }
//
//    public String getCiudad() {
//        return ciudad;
//    }
//
//    public void setCiudad(String ciudad) {
//        this.ciudad = ciudad;
//    }
//
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
//
//    public String getRfc() {
//        return rfc;
//    }
//
//    public void setRfc(String rfc) {
//        this.rfc = rfc;
//    }
//
//    public String getCurp() {
//        return curp;
//    }
//
//    public void setCurp(String curp) {
//        this.curp = curp;
//    }
//
//    public String getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(String direccion) {
//        this.direccion = direccion;
//    }
//
//    public String getSucursalRegistro() {
//        return sucursalRegistro;
//    }
//
//    public void setSucursalRegistro(String sucursalRegistro) {
//        this.sucursalRegistro = sucursalRegistro;
//    }
//
//    public void generarRFC() {
//
//    }
//    public String toString() {
//        return "Cliente{" +
//                "apellidos='" + apellidos + '\'' +
//                ", añoNacimiento=" + añoNacimiento +
//                ", ciudad='" + ciudad + '\'' +
//                ", estado='" + estado + '\'' +
//                ", rfc='" + rfc + '\'' +
//                ", curp='" + curp + '\'' +
//                ", direccion='" + direccion + '\'' +
//                ", sucursalRegistro='" + sucursalRegistro + '\'' +
//
//                ", contraseña='" + contraseña + '\'' +
//                '}';
//    }
//}
