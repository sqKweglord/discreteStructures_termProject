import java.util.Scanner;

/**
 * Class: Selection
 * @author Harrison Brown
 * @version 2
 */
public class Selection {

    /**
     * A method to test the Selection sort algorithm
     * <p>
     *     The method creates an instance of the "Bubble" class and prompts the user for a size
     *     The method class other methods to generate an array of that size and sort it
     *     The array is displayed before and after it is sorted so the user can verify the sort functions correctly
     * </p>
     */
    public Selection() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of items to sort with the selection sort: ");
        int x = scan.nextInt();

        //create an array of size x and print it
        int[] arr = Sorter.createArray(x);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
        System.out.println();

        //sort the array and print sorted values
        Sorter.selection(arr);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
    }
}
