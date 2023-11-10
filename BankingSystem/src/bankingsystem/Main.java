package bankingsystem;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        while (true) {
           
            System.out.println("Welcome to the Bank!");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    User user = new User(name, age);
                    bank.openAccount(user);
                    break;
                case 2:
                    
                    System.out.print("Enter account number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccountNumber, depositAmount);
                    break;
                case 3:
                    
                    System.out.print("Enter account number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccountNumber, withdrawAmount);
                    break;
                case 4:
                   
                    System.out.print("Enter account number: ");
                    int checkBalanceAccountNumber = scanner.nextInt();
                    bank.checkBalance(checkBalanceAccountNumber);
                    break;
                case 5:
                  
                    List<User> allUsers = bank.getAllUsers();
                    if (allUsers != null) {
                        System.out.println("All Users:");
                        for (User u : allUsers) {
                            System.out.println(" Name: " + u.getName() + ", Age: " + u.getAge());
                        }
                    }
                    break;
                case 6:
                    
                    bank.saveAccounts();
                    System.out.println("Thank you for using the Bank. Goodbye!");
                    System.exit(0);
                    break;
                default:
                   
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
