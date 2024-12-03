
public class PaymentProcessor {

    public synchronized boolean processPayment(String customerName, double amount) {
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment processed for customer: " + customerName + " Amount: " + amount);
        return true; 
    }
}
