
package menubank;


public class BankAccount {
    private long accountNumber;
    private char typeAccount;
    private double amount;
    public BankAccount(long accountNumber, char typeAccount) {
        this.accountNumber = accountNumber;
        this.typeAccount = Character.toUpperCase(typeAccount);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public void setTypeAccount(char typeAccount) {
        this.typeAccount = typeAccount;
    }
    public char getTypeAccount() {
        return typeAccount;
    }

}
