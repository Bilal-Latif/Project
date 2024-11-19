class PrinterTray {
    private int pages;

    public PrinterTray(int initialPages) {
        pages = initialPages;
    }

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

    public synchronized void addPages(int newPages) {
        pages += newPages;
        System.out.println("Added " + newPages + " pages to the tray. Total pages: " + pages);
        // Notify waiting threads (e.g., the printing thread) that pages are available
        notify();
    }

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

    public void run() {
        synchronized (tray) {
            while (!tray.getPages(pagesToPrint)) {
                try {
                    System.out.println("Printer is waiting for more pages...");
                    tray.wait(); 
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

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tray.addPages(10);
        }
    }
}

public class PrinterSimulation {
    public static void main(String[] args) {
        PrinterTray tray = new PrinterTray(10);
        PrinterJob printerJob = new PrinterJob(tray, 15);
        Thread printThread = new Thread(printerJob);
        TrayCalculator trayCalculator = new TrayCalculator(tray);
        Thread calculatorThread = new Thread(trayCalculator);
        printThread.start();
        calculatorThread.start();
        try {
            printThread.join();
            calculatorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Printing job completed.");
    }
}
