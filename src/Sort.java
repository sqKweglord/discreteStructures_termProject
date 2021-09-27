import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

import static java.time.Duration.ZERO;

public class Sort implements Runnable {

    ArrayList<int[]> arrays;

    //type of sort to perform, 1 for bubble, 2 for select
    int sortType;

    Duration totalTime = ZERO;

    public Sort(ArrayList<int[]> arrs, int n) throws SortTypeException {
        arrays = arrs;
        if (n < 1 || n > 2) {
            throw new SortTypeException( n + ": Not a valid sort");
        } else {
            sortType = n;
        }
    }

    //method to bubble sort
    public void bubble(int[] array) {
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
        //return array;
    }

    //method to selection sort
    public void selection(int[] array) {
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
        //return array;
    }

    @Override
    public void run() {
        switch (sortType) {
            case 1:
                for (int[] arr : arrays) {
                    Instant start = Instant.now();
                    bubble(arr);
                    Instant end = Instant.now();
                    totalTime = totalTime.plus(Duration.between(start, end));
                }
                break;
            case 2:
                for (int[] arr : arrays) {
                    Instant start = Instant.now();
                    selection(arr);
                    Instant end = Instant.now();
                    totalTime = totalTime.plus(Duration.between(start, end));
                }
                break;
        }

        /*
        System.out.println("Elements in a single array: " + (arrays.get(0).length));
        //System.out.println("Total Number of items sorted: " + (arrays.get(0).length * 1000));
        //System.out.println();
        if (sortType == 1) {
            System.out.println("Sort is BUBBLE");
            System.out.println("Total time: " + totalTime.toMillis() + " milliseconds");
            System.out.println("Average time: " + (float) totalTime.toMillis() / 1000 + " milliseconds");
        } else {
            System.out.println("Sort is SELECT");
            System.out.println("Total time: " + totalTime.toMillis() + " milliseconds");
            System.out.println("Average time: " + (float) totalTime.toMillis() / 1000 + " milliseconds");
        }
        */

    }

    @Override
    public String toString() {
        String msg;
        if (sortType == 1) {
            msg = "BUBBLE";
        } else {
            msg = "SELECT";
        }
        msg = msg + ("\nElements in a single array: " + (arrays.get(0).length)) + ("\nTotal time: " + totalTime.toMillis() + " milliseconds") + ("\nAverage Time: " + (float) totalTime.toMillis() / 1000 + " milliseconds\n");

        return msg;
    }

}

