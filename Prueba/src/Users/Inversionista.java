package Users;

import Sucursales.SucursalAcueducto;
import Sucursales.SucursalMadero;
import Users.Empleados.Empleado;
import Users.utils.UsuarioUtils;
import Users.utils.constantes.Rol;
import Sistemas.Menus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Inversionista extends Usuario {
    static String nombre;
    static String apellido;
    static String domicilio;
    static String nacimiento;
    static String rfc;
    static String curp;
    static String user;
    static String password;
    static double saldo;
    static LocalDate localDate;
    static Inversion inversion;
    static Scanner sc = new Scanner(System.in);

    Inversionista(String nombre, String apellido, String direccion, String nacimiento,
                  String rfc, String curp, String user, String password,
                  double saldo){
        super(nombre, apellido, direccion, nacimiento, rfc, curp, user, password, Rol.INVERSIONISTA);
    }
    public static void registrarInversionistaMadero(){
        ArrayList<String> datosComun = UsuarioUtils.registarUsuarioComun();
        nombre = datosComun.get(0);
        apellido = datosComun.get(1);
        domicilio = datosComun.get(2);
        nacimiento = datosComun.get(3);
        rfc = datosComun.get(4);
        curp = datosComun.get(5);
        user = datosComun.get(6);
        password = datosComun.get(7);
        System.out.println("Inserte el saldo inicial del inversionista");
        while (true){
            try {
                saldo = sc.nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.println("Ingreso un valor invalido intentelo de nuevo");
            }
        }

        Inversionista inversionista = new Inversionista(nombre, apellido, domicilio, nacimiento, rfc, curp, user, password, saldo);
        SucursalMadero.getInversionistas().add(inversionista);
    }


    public static void proveerFondos(){
        System.out.println("A cual sucursal le gustaria añadir fondos?");
        System.out.println("1.Madero");
        System.out.println("2.Acueducto");
        System.out.println("3.Salir");
        Scanner sc = new Scanner(System.in);
        int sucursal;
        double cantidad;
        while(true){
            try {
                sucursal = sc.nextInt();
                if(sucursal != 1 && sucursal != 2 && sucursal != 3){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("El valor insertado es incorrecto o no existe, intentelo de nuevo:");
                sc.nextLine();
            }
        }
        while (true){
            System.out.println("Ingrese la cantidad a invertir");
            while(true){
                try {
                    cantidad = sc.nextDouble();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Ocurrio un error intentar de nuevo");
                }
            }
            if (cantidad <= saldo){
                localDate = LocalDate.now();
                String fecha = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                inversion = new Inversion(nombre + apellido, fecha, String.valueOf(cantidad));
                System.out.println("Se ah hecho la inversion correctamente");
                break;
            }else{
                System.out.println("La cantidad que se quiere invertir es mayor al saldo actual");
                System.out.println("Intentelo de nuevo");
            }
        }

        switch (sucursal){
            case 1:
                SucursalMadero.setHistorialInversiones(inversion.formated);
                break;
            case 2:
                SucursalAcueducto.setHistorialInversiones(inversion.formated);
                break;
            case 3:
                Menus menu = new Menus();
                menu.mostrarMenuInversionista(user);
                break;
        }
    }

    public static void setSaldo(double Nuevsaldo) {
        saldo = Nuevsaldo;
    }
}

