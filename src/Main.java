import java.util.Random;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

import static java.time.Duration.ZERO;

//use the same arrays for each sort for a better test
//multi thread to run both sorts at once
//do all iterations with a single run of the main
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
            int sort;
            do {
                System.out.print("enter 1 for bubble or 2 for selection: ");
                sort = scan.nextInt();
            } while (sort < 1 || sort > 2);

            //initializes the duration since it can't be empty or null
            Duration totalTimeBubble = ZERO;
            Duration totalTimeSelect = ZERO;

            //runs 1000 trials of the sort type chosen
            for (int count = 0; count < 1000; count++) {
                //creates random array for current trial
                int[] arr = createArray(size);

                //decides which sort to do
                switch (sort) {
                    case 1:
                        Instant start = Instant.now();
                        bubble(arr);
                        Instant end = Instant.now();
                        totalTimeBubble = totalTimeBubble.plus(Duration.between(start, end));
                        break;
                    case 2:
                        Instant start2 = Instant.now();
                        selection(arr);
                        Instant end2 = Instant.now();
                        totalTimeSelect = totalTimeSelect.plus(Duration.between(start2, end2));
                        break;
                }
            }

            System.out.println();

            //outputs the total elapsed time and averaged elapsed time as milliseconds
            System.out.println("Total Number of items sorted: " + (size * 1000));

            if (sort == 1) {
                System.out.println("Total time for Bubble: " + totalTimeBubble.toMillis() + " milliseconds");
                System.out.println("Average time for Bubble: " + (float) totalTimeBubble.toMillis() / 1000 + " milliseconds");
            } else {
                System.out.println("Total time for Selection: " + totalTimeSelect.toMillis() + " milliseconds");
                System.out.println("Average time for Selection: " + (float) totalTimeSelect.toMillis() / 1000 + " milliseconds");
            }
            System.out.println();
            System.out.print("Do you want to go again(y/n): ");
            String ans = scan.next();
            if (ans.equals("n")) {
                loop = false;
            }
            else {
                System.out.println();
            }
        } while (loop);
    }
}
