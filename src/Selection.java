import java.util.Random;
import java.util.Scanner;

public class Selection {

    public Selection() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number of items to sort with the selection sort: ");
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
