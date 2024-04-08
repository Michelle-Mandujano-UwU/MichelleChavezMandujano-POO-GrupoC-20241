package examen.u2;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExamenU2 {
private static final ArrayList<Animal> animales = new ArrayList<>();
private static final ArrayList<Actividad> actividades = new ArrayList<>();
private static final ArrayList<Empleado> empleados = new ArrayList<>();
private static final ArrayList<Visitante> visitantes = new ArrayList<>();
private static final Map<String, Visitante> visitantesRegistrados = new HashMap<>();
private static final List<Visita> visitas = new ArrayList<>();


    
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int opción;
    do {
            mostrarMenu();
            opción = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opción) {
                case 1:
                    registrarEmpleado();
                    break;
                case 2:
                    imprimirListaEmpleados();
                    break;
                 case 3:
                    registrarVisitante();
                    break;
                case 4:
                    mostrarVisitantesRegistrados();
                    break;
                case 5:
                    registrarVisita();
                    break;
                case 6:
                    mostrarVisita();
                    break;
                case 7:
                    registrarAnimales();
                    break;
                case 8: 
                    registrarActividad(scanner, empleados, animales);
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intentalo de nuevo.");
            }
        } while (opción != 9);
    
    ///////////// PUNTO 6
    Scanner scan= new Scanner(System.in);
    String contraseña = "director";
    System.out.println("¿Eres el director del zoológico?\n\tSí = s\n\tNo = n");
    String answer = scan.nextLine();
    if("s".equals(answer)) {
        System.out.println("Introduzca la contraseña: ");
        String contraseñaIngresada = scan.nextLine();
        if(contraseñaIngresada.equals(contraseña)) {
            System.out.println("¿Qué desea hacer?\n1. Registrar\n2.Modificación\n3.Eliminación\n4.Consulta");
            int opcion=scan.nextInt();
            switch (opcion) {
                case 1: 
                    System.out.println("Registrar animales: 1\nRegistrar empleados: 2\nRegistrar visitantes: 3");
                    int i= scan.nextInt();
                switch (i) {
                    case 1:
                        registrarAnimales();
                        break;
                    case 2:
                        registrarEmpleado();
                        break;
                    case 3:
                        registrarVisitante();
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
              }
                case 2: //Modificacicón
                    System.out.println("¿Qué se desea modificar?\nAnimales: 1\nEmpleados:2\nVisitantes:3");
                    int j= scan.nextInt();
                switch (j) {
                    case 1:
                        System.out.println("Ingrese el ID del animal que desea modificar");
                        int idABuscar= scan.nextInt();
                        modificarAnimal(idABuscar);
                        break;
                    case 2:
                        System.out.println("¿Desea modificar los datos de un empleado? (Introduzca s/n): ");
                        String respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el ID del empleado: ");
                            int idEmpleado = scanner.nextInt();
                            modificarEmpleado(idEmpleado);
                            }
                        break;
                    case 3:
                        System.out.println("¿Desea modificar los datos de un visitante? (Introduzca s/n): ");
                        String respuesta2 =scan.nextLine();
                        if(respuesta2.equalsIgnoreCase("s")){
                            System.out.print("Ingrese el nombre del visitante: ");
                            String nombreNuevo = scan.nextLine();
                            System.out.print("Ingrese los apellidos del visitante: ");
                            String apellidosNuevos = scan.nextLine();
                            System.out.print("Ingrese la CURP del visitante: ");
                            String curpNueva = scan.nextLine();
                            modificarVisitante(nombreNuevo, apellidosNuevos, curpNueva);
                        }
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
              }
                case 3://Eliminar
                    Scanner scanner5= new Scanner(System.in);
                    System.out.println("¿Qué se desea eliminar?\nAnimales: 1\nEmpleados:2");
                    int op=scanner5.nextInt();
                    switch(op){
                        case 1: //Animales
                            System.out.println("¿Desea eliminar un animal? (Introduzca s/n): ");
                            String respuesta = scanner.nextLine();
                            if (respuesta.equalsIgnoreCase("s")) {
                                System.out.print("Ingrese el nombre del animal: ");
                                String nombreAnimal = scanner.nextLine();
                                eliminarAnimal(nombreAnimal);
                              }
                            break;
                        case 2: //Empleados
                            System.out.println("¿Desea eliminar un empleado? (Introduzca s/n): ");
                            String respuesta2 = scanner5.nextLine();
                            if (respuesta2.equalsIgnoreCase("s")) {
                            System.out.print("Ingrese el ID del empleado: ");
                            int idEmpleado = scanner5.nextInt();
                            eliminarEmpleado(idEmpleado);
                            }
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                case 4: //Consultas
                    System.out.println("¿Qué se desea consultar?\nAnimales: 1\nEmpleados:2\nVisitantes:3");
                    int k= scan.nextInt();
                    switch (k) {
                        case 1:
                            System.out.print("Ingrese el nombre del animal: ");
                            String nombreAnimal = scanner.nextLine();
                            consultarAnimal(nombreAnimal);
                            break;
                        case 2:
                            System.out.print("Ingrese el id del visitante: ");
                            String idVisitante = scanner.nextLine();
                            consultarVisitante(idVisitante);
                        case 3: 
                            System.out.print("Ingrese el ID del empleado: ");
                            int idEmpleado = scanner.nextInt();
                            consultarEmpleado(idEmpleado);
                    }
            }
        }
    } else {
        System.out.println("fin del programa");
    }
  
    } //Se cierra el método main
    private static void mostrarMenu() {
        System.out.println("\n**Menu de registro de empleados**");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Imprimir lista de empleados");
        System.out.println("3. Registrar visitante");
        System.out.println("4. Imprimir lista de visitantes");
        System.out.println("5. Registrar visita");
        System.out.println("6. Mostrar visita");
        System.out.println("7. Registar animal");
        System.out.println("8. Registrar actividad");
        System.out.println("9. Salir");
        System.out.print("Selecciona una opcion: ");
    }
    private static void registrarVisita() {
        Scanner scanner1= new Scanner(System.in);
        List<Empleado> listaGuias = empleados.stream().filter(x->x.getRol().equalsIgnoreCase("Guia")).collect(Collectors.toList());
        
    	System.out.println("\n**Registro de visita**");
    	
    	 System.out.print("Fecha de visita (YYYY-MM-DD):");
         String fechaVisita = scanner1.nextLine();
         LocalDate fechaVisitaParsed = LocalDate.parse(fechaVisita, DateTimeFormatter.ISO_DATE);
         
         for(Empleado empleado : listaGuias) {
        	 System.out.println("- "+empleado.getIdEmpleado()+" -> "+empleado.getFullName());
         }
         System.out.print("Guía a cargo:");
         int guiaSeleccionado = Integer.parseInt(scanner1.nextLine());
         
         for(Visitante visitante : visitantesRegistrados.values()) {
        	 System.out.println("- "+visitante.getIdVisitante()+" -> "+visitante.getFullName());
         }
         System.out.print("Visitantes:");
         String visitantesSeleccionados = scanner1.nextLine();
        
         List<String> listIdVisitantes = Arrays.asList(visitantesSeleccionados.split(","));
         List<Visitante> visitantesInv = visitantesRegistrados.values().stream().filter(x-> listIdVisitantes.contains(x.getIdVisitante()+"")).collect(Collectors.toList());

         Empleado guiaACargo = empleados.stream().filter(x->x.getIdEmpleado() == guiaSeleccionado ).collect(Collectors.toList()).get(0);
         
         Visita v = new Visita(guiaACargo, visitantesInv, fechaVisitaParsed);
         
         visitas.add(v);
         
         System.out.println("\nVisita registrada exitosamente!");       
    }
    
    private static void mostrarVisita() {
    	if(visitas.isEmpty()) {
    		System.out.println("\nNo hay visitas.");
            return;
    	}else {
    		System.out.println("\n**Lista de visitas registradas**");
    		
    		for(Visita visita : visitas) {
    			System.out.println("Fecha visita: "+visita.getFechaVisita());
    			System.out.println("Costo Total: "+visita.getCostoTotal());
    			System.out.println("Cantidad de Adultos: "+visita.getCantidadAdultos());
    			System.out.println("Cantidad de ni�os: "+visita.getCantidadNinos());
    			System.out.println("Guia a cargo: "+visita.getGuiaCargo().getFullName());
    			System.out.println("Visitantes ---");
    			
    			for (Visitante visitante : visita.getVisitantes()) {
        			System.out.println(" - "+visitante.getFullName());
				}
    		}
    		System.out.println("---------------------------------------------------------");
    	}
    }

    public static Empleado findEmpleadoByNombre(ArrayList<Empleado> empleados, String nombre) {
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equals(nombre)) {
                return empleado;
            }
        }
    return null;
    }
    public static Animal findAnimalById(ArrayList<Animal> animales, int idAnimal) {
    for (Animal animal : animales) {
        if (animal.getIdAnimal() == idAnimal) {
            return animal;
        }
    }
    return null;
}
    private static void mostrarMenuParaDirector() {
        System.out.println("\n**Menu para Director**");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Imprimir lista de empleados");
        System.out.println("3. Registrar visitante");
        System.out.println("4. Imprimir lista de visitantes");
        System.out.println("5. Registrar visita");
        System.out.println("6. Mostrar visita");
        System.out.println("7. Salir");
        System.out.print("Selecciona una opcion: ");
    }
    
    public static void registrarAnimales () {
    Scanner leer = new Scanner (System.in);
    /*ArrayList<Animal> animales = new ArrayList<>();*/
    
    boolean continuar = true;
    while (continuar) {
    Scanner leer1 = new Scanner(System.in);
    System.out.println("¿Desea agregar un nuevo animal? Ingrese una letra:  \ns: si\nn: no");
    String respuesta = leer1.nextLine();
    Animal animal1;
        if (respuesta.equalsIgnoreCase("s")) {
     
    Scanner scanner1 = new Scanner(System.in);

    System.out.println("Ingresa el tipo de animal:");
    System.out.println("1. Mamífero");
    System.out.println("2. Ave");
    System.out.println("3. Reptil");

    int opcion = scanner1.nextInt();
    String tipoAnimal;
    switch (opcion) {
        case 1:
            tipoAnimal = "Mamífero";
            break;
        case 2:
            tipoAnimal = "Ave";
            break;
        case 3:
            tipoAnimal = "Reptil";
            break;
        default:
            System.out.println("Opción no válida.");
            return;
        }
     
     System.out.print("Ingresa el nombre del animal: ");
     String nombreAnimal = leer.nextLine();
     
     System.out.print("Ingresa el ID del animal (Número entero): ");
     int idAnimal = leer.nextInt();


     System.out.println("Ingresa la fecha de nacimiento (aaaa-mm-dd): ");
     String fechaNacimientoStr = leer.nextLine();
     LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
     
     System.out.println("Ingresa la fecha de llegada al zoologico (aaaa-mm-dd): ");
     String fechaLlegadaStr = leer.nextLine();
     LocalDate fechaLlegada = LocalDate.parse(fechaLlegadaStr);

    System.out.println("Ingresa su peso (en kg): ");
    double peso= leer.nextDouble();
    
    System.out.println("¿Cuántas enfermedades tiene el animal?");
    int cantidadEnfermedades = leer.nextInt();

    ArrayList<String> enfermedades = new ArrayList<>();
    for (int i = 0; i < cantidadEnfermedades; i++) {
        System.out.println("Introduzca la enfermedad " + (i + 1) + ": ");
        Scanner leer2 = new Scanner(System.in);
        String enfermedad = leer2.nextLine();
        enfermedades.add(enfermedad);
    }
    System.out.println(enfermedades);
    
    leer.nextLine();
    System.out.println("¿Cada cuánto tiempo se tiene que alimentar al animal: ");
    String frecuenciaAlimentacion= leer.nextLine();
    
    System.out.println("Ingresa su tipo de alimentación: ");
    String tipoAlimentacion=leer.nextLine();
    
    System.out.println("¿Cuenta con vacunas? \n1= Sí\n2=No");
    
    int i = leer.nextInt();
    boolean cuentaConVacunas;
    if(i== 1){
         cuentaConVacunas=true;
    } else {
         cuentaConVacunas=false;
    }    
    
    Animal animal = new Animal(tipoAnimal,nombreAnimal,idAnimal, fechaNacimiento, fechaLlegada, peso, enfermedades, frecuenciaAlimentacion, tipoAlimentacion, cuentaConVacunas);
    leer.nextLine();
    animales.add(animal);
            /*System.out.println(animal.getInformacion());*/
        } else {
            continuar = false;
        }
    } //Se cierra el ciclo while
    /*//for each para  Imprimir los animales registrados
    for (Animal animal : animales) {
    System.out.println("**Animal:**");
    System.out.println("Nombre: " + animal.getTipoAnimal());
    System.out.println("Tipo: "+ animal.getNombreAnimal());
    System.out.println("Id: "+ animal.getIdAnimal());
    System.out.println("Fecha de nacimiento: " + animal.getFechaNacimiento());
    System.out.println("Fecha de llegada al zoológico: " + animal.getFechaLlegadaZoologico());
    System.out.println("Peso: " + animal.getPeso());
    System.out.println("Enfermedades: " + animal.getEnfermedades());
    System.out.println("Frecuencia de alimentación: " + animal.getFrecuenciaAlimentacion());
    System.out.println("Tipo de alimentación: " + animal.getTipoAlimentacion());
    System.out.println("Cuenta con vacunas: " + animal.getCuentaConVacunas());
    System.out.println();
}*/
    }
    private static void registrarEmpleado() {
        /*ArrayList<Empleado> empleados = new ArrayList<>();*/
        Scanner scanner1= new Scanner(System.in);
        System.out.println("\n**Registro de empleado**");

        System.out.print("Nombre: ");
        String nombre = scanner1.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner1.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoString = scanner1.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);

        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        String fechaIngresoString = scanner1.nextLine();
        LocalDate fechaIngreso = LocalDate.parse(fechaIngresoString, DateTimeFormatter.ISO_DATE);

        System.out.print("RFC: ");
        String rfc = scanner1.nextLine();

        System.out.print("CURP: ");
        String curp = scanner1.nextLine();

        System.out.print("Salario: ");
        double salario = scanner1.nextDouble();
        scanner1.nextLine(); // Consumir el salto de línea

        System.out.print("Rol: ");
        String rol = scanner1.nextLine();

        Empleado empleado = new Empleado(empleados.size(),nombre, apellidos, fechaNacimiento, fechaIngreso, rfc, curp, salario, rol);
        empleados.add(empleado);

        System.out.println("\nEmpleado registrado exitosamente!");
    }

    private static void imprimirListaEmpleados() {

        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados registrados.");
            return;
        }

        System.out.println("\n**Lista de empleados**");
        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
    }
    
    private static void registrarVisitante() {
        Scanner scanner2= new Scanner(System.in);
        System.out.println("\n**Registro de visitante**");

        System.out.print("Nombre: ");
        String nombre = scanner2.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner2.nextLine();
        

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoString = scanner2.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoString, DateTimeFormatter.ISO_DATE);

        System.out.print("CURP: ");
        String curp = scanner2.nextLine();

        System.out.print("Fecha de registro (YYYY-MM-DD): ");
        String fechaRegistroString = scanner2.nextLine();
        LocalDate fechaRegistro = LocalDate.parse(fechaRegistroString, DateTimeFormatter.ISO_DATE);
        
        String claveVisitante = nombre.toLowerCase() + "_" + apellidos.toLowerCase() + "_" + curp.toLowerCase();
        
        Visitante visitante = visitantesRegistrados.get(claveVisitante);

        if (visitante == null) {
            System.out.print("Edad: ");
            int edad = scanner2.nextInt();
            scanner2.nextLine(); // Consumir el salto de línea

            visitante = new Visitante(visitantesRegistrados.values().size(),nombre, apellidos, fechaNacimiento, curp, edad, fechaRegistro);
            visitantesRegistrados.put(claveVisitante, visitante);
        }

        visitante.aumentarNumeroVisitas();
        System.out.println("\nVisitante registrado exitosamente!");
        System.out.println("Número de visitas: " + visitante.getNumeroVisitas());
    }
    
    private static void mostrarVisitantesRegistrados() {
        if (visitantesRegistrados.isEmpty()) {
            System.out.println("\nNo hay visitantes registrados.");
            return;
        }

        System.out.println("\n**Lista de visitantes registrados**");
        for (Visitante visitante : visitantesRegistrados.values()) {
            System.out.println(visitante);
        }
    }
    public static void modificarAnimal(int idAnimal) {
    Animal animal = buscarAnimal(idAnimal);

    if (animal == null) {
        System.out.println("No se encontró un animal con el ID " + idAnimal);
        return;
    }

    // Obtener la información del animal
    String tipoAnimal = animal.getTipoAnimal();
    String nombreAnimal = animal.getNombreAnimal();
    int idAnimalInt = animal.getIdAnimal();
    LocalDate fechaNacimiento = animal.getFechaNacimiento();
    LocalDate fechaLlegada = animal.getFechaLlegadaZoologico();
    double peso = animal.getPeso();
    ArrayList<String> enfermedades = animal.getEnfermedades();
    String frecuenciaAlimentacion = animal.getFrecuenciaAlimentacion();
    String tipoAlimentacion = animal.getTipoAlimentacion();
    boolean cuentaConVacunas = animal.getCuentaConVacunas();

    Scanner leer3 = new Scanner(System.in);

    System.out.println("¿Desea modificar el tipo de animal? (s/n): ");
    String respuestaTipoAnimal = leer3.nextLine();
    if (respuestaTipoAnimal.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo tipo de animal: ");
        tipoAnimal = leer3.nextLine();
    }

    System.out.println("¿Desea modificar el nombre del animal? (s/n): ");
    String respuestaNombreAnimal = leer3.nextLine();
    if (respuestaNombreAnimal.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo nombre del animal: ");
        nombreAnimal = leer3.nextLine();
    }
    System.out.println("¿Desea modificar la fecha de nacimiento del animal? (s/n): ");
    String respuestaFechaNacimientoAnimal = leer3.nextLine();
    if(respuestaFechaNacimientoAnimal.equalsIgnoreCase("s")) {
        System.out.println("Ingrese la nueva fecha de nacimiento (aaaa-mm-dd): ");
        String nuevaFechaNacimientoStr = leer3.nextLine();
        LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr);  
        animal.setFechaNacimiento(nuevaFechaNacimiento);

    }
    
    System.out.println("¿Desea modificar la fecha de llegada del animal? (s/n): ");
    String respuestaFechaLlegadaAnimal = leer3.nextLine();
    if (respuestaFechaLlegadaAnimal.equalsIgnoreCase("s")) {
        String nuevaFechaLlegadaStr = leer3.nextLine();
        LocalDate nuevaFechaLlegada = LocalDate.parse(nuevaFechaLlegadaStr);
        animal.setFechaLlegadaZoologico(nuevaFechaLlegada);
    }
    System.out.println("¿Desea modificar el peso del animal? (s/n): ");
    String respuestaPeso = leer3.nextLine();
    if (respuestaPeso.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo peso del animal: ");
        double nuevoPeso = leer3.nextDouble();
        animal.setPeso(nuevoPeso);
    }

    System.out.println("¿Desea modificar las enfermedades del animal? (s/n): ");
    String respuestaEnfermedades = leer3.nextLine();
    if (respuestaEnfermedades.equalsIgnoreCase("s")) {
        animal.setEnfermedades(new ArrayList<>());

        System.out.println("¿Cuántas enfermedades tiene el animal? ");
        int cantidadEnfermedades = leer3.nextInt();
        for (int i = 0; i < cantidadEnfermedades; i++) {
            System.out.println("Introduzca la enfermedad " + (i + 1) + ": ");
            String enfermedad = leer3.nextLine();
            animal.getEnfermedades().add(enfermedad);
        }
    }
    System.out.println("¿Desea modificar la frecuencia de alimentación del animal? (s/n): ");
    String respuestaFrecuenciaAlimentacion = leer3.nextLine();
    if (respuestaFrecuenciaAlimentacion.equalsIgnoreCase("s")) {
        System.out.println("Ingrese la nueva frecuencia de alimentación: ");
        String nuevaFrecuenciaAlimentacion = leer3.nextLine();
        animal.setFrecuenciaAlimentacion(nuevaFrecuenciaAlimentacion);
    }
    System.out.println("¿Desea modificar el tipo de alimentación del animal? (s/n): ");
    String respuestaTipoAlimentacion = leer3.nextLine();
    if (respuestaTipoAlimentacion.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo tipo de alimentación: ");
        String nuevoTipoAlimentacion = leer3.nextLine();
        animal.setTipoAlimentacion(nuevoTipoAlimentacion);
    }

    System.out.println("¿Desea modificar si el animal tiene vacunas? (s/n): ");
    String respuestaVacunas = leer3.nextLine();
    if (respuestaVacunas.equalsIgnoreCase("s")) {
        System.out.println("¿El animal tiene vacunas? (1=Sí, 2=No): ");
        int respuestaVacunasInt = leer3.nextInt();
        boolean nuevoCuentaConVacunas = respuestaVacunasInt == 1;
        animal.setCuentaConVacunas(nuevoCuentaConVacunas);
    }
    System.out.println("Información del animal actualizada correctamente.");
    }
    public static Animal buscarAnimal(int idAnimal) {
    for (Animal animal : animales) {
        if (animal.getIdAnimal()==idAnimal) {
            return animal;
        }
    }
    return null;
}
    
    public static void modificarVisitante(String nombre, String apellidos, String curp) {
    String claveVisitante = nombre.toLowerCase() + "_" + apellidos.toLowerCase() + "_" + curp.toLowerCase();
    Visitante visitante = visitantesRegistrados.get(claveVisitante);

    if (visitante != null) {
        System.out.println("Nombre: " + visitante.getNombre());
        System.out.println("Apellidos: " + visitante.getApellidos());
        System.out.println("Fecha de nacimiento: " + visitante.getFechaNacimiento());
        System.out.println("CURP: " + visitante.getCurp());
        System.out.println("Edad: " + visitante.getEdad());
        System.out.println("Número de visitas: " + visitante.getNumeroVisitas());

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
        System.out.println("1. Nombre");
        System.out.println("2. Apellidos");
        System.out.println("3. Fecha de nacimiento");
        System.out.println("4. CURP");

        int opcion = scanner2.nextInt();
        scanner2.nextLine(); // Consumir el salto de línea
        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner2.nextLine();
                visitante.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.print("Ingrese los nuevos apellidos: ");
                String nuevosApellidos = scanner2.nextLine();
                visitante.setApellidos(nuevosApellidos);
                break;
            case 3:
                System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                String nuevaFechaNacimientoString = scanner2.nextLine();
                LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoString, DateTimeFormatter.ISO_DATE);
                visitante.setFechaNacimiento(nuevaFechaNacimiento);
                break;
            case 4:
                System.out.print("Ingrese la nueva CURP: ");
                String nuevaCurp = scanner2.nextLine();
                visitante.setCurp(nuevaCurp);
                break;
        }

        visitantesRegistrados.put(claveVisitante, visitante);
        System.out.println("Datos del visitante modificados exitosamente!");
    } else {
        System.out.println("No se encontró un visitante con la clave " + claveVisitante);
    }
}
    public static void modificarEmpleado(int idEmpleado) {

    Empleado empleado = null;

    for (Empleado empleadoAux : empleados) {
        if (empleadoAux.getIdEmpleado() == idEmpleado) {
            empleado = empleadoAux;
            break;
        }
    }

    if (empleado != null) {
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Apellidos: " + empleado.getApellidos());
        System.out.println("Fecha de nacimiento: " + empleado.getFechaNacimiento());
        System.out.println("Fecha de ingreso: " + empleado.getFechaIngreso());
        System.out.println("RFC: " + empleado.getRfc());
        System.out.println("CURP: " + empleado.getCurp());
        System.out.println("Salario: " + empleado.getSalario());
        System.out.println("Rol: " + empleado.getRol());

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("¿Qué datos desea modificar? (Introduzca el número):");
        System.out.println("1. Nombre");
        System.out.println("2. Apellidos");
        System.out.println("3. Fecha de nacimiento");
        System.out.println("4. Fecha de ingreso");
        System.out.println("5. RFC");
        System.out.println("6. CURP");
        System.out.println("7. Salario");
        System.out.println("8. Rol");

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
                empleado.setFechaIngreso(nuevaFechaIngreso);
                break;
            case 5:
                System.out.print("Ingrese el nuevo RFC: ");
                String nuevoRfc = scanner2.nextLine();
                empleado.setRfc(nuevoRfc);
                break;
            case 6:
                System.out.print("Ingrese la nueva CURP: ");
                String nuevaCurp = scanner2.nextLine();
                empleado.setCurp(nuevaCurp);
                break;
            case 7:
                System.out.print("Ingrese el nuevo salario: ");
                double nuevoSalario = scanner2.nextDouble();
                empleado.setSalario(nuevoSalario);
                break;
            case 8:
                System.out.print("Ingrese el nuevo rol: ");
                String nuevoRol = scanner2.nextLine();
                empleado.setRol(nuevoRol);
                break;
        }

        System.out.println("Datos del empleado modificados exitosamente!");
    } else {
        System.out.println("No se encontró un empleado con el ID " + idEmpleado);
    }
}
    
    public static void eliminarEmpleado(int idEmpleado) {
    Empleado empleado = null;
    for (Empleado empleadoAux : empleados) {
        if (empleadoAux.getIdEmpleado() == idEmpleado) {
            empleado = empleadoAux;
            break;
        }
    }
    if (empleado != null) {
        empleados.remove(empleado);
        System.out.println("Empleado eliminado exitosamente!");
    } else {
        System.out.println("No se encontró un empleado con el ID " + idEmpleado);
    }
}
    public static void eliminarAnimal(String nombreAnimal) {
    Animal animal = null;

    for (Animal animalAux : animales) {
        if (animalAux.getNombreAnimal().equals(nombreAnimal)) {
            animal = animalAux;
            break;
        }
    }
    if (animal != null) {
        animales.remove(animal);
        System.out.println("Animal eliminado exitosamente!");
    } else {
        System.out.println("No se encontró un animal con el nombre " + nombreAnimal);
    }
}
    public static Visitante consultarVisitante(String claveVisitante) {
    Visitante visitante = visitantesRegistrados.get(claveVisitante);
    if (visitante != null) {
        System.out.println("**Información del visitante**");
        System.out.println("Nombre: " + visitante.getNombre());
        System.out.println("Apellidos: " + visitante.getApellidos());
        System.out.println("Fecha de nacimiento: " + visitante.getFechaNacimiento());
        System.out.println("CURP: " + visitante.getCurp());
        System.out.println("Edad: " + visitante.getEdad());
        System.out.println("Fecha de registro: " + visitante.getFechaRegistro());
        System.out.println("Número de visitas: " + visitante.getNumeroVisitas());
    } else {
        System.out.println("No se encontró un visitante con la clave " + claveVisitante);
    } return visitante;
}
    public static Empleado consultarEmpleado(int idEmpleado) {
    Empleado empleado = null;
    for (Empleado empleadoAux : empleados) {
        if (empleadoAux.getIdEmpleado() == idEmpleado) {
            empleado = empleadoAux;
            break;
        }
    }
    if (empleado != null) {
        System.out.println("**Información del empleado**");
        System.out.println("ID: " + empleado.getIdEmpleado());
        System.out.println("Nombre: " + empleado.getNombre());
        System.out.println("Apellidos: " + empleado.getApellidos());
        System.out.println("Fecha de nacimiento: " + empleado.getFechaNacimiento());
        System.out.println("Fecha de ingreso: " + empleado.getFechaIngreso());
        System.out.println("RFC: " + empleado.getRfc());
        System.out.println("CURP: " + empleado.getCurp());
        System.out.println("Salario: " + empleado.getSalario());
        System.out.println("Rol: " + empleado.getRol());
    } else {
        System.out.println("No se encontró un empleado con el ID " + idEmpleado);
    }return empleado;
}
    public static Animal consultarAnimal(String nombreAnimal) {

    // Buscar al animal por su nombre
    Animal animal = null;

    for (Animal animalAux : animales) {
        if (animalAux.getNombreAnimal().equals(nombreAnimal)) {
            animal = animalAux;
            break;
        }
    }

    if (animal != null) {
        System.out.println("**Información del animal**");
        System.out.println("Nombre: " + animal.getNombreAnimal());
        System.out.println("Tipo: " + animal.getTipoAnimal());
        System.out.println("ID: " + animal.getIdAnimal());
        System.out.println("Fecha de nacimiento: " + animal.getFechaNacimiento());
        System.out.println("Fecha de llegada al zoológico: " + animal.getFechaLlegadaZoologico());
        System.out.println("Peso: " + animal.getPeso());
        System.out.println("Enfermedades: " + animal.getEnfermedades());
        System.out.println("Frecuencia de alimentación: " + animal.getFrecuenciaAlimentacion());
        System.out.println("Tipo de alimentación: " + animal.getTipoAlimentacion());
        System.out.println("¿Cuenta con vacunas?: " + animal.getCuentaConVacunas());
    } else {
        System.out.println("No se encontró un animal con el nombre " + nombreAnimal);
    }

    return animal;
}
 public static void registrarActividad(Scanner scanner, ArrayList<Empleado> empleados, ArrayList<Animal> animales) {
     
    System.out.println("Seleccione el empleado responsable (ingrese el nombre): ");
    String nombreEmpleado = scanner.nextLine();
    Empleado empleadoResponsable = findEmpleadoByNombre(empleados, nombreEmpleado);
    if (empleadoResponsable == null) {
        System.out.println("No se encontró un empleado con el nombre " + nombreEmpleado);
        return;
    } else if (!empleadoResponsable.getRol().equals("mantenimiento")) {
        System.out.println("El empleado no tiene rol de mantenimiento.");
        return;
    }
    System.out.println("Introduzca el ID del animal: ");
    int idAnimal = scanner.nextInt();
    Animal animalEncontrado = findAnimalById(animales, idAnimal);

    if (animalEncontrado == null) {
        System.out.println("No se encontró un animal con el ID " + idAnimal);
        return;
    }
    // Seleccionar el proceso realizado
    System.out.println("Seleccione el proceso realizado: ");
    System.out.println("1. Mantenimiento");
    System.out.println("2. Limpieza");
    System.out.println("3. Alimentación");
    int opcionProceso = scanner.nextInt();
    String procesoRealizado = "";
    switch (opcionProceso) {
        case 1:
            procesoRealizado = "Mantenimiento";
            break;
        case 2:
            procesoRealizado = "Limpieza";
            break;
        case 3:
            procesoRealizado = "Alimentación";
            break;
    }
    System.out.println("Introduzca la fecha del proceso (dd/mm/aaaa): ");
    String fechaProcesoStr = scanner.nextLine();
    Date fechaProceso = new Date(fechaProcesoStr);

    System.out.println("Introduzca las observaciones (opcional): ");
    String observaciones = scanner.nextLine();

    Actividad actividad = new Actividad(empleadoResponsable, idAnimal, procesoRealizado, fechaProceso, observaciones);

    actividades.add(actividad);

    System.out.println("Actividad registrada correctamente.");
}
   


}//Fin de la clase
