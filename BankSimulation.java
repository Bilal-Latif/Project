class BankAccount {
    private int balance;

    // Constructor to initialize balance
    public BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    // Synchronized method to ensure thread-safe withdrawal
    public synchronized boolean withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            try {
                Thread.sleep(100); // Simulate some processing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", remaining balance: " + balance);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient funds.");
            return false;
        }
    }

    // Method to get the current balance
    public int getBalance() {
        return balance;
    }
}

class User implements Runnable {
    private BankAccount account;
    private int withdrawalAmount;

    // Constructor
    public User(BankAccount account, int withdrawalAmount) {
        this.account = account;
        this.withdrawalAmount = withdrawalAmount;
    }

    // Run method to simulate withdrawal in a separate thread
    @Override
    public void run() {
        account.withdraw(withdrawalAmount);
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        // Create a bank account with initial balance of 50000
        BankAccount account = new BankAccount(50000);

        // Create User A and User B threads
        Thread userA = new Thread(new User(account, 45000), "User A");
        Thread userB = new Thread(new User(account, 20000), "User B");

        // Start both threads (simulating concurrent access)
        userA.start();
        userB.start();

        try {
            // Wait for both threads to finish
            userA.join();
            userB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final balance after both transactions
        System.out.println("Final account balance: " + account.getBalance());
    }
}
