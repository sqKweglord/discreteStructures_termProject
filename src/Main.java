import java.util.Random;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import static java.time.Duration.ZERO;

//use the same arrays for each sort for a better test
//multi thread to run both sorts at once
//do all iterations with a single run of the main
public class Main {

    //method to create all 1000 arrays at once so that they can be used with each sort
    public static ArrayList<int[]> returnArrays(int n) {
        ArrayList<int[]> arrays = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            arrays.add(createArray(n));
        }
        return arrays;
    }

    //method to generate a random array of a specified size
    public static int[] createArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    //method to bubble sort
    public static void bubble(int[] array) {
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
        //return array;
    }


    public static void main(String[] args) {
        //creates scanner
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        do {
        //gets amount of values to be sorted
        System.out.print("Enter the number of values to sort: ");
        int size = scan.nextInt();
        scan.reset();

        //gets the type of sort to use
        /*int sort;
        do {
            System.out.print("enter 1 for bubble or 2 for selection: ");
            sort = scan.nextInt();
        } while (sort < 1 || sort > 2);*/

        //initializes the duration since it can't be empty or null
        Duration totalTimeBubble = ZERO;
        Duration totalTimeSelect = ZERO;

        //creates 1000 arrays and duplicates them so that each sort can have a copy

            ArrayList<int[]> arrs = returnArrays(size);
            ArrayList<int[]> arrs2 = (ArrayList<int[]>) arrs.clone();

            //do the bubble sort
            for (int[] arr : arrs) {
                Instant start = Instant.now();
                bubble(arr);
                Instant end = Instant.now();
                totalTimeBubble = totalTimeBubble.plus(Duration.between(start, end));
            }

            //do the selection sort
            for (int[] arr : arrs2) {
                Instant start2 = Instant.now();
                selection(arr);
                Instant end2 = Instant.now();
                totalTimeSelect = totalTimeSelect.plus(Duration.between(start2, end2));
            }



        System.out.println();

        //outputs the total elapsed time and averaged elapsed time as milliseconds
        System.out.println("Total Number of items sorted: " + (size * 1000));
        System.out.println();
        System.out.println("Total time for Bubble: " + totalTimeBubble.toMillis() + " milliseconds");
        System.out.println("Average time for Bubble: " + (float)totalTimeBubble.toMillis()/1000+ " milliseconds");
        System.out.println();
        System.out.println("Total time for Selection: " + totalTimeSelect.toMillis() + " milliseconds");
        System.out.println("Average time for Selection: " + (float)totalTimeSelect.toMillis()/1000 + " milliseconds");

        System.out.println();
        System.out.print("Do you want to go again(y/n): ");
        String ans = scan.next();

        if(ans.equals("n")) {
            loop = false;
        } else {
            System.out.println();
        }
        } while(loop);
    }
}
