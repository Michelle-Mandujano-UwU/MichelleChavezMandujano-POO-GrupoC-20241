package Sistemas;

import java.util.*;

import Users.*;
import Users.utils.UsuarioUtils;
import tarjetas.EstadoSolicitud;
import tarjetas.SolicitudTarjeta;
import tarjetas.TarjetasDebito;
import tarjetas.TarjetaSimplicity;
import tarjetas.TarjetaPlatino;
import tarjetas.TarjetaOro;
import utils.UsuarioEnSesion;
import Users.Empleados.*;
import Users.Empleados.GerenteSucursal;
import java.time.*;
import Sucursales.*;
import Users.utils.constantes.Rol;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import tarjetas.TipoTarjeta;

public class Menus {

    TarjetasDebito tarjetaDebito = null;
    TarjetaSimplicity tarjetaSimplicity = null;
    TarjetaPlatino tarjetaPlatino = null;
    TarjetaOro tarjetaOro = null;
    Scanner scanner = new Scanner(System.in);
    static Empleado usuarioActual;
    String salir = "6";
    static int sucur;

    public static void inicioSesion() {
        Scanner scanner = new Scanner(System.in);
        boolean datosIncorrectos = false;

        do {
            //El Gerente de madero es b y la contraseña es a
            System.out.println("------------------------- BIENVENIDO AL SISTEMA DEL BANCO -------------------------");
            System.out.println("Ingrese la Sucural a la Que Desea Ingresar");
            System.out.println("1.Madero");
            System.out.println("2.Acueducto");
            System.out.println("3, Cerrar Programa");

            String input = scanner.nextLine();

            try {
                sucur = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                datosIncorrectos = true;
                continue;
            }

            if (sucur == 3) {
                break;
            } else if (sucur != 1 & sucur != 2 & sucur != 3) {
                System.out.println("Ingresa Una Opción Valida");
                datosIncorrectos = true;
                continue;
            }

            while (true) {
                if (sucur == 1) {

                    System.out.println("BIENVENIDO A LA SUCURSAL MADERO\n");
                    System.out.println("Ingresa tu Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.println("Ingresa tu Contraseña: ");
                    String contrasena = scanner.nextLine();
                    usuarioActual = verificarInicioSesionMadero(usuario, contrasena);

                    if (usuarioActual != null) {
                        datosIncorrectos = false;
                        UsuarioEnSesion.obtenerInstancia().setUsuarioActual(usuarioActual);
                        seleccionarMenu();
                        break;
                    } else {

                        //VERIFICAR SI ES UN CLIENTE...
                        
                        Cliente c = verificarInicioSesionMaderoCliente(usuario, contrasena);
                        
                        if(c != null){
                            
                            Menus m = new Menus();
                            m.mostrarMenuCliente(c);
                            break;
                        }else{
                            System.out.println("\nUsuario o Contraseña Incorrectos");
                            datosIncorrectos = true;
                        }
                        
                        //--------------------------------------

                        /*System.out.println("\nUsuario o Contraseña Incorrectos");
                            datosIncorrectos = true;*/
                            
                    }

                } else if (sucur == 2) {

                    System.out.println("BIENVENIDO A LA SUCURSAL ACUEDUCTO\n");
                    System.out.println("Ingresa tu Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.println("Ingresa tu Contraseña: ");
                    String contrasena = scanner.nextLine();
                    usuarioActual = verificarInicioSesionMadero(usuario, contrasena);

                    if (usuarioActual != null) {
                        datosIncorrectos = false;
                        UsuarioEnSesion.obtenerInstancia().setUsuarioActual(usuarioActual);
                        seleccionarMenu();
                        break;
                    } else {
                        System.out.println("\nUsuario o Contraseña Incorrectos.");
                        datosIncorrectos = true;
                    }

                }
            }

        } while (datosIncorrectos);
    }

    private static Cliente verificarInicioSesionMaderoCliente(String user, String contrasena) {
        System.out.println("VERIFICANDO INICIO DE SESION PARA USUARIOS "+user+" - "+contrasena);
        System.out.println("NUMERO DE CLIENTES "+SucursalMadero.getClientes().size());
        
        for (Cliente cliente : SucursalMadero.getClientes()) {
            if (cliente.getUsername().equals(user) && cliente.getContraseña().equals(contrasena)) {
                return cliente;
            }
        }

        return null;
    }

    private static Empleado verificarInicioSesionMadero(String user, String contrasena) {

        for (Map.Entry<Rol, ArrayList<Empleado>> entry : SucursalMadero.getEmpleados().entrySet()) {
            ArrayList<Empleado> usuarioList = entry.getValue();
            for (Empleado empleado : usuarioList) {
                if (empleado.getNombreUsuario().equals(user) && empleado.getContraseña().equals(contrasena)) {
                    return empleado;
                }
            }
        }

        return null;
    }

    private Empleado verificarInicioSesionAcueducto(String user, String contrasena) {
        for (Map.Entry<Rol, ArrayList<Empleado>> entry : SucursalAcueducto.getEmpleados().entrySet()) {
            ArrayList<Empleado> usuarioList = entry.getValue();
            for (Empleado empleado : usuarioList) {
                if (empleado.getNombreUsuario().equals(user) && empleado.getContraseña().equals(contrasena)) {
                    return empleado;
                }
            }
        }
        return null;
    }

    private static void seleccionarMenu() {
        Empleado usuario = UsuarioEnSesion.obtenerInstancia().getUsuarioActual();
        if (sucur == 1) {
            switch (usuario.getRol()) {
                case GERENTE_SUCURSAL:
                    menuGerenteMadero();
                    break;
                case INVERSIONISTA:
                    mostrarMenuInversionista(usuario.getNombreUsuario());
                    break;
                case CAPTURISTA:
                    menuCapturistaMadero();
                    break;
                case EJECUTIVO_CUENTA:
                    menuEjecutivoDeCuentaMadero();
                    break;
            }
        } else {
            switch (usuario.getRol()) {
                case GERENTE_SUCURSAL:
                    menuGerenteAcueducto();
                    break;
                case INVERSIONISTA:
                    mostrarMenuInversionista(usuario.getNombreUsuario());
                    break;
                case CAPTURISTA:
                    menuCapturistaAcueducto();
                    break;
                case EJECUTIVO_CUENTA:
                    menuEjecutivoDeCuentaAcueducto();
                    break;
            }
        }

    }

    public static void mostrarMenuInversionista(String nombreUsuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------INVERSIONISTA----------------");
        System.out.println("1. Proveer fondos al banco");
        System.out.println("2. Remover fondos del banco");
        System.out.println("3. Salir");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                //Inversionista.proveerFondos()
                break;
            case 2:
                //Inversionista.removerFondos()
                break;
        }
    }

    public void mostrarMenuCliente(Cliente cliente) {
        int opcion = 0;
        do {
            System.out.println("QUE DESEA HACER?");
            System.out.println("1. Ver tarjetas.Tarjetas");
            System.out.println("2. Agregar o Retirar Dinero");
            System.out.println("3. Solicitar Tarjeta de Credito");
            System.out.println("4. Historial de solicitudes");
            System.out.println("5. Cerrar Sesion");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (cliente.getTarjetaDebito() != null) {
                        System.out.println("--------- TARJETA DE DEBITO ---------");
                        System.out.println(cliente.getTarjetaDebito().toString());
                        
                         System.out.println("--------- TARJETA DE CREDITO ---------");

                    }
                    break;
                case 2:
                    if (cliente.getTarjetaDebito() != null) {
                        System.out.println("1. Agregar dinero");
                        System.out.println("2. Retirar dinero");
                        int opcionDinero = scanner.nextInt();
                        String tarjeta;

                        switch (opcionDinero) {
                            case 1:
                                System.out.println("Ingrese la cantidad a agregar");
                                double dinero = scanner.nextDouble();
                                cliente.getTarjetaDebito().agregarDinero(dinero);
                                System.out.println("Se ha agregado un total de $"+dinero+" pesos a su tarjeta de debito");
                                /*System.out.println("Ingrese la Tarjeta a la que Desea Retirarle Dinero");
                                System.out.println("1. Debito 2. Simplicity 3. Platino 4. Oro");
                                tarjeta = scanner.nextLine();
                                
                                switch (tarjeta) {
                                    case "1":
                                        System.out.println("Ingrese la cantidad a agregar:");
                                        double cantidadAgregar = scanner.nextDouble();
                                        tarjetaDebito.agregarDinero(cantidadAgregar);
                                        break;
                                    case "2":
                                        System.out.println("Ingrese la cantidad a agregar:");
                                        double cantidadAgregarS = scanner.nextDouble();
                                        tarjetaSimplicity.agregarDinero(cantidadAgregarS);
                                        break;
                                    case "3":
                                        System.out.println("Ingrese la cantidad a agregar:");
                                        double cantidadAgregarP = scanner.nextDouble();
                                        tarjetaPlatino.agregarDinero(cantidadAgregarP);
                                        break;
                                    case "4":
                                        System.out.println("Ingrese la cantidad a agregar:");
                                        double cantidadAgregarO = scanner.nextDouble();
                                        tarjetaOro.agregarDinero(cantidadAgregarO);
                                        break;
                                }*/
                                
                             break;
                            case 2:
                                System.out.println("Ingrese la Tarjeta a la que Desea Retirarle Dinero");
                                System.out.println("1. Debito 2. Simplicity 3. Platino 4. Oro");
                                tarjeta = scanner.nextLine();
                             break;

                        }
                    } else {
                        System.out.println("No se ha creado ninguna tarjeta de débito");
                    }
                    break;
                case 3:
                    
                    //VERIFICAR SI YA HAY UNA SOLICITUD REGISTRADA DEL USUARIO
                    
                     List<SolicitudTarjeta> solicitudesCliente =
                            SucursalMadero.getSolicitudesTarjeta().stream().
                                    filter(x -> x.getCliente() == cliente && x.getEstado() == EstadoSolicitud.EN_PROCESO).collect(Collectors.toList());
                    
                    if ((cliente.getTarjetaDebito().getCantidad() >= 50000 || 
                            cliente.getTarjetaDebito().getCantidad() >= 100000 || 
                            cliente.getTarjetaDebito().getCantidad() >= 200000) && solicitudesCliente.size() == 0 ) {
                        
                        if (cliente.getTarjetaDebito().getCantidad() <= 100000) {
                            
                            System.out.println("Puedes solicitar una tarjeta Simplicity ");
                            System.out.println("1. Solicitar Simplicity 2. Rechazar");
                            int opcionSolicitar = scanner.nextInt();
                            switch (opcionSolicitar) {
                                case 1:

                                    SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.SIMPLICITY, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                 
                                    System.out.println("Solicitud de tarjeta creada, espere a que un agente lo apruebe...");
                                    
                                    break;
                                case 2:
                                    
                                    break;
                            }
                        } else if (cliente.getTarjetaDebito().getCantidad() <= 200000) {
                            
                            System.out.println("Puedes solicitar alguna de las siguientes tarjetas: Simplicity o Platino ");
                            System.out.println("1. Solicitar Simplicity 2. Solicitar Platino 3. Rechazar");
                            int opcionSolicitar = scanner.nextInt();
                            switch (opcionSolicitar) {
                                case 1:
                                       SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.SIMPLICITY, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                 
                                    System.out.println("Solicitud de tarjeta creada, espere a que un agente lo apruebe...");
                                    
                                    break;
                                case 2:
                                    SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.PLATINO, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                    break;
                                case 3:
                                    break;
                            }
                        } else {
                            
                            System.out.println("Puedes solicitar alguna de las siguientes tarjetas: Simplicity, Platino u Oro");
                            System.out.println("1. Solicitar Simplicity 2. Solicitar Platino 3. Solicitar Oro 4.Rechazar");
                            int opcionSolicitar = scanner.nextInt();
                            switch (opcionSolicitar) {
                                case 1:

                                    SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.SIMPLICITY, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                 
                                    System.out.println("Solicitud de tarjeta creada, espere a que un agente lo apruebe...");
                                    
                                    break;
                                case 2:

                                    SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.PLATINO, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                 
                                    System.out.println("Solicitud de tarjeta creada, espere a que un agente lo apruebe...");
                                    
                                    break;
                                case 3:
                                    SucursalMadero.addSolicitudTarjeta(
                                            new SolicitudTarjeta(cliente, TipoTarjeta.ORO, LocalDateTime.now(), EstadoSolicitud.EN_PROCESO,
                                                    SucursalMadero.getSolicitudesTarjeta().size()));
                                 
                                    System.out.println("Solicitud de tarjeta creada, espere a que un agente lo apruebe...");
                                    break;
                                case 4:
                                    break;
                            }
                        }
                        
                    } else {
                        System.out.println("Por el momento no puedes solicitar una tarjeta de credito");
                    }
                    
                    break;
                case 4:
                        viewSolicitudesOfClient(cliente);
                    break;
                case 5:
                    salir = "5";
                    UsuarioEnSesion.obtenerInstancia().cerrarSesion();
                    inicioSesion();
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 5);
        System.out.println("\nSesión cerrada");
    }

    public void clienteTarjetas() {
        if (tarjetaDebito != null) {
            System.out.println("--------- TARJETA DE DEBITO ---------");
            System.out.println(tarjetaDebito.toString());
        } else {

        }
    }

    ///////////////////////////MENU EMPLEADOS//////////////////////////////////
    ///////////////////////////MENU EMPLEADOS//////////////////////////////////
    private static int contadorIdEmpleado = 1;
    public static final List<EjecutivoDeCuenta> ejecutivosMadero = new ArrayList<>();
    public static final List<EjecutivoDeCuenta> ejecutivosAcueducto = new ArrayList<>();
    public static final List<GerenteSucursal> gerentesMadero = new ArrayList<>();
    public static final List<GerenteSucursal> gerentesAcueducto = new ArrayList<>();
    public static final List<Capturista> capturistasMadero = new ArrayList<>();
    public static final List<Capturista> capturistasAcueducto = new ArrayList<>();
    public static GerenteSucursal gerenteMadero;
    public static GerenteSucursal gerenteAcueducto;
    static Scanner scaner = new Scanner(System.in);

    public static void mostrarMenuEmpleado() {
        //////////////////ASIGNAR GERENTE POR DEFAULT EN SUCURSAL MADERO

        int idGerenteMadero = 1;
        String nombreGerenteMadero = "Diana";
        String apellidosGerenteMadero = "Campos Rosas";
        LocalDate fechaNacimientoGerenteMadero = LocalDate.of(2005, 10, 24);
        String ciudadGerenteMadero = "Morelia";
        String estadoGerenteMadero = "Michoacán";
        String curpGerenteMadero = "CARD051024MMNMSNA5";
        String direccionGerenteMadero = "Calle Morelos #456";
        Sucursal sucursalGerenteMadero = new SucursalMadero("Madero", "Av. Madero #123");
        double salarioGerenteMadero = 50000.00;
        Rol rolGerenteMadero = Rol.GERENTE_SUCURSAL;
        LocalDate fechaInicioTrabajoGerenteMadero = LocalDate.of(2020, 01, 01);
        String contraseñaGerenteMadero = "gerente madero";
        String nombreUsuarioGerenteMadero = "diana_campos";

        gerenteMadero = new GerenteSucursal(idGerenteMadero, nombreGerenteMadero, apellidosGerenteMadero, fechaNacimientoGerenteMadero, ciudadGerenteMadero, estadoGerenteMadero, curpGerenteMadero, direccionGerenteMadero, sucursalGerenteMadero,
                salarioGerenteMadero, rolGerenteMadero, fechaInicioTrabajoGerenteMadero, contraseñaGerenteMadero, nombreUsuarioGerenteMadero);

        SucursalMadero sucursalMadero = new SucursalMadero("Madero", "Av. Madero #123", gerenteMadero);
        sucursalMadero.setGerenteSucursal(gerenteMadero);
        Map<String, Sucursal> sucursales = new HashMap<>();
        sucursales.put("Madero", sucursalMadero);
//        System.out.println(sucursalMadero.toString());
//        System.out.println(gerenteMadero.toString());

//////////////// ASIGNAR GERENTE POR DEFAULT EN SUCURSAL ACUEDUCTO
        int idGerenteAcueducto = 2;
        String nombreGerenteAcueducto = "Michelle";
        String apellidosGerenteAcueducto = "Mandujano Chávez";
        LocalDate fechaNacimientoGerenteAcueducto = LocalDate.of(2003, 12, 21);
        String ciudadGerenteAcueducto = "Morelia";
        String estadoGerenteAcueducto = "Michoacán";
        String curpGerenteAcueducto = "CAMA001221MGTHNBA1";
        String direccionGerenteAcueducto = "Calle Rio mayo #456";
        Sucursal sucursalGerenteAcueducto = new SucursalAcueducto("Acueducto", "Av. Acueducto #220");
        double salarioGerenteAcueducto = 50000.00;
        Rol rolGerenteAcueducto = Rol.GERENTE_SUCURSAL;
        LocalDate fechaInicioTrabajoGerenteAcueducto = LocalDate.of(2020, 01, 01);
        String contraseñaGerenteAcueducto = "gerente acueducto";
        String nombreUsuarioGerenteAcueducto = "michelle_chavez";

        gerenteAcueducto = new GerenteSucursal(idGerenteAcueducto, nombreGerenteAcueducto, apellidosGerenteAcueducto, fechaNacimientoGerenteAcueducto, ciudadGerenteAcueducto, estadoGerenteAcueducto, curpGerenteAcueducto, direccionGerenteAcueducto, sucursalGerenteAcueducto,
                salarioGerenteAcueducto, rolGerenteAcueducto, fechaInicioTrabajoGerenteAcueducto, contraseñaGerenteAcueducto, nombreUsuarioGerenteAcueducto);

        SucursalAcueducto sucursalAcueducto = new SucursalAcueducto("Acueducto", "Av. Acueducto #220", gerenteAcueducto);
        sucursales.put("Acueducto", sucursalAcueducto);

//        System.out.println(sucursalAcueducto.toString());
        /////////////////////////////////////////
        Scanner leer1 = new Scanner(System.in);
        int opcionSucursal;
        do {
            System.out.println("\n**Bienvenido al sistema de registro de empleados**");
            System.out.println("\nSeleccione la sucursal a la que desea entrar:");
            System.out.println("1. Sucursal Madero");
            System.out.println("2. Sucursal Acueducto");
            System.out.print("Opción: ");

            opcionSucursal = leer1.nextInt();
            leer1.nextLine();
        } while (opcionSucursal < 1 || opcionSucursal > 2);

        int opcionMenu;
        switch (opcionSucursal) {
            case 1: //Sucursal Madero
                System.out.println(sucursalMadero.toString());

                do {
                    System.out.println("¿A qué menu desea acceder?(se requiere la contraseña)\n1. Gerente"
                            + "\n2. Capturista\n3. Ejecutivos de cuenta");
                    opcionMenu = scaner.nextInt();
                    switch (opcionMenu) {
                        case 1://Menú gernete
                            menuGerenteMadero();
                            break;
                        case 2: //menu capturistas
                            if (capturistasMadero.isEmpty()) {
                                registrarCapturistaMadero(); // Solicitar registro de capturista
                            } else {
                                menuCapturistaMadero();//Línea 145
                            }
                            break;
                        case 3: //Menu ejecutivos de cuenta
                            menuEjecutivoDeCuentaMadero();
                            break;
                    }
                } while (opcionMenu != 4);
            case 2://Sucursal acueducto
                System.out.println(sucursalAcueducto.toString());

                do {
                    System.out.println("¿A qué menu desea acceder?(se requiere la contraseña)\n1. Gerente"
                            + "\n2. Capturista\n3. Ejecutivos de cuenta");
                    opcionMenu = scaner.nextInt();
                    switch (opcionMenu) {
                        case 1:
                            menuGerenteAcueducto();
                            break;
                        case 2:
                            if (capturistasAcueducto.isEmpty()) {
                                registrarCapturistaAcueducto();
                            } else {
                                menuCapturistaAcueducto();
                            }
                            break;
                        case 3:
                            menuEjecutivoDeCuentaAcueducto();
                            break;
                    }
                } while (opcionMenu != 4);
            default:
                System.out.println("Opción no válida.");
        }

        leer1.close();
    }

    public static void menuGerenteMadero() {
        Scanner scan = new Scanner(System.in);
        AltaCliente s = new AltaCliente();
        int opcion;
        do {
            System.out.println("¿Qué desea hacer? ");
            System.out.println("1. Registrar ejecutivo de cuenta\n2. Modificar ejecutivo de cuenta"
                    + "\n3.Eliminar ejecutivo de cuenta\n4. Mostrar ejecutivos de cuenta registrados en la sucursal Madero"
                    + "\n5. Registrar capturista\n6. Modificar capturista\n7.Eliminar capturista\n8.Mostrar capturistas registrados\n9. Registrar cliente"
                    + "\n10.Modificar cliente\n11.Eliminar cliente\n12. Mostrar clientes registrados"
                    + "\n13. Autorizar tarjeta de crédito\n14. Registrar inversionista\n15. Modificar inversionista"
                    + "\n16. Eliminar inversionista\n17. Salir");
            opcion = scan.nextInt();
            switch (opcion) {
                case 1:
                    registrarEjecutivoDeCuentaMadero();
                    break;
                case 2:
                    scan.nextLine();
                    System.out.print("Ingrese el nombre de usuario del ejecutivo de cuenta que desea modificar: ");
                    String nombreUsuario1 = scan.nextLine();
                    modificarEjecutivosMadero(nombreUsuario1);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre de usuario del ejecutivo de cuenta que desea eliminar: ");
                    String nombreUsuario2 = scan.nextLine();
                    eliminarEjecutivoMadero(nombreUsuario2);
                    break;
                case 4:
                    mostrarEjecutivosRegistradosMadero();
                    break;
                case 5:
                    registrarCapturistaMadero();
                    break;
                case 6:
                    System.out.print("Ingrese el nombre de usuario del capturista que desea modificar: ");
                    String nombreUsuario3 = scan.nextLine();
                    modificarCapturistaMadero(nombreUsuario3);
                    break;
                case 7:
                    System.out.print("Ingrese el nombre de usuario del capturista que desea eliminar: ");
                    String nombreUsuario4 = scan.nextLine();
                    eliminarCapturistaMadero(nombreUsuario4);
                    break;
                case 8: //Mostrrar capturistas
                    mostrarCapturistasMadero();
                    break;
                case 9: //Registrar cliente
                    
                    Cliente c = s.DarAltaCliente();
                    SucursalMadero.addCliente(c);
                    break;
                case 10://Modificar datos del cliente
                    s.modificarDatosCliente();
                    break;

                case 11: //Eliminar cliente
                    System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
                    String nombreUsuario5 = scan.nextLine();
                    s.eliminarCliente(nombreUsuario5);
                    break;
                case 12: //Mostrar cliente
                    s.mostrarLista();
                    break;
                case 13: //Autorizar tarjeta de crédito
                       getSolicitudesTarjetaToAccept();
                    break;
                case 14: //Registrar inversionista
                        registarInversionistaMadero();
                        break;
                    case 15: //Modificar inversionista
                        modificarInversionistaMadero();
                        break;
                    case 16: // Eliminar inversionista
                        eliminarInversionista();
                        break;
                case 17:
                    break;

            }
        } while (opcion != 17);
        inicioSesion();
    }
    
    
    public static void viewSolicitudesOfClient(Cliente cliente){
        System.out.println("###-- Historial de solicitudes --###");
            
        List<SolicitudTarjeta> solicitudesCliente =
                SucursalMadero.getSolicitudesTarjeta().stream().filter(x -> x.getCliente() == cliente).collect(Collectors.toList());
        
        for(int i = 0; i < solicitudesCliente.size();i++){
            System.out.println((i+1)+": "+solicitudesCliente.get(i).toStringCliente());
        }
        
    }
    
    public static void getSolicitudesTarjetaToAccept(){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Solicitudes de tarjeta disponibles ");

        List<SolicitudTarjeta> solicitudesAvaliable =
                SucursalMadero.getSolicitudesTarjeta().stream().filter(x -> x.getEstado() == EstadoSolicitud.EN_PROCESO).collect(Collectors.toList());
        
        
        for(int i = 0; i < solicitudesAvaliable.size();i++){
            System.out.println((i+1)+": "+solicitudesAvaliable.get(i).toString());
        }
      
        System.out.println("Selecciona el número de solicitud");
        int idSolicitud = scan.nextInt();
        
        System.out.println("1.- Aprobar \n2.- Rechazar");
        int stats = scan.nextInt();
        
        if(stats == 1){
            SucursalMadero.getSolicitudesTarjeta().stream().
                    filter(x -> x.getIdSolicitud() == solicitudesAvaliable.get(idSolicitud - 1).getIdSolicitud())
                    .collect(Collectors.toList()).get(0).setEstado(EstadoSolicitud.APROBADA);
            System.out.println("Solicitud Aprobada con éxito");
        }else{
              SucursalMadero.getSolicitudesTarjeta().stream().
                    filter(x -> x.getIdSolicitud() == solicitudesAvaliable.get(idSolicitud - 1).getIdSolicitud())
                      .collect(Collectors.toList()).get(0).setEstado(EstadoSolicitud.RECHAZADA);
            System.out.println("Solicitud Rechazada");
        }
                
    }
    

    //MADERO
    public static void menuCapturistaMadero() {

        // Solicitar nombre de usuario y contraseña
        scaner.nextLine();
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scaner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scaner.nextLine();

        boolean usuarioValido = false;
        for (Capturista capturista : capturistasMadero) {
            if (capturista.getNombreUsuario().equals(nombreUsuario) && capturista.getContraseña().equals(contraseña)) {
                usuarioValido = true;
                break;
            }
        }
        if (usuarioValido) {
            int opcion;
            do {
                System.out.println("¿Qué desea hacer? ");
                System.out.println("1. Registrar ejecutivo de cuenta\n2. Modificar ejecutivo de cuenta"
                        + "\n3.Eliminar ejecutivo de cuenta\n4. Mostrar ejecutivos de cuenta registrados en la sucursal Madero"
                        + "\n5. Salir");
                opcion = scaner.nextInt();
                switch (opcion) {
                    case 1:
                        registrarEjecutivoDeCuentaMadero();
                        break;
                    case 2:
                        scaner.nextLine();
                        System.out.print("Ingrese el nombre de usuario del empleado que desea modificar: ");
                        String nombreUsuario1 = scaner.nextLine();
                        modificarEjecutivosMadero(nombreUsuario1);
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de usuario del empleado que desea eliminar: ");
                        String nombreUsuario2 = scaner.nextLine();
                        eliminarEjecutivoMadero(nombreUsuario2);
                    case 4:
                        mostrarEjecutivosRegistradosMadero();
                        break;
                    case 5:
                        break;
                }
            } while (opcion != 5);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    public static void registrarCapturistaMadero() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\n**Registro de empleado con rol de capturista en sucursal Madero**");

        System.out.print("Nombre: ");
        String nombre = scanner1.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner1.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner1.nextLine();

        boolean datoValido = false;
        LocalDate fechaNacimiento = LocalDate.now();
        while (!datoValido) {
            try {
                System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                String fechaNacimientoString = scanner1.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido = false;
                scaner.nextLine();
            }
        }

        boolean datoValido2 = false;
        LocalDate fechaIngreso = LocalDate.now();
        while (!datoValido2) {
            try {
                System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
                String fechaIngresoString = scanner1.nextLine();
                fechaIngreso = LocalDate.parse(fechaIngresoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido2 = false;
                scaner.nextLine();
            }
        }

        System.out.print("ciudad: ");
        String ciudad = scanner1.nextLine();

        System.out.print("estado: ");
        String estado = scanner1.nextLine();

        double salario = 0;
        boolean datoValido3 = false;
        while (!datoValido3) {
            try {
                System.out.print("Salario: ");
                salario = scanner1.nextDouble();
                break;
            } catch (Exception error) {
                System.out.println("El argumento no es válido");
                datoValido3 = false;
                scanner1.nextLine();
            }
        }
        scanner1.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner1.nextLine();

        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner1.nextLine();

        int idEmpleado = contadorIdEmpleado++;

        SucursalMadero sucursalMadero = new SucursalMadero("Madero", "Av. Madero #123");
        Capturista capturista = new Capturista(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, sucursalMadero, salario, Rol.CAPTURISTA, fechaIngreso, contraseña, nombreUsuario);
        capturistasMadero.add(capturista);

        System.out.println("\nEmpleado registrado exitosamente!");

    }

    public static void modificarCapturistaMadero(String nombreUsuario) {
        Scanner scanner2 = new Scanner(System.in);

        Capturista capturistaAModificar = null;
        for (Capturista capturista : capturistasMadero) {
            if (capturista.getNombreUsuario().equals(nombreUsuario)) {
                capturistaAModificar = capturista;
                break;
            }
        }
        if (capturistaAModificar != null) {
            System.out.println("\nInformación actual del capturista:");
            System.out.println("  Nombre: " + capturistaAModificar.getNombre());
            System.out.println("  Apellidos: " + capturistaAModificar.getApellidos());
            System.out.println("  Fecha de nacimiento: " + capturistaAModificar.getFechaNacimiento());
            System.out.println("  Fecha de ingreso: " + capturistaAModificar.getFechaInicioTrabajo());
            System.out.println("  Salario: " + capturistaAModificar.getSalario());

            System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
            System.out.println("1. Nombre");
            System.out.println("2. Apellidos");
            System.out.println("3. Fecha de nacimiento");
            System.out.println("4. Fecha de ingreso");
            System.out.println("5. Salario");
            System.out.println("0. Salir");

            int opcion = scanner2.nextInt();
            scanner2.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner2.nextLine();
                    capturistaAModificar.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    String nuevosApellidos = scanner2.nextLine();
                    capturistaAModificar.setApellidos(nuevosApellidos);
                    break;
                case 3:
                    System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                    String nuevaFechaNacimientoString = scanner2.nextLine();
                    LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoString, DateTimeFormatter.ISO_DATE);
                    capturistaAModificar.setFechaNacimiento(nuevaFechaNacimiento);
                    break;
                case 4:
                    System.out.print("Ingrese la nueva fecha de ingreso (YYYY-MM-DD): ");
                    String nuevaFechaIngresoString = scanner2.nextLine();
                    LocalDate nuevaFechaIngreso = LocalDate.parse(nuevaFechaIngresoString, DateTimeFormatter.ISO_DATE);
                    capturistaAModificar.setFechaInicioTrabajo(nuevaFechaIngreso);
                    break;

                case 5:
                    boolean datoValido = false;
                    double nuevoSalario = 0;
                    while (!datoValido) {
                        try {
                            System.out.print("Ingrese el nuevo salario: ");
                            nuevoSalario = scaner.nextDouble();
                            datoValido = true;
                        } catch (Exception error) {
                            System.out.println("El argumento no es válido");
                            datoValido = false;
                            scaner.nextLine();
                        }
                    }
                    capturistaAModificar.setSalario(nuevoSalario);
                    scanner2.nextLine();
                    break;
                case 0:
                    break;
            }

            System.out.println("\nInformación del capturista modificada con éxito.");
        } else {
            System.out.println("No se encontró ningún capturista con el nombre de usuario: " + nombreUsuario);
        }
    }

    public static void eliminarCapturistaMadero(String nombreUsuario) {
        Scanner scanner4 = new Scanner(System.in);

        // Find the capturista to remove
        Capturista capturistaAEliminar = null;
        for (Capturista capturista : capturistasMadero) {
            if (capturista.getNombreUsuario().equals(nombreUsuario)) {
                capturistaAEliminar = capturista;
                break;
            }
        }

        if (capturistaAEliminar != null) {
            System.out.println("\n¿Está seguro de que desea eliminar al capturista con el nombre de usuario: " + nombreUsuario + "?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = scanner4.nextInt();
            if (opcion == 1) {
                capturistasMadero.remove(capturistaAEliminar);
                System.out.println("\nCapturista eliminado con éxito.");
            } else {
                System.out.println("\nEliminación cancelada.");
            }
        } else {
            System.out.println("No se encontró ningún capturista con el nombre de usuario: " + nombreUsuario);
        }

    }

    public static void registrarEjecutivoDeCuentaMadero() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\n**Registro de empleado con rol de ejecutivo de cuenta en sucursal Madero**");

        System.out.print("Nombre: ");
        String nombre = scanner1.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner1.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner1.nextLine();

        boolean datoValido = false;
        LocalDate fechaNacimiento = LocalDate.now();
        while (!datoValido) {
            try {
                System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                String fechaNacimientoString = scanner1.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido = false;
                scaner.nextLine();
            }
        }

        boolean datoValido2 = false;
        LocalDate fechaIngreso = LocalDate.now();
        while (!datoValido2) {
            try {
                System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
                String fechaIngresoString = scanner1.nextLine();
                fechaIngreso = LocalDate.parse(fechaIngresoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido2 = false;
                scaner.nextLine();
            }
        }

        System.out.print("ciudad: ");
        String ciudad = scanner1.nextLine();

        System.out.print("estado: ");
        String estado = scanner1.nextLine();

        double salario = 0;
        boolean datoValido3 = false;
        while (!datoValido3) {
            try {
                System.out.print("Salario: ");
                salario = scanner1.nextDouble();
                break;
            } catch (Exception error) {
                System.out.println("El argumento no es válido");
                datoValido3 = false;
                scanner1.nextLine();
            }
        }

        System.out.print("Contraseña: ");
        String contraseña = scanner1.nextLine();
        if (contraseña.isEmpty()) {
            System.out.println("Ingrese de nuevo la contraseña:");
            contraseña = scanner1.nextLine();
        }

        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner1.nextLine();

        int idEmpleado = contadorIdEmpleado++;

        SucursalMadero sucursalMadero = new SucursalMadero("Madero", "Av. Madero #123");
        EjecutivoDeCuenta ejecutivoDeCuenta = new EjecutivoDeCuenta(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, sucursalMadero, salario, Rol.EJECUTIVO_CUENTA, fechaIngreso, contraseña, nombreUsuario);
        ejecutivosMadero.add(ejecutivoDeCuenta);

        System.out.println("\nEmpleado registrado exitosamente!");

    }

    public static void mostrarEjecutivosRegistradosMadero() {
        if (ejecutivosMadero.isEmpty()) {
            System.out.println("No hay ejecutivos de cuenta registrados.");
            return;
        }

        System.out.println("\n**Lista de ejecutivos de cuenta registrados:**");
        for (EjecutivoDeCuenta ejecutivo : ejecutivosMadero) {
            System.out.println(ejecutivo);
        }
    }

    public static void modificarEjecutivosMadero(String nombreUsuario) {

        EjecutivoDeCuenta empleado = null;

        if (ejecutivosMadero.isEmpty()) {
            System.out.println("No hay ejecutivos de cuenta registrados en esta sucursal");
            return;
        }
        for (EjecutivoDeCuenta empleadoAux : ejecutivosMadero) {
            if (empleadoAux.getNombreUsuario().equals(nombreUsuario)) {
                empleado = empleadoAux;
                break;
            }
        }

        if (empleado != null) {
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Apellidos: " + empleado.getApellidos());
            System.out.println("Fecha de nacimiento: " + empleado.getFechaNacimiento());
            System.out.println("Fecha de ingreso: " + empleado.getFechaInicioTrabajo());
            System.out.println("Salario: " + empleado.getSalario());
            System.out.println("Rol: " + empleado.getRol());

            Scanner scanner2 = new Scanner(System.in);
            System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
            System.out.println("1. Nombre");
            System.out.println("2. Apellidos");
            System.out.println("3. Fecha de nacimiento");
            System.out.println("4. Fecha de ingreso");
            System.out.println("5. Salario");

            int opcion = scanner2.nextInt();
            scanner2.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner2.nextLine();
                    empleado.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    String nuevosApellidos = scanner2.nextLine();
                    empleado.setApellidos(nuevosApellidos);
                    break;
                case 3:
                    System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                    String nuevaFechaNacimientoString = scanner2.nextLine();
                    LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoString, DateTimeFormatter.ISO_DATE);
                    empleado.setFechaNacimiento(nuevaFechaNacimiento);
                    break;
                case 4:
                    System.out.print("Ingrese la nueva fecha de ingreso (YYYY-MM-DD): ");
                    String nuevaFechaIngresoString = scanner2.nextLine();
                    LocalDate nuevaFechaIngreso = LocalDate.parse(nuevaFechaIngresoString, DateTimeFormatter.ISO_DATE);
                    empleado.setFechaInicioTrabajo(nuevaFechaIngreso);
                    break;

                case 5:
                    System.out.print("Ingrese el nuevo salario: ");
                    double nuevoSalario = scanner2.nextDouble();
                    empleado.setSalario(nuevoSalario);
                    break;
            }

            System.out.println("Datos del empleado modificados exitosamente!");
        } else {
            System.out.println("No se encontró un empleado con el nombre de usuario " + nombreUsuario);
        }
    }

    public static void eliminarEjecutivoMadero(String nombreUsuario) {
        EjecutivoDeCuenta empleado = null;
        for (EjecutivoDeCuenta empleadoAux : ejecutivosMadero) {
            if (empleadoAux.getNombreUsuario().equals(nombreUsuario)) {
                empleado = empleadoAux;
                break;
            }
        }
        if (empleado != null) {
            ejecutivosMadero.remove(empleado);
            System.out.println("Empleado eliminado exitosamente!");
        } else {
            System.out.println("No se encontró un empleado con el nombre de usuario " + nombreUsuario);
        }
    }

    public static void registarInversionistaMadero() {
        Inversionista.registrarInversionistaMadero();
    }

    public static void modificarInversionistaMadero() {
        SucursalMadero.modificarDatosInversionista();
    }

    public static void eliminarInversionista() {
        System.out.println("Inserte el nombre de usuario del Inverionista a eliminar");
        Scanner scanner = new Scanner(System.in);
        String usuarioABuscar = scanner.nextLine();

        Usuario inversionistaAEliminar = null;
        for (Usuario usuario : SucursalMadero.getInversionistas()) {
            if (usuario.getUser().equals(usuarioABuscar)) {
                inversionistaAEliminar = usuario;
            }
        }
        if (inversionistaAEliminar != null) {
            System.out.println("Se ah eliminado correctamente");
            SucursalMadero.getInversionistas().remove(inversionistaAEliminar);
        } else {
            System.out.println("El inversionista con el nombre de usuario insertado no existe");
        }

    }

    public static void menuEjecutivoDeCuentaMadero() {
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scan1.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scan1.nextLine();

        boolean usuarioValido = false;
        for (EjecutivoDeCuenta ejecutivo : ejecutivosMadero) {
            if (ejecutivo.getNombreUsuario().equals(nombreUsuario) && ejecutivo.getContraseña().equals(contraseña)) {
                usuarioValido = true;
                break;
            }
        }
        if (usuarioValido) {
            int opcion;
            AltaCliente s = new AltaCliente();

            do {
                System.out.println("¿Qué desea hacer? ");
                System.out.println("1. Registrar cliente\n2. Modificar cliente"
                        + "\n3.Eliminar cliente\n4. Mostrar clientes registrados en la sucursal Madero"
                        + "\n5. Autorizar tarjeta de crédito\n6. Salir");
                opcion = scan1.nextInt();
                switch (opcion) {
                    case 1:
                        s.DarAltaCliente();
                        break;
                    case 2:
                        scan1.nextLine();
                        System.out.print("Ingrese el nombre de usuario del cliente que desea modificar: ");
                        String nombreUsuario1 = scan1.nextLine();
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
                        String nombreUsuario2 = scan1.nextLine();
                    case 4:
                        s.mostrarLista();
                        break;
                    case 5:
                        SolicitudTarjeta solicitudes = new SolicitudTarjeta();
                        autorizarSolicitudesDeTarjetas(solicitudes, gerenteMadero);

                        break;
                    case 6:
                        break;
                }
            } while (opcion != 6);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    //ACUEDUCTO
    public static void menuGerenteAcueducto() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scan.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scan.nextLine();
        AltaCliente s = new AltaCliente();

        boolean usuarioValido = false;
        if (gerenteAcueducto.getNombreUsuario().equals(nombreUsuario) && gerenteMadero.getContraseña().equals(contraseña)) {
            usuarioValido = true;
        }
        if (usuarioValido) {
            int opcion;
            do {
                System.out.println("¿Qué desea hacer? ");
                System.out.println("1. Registrar ejecutivo de cuenta\n2. Modificar ejecutivo de cuenta"
                        + "\n3.Eliminar ejecutivo de cuenta\n4. Mostrar ejecutivos de cuenta registrados en la sucursal Madero"
                        + "\n5. Registrar capturista\n6. Modificar capturista\n7.Eliminar capturista\n8.Mostrar capturistas registrados\n9. Registrar cliente"
                        + "\n10.Modificar cliente\n11.Eliminar cliente\n12. Mostrar clientes registrados"
                        + "\n13. Autorizar tarjeta de crédito\n14. Registrar inversionista\n15. Modificar inversionista"
                        + "\n16. Eliminar inversionista\n17. Salir");
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1:
                        registrarEjecutivoDeCuentaAcueducto();
                        break;
                    case 2:
                        scan.nextLine();
                        System.out.print("Ingrese el nombre de usuario del ejecutivo de cuenta que desea modificar: ");
                        String nombreUsuario1 = scan.nextLine();
                        modificarEjecutivosAcueducto(nombreUsuario1);
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de usuario del ejecutivo de cuenta que desea eliminar: ");
                        String nombreUsuario2 = scan.nextLine();
                        eliminarEjecutivoAcueducto(nombreUsuario2);
                        break;
                    case 4:
                        mostrarEjecutivosRegistradosAcueducto();
                        break;
                    case 5:
                        registrarCapturistaAcueducto();
                        break;
                    case 6:
                        System.out.print("Ingrese el nombre de usuario del capturista que desea modificar: ");
                        String nombreUsuario3 = scan.nextLine();
                        modificarCapturistaAcueducto(nombreUsuario3);
                        break;
                    case 7:
                        System.out.print("Ingrese el nombre de usuario del capturista que desea eliminar: ");
                        String nombreUsuario4 = scan.nextLine();
                        eliminarCapturistaAcueducto(nombreUsuario4);
                        break;
                    case 8: //Mostrrar capturistas
                        mostrarCapturistasMadero();
                        break;
                    case 9: //Registrar cliente
                        s.DarAltaCliente();
                        break;
                    case 10://Modificar datos del cliente
                        s.modificarDatosCliente();
                        break;

                    case 11: //Eliminar cliente
                        System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
                        String nombreUsuario5 = scan.nextLine();
                        s.eliminarCliente(nombreUsuario5);
                        break;
                    case 12: //Mostrar clientes
                        s.mostrarLista();

                    case 13: //Autorizar tarjeta de crédito
                       

                        break;
                    case 14: //Registrar inversionista
                        registarInversionistaMadero();
                        break;
                    case 15: //Modificar inversionista
                        modificarInversionistaMadero();
                        break;
                    case 16: // Eliminar inversionista
                        eliminarInversionista();
                        break;
                    case 17:
                        break;

                }
            } while (opcion != 17);
            inicioSesion();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }

    public static void menuCapturistaAcueducto() {

        // Solicitar nombre de usuario y contraseña
        scaner.nextLine();
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scaner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scaner.nextLine();

        boolean usuarioValido = false;
        for (Capturista capturista : capturistasAcueducto) {
            if (capturista.getNombreUsuario().equals(nombreUsuario) && capturista.getContraseña().equals(contraseña)) {
                usuarioValido = true;
                break;
            }
        }
        if (usuarioValido) {
            int opcion;
            do {
                System.out.println("¿Qué desea hacer? ");
                System.out.println("1. Registrar ejecutivo de cuenta\n2. Modificar ejecutivo de cuenta"
                        + "\n3.Eliminar ejecutivo de cuenta\n4. Mostrar ejecutivos de cuenta registrados en la sucursal Madero"
                        + "\n5. Salir");
                opcion = scaner.nextInt();
                switch (opcion) {
                    case 1:
                        registrarEjecutivoDeCuentaAcueducto();
                        break;
                    case 2:
                        System.out.print("Ingrese el nombre de usuario del empleado que desea modificar: ");
                        String nombreUsuario1 = scaner.nextLine();
                        modificarEjecutivosAcueducto(nombreUsuario1);
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de usuario del empleado que desea eliminar: ");
                        String nombreUsuario2 = scaner.nextLine();
                        eliminarEjecutivoAcueducto(nombreUsuario2);
                    case 4:
                        mostrarEjecutivosRegistradosAcueducto();
                        break;
                    case 5:
                        break;
                }
            } while (opcion != 5);
        } else {
            System.out.println("Nombre de usuaurio o contraseña inválidos");
        }
    }

    public static void registrarCapturistaAcueducto() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\n**Registro de empleado con rol de capturista en sucursal Acueducto**");

        System.out.print("Nombre: ");
        String nombre = scanner1.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner1.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner1.nextLine();

        boolean datoValido = false;
        LocalDate fechaNacimiento = LocalDate.now();
        while (!datoValido) {
            try {
                System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                String fechaNacimientoString = scanner1.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido = false;
                scaner.nextLine();
            }
        }
        System.out.print("ciudad: ");
        String ciudad = scanner1.nextLine();

        System.out.print("estado: ");
        String estado = scanner1.nextLine();

        boolean datoValido2 = false;
        LocalDate fechaIngreso = LocalDate.now();
        while (!datoValido2) {
            try {
                System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
                String fechaIngresoString = scanner1.nextLine();
                fechaIngreso = LocalDate.parse(fechaIngresoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido2 = false;
                scaner.nextLine();
            }
        }

        double salario = 0;
        boolean datoValido3 = false;
        while (!datoValido3) {
            try {
                System.out.print("Salario: ");
                salario = scanner1.nextDouble();
                break;
            } catch (Exception error) {
                System.out.println("El argumento no es válido");
                datoValido3 = false;
                scanner1.nextLine();
            }
        }
        scanner1.nextLine();
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner1.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = scanner1.nextLine();

        int idEmpleado = contadorIdEmpleado++;

        SucursalAcueducto sucursalAcueducto = new SucursalAcueducto("Acueducto", "Av. Acueducto #256");
        Capturista capturista = new Capturista(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, sucursalAcueducto, salario, Rol.CAPTURISTA, fechaIngreso, contraseña, nombreUsuario);
        capturistasAcueducto.add(capturista);

        System.out.println("\n**Empleado registrado exitosamente!");

        System.out.println("\n**Capturistas registrados en Sucursal Acueducto:**");
        for (Capturista empleado : capturistasAcueducto) {
            System.out.println(empleado.toString());
        }
    }

    public static void modificarCapturistaAcueducto(String nombreUsuario) {
        Scanner scanner2 = new Scanner(System.in);

        Capturista capturistaAModificar = null;
        for (Capturista capturista : capturistasAcueducto) {
            if (capturista.getNombreUsuario().equals(nombreUsuario)) {
                capturistaAModificar = capturista;
                break;
            }
        }
        if (capturistaAModificar != null) {
            System.out.println("\nInformación actual del capturista:");
            System.out.println("  Nombre: " + capturistaAModificar.getNombre());
            System.out.println("  Apellidos: " + capturistaAModificar.getApellidos());
            System.out.println("  Fecha de nacimiento: " + capturistaAModificar.getFechaNacimiento());
            System.out.println("  Fecha de ingreso: " + capturistaAModificar.getFechaInicioTrabajo());
            System.out.println("  Salario: " + capturistaAModificar.getSalario());

            System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
            System.out.println("1. Nombre");
            System.out.println("2. Apellidos");
            System.out.println("3. Fecha de nacimiento");
            System.out.println("4. Fecha de ingreso");
            System.out.println("5. Salario");
            System.out.println("0. Salir");

            int opcion = scanner2.nextInt();
            scanner2.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner2.nextLine();
                    capturistaAModificar.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Ingrese los nuevos apellidos: ");
                    String nuevosApellidos = scanner2.nextLine();
                    capturistaAModificar.setApellidos(nuevosApellidos);
                    break;
                case 3:
                    System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                    String nuevaFechaNacimientoString = scanner2.nextLine();
                    LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoString, DateTimeFormatter.ISO_DATE);
                    capturistaAModificar.setFechaNacimiento(nuevaFechaNacimiento);
                    break;
                case 4:
                    System.out.print("Ingrese la nueva fecha de ingreso (YYYY-MM-DD): ");
                    String nuevaFechaIngresoString = scanner2.nextLine();
                    LocalDate nuevaFechaIngreso = LocalDate.parse(nuevaFechaIngresoString, DateTimeFormatter.ISO_DATE);
                    capturistaAModificar.setFechaInicioTrabajo(nuevaFechaIngreso);
                    break;

                case 5:
                    boolean datoValido = false;
                    double nuevoSalario = 0;
                    while (!datoValido) {
                        try {
                            System.out.print("Ingrese el nuevo salario: ");
                            nuevoSalario = scaner.nextDouble();
                            datoValido = true;
                        } catch (Exception error) {
                            System.out.println("El argumento no es válido");
                            datoValido = false;
                            scaner.nextLine();
                        }
                    }
                    capturistaAModificar.setSalario(nuevoSalario);
                    scanner2.nextLine();
                    break;
                case 0:
                    break;
            }

            System.out.println("\nInformación del capturista modificada con éxito.");
        } else {
            System.out.println("No se encontró ningún capturista con el nombre de usuario: " + nombreUsuario);
        }
    }

    public static void registrarEjecutivoDeCuentaAcueducto() {

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("\n**Registro de empleado con rol de ejecutivo de cuenta en sucursal Acueducto**");

        System.out.print("Nombre: ");
        String nombre = scanner1.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner1.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner1.nextLine();

        boolean datoValido = false;
        LocalDate fechaNacimiento = LocalDate.now();
        while (!datoValido) {
            try {
                System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                String fechaNacimientoString = scanner1.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido = false;
                scaner.nextLine();
            }
        }

        boolean datoValido2 = false;
        LocalDate fechaIngreso = LocalDate.now();
        while (!datoValido2) {
            try {
                System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
                String fechaIngresoString = scanner1.nextLine();
                fechaIngreso = LocalDate.parse(fechaIngresoString, DateTimeFormatter.ISO_DATE);
                break;
            } catch (Exception error) {
                System.out.println("Ingrese la fecha con el formato adecuado (YYYY-MM-DD)");
                datoValido2 = false;
                scaner.nextLine();
            }
        }

        System.out.print("ciudad: ");
        String ciudad = scanner1.nextLine();

        System.out.print("estado: ");
        String estado = scanner1.nextLine();

        double salario = 0;
        boolean datoValido3 = false;
        while (!datoValido3) {
            try {
                System.out.print("Salario: ");
                salario = scanner1.nextDouble();
                break;
            } catch (Exception error) {
                System.out.println("El argumento no es válido");
                datoValido3 = false;
                scanner1.nextLine();
            }
        }
        scanner1.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner1.nextLine();

        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner1.nextLine();

        int idEmpleado = contadorIdEmpleado++;

        SucursalAcueducto sucursalAcueducto = new SucursalAcueducto("Acueducto", "Av. Acueducto #256");
        EjecutivoDeCuenta ejecutivoDeCuenta = new EjecutivoDeCuenta(idEmpleado, nombre, apellidos, fechaNacimiento, ciudad, estado, direccion, sucursalAcueducto, salario, Rol.EJECUTIVO_CUENTA, fechaIngreso, contraseña, nombreUsuario);
        ejecutivosAcueducto.add(ejecutivoDeCuenta);

        System.out.println("\nEmpleado registrado exitosamente!");

    }

    public static void mostrarEjecutivosRegistradosAcueducto() {
        if (ejecutivosAcueducto.isEmpty()) {
            System.out.println("No hay ejecutivos de cuenta registrados.");
            return;
        }

        System.out.println("\n**Lista de ejecutivos de cuenta registrados:**");
        for (EjecutivoDeCuenta ejecutivo : ejecutivosAcueducto) {
            System.out.println(ejecutivo);
        }
    }

    public static void modificarEjecutivosAcueducto(String nombreUsuario) {

        EjecutivoDeCuenta empleado = null;
        if (ejecutivosAcueducto.isEmpty()) {
            System.out.println("No hay ejecutivos de cuenta registrados en esta sucursal");
            return;
        } else {
            for (EjecutivoDeCuenta empleadoAux : ejecutivosAcueducto) {
                if (empleadoAux.getNombreUsuario().equals(nombreUsuario)) {
                    empleado = empleadoAux;
                    break;
                }
            }

            if (empleado != null) {
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Apellidos: " + empleado.getApellidos());
                System.out.println("Fecha de nacimiento: " + empleado.getFechaNacimiento());
                System.out.println("Fecha de ingreso: " + empleado.getFechaInicioTrabajo());
                System.out.println("Salario: " + empleado.getSalario());
                System.out.println("Rol: " + empleado.getRol());

                Scanner scanner2 = new Scanner(System.in);
                System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
                System.out.println("1. Nombre");
                System.out.println("2. Apellidos");
                System.out.println("3. Fecha de nacimiento");
                System.out.println("4. Fecha de ingreso");
                System.out.println("5. Salario");

                int opcion = scanner2.nextInt();
                scanner2.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nuevoNombre = scanner2.nextLine();
                        empleado.setNombre(nuevoNombre);
                        break;
                    case 2:
                        System.out.print("Ingrese los nuevos apellidos: ");
                        String nuevosApellidos = scanner2.nextLine();
                        empleado.setApellidos(nuevosApellidos);
                        break;
                    case 3:
                        System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                        String nuevaFechaNacimientoString = scanner2.nextLine();
                        LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoString, DateTimeFormatter.ISO_DATE);
                        empleado.setFechaNacimiento(nuevaFechaNacimiento);
                        break;
                    case 4:
                        System.out.print("Ingrese la nueva fecha de ingreso (YYYY-MM-DD): ");
                        String nuevaFechaIngresoString = scanner2.nextLine();
                        LocalDate nuevaFechaIngreso = LocalDate.parse(nuevaFechaIngresoString, DateTimeFormatter.ISO_DATE);
                        empleado.setFechaInicioTrabajo(nuevaFechaIngreso);
                        break;
                    case 5:
                        System.out.print("Ingrese el nuevo salario: ");
                        double nuevoSalario = scanner2.nextDouble();
                        empleado.setSalario(nuevoSalario);
                        break;
                }

                System.out.println("Datos del empleado modificados exitosamente!");
            } else {
                System.out.println("No se encontró un empleado con el nombre de usuario " + nombreUsuario);
            }
        }
    }

    public static void eliminarEjecutivoAcueducto(String nombreUsuario) {
        EjecutivoDeCuenta empleado = null;
        for (EjecutivoDeCuenta empleadoAux : ejecutivosAcueducto) {
            if (empleadoAux.getNombreUsuario().equals(nombreUsuario)) {
                empleado = empleadoAux;
                break;
            }
        }
        if (empleado != null) {
            ejecutivosAcueducto.remove(empleado);
            System.out.println("Empleado eliminado exitosamente!");
        } else {
            System.out.println("No se encontró un empleado con el nombre de usuario " + nombreUsuario);
        }
    }

    public static void eliminarCapturistaAcueducto(String nombreUsuario) {
        Scanner scanner4 = new Scanner(System.in);

        // Find the capturista to remove
        Capturista capturistaAEliminar = null;
        for (Capturista capturista : capturistasAcueducto) {
            if (capturista.getNombreUsuario().equals(nombreUsuario)) {
                capturistaAEliminar = capturista;
                break;
            }
        }
        if (capturistaAEliminar != null) {
            System.out.println("\n¿Está seguro de que desea eliminar al capturista con el nombre de usuario: " + nombreUsuario + "?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            int opcion = scanner4.nextInt();
            if (opcion == 1) {
                capturistasAcueducto.remove(capturistaAEliminar);
                System.out.println("\nCapturista eliminado con éxito.");
            } else {
                System.out.println("\nEliminación cancelada.");
            }
        } else {
            System.out.println("No se encontró ningún capturista con el nombre de usuario: " + nombreUsuario);
        }

    }

    public static void menuEjecutivoDeCuentaAcueducto() {
        AltaCliente s = new AltaCliente();
        Scanner scan2 = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scan2.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scan2.nextLine();

        boolean usuarioValido = false;
        for (EjecutivoDeCuenta ejecutivo : ejecutivosAcueducto) {
            if (ejecutivo.getNombreUsuario().equals(nombreUsuario) && ejecutivo.getContraseña().equals(contraseña)) {
                usuarioValido = true;
                break;
            }
        }
        if (usuarioValido) {
            int opcion;
            do {
                System.out.println("¿Qué desea hacer? ");
                System.out.println("1. Registrar cliente\n2. Modificar cliente"
                        + "\n3.Eliminar cliente\n4. Mostrar clientes registrados en la sucursal Acueducto"
                        + "\n5. Autorizar tarjeta de crédito\n6. Salir");
                opcion = scan2.nextInt();
                switch (opcion) {
                    case 1:
                        s.DarAltaCliente();
                        break;
                    case 2:
                        scan2.nextLine();
                        s.modificarDatosCliente();
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre de usuario del cliente que desea eliminar: ");
                        String nombreUsuario2 = scan2.nextLine();
                        s.eliminarCliente((nombreUsuario2));
                    case 4:
                        s.mostrarLista();
                        break;

                    case 5:
                        break;
                    case 6:
                        break;
                }
            } while (opcion != 6);
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }

    }

    public static void mostrarCapturistasAcueducto() {
        if (capturistasAcueducto.isEmpty()) {
            System.out.println("No hay capturistas registrados.");
            return;
        }

        System.out.println("\n**Lista de capturistas registrados:**");
        for (Capturista capturista : capturistasAcueducto) {
            System.out.println("\n" + capturista);
        }
    }

    public static void mostrarCapturistasMadero() {
        if (capturistasMadero.isEmpty()) {
            System.out.println("No hay capturistas registrados.");
            return;
        }

        System.out.println("\n**Lista de capturistas registrados:**");
        for (Capturista capturista : capturistasMadero) {
            System.out.println("\n" + capturista);
        }
    }

    ///////////Aceptar o rechazar tarjetas
    public static void autorizarSolicitudesDeTarjetas(SolicitudTarjeta solicitud, Empleado autorizador) {
        Scanner scannerr = new Scanner(System.in);
        if (autorizador.getRol() == Rol.EJECUTIVO_CUENTA || autorizador.getRol() == Rol.GERENTE_SUCURSAL) {
            if (solicitud.getEstado() == EstadoSolicitud.EN_PROCESO) {
                System.out.println("Empleado " + autorizador.getNombre() + "con rol" + autorizador.getRol());
                System.out.println("Solicitud de tarjeta " + solicitud.getTipoTarjeta());

                System.out.print("¿Autorizar solicitud? (responda: si o no): ");
                String respuesta = scannerr.nextLine().toLowerCase();

                if (respuesta.equals("si")) {
                    solicitud.setEstado(EstadoSolicitud.APROBADA);
                    //Método para generar tarjeta
                    System.out.println("Solicitud autorizada correctamente. Tarjeta generada");
                } else {
                    solicitud.setEstado(EstadoSolicitud.RECHAZADA);

                }

            } else {
                System.out.println("La solicitud ya fue procesada");
            }
        } else {
            System.out.println("No eres ejecutivo ni gerente. Solo estos roles puede autorizar o rechazar tarjetas.");
        }
    }

}
