import java.util.ArrayList;
import java.util.Random;

/**
 * Class: Sorter
 * @Author Harrison Brown
 * @Version 1
 *
 * Started Writing:
 * Version Date:
 *
 * description:
 * The Sorter class contains most of the sorting functionality of the system
 * It has methods for each sort with various data types supported
 * It also contains the methods to generate the random arrays
 * Because of the frequency of their use, they are static so that all classes can use them without needing imports
 */

public class Sorter {

    //bubble int
    public static void bubble(int[] array) {
        for (int j = array.length; j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
        }
    }

    //selection int
    public static void selection(int[] array) {
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
    }

    //bubble long
    public static void bubble(long[] array) {
        int n = array.length;

        for (int j = n; j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (array[i - 1] > array[i]) {
                    long temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
        }
    }

    //bubble float
    public static void bubble(float[] array) {
        int n = array.length;

        for (int j = n; j > 0; j--) {
            for (int i = 1; i < j; i++) {
                if (array[i - 1] > array[i]) {
                    float temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
        }
    }

    //selection long
    public static void selection(long[] array) {
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
                long temp = array[j];
                array[j] = array[iMin];
                array[iMin] = temp;
            }
        }
    }

    //selection float
    public static void selection(float[] array) {
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
                float temp = array[j];
                array[j] = array[iMin];
                array[iMin] = temp;
            }
        }
    }

    //method to generate a random array of a specified size
    public static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(15000);
        }
        return array;
    }

    //method to create all 1000 arrays at once so that they can be used with each sort
    public static ArrayList<int[]> returnArrays(int amt, int n) {
        ArrayList<int[]> arrays = new ArrayList<>(amt);
        for (int i = 0; i < amt; i++) {
            arrays.add(createArray(n));
        }
        return arrays;
    }

}
