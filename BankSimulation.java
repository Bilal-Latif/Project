class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        balance = initialBalance;
    }

    public synchronized boolean withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            try {
                Thread.sleep(100);
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

    public int getBalance() {
        return balance;
    }
}

class User implements Runnable {
    private BankAccount account;
    private int withdrawalAmount;

    public User(BankAccount account, int withdrawalAmount) {
        this.account = account;
        this.withdrawalAmount = withdrawalAmount;
    }

    public void run() {
        account.withdraw(withdrawalAmount);
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        // Create a bank account with initial balance of 50000
        BankAccount account = new BankAccount(50000);
        Thread userA = new Thread(new User(account, 45000), "User A");
        Thread userB = new Thread(new User(account, 20000), "User B");
        userA.start();
        userB.start();
        try {
            userA.join();
            userB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final account balance: " + account.getBalance());
    }
}
