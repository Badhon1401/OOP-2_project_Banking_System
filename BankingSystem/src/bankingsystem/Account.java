package bankingsystem;
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private int accountNumber;
    private double balance;
    private User user;
    public Account(int accountNumber, User user) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public User getUser() {
        return user;
    }
}
