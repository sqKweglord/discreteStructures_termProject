import java.util.ArrayList;
import java.util.Random;

/**
 * @author Harrison Brown
 * @version 1
 *
 * The Sorter class contains most of the sorting and randomizing functionality of the system
 */

public class Sorter {

    /**
     * Bubble sort for ints
     * @param array array to be sorted
     */
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

    /**
     * Selection sort for ints
     * @param array the array to be sorted
     */
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

    /**
     * Bubble sort for longs
     * @param array the array to be sorted
     */
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

    /**
     * Bubble sort for floats
     * @param array the array to be sorted
     */
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

    /**
     * Selection sort for longs
     * @param array the array to be sorted
     */
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

    /**
     * Selection sort for floats
     * @param array the array to be sorted
     */
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

    /**
     * randomly generates an array of length size using 15,000 as the ceiling for the generated numbers
     * @param size length to make the array
     * @return randomly generated array of length size
     */
    public static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(15000);
        }
        return array;
    }

    /**
     * randomly generates a user input amount of random arrays and stores them in an arraylist
     * @param amt the amount of arrays to make
     * @param n the size each array should be
     * @return an arraylist of amt int[] of length n
     */
    public static ArrayList<int[]> returnArrays(int amt, int n) {
        ArrayList<int[]> arrays = new ArrayList<>(amt);
        for (int i = 0; i < amt; i++) {
            arrays.add(createArray(n));
        }
        return arrays;
    }

}
