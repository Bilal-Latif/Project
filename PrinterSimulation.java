class PrinterTray {
    private int pages;

    // Constructor to initialize the printer tray with a certain number of pages
    public PrinterTray(int initialPages) {
        pages = initialPages;
    }

    // Synchronized method to get pages (for the printing thread)
    public synchronized boolean getPages(int requiredPages) {
        if (pages >= requiredPages) {
            pages -= requiredPages;
            System.out.println("Printing " + requiredPages + " pages. Pages remaining: " + pages);
            return true;
        } else {
            System.out.println("Not enough pages. Required: " + requiredPages + ", Available: " + pages);
            return false;
        }
    }

    // Synchronized method to add pages (for the calculator thread)
    public synchronized void addPages(int newPages) {
        pages += newPages;
        System.out.println("Added " + newPages + " pages to the tray. Total pages: " + pages);
        // Notify waiting threads (e.g., the printing thread) that pages are available
        notify();
    }

    // Method to get the current number of pages
    public synchronized int getPageCount() {
        return pages;
    }
}

class PrinterJob implements Runnable {
    private PrinterTray tray;
    private int pagesToPrint;

    public PrinterJob(PrinterTray tray, int pagesToPrint) {
        this.tray = tray;
        this.pagesToPrint = pagesToPrint;
    }

    @Override
    public void run() {
        synchronized (tray) {
            // If there are not enough pages, wait until more are available
            while (!tray.getPages(pagesToPrint)) {
                try {
                    System.out.println("Printer is waiting for more pages...");
                    tray.wait(); // Wait for more pages to be added to the tray
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class TrayCalculator implements Runnable {
    private PrinterTray tray;

    public TrayCalculator(PrinterTray tray) {
        this.tray = tray;
    }

    @Override
    public void run() {
        // Simulate adding pages to the tray periodically
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(3000); // Wait for 3 seconds before adding more pages
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Add 10 pages every 3 seconds
            tray.addPages(10);
        }
    }
}

public class PrinterSimulation {
    public static void main(String[] args) {
        // Create a printer tray with an initial number of 10 pages
        PrinterTray tray = new PrinterTray(10);

        // Create the printer job that needs 15 pages
        PrinterJob printerJob = new PrinterJob(tray, 15);
        Thread printThread = new Thread(printerJob);

        // Create the tray calculator that will periodically add pages to the tray
        TrayCalculator trayCalculator = new TrayCalculator(tray);
        Thread calculatorThread = new Thread(trayCalculator);

        // Start the threads
        printThread.start();
        calculatorThread.start();

        try {
            // Wait for both threads to complete
            printThread.join();
            calculatorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printing job completed.");
    }
}
