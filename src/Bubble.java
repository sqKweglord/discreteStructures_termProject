import java.util.Random;
import java.util.Scanner;

public class Bubble {

    public Bubble() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number of items to sort with the bubble sort: ");
        int x = scan.nextInt();

        //create array of size x and print it
        int[] arr = createArray(x);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
        System.out.println();

        //sort the array and print the sorted values
        bubble(arr);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
    }

    //method to bubble sort
    public int[] bubble(int[] array) {
        int n = array.length;

        for (int j = n; j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
        }
        return array;
    }

    //method to generate a random array of a specified size
    public int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(5000);
        }
        return array;
    }
}
