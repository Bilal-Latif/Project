
public class OrderProcessingSystem {

    public static void main(String[] args) {
        Product mobile = new Product("Mobile", 5);
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        Order order1 = new Order(mobile, "Customer 1", 2, paymentProcessor);
        Order order2 = new Order(mobile, "Customer 2", 3, paymentProcessor);
        Order order3 = new Order(mobile, "Customer 3", 1, paymentProcessor);

        order1.start();
        order2.start();
        order3.start();

        try {
            order1.join();
            order2.join();
            order3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Remaining stock of " + mobile.getProductName() + ": " + mobile.getStockQuantity());
    }
}
