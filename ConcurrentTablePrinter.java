
public class ConcurrentTablePrinter {

    static String[] rollNumbers = {
        "2019-SE-001", "2019-SE-002", "2019-SE-003", "2019-SE-004",
        "2019-SE-005", "2019-SE-006", "2019-SE-007", "2019-SE-008"
    };

    static String[] birthDates = {
        "05-October", "12-September", "22-June", "15-January",
        "03-March", "09-November", "25-December", "30-April"
    };

    static class RollNumberPrinter extends Thread {
 
        public void run() {
            System.out.println("Roll Numbers:");
            for (String rollNumber : rollNumbers) {
                System.out.println(rollNumber);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class BirthDatePrinter extends Thread {

        public void run() {
            System.out.println("\nBirth Dates:");
            for (String birthDate : birthDates) {
                System.out.println(birthDate);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread rollNumberThread = new RollNumberPrinter();
        Thread birthDateThread = new BirthDatePrinter();

        rollNumberThread.start();
        birthDateThread.start();

        try {
            rollNumberThread.join();
            birthDateThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nBoth tables printed concurrently.");
    }
}
