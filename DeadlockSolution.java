public class DeadlockSolution {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final Object lock3 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock 2...");
                    synchronized (lock3) {
                        System.out.println("Thread 1: Acquired lock 3!");
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock 1...");
                synchronized (lock2) {
                    System.out.println("Thread 2: Acquired lock 2...");
                    synchronized (lock3) {
                        System.out.println("Thread 2: Acquired lock 3!");
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 3: Holding lock 1...");
                synchronized (lock2) {
                    System.out.println("Thread 3: Acquired lock 2...");
                    synchronized (lock3) {
                        System.out.println("Thread 3: Acquired lock 3!");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
