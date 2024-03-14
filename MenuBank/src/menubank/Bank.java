
package menubank;

import java.util.ArrayList;


public class Bank {
    private ArrayList <Employee> employee = new ArrayList<Employee>();

    

    

    public void addEmployees(String name, int age, double salary, long accountNumber, char typeAccount) {
        employee.add(new Employee(name, age, salary, accountNumber, typeAccount));
    }

    public void addBankAcc(int i, long bankAccount, char type) {
        employee.get(i).addBankAcc(bankAccount, type);
    }
    public ArrayList<Employee> getEmployee() {
        return employee;
    }
}
