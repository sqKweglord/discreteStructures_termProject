//a class to demonstrate the functionality of the selection sort

import java.util.Scanner;

/**
 * Class: Selection
 * @Author Harrison Brown
 * @Version 2
 *
 * Started Writing:
 * Version Date:
 *
 * Description:
 * The Selection class allows the user to test just the bubble sort algorithm
 * It takes in a user generated value for the number of value to sort,
 * then displays the array before and after being sorted
 */

public class Selection {

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
