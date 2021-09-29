import java.util.Scanner;

/**
 * Class: Bubble
 * @Author Harrison Brown
 * @Version 2
 *
 * Started Writing:
 * Version Date:
 *
 * Description:
 * The uBbble class allows the user to test just the bubble sort algorithm
 * It takes in a user generated value for the number of value to sort,
 * then displays the array before and after being sorted
 */

public class Bubble {

    public Bubble() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of items to sort with the bubble sort: ");
        int x = scan.nextInt();

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
