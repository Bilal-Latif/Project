package genericarrayprinter;

public class GenericArrayPrinter {

    // Generic method to print an array in reverse order
    public static <T> void printArrayInReverse(T[] array) {
        System.out.print("Array in reverse order: ");
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Integer array
        Integer[] intArray = {1, 2, 3, 4, 5};
        printArrayInReverse(intArray);

        // Double array
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
        printArrayInReverse(doubleArray);

        // Character array
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
        printArrayInReverse(charArray);
    }
}