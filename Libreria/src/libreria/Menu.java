package libreria;
import java.util.Scanner;
import usuarios.Usuario;


public class Menu {
    static Scanner scanner= new Scanner (System.in);
    private Libreria libreria = new Libreria();
    
    public void iniciarSesión() {
        boolean datosCorrectos = false;
        do {
        System.out.println("\nBienvenido al Sistema de la Biblioteca");
        System.out.println("Para continuar, inicia sesión\n");
        
        System.out.println("Ingresa tu usuario");
        String usuario = scanner.nextLine();
        
        System.out.println("Ingresa tu contraseña");
        String contraseña = scanner.nextLine();
        
        Usuario usuarioActual= libreria.verificarInicioSesión(usuario, contraseña);
        if (usuarioActual!=null) {
            datosCorrectos = true;
            seleccionarMenu(usuarioActual);
        } else {
            System.out.println("Usuario o contraseña incorrectos. Intenta de nuevo");
            }
        } while (!datosCorrectos);
    }
    
    private void seleccionarMenu (Usuario usuario){
        switch (usuario.getRol()){
            case CLIENTE :  mostrarMenuCliente();
            case ASISTENTE: mostrarMenuAsistente ();
            case GERENTE: mostrarMenuGerente ();
        }
    }
    
    public void mostrarMenuCliente(){ //Libros, rentas y cerrar sesión
        int op;
        do {
            System.out.println("\nBIENVENIDO CLIENTE");
            System.out.println("Elige una opción: ");
            System.out.println("1. Consultar libros\n2. Rentar libro\n3. Cerrar sesión");
            op= scanner.nextInt();
        } while (op>3);
        switch(op) {
            case 1: consultarLibros();
                    break;
            case 2: rentarLibro();
                    break;
            case 3: cerrarSesión();
                    break;
        }
       
    }
    
    public void mostrarMenuAsistente(){
        int op;
        do {
            System.out.println("\nBIENVENIDO ASISTENTE");
            System.out.println("Elige una opción: ");
                System.out.println("1.Registrar libros prestados \n2. Crear credencial para préstamso"
                        + "\n3. Registrar opiniones de los clientes\n4. Cerrar sesión");
            op= scanner.nextInt();
        } while (op>4);
        switch(op) {
            case 1: librosPrestados();
                    break;
            case 2: crearCredenciales();
                    break;
            case 3: opinionesClientes();
                    break;
            case 4: cerrarSesión();
                    break;
        }
    }
    
    public void mostrarMenuGerente (){ //Eliminar asistente y hacer rentas
        int op;
        do {
            System.out.println("\nBIENVENIDO GERENTE");
            System.out.println("Elige una opción: ");
                System.out.println("1. Rentar libro\n2. Asignar asistente\n3. Eliminar asistente\n"
                        + "4. Agregar nuevos libros\n5. Eliminar libros\n6. Cerrar sesión");
            op= scanner.nextInt();
        } while (op>6);
        switch(op) {
            case 1: rentarLibroGerente();
                    break;
            case 2: asignarAsistente();
                    break;
            case 3: eliminarAsistente();
                    break;
            case 4: agregarLibros();
                    break;
            case 5: eliminarLibro();
                    break;
            case 6: cerrarSesión();
                    break;   
        }
    }
    
    private void cerrarSesión(){
        
    }
    ////////////////////Cliente/////////////////////////////
    private void consultarLibros(){
        
    }
    private void rentarLibro(){
        
    }
    
    ////////////////////Gerente///////////////////////////////
    private void rentarLibroGerente(){
        
    }
    private void asignarAsistente(){
        
    }
    private void eliminarAsistente(){
        
    }
    private void agregarLibros(){
        
    }
    private void eliminarLibro(){
        
    }
    ///////////////////Asistente////////////////////////////
    private void librosPrestados(){
        
    }
    private void crearCredenciales (){
        
    }
    private void opinionesClientes(){
        
    }
}
