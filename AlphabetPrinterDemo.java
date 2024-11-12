import java.util.Random;

class AlphabetPrinter implements Runnable {
    // Counter to track how many letters have been printed
    private static int count = 0;

    @Override
    public void run() {
        // Generate a random alphabet (A-Z)
        Random random = new Random();
        char randomLetter = (char) ('A' + random.nextInt(26));
        
        // Print the character
        System.out.print(randomLetter + " ");
        
        // Increment counter after printing
        count++;

        // Sleep for a random time between 100ms to 500ms to simulate fluctuating visualization
        try {
            Thread.sleep(100 + random.nextInt(400));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class AlphabetPrinterDemo {
    public static void main(String[] args) {
        // Create a list of threads to run
        Thread[] threads = new Thread[26];
        
        for (int i = 0; i < 26; i++) {
            // Each thread will print a random alphabet
            threads[i] = new Thread(new AlphabetPrinter());
        }

        // Start all threads
        for (int i = 0; i < 26; i++) {
            threads[i].start();
        }

        // Ensure all threads finish before main exits
        for (int i = 0; i < 26; i++) {
            try {
                threads[i].join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println("\nAll characters printed.");
    }
}
