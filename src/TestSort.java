import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Harrison Brown
 * @version 2
 */
public class TestSort {
    /**
     * A method to test the sort algorithms
     * <p>
     *     The method creates an instance of the "TestSort" class and prompts the user for a size
     *     The method class other methods to generate an array of that size and sort it
     *     The array is displayed before and after it is sorted so the user can verify the sort functions correctly
     * </p>
     */
        public TestSort() {

            int[] arr1;
            int[] arr2;

            //create array of size x and print it
            arr1 = Sorter.createArray(getInput(1));
            printArr(arr1);
            System.out.println();
            //System.out.println();
            Sorter.bubble(arr1);
            printArr(arr1);
            System.out.println();
            System.out.println();
            arr2 = Sorter.createArray(getInput(2));
            printArr(arr2);
            System.out.println();
            //System.out.println();
            Sorter.selection(arr2);
            printArr(arr2);
        }


    private void printArr(int[] array) {
        for (int num : array) {
            System.out.print(num + "\s");
        }
    }

    private int getInput(int n) {
        Scanner scan = new Scanner(System.in);
        String choice;
        int x = 0;
        boolean loop;
        String sort;

        if (n == 1) {
            sort = "Bubble";

        } else {
            sort = "Selection";
        }

        do {
            System.out.print("Enter the number of items to sort with the " + sort + " sort: ");
            choice = scan.next();
            try {
                x = parseInt(choice);
                loop = false;
            } catch (NumberFormatException e) {
                loop = true;
            }
        } while (loop);

        return x;
    }

}
