import java.util.Scanner;

public class ATMInterface {

    static Scanner sc = new Scanner(System.in);

    static double balance = 10000;
    static String pin = "1234";
    static String[] history = new String[100];
    static int transactionCount = 0;

    public static void main(String[] args) {
        welcome();
        login();
        menu();
    }

    public static void welcome() {
        System.out.println("=================================");
        System.out.println("         ATM INTERFACE");
        System.out.println("=================================");
    }

    public static void login() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter ATM PIN: ");
            String enteredPin = sc.next();

            if (enteredPin.equals(pin)) {
                System.out.println("Login Successful!");
                return;
            } else {
                attempts--;
                System.out.println("Wrong PIN!");
                System.out.println("Attempts Left: " + attempts);
            }
        }

        System.out.println("Account Blocked!");
        System.exit(0);
    }

    public static void menu() {
        boolean run = true;

        while (run) {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = getInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    showHistory();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    run = false;
                    System.out.println("Thank you for using ATM.");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void checkBalance() {
        System.out.println("Current Balance: Rs " + balance);
    }

    public static void deposit() {
        System.out.print("Enter deposit amount: ");
        double amount = getDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        balance += amount;
        addTransaction("Deposited Rs " + amount);
        System.out.println("Deposit Successful.");
        System.out.println("Updated Balance: Rs " + balance);
    }

    public static void withdraw() {
        System.out.print("Enter withdraw amount: ");
        double amount = getDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= amount;
        addTransaction("Withdrawn Rs " + amount);
        System.out.println("Withdrawal Successful.");
        System.out.println("Updated Balance: Rs " + balance);
    }

    public static void showHistory() {
        if (transactionCount == 0) {
            System.out.println("No Transactions Yet.");
            return;
        }

        System.out.println("\n====== TRANSACTION HISTORY ======");

        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + history[i]);
        }
    }

    public static void changePin() {
        System.out.print("Enter old PIN: ");
        String oldPin = sc.next();

        if (!oldPin.equals(pin)) {
            System.out.println("Wrong Old PIN!");
            return;
        }

        System.out.print("Enter new PIN: ");
        String newPin = sc.next();

        if (newPin.length() != 4) {
            System.out.println("PIN must be 4 digits!");
            return;
        }

        pin = newPin;
        System.out.println("PIN Changed Successfully!");
        addTransaction("PIN Changed");
    }

    public static void addTransaction(String transaction) {
        history[transactionCount] = transaction;
        transactionCount++;
    }

    public static int getInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Enter valid integer: ");
                sc.next();
            }
        }
    }

    public static double getDouble() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.print("Enter valid number: ");
                sc.next();
            }
        }
    }
}