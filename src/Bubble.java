//a class to demonstrate the functionality of the bubble sort
import java.util.Scanner;

public class Bubble {

    public Bubble() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number of items to sort with the bubble sort: ");
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
