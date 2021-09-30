import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Harrison Brown
 * @version 2
 */
public class Bubble {

    /**
     * A method to test the bubble sort algorithm
     * <p>
     *     The method creates an instance of the "Bubble" class and prompts the user for a size
     *     The method class other methods to generate an array of that size and sort it
     *     The array is displayed before and after it is sorted so the user can verify the sort functions correctly
     * </p>
     */
    public Bubble() {
        Scanner scan = new Scanner(System.in);
        String choice;
        int x = 0;
        boolean loop;

        do {
            System.out.print("Enter the number of items to sort with the bubble sort: ");
            choice = scan.next();
            try {
                x = parseInt(choice);
                loop = false;
            } catch (NumberFormatException e) {
                loop = true;
            }
        } while(loop);

        //create array of size x and print it
        int[] arr = Sorter.createArray(x);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
        System.out.println();

        //sort the array and print the sorted values
        Sorter.bubble(arr);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
    }
}
