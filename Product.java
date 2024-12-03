
public class Product {
    private String productName;
    private int stockQuantity;

    public Product(String productName, int stockQuantity) {
        this.productName = productName;
        this.stockQuantity = stockQuantity;
    }

    public synchronized boolean processOrder(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity; 
            return true;
        } else {
            return false; 
        }
    }

    public String getProductName() {
        return productName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
