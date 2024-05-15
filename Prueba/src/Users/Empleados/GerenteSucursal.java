package Users.Empleados;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Users.utils.constantes.Rol;
import Sucursales.Sucursal;
import tarjetas.*;


public class GerenteSucursal extends Empleado {
    private ArrayList<SolicitudTarjeta> solicitudesTarjeta = new ArrayList<SolicitudTarjeta>();

    private static int contadorIdEmpleado= 1;
//    public GerenteSucursal() {
//        super();
//    }

    public GerenteSucursal(int idEmpleado, String nombre, String apellidos, LocalDate fechaNacimiento,
                           String ciudad, String estado, String CURP, String direccion,  Sucursal sucursal,
                           double salario, Rol rol, LocalDate fechaInicioTrabajo, String contraseña, String nombreUsuario) {
        super(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion,
                sucursal, salario, Rol.GERENTE_SUCURSAL, fechaInicioTrabajo, contraseña, nombreUsuario);
        this.idEmpleado= idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos != null ? apellidos : "Sin apellidos"; // Asignar valor por defecto si es nulo
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.estado = estado;
        this.direccion = direccion;
        this.sucursal = sucursal;
        this.salario = salario;
        this.fechaInicioTrabajo = fechaInicioTrabajo;
        this.contraseña= contraseña;
        this.CURP= generarCurp();
        this.rfc= generarRFC();
    }


    public void autorizarSolicitud(SolicitudTarjeta solicitud, Empleado autorizador) {
        if (autorizador.getRol() == Rol.EJECUTIVO_CUENTA || autorizador.getRol() == Rol.GERENTE_SUCURSAL) {
            if (solicitud.getEstado() == EstadoSolicitud.EN_PROCESO) {
                System.out.println("Empleado: " + autorizador.getNombre() + " (" + autorizador.getRol() + ")");
                System.out.println("Solicitud de tarjeta: " + solicitud.getTipoTarjeta());

                System.out.print("¿Autorizar solicitud? (ingrese si o no): ");
                Scanner scanner = new Scanner(System.in);
                String respuesta = scanner.nextLine().toLowerCase();

                if (respuesta.equals("si")) {
                    solicitud.setEstado(EstadoSolicitud.APROBADA);
                    generarTarjeta(solicitud);
                    System.out.println("Solicitud autorizada correctamente.");
                } else {
                    solicitud.setEstado(EstadoSolicitud.RECHAZADA);
                    System.out.println("Solicitud rechazada.");
                }
            } else {
                System.out.println("La solicitud ya fue procesada.");
            }
        } else {
            System.out.println("Solo los ejecutivos de cuenta y gerentes pueden autorizar solicitudes.");
        }
    }


    public void generarTarjeta(SolicitudTarjeta solicitud) {
        switch (solicitud.getTipoTarjeta()) {
            case PLATINO:
                TarjetaPlatino.crearTarjetaPlatino();
                break;
            case ORO:
                TarjetaOro.crearTarjetaOro();
                break;
            case SIMPLICITY:
                TarjetaSimplicity.crearTarjetaSimplicity();
                break;
            default:
                System.out.println("Tipo de tarjeta no reconocido: " + solicitud.getTipoTarjeta());
        }
    }

//    private void generarTarjetaPlatino(SolicitudTarjeta solicitud) {
//        TarjetaPlatino tarjetaPlatino = new TarjetaPlatino(solicitud.getCliente());
//
//        solicitud.getCliente().setTarjetaPlatino(tarjetaPlatino);
//
//        System.out.println("Tarjeta Platino generada y asociada al cliente: " + solicitud.getCliente().getNombre());
//    }
//    private void generarTarjetaOro(SolicitudTarjeta solicitud) {
//        TarjetaOro tarjetaOro = new TarjetaOro(solicitud.getCliente());
//
//        solicitud.getCliente().setTarjetaOro(tarjetaOro);
//
//        System.out.println("Tarjeta Oro generada y asociada al cliente: " + solicitud.getCliente().getNombre());
//    }
//    private void generarTarjetaSimplicity(SolicitudTarjeta solicitud) {
//        TarjetaSimplicity tarjetaSimplicity  = new TarjetaSimplicity(solicitud.getCliente());
//
//        solicitud.getCliente().setTarjetaOro(tarjetaSimplicity );
//
//        System.out.println("Tarjeta Simplicity generada y asociada al cliente: " + solicitud.getCliente().getNombre());
//    }


    public static void mostrarSolicitudesPendientes(ArrayList<SolicitudTarjeta> solicitudes) {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes .");
        } else {
            System.out.println("Solicitudes pendientes:");
            for (SolicitudTarjeta solicitud : solicitudes) {
                System.out.println("Cliente: " + solicitud.getCliente().getNombre() + " (" + solicitud.getTipoTarjeta() + ")");
            }
        }
    }



    public void setRfc(String rfc) {
        this.rfc = generarRFC();
    }
    public void setCURP(String CURP) {
        this.CURP = generarCurp();
    }



    @Override
    public String toString() {
        return "GerenteSucursal{" +
                "numeroEmpleado=" + idEmpleado +
                ", " + super.toString() +
                '}';
    }
}

