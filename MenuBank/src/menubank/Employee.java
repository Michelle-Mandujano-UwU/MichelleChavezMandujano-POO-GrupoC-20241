
package menubank;

import java.util.ArrayList;



public class Employee {
     private String name;
    private int age;
    private double salary;
    private int limCuenta=10;
    private ArrayList <BankAccount> account = new ArrayList<BankAccount>(); 
    private int moneyAccount=0;
    

    public Employee(String name, int age, double salary, long accountNumber, char typeAccount) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.account.add(new BankAccount(accountNumber, typeAccount));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18 && age <= 65) {
            this.age = age;
        } else {
            System.out.println("Invalid age");
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary");
        }
    }


    public int moneyAccount() {
        return moneyAccount;
    }

    public int limCuenta() {
        return limCuenta;
    }
 

    public void withdraw(double money, int i){
        
        if (money <= this.account.get(i).getAmount()) {
            this.account.get(i).setAmount(this.account.get(i).getAmount()-money);
            System.out.println(String.format("Saldo en cuenta: %f", this.account.get(i).getAmount()));
        }else{
            System.out.println("Saldo insuficiente");
        }
        
    }
    
    public void deposit(double amount, int i){
        this.account.get(i).setAmount(this.account.get(i).getAmount()+amount);
        System.out.println(String.format("Saldo actual: %f", this.account.get(i).getAmount()));
    }

    public BankAccount getBankAcc(int i){
        return account.get(i);
    }
    
    public void addBankAcc(long accountN, char type){
        this.account.add(new BankAccount(accountN, type));
    }
    
    public ArrayList<BankAccount> getAccount() {
        return account;
    }
}
