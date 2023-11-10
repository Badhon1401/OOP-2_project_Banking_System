package bankingsystem;
import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private int accountNumber=generateAccountNumber();  // New field to store the account number

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    private int generateAccountNumber() {
        Random random = new Random();
        int minAccountNumber = 1000000;
        int maxAccountNumber = 2000000;
        int accountNumber;
        accountNumber = minAccountNumber + random.nextInt(maxAccountNumber - minAccountNumber);
        return accountNumber;
    }

}
