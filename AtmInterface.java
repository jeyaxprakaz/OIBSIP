import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private List<String> transactions;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            addTransaction("Withdrawal - Amount: $" + amount);
            balance -= amount;
        } else {
            System.out.println("Invalid amount or insufficient balance. Withdrawal failed.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            addTransaction("Deposit - Amount: $" + amount);
            balance += amount;
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public void transfer(double amount) {
        if (amount > 0 && amount <= balance) {
            addTransaction("Transfer - Amount: $" + amount);
            balance -= amount;
        } else {
            System.out.println("Invalid amount or insufficient balance. Transfer failed.");
        }
    }
}

class ATM {
    private User currentUser;

    public void start() {
        System.out.println("Welcome to the ATM!");
        authenticateUser();
        displayMenu();
    }

    private void authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        // Validate user credentials (compare with stored user data)
        // For simplicity, let's assume there's a predefined user
        if ("12345".equals(userId) && "6789".equals(userPin)) {
            currentUser = new User(userId, userPin, 10000.0); // Initial balance: $10,000
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Exiting...");
            System.exit(0);
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    handleTransactionsHistory();
                    break;
                case 2:
                    handleWithdraw();
                    break;
                case 3:
                    handleDeposit();
                    break;
                case 4:
                    handleTransfer();
                    break;
                case 5:
                    System.out.println("Exiting ATM. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void handleTransactionsHistory() {
        System.out.println("Displaying Transactions History:");
        List<String> transactions = currentUser.getTransactions();
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void handleWithdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        currentUser.withdraw(amount);
        System.out.println("Withdrawal successful. Remaining balance: $" + currentUser.getBalance());
    }

    private void handleDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
        System.out.println("Deposit successful. Updated balance: $" + currentUser.getBalance());
    }

    private void handleTransfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();
        currentUser.transfer(amount);
        System.out.println("Transfer successful. Remaining balance: $" + currentUser.getBalance());
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
