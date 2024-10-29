class Rectangle {
    // Attributes
    private double length;
    private double width;

    // Default constructor
    public Rectangle() {
        this.length = 1.0;
        this.width = 1.0;
    }

    // Parameterized constructor
    public Rectangle(double length, double width) {
        setLength(length);
        setWidth(width);
    }

    // Get method for length
    public double getLength() {
        return length;
    }

    // Set method for length with validation
    public void setLength(double length) {
        if (length > 0.0 && length < 20.0) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length must be > 0.0 and < 20.0");
        }
    }

    // Get method for width
    public double getWidth() {
        return width;
    }

    // Set method for width with validation
    public void setWidth(double width) {
        if (width > 0.0 && width < 20.0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width must be > 0.0 and < 20.0");
        }
    }

    // Method to calculate area
    public double area() {
        return length * width;
    }

    // Method to calculate perimeter
    public double perimeter() {
        return 2 * (length + width);
    }
}
