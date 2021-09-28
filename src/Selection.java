import java.util.Random;
import java.util.Scanner;

public class Selection {

    public Selection() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number of items to sort with the selection sort: ");
        int x = scan.nextInt();

        //create an array of size x and print it
        int[] arr = createArray(x);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
        System.out.println();

        //sort the array and print sorted values
        selection(arr);
        for (int num : arr) {
            System.out.print(num + "\s");
        }
    }

    //method to generate a random array of a specified size
    private int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(5000);
        }
        return array;
    }

    //method to selection sort
    private int[] selection(int[] array) {
        int i,j;
        int iMin;
        for(j = 0; j < array.length; j++){
            iMin = j;

            for ( i = j+1; i < array.length; i++) {
                if (array[i] < array[iMin]) {
                    iMin = i;
                }
            }

            if ( iMin != j ) {
                int temp = array[j];
                array[j] = array[iMin];
                array[iMin] = temp;
            }
        }
        return array;
    }
}
