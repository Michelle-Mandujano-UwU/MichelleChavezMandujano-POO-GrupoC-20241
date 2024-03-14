
package menubank;

import java.util.Scanner;


public class MenuBank {

    
    public static void main(String[] args) {
        boolean main = true;
        int opciones;
        int opciones2; 
        double money;
        Bank bank = new Bank();
        Scanner leer = new Scanner(System.in);
        Scanner cuenta = new Scanner(System.in);

        bank.getEmployee().add(new Employee("Michelle", 21, 15000, 123456, 'A'));
        bank.getEmployee().add(new Employee("Diana", 18, 12000, 125874, 'B'));

        while (main) {
            System.out.println("\n Elije una opcion: \n1- Mostrar los datos del usuario \n2- Modificar o mostrar los datos en el banco \n3- Salir");
            opciones = leer.nextInt();

            switch (opciones) {
                case 1:
                    System.out.println("\n Elije una opcion: \n1- Mostrar toda la informacion de los usuarios \n2- Mostrar informacion de un usuario \n3- Salir ");

                    opciones = leer.nextInt();

                    switch (opciones) {
                        case 1:
                            if (bank.getEmployee().size() > 0) {
                                for (int j = 0; j < bank.getEmployee().size(); j++) {
                                    System.out.println(bank.getEmployee().get(j).getName());
                                    for (int j2 = 0; j2 < bank.getEmployee().get(j).getAccount().size(); j2++) {
                                        System.out.println(j2 + 1 + "*"
                                                + bank.getEmployee().get(j).getAccount().get(j2).getAccountNumber()
                                                + " "
                                                + bank.getEmployee().get(j).getAccount().get(j2).getTypeAccount());
                                    }
                                }
                            } else {
                                System.out.println("No se tiene ningun usuario para mostrar \n");
                            }
                            break;
                        case 2:
                            System.out.println("Elija de que empleado mostrar su infomacion");
                            for (int j = 0; j < bank.getEmployee().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(j).getName());
                            }
                            opciones = leer.nextInt();

                            for (int j2 = 0; j2 < bank.getEmployee().get(opciones).getAccount().size(); j2++) {
                                System.out.println(j2 + 1 + "*"
                                        + bank.getEmployee().get(opciones).getAccount().get(j2).getAccountNumber()
                                        + " "
                                        + bank.getEmployee().get(opciones).getAccount().get(j2).getTypeAccount());
                            }

                            break;
                    }

                    break;
                case 2:
                    System.out.println("\n Elije una opcion: \n1- Añadir cuenta extra\n2- Retirar \n3- Depositar");
                    opciones = leer.nextInt();
                    switch (opciones) {
                        case 1:
                            System.out.println("Elija a que empleado agregar ");
                            for (int j = 0; j < bank.getEmployee().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(j).getName());
                            }

                            opciones = leer.nextInt();
                            System.out.println("Intruduce nuevo numero de Cuenta");
                            long accountNew = leer.nextLong();
                            System.out.println("Intruduce el tipo de Cuenta");
                            char nType = cuenta.nextLine().charAt(0);
                            bank.getEmployee().get(opciones).addBankAcc(accountNew, nType);
                            System.out.println("Se ah añadido la cuenta");
                            break;
                  
                        case 2:
                            System.out.println("De que usuario sera el retiro");
                            for (int j = 0; j < bank.getEmployee().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(j).getName());
                            }
                            opciones = leer.nextInt();
                            System.out.println("Elejir cuenta");
                            for (int j = 0; j < bank.getEmployee().get(opciones).getAccount().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(opciones).getAccount().get(j));
                            }
                            opciones2 = leer.nextInt();
                            System.out.println("El dinero en cuenta es:"
                                    + bank.getEmployee().get(opciones).getAccount().get(opciones2).getAmount()
                                    + "\nCantidad a retirar");
                            money = leer.nextDouble();
                            bank.getEmployee().get(opciones).withdraw(money, opciones2);
                            break;
                        case 3:
                            System.out.println("Deposito a:");
                            for (int j = 0; j < bank.getEmployee().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(j).getName());
                            }
                            opciones = leer.nextInt();
                            System.out.println("Cuenta:");
                            for (int j = 0; j < bank.getEmployee().get(opciones).getAccount().size(); j++) {
                                System.out.println(j + "*" + bank.getEmployee().get(opciones).getAccount().get(j));
                            }
                            opciones2 = leer.nextInt();
                            System.out.println("El dinero en cuenta es:"
                                    + bank.getEmployee().get(opciones).getAccount().get(opciones2).getAmount()
                                    + "\nCantidad a depositar");
                            money = leer.nextDouble();
                            bank.getEmployee().get(opciones).deposit(money, opciones2);
                            break;
                            
                        default:
                            
                            break;
                    }
                  
                    
                case 3:
                    
                    main = false;
                    
                    break;
                    
                default:
                    
                    break;
            }
        }
    }
    
    
}
