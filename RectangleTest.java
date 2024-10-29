import java.util.Scanner;

public class RectangleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rectangle rectangle = new Rectangle(); // Default rectangle

        System.out.println("Default Rectangle:");
        System.out.println("Length: " + rectangle.getLength());
        System.out.println("Width: " + rectangle.getWidth());
        System.out.println("Area: " + rectangle.area());
        System.out.println("Perimeter: " + rectangle.perimeter());

        // User input for length and width
        try {
            System.out.print("\nEnter length: ");
            double length = scanner.nextDouble();
            rectangle.setLength(length);

            System.out.print("Enter width: ");
            double width = scanner.nextDouble();
            rectangle.setWidth(width);

            System.out.println("\nUpdated Rectangle:");
            System.out.println("Length: " + rectangle.getLength());
            System.out.println("Width: " + rectangle.getWidth());
            System.out.println("Area: " + rectangle.area());
            System.out.println("Perimeter: " + rectangle.perimeter());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numeric values.");
        } finally {
            scanner.close();
        }
    }
}
