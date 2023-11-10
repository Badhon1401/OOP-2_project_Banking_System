package bankingsystem;
import java.io.*;
import java.util.*;

public class Bank {
    private Map<Integer, Account> accounts;
    private static final String FILE_NAME = "accounts.txt";
    private Set<Integer> generatedAccountNumbers;

    public Bank() {
        accounts = new HashMap<>();
        generatedAccountNumbers = new HashSet<>();
        loadAccounts();
    }
    
    public List<User> getAllUsers() {
        if (!accounts.isEmpty()) {
            List<User> userList = new ArrayList<>();
            for (Account account : accounts.values()) {
                userList.add(account.getUser());
            }
            return userList;
        } else {
            System.out.println("No existing users found!");
            return null;
        }
    }

    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accountNumber = Integer.parseInt(parts[0]);
                String userName = parts[1];
                int userAge = Integer.parseInt(parts[2]);
                double balance = Double.parseDouble(parts[3]);

                User user = new User(userName, userAge);
                Account account = new Account(accountNumber, user);
                account.deposit(balance);
                accounts.put(accountNumber, account);
                generatedAccountNumbers.add(accountNumber);
            }
        } catch (IOException e) {
            // Handle exceptions (file not found, invalid format, etc.)
            e.printStackTrace();
        }
    }

    void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accounts.values()) {
                User user = account.getUser();
                writer.write(account.getAccountNumber() + "," + user.getName() + "," +
                        user.getAge() + "," + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAccount(User user) {
    	int accountNumber;
    	while(true) {
        accountNumber = generateAccountNumber();
        if((generatedAccountNumbers.contains(accountNumber))){
        	break;
        }
    	}
        Account account = new Account(accountNumber, user);
        accounts.put(accountNumber, account);
        saveAccounts();
        System.out.println("Account created successfully. Account Number: " + accountNumber);
    }

    public void deposit(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            saveAccounts();
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            saveAccounts();
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void checkBalance(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getUser().getName());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

     private int generateAccountNumber() {
        Random random = new Random();
        int minAccountNumber = 1000000;
        int maxAccountNumber = 2000000;
        int accountNumber;

        do {
            accountNumber = minAccountNumber + random.nextInt(maxAccountNumber - minAccountNumber);
        } while (generatedAccountNumbers.contains(accountNumber));

        generatedAccountNumbers.add(accountNumber);
        return accountNumber;
    }
}
