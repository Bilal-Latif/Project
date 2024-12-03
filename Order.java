
public class Order extends Thread {
    private Product product;
    private String customerName;
    private int orderQuantity;
    private PaymentProcessor paymentProcessor;

    public Order(Product product, String customerName, int orderQuantity, PaymentProcessor paymentProcessor) {
        this.product = product;
        this.customerName = customerName;
        this.orderQuantity = orderQuantity;
        this.paymentProcessor = paymentProcessor;
    }

    public void run() {
        if (product.processOrder(orderQuantity)) {
            if (paymentProcessor.processPayment(customerName, orderQuantity * 90)) { 
                System.out.println("Order placed successfully for " + customerName + " for " + orderQuantity + " " + product.getProductName());
            } else {
                System.out.println("Payment failed for " + customerName);
            }
        } else {
            System.out.println("Not enough stock for " + customerName);
        }
    }
}
