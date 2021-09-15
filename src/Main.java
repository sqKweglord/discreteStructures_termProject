import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    //method to generate a random array of a specified size
    public static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(5000);
        }
        return array;
    }

    //method to bubble sort
    public static int[] bubble(int[] array) {
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

    //method to selection sort
    public static int[] selection(int[] array) {
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


    public static void main(String[] args) {
        /*List<int[]> listOfArrays = new ArrayList<>();
        int count = 0;
        while (count < 1000) {
            int size = 1000;
            int[] temp = createArray(size);
            temp = bubble(temp);
            listOfArrays.add(temp);
            count++;
        }

        for (int[] x : listOfArrays) {
            for (int y : x) {
                System.out.print(y + " ");

            }
            System.out.println();
        }

        int[] temp = createArray(10);
        for (int x : temp) {
            System.out.print(x + " ");
        }
        System.out.println();

        temp = selection(temp);
        for (int x : temp) {
            System.out.print(x + " ");
        }*/

        Bubble bub = new Bubble();
        //Selection select = new Selection();
    }
}
