import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private double balance;
    private String pin;
    private boolean isLocked;
    private List<Transaction> transactionHistory;

    public BankAccount(double initialBalance, String pin) {
        balance = initialBalance;
        this.pin = pin;
        isLocked = false;
        transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (!isLocked) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add(new Transaction("Withdrawal", amount));
                System.out.println("Withdrawn: $" + amount);
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        } else {
            System.out.println("Account is locked. Please unlock it to make transactions.");
        }
    }

    public boolean changePin(String oldPin, String newPin) {
        if (oldPin.equals(pin)) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
            return true;
        } else {
            System.out.println("Incorrect old PIN. PIN change failed.");
            return false;
        }
    }

    public void lockAccount() {
        isLocked = true;
        System.out.println("Account locked.");
    }

    public void unlockAccount(String pin) {
        if (pin.equals(this.pin)) {
            isLocked = false;
            System.out.println("Account unlocked.");
        } else {
            System.out.println("Incorrect PIN. Account unlock failed.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": $" + transaction.getAmount());
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        userAccount = account;
    }

    public void checkBalance() {
        System.out.println("Your account balance is: $" + userAccount.getBalance());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: $");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        System.out.print("Create a PIN for your account: ");
        String pin = scanner.nextLine();

        BankAccount account = new BankAccount(initialBalance, pin);
        ATM atm = new ATM(account);

        boolean running = true;
        while (running) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Lock Account");
            System.out.println("6. Unlock Account");
            System.out.println("7. Transaction History");
            System.out.println("8. Exit");
            System.out.print("Select an option (1-8): ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.userAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter your PIN: ");
                    String enteredPin = scanner.next();
                    if (enteredPin.equals(atm.userAccount.getPin())) {
                        System.out.print("Enter the withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.userAccount.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Incorrect PIN. Withdrawal failed.");
                    }
                    break;
                case 4:
                    System.out.print("Enter your current PIN: ");
                    String oldPin = scanner.next();
                    System.out.print("Enter your new PIN: ");
                    String newPin = scanner.next();
                    atm.userAccount.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.userAccount.lockAccount();
                    break;
                case 6:
                    System.out.print("Enter your PIN to unlock: ");
                    String unlockPin = scanner.next();
                    atm.userAccount.unlockAccount(unlockPin);
                    break;
                case 7:
                    atm.userAccount.displayTransactionHistory();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}
