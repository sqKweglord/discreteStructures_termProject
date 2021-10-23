import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Class: Main
 * @author Harrison Brown
 * @version 3.0
 * Class: CSE 2300 Section 1
 * Started Writing: 09/15/2021
 * Finished Writing: 09/30/2021
 * Documentation date: 9/30/2021
 *<p>
 *      Required system classes:
 *      SortTypeException
 *      Bubble
 *      Selection
 *      Sorter
 *      Sortable
 *</p>
 * <p>
 *      System Description:
 *      A program to compare the efficiency of the Bubble Sort to the efficiency of the Selection Sort.
 *      The program allows the user to test the sort algorithms before executing the primary test.
 *      The program will compute the total time for each sort as well as the average.
 *      The user will be able to send the results to a csv if they choose to.
 * </p>
 */
public class Main {

    /**
     * The "main" method that runs on execution
     * <p>
     *     The main is the primary method to execute the tests.
     *     It prompts the user for a several options:
     *          1. if they want to test the algorithms
     *          2. use custom values instead of the default ones listed in the rubric
     *          3. if they want to output to a csv
     *
     *     During runtime the method performs based on the users choices.
     *     There are also several while loops and try-catch statements to validate user input
     *     and ensure the program doesn't crash based on user input.
     * @param args generic main arguments
     */
    public static void main(String[] args) {
        //A PrintWriter for if the user decides to output to a csv
        PrintWriter pw = null;

        //The amount of arrays for each sort method to sort
        int arrCnt = 0;

        //The small length of arrays
        int elCntSm = 0;

        //The medium length of arrays
        int elCntMd = 0;

        //The large length of arrays
        int elCntLg = 0;

        //A String to store user input
        String ch1;

        //a boolean to hold if while loops should continue looping
        boolean loop = true;

        //a scanner instance
        Scanner scan = new Scanner(System.in);

        //***ask the user if the want to test the sorts***
        do {
            scan.reset();
            System.out.print("Do you want to test the sorting algorithms before performing the main test? (y/n): ");
            ch1 = scan.next();
            if (ch1.equalsIgnoreCase("y") || ch1.equalsIgnoreCase("n")) {
                loop = false;
            }
        } while (loop);

        scan.reset();
        System.out.println();
        if (ch1.equals("y")) {
            //test sorts
            TestSort();
            System.out.println();
            System.out.println();
        }

        //***ask user for size or default***
        String ch2;
        System.out.println("The default values for the test is 1000 arrays of each size");
        System.out.println("The small size is 500, the medium is 2500, and the large is 5000");
        do {
            scan.reset();
            System.out.print("Do you want to use these values or input custom ones? (1 default, 2 for custom): ");
            ch2 = scan.next();
            scan.reset();
            try {
                loop = parseInt(ch2) < 1 || parseInt(ch2) > 2;
            } catch (NumberFormatException e) {
                loop = true;
            }
        } while (loop);

        System.out.println();
        if (parseInt(ch2) == 2) {
            loop = true;
            String ch3, ch4, ch5, ch6;
            do {
                System.out.print("Enter the number of arrays to use: ");
                ch3 = scan.next();
                scan.reset();
                System.out.print("Enter the length for the small arrays: ");
                ch4 = scan.next();
                scan.reset();
                System.out.print("Enter the length for the medium arrays: ");
                ch5 = scan.next();
                scan.reset();
                System.out.print("Enter the length for the large arrays: ");
                ch6 = scan.next();
                scan.reset();
                try {
                    arrCnt = parseInt(ch3);
                    elCntSm = parseInt(ch4);
                    elCntMd = parseInt(ch5);
                    elCntLg = parseInt(ch6);
                    loop = false;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Invalid value type, please try again");
                    System.out.println();
                }
            }while (loop);
        } else {
            arrCnt = 1000;
            elCntSm = 500;
            elCntMd = 2500;
            elCntLg = 5000;
        }
        System.out.println();

        //***generate all arrays and arraylists for test***

        // create arrCnt number of arrays of each size
        ArrayList<int[]> arrays1 = returnArrays(arrCnt, elCntSm);
        ArrayList<int[]> arrays2 = returnArrays(arrCnt, elCntMd);
        ArrayList<int[]> arrays3 = returnArrays(arrCnt, elCntLg);

        //adds the arraylists to an arraylist that can be incremented through
        ArrayList<ArrayList<int[]>> allArrays = new ArrayList<>(3);
        allArrays.add(arrays1);
        allArrays.add(arrays2);
        allArrays.add(arrays3);

        //copies arrays to be used by the selection sort
        ArrayList<ArrayList<int[]>> allArraysAgain = new ArrayList<>(3);

        for (ArrayList<int[]> z : allArrays) {
            ArrayList<int[]> newList = new ArrayList<>(1000);
            for (int[] y : z) {
                newList.add(y.clone());
            }
            allArraysAgain.add(newList);
        }

        //***execute sorts***

        //try block to catch SortTypeException or IOException
        try {

            //initializes the sorts
            Sortable[] sorts = new Sortable[6];
            for (int i = 1; i < 7; i++) {
                if (i < 4) {
                    sorts[i - 1] = new Sortable(allArrays.get(i - 1), 1);
                } else {
                    sorts[i - 1] = new Sortable(allArraysAgain.get(i - 4), 2);
                }

            }

            //creates a thread group
            ThreadGroup tg1 = new ThreadGroup("Sorts");

            //creates the threads and assigns them the thread group
            Thread[] threads = new Thread[6];
            for (int i = 0; i < 6; i++) {
                threads[i] = new Thread(tg1, sorts[i]);
                threads[i].start();
            }

            //joins the threads
            try {
                for (Thread t : threads) {
                    t.join();
                }
            } catch (InterruptedException x) {
                x.printStackTrace();
            }

            //***print arrCnt***
            System.out.println("Amount of arrays tested per sort: " + arrCnt);
            System.out.println();

            //displays the info for each sort in the console
            //will probably output to a csv here as well
            for (Sortable s : sorts) {
                System.out.print(s);
                System.out.println("******************************");
            }

            System.out.println();

            //***ask user if they want to output to a csv***
            loop = true;
            do {
                scan.reset();
                System.out.print("Do you want to output the results to a csv? (y/n): ");
                ch1 = scan.next();
                if (ch1.equalsIgnoreCase("y") || ch1.equalsIgnoreCase("n")) {
                    loop = false;
                }
            } while (loop);

            //***output to csv***

            if (ch1.equalsIgnoreCase("y")) {
                long[] bubTot = new long[3];
                float[] bubAvg = new float[3];

                long[] selTot = new long[3];
                float[] selAvg = new float[3];

                int c1 = 0;
                int c2 = 0;
                int c3 = 0;
                int c4 = 0;

                for (Sortable sort : sorts) {
                    if (sort.getSortType() == 1) {
                        bubTot[c1] = sort.getTotalTime().toMillis();
                        c1++;
                        bubAvg[c2] = sort.getAvgTime();
                        c2++;
                    }
                    if (sort.getSortType() == 2) {
                        selTot[c3] = sort.getTotalTime().toMillis();
                        c3++;
                        selAvg[c4] = sort.getAvgTime();
                        c4++;
                    }
                }

                bubble(bubTot);
                bubble(selTot);
                bubble(bubAvg);
                bubble(selAvg);


                File results = new File("results.csv");
                pw = new PrintWriter(results);
                pw.println(","+elCntSm+","+elCntMd+","+elCntLg);
                //pw.println(",bubble total,bubble average,selection total,selection average");

                pw.println("Bubble Total," + arrayAsString(bubTot));
                pw.println("Selection Total," + arrayAsString(selTot));
                pw.println("Bubble Average," + arrayAsString(bubAvg));
                pw.println("Selection Average," + arrayAsString(selAvg));
            }

            //prints the stacktrace if a SortTypeException occurs
        } catch (SortTypeException e) {
            System.out.println("Error with sortType");
            e.printStackTrace();
        } catch (IOException x) {
            System.out.println("IO Error");
            x.printStackTrace();
        } finally {
            if (pw != null) {pw.close();}
        }
    }

    private static String arrayAsString(long[] arr) {
        StringBuilder string = new StringBuilder();
        for (long l : arr) {
            string.append(l).append(",");
        }

        return string.toString();
    }

    private static String arrayAsString(float[] arr) {
        StringBuilder string = new StringBuilder();
        for (float v : arr) {
            string.append(v).append(",");
        }

        return string.toString();
    }

    //methods to test the sorts

    /**
     * A method to test the sort algorithms
     * <p>
     *     The method creates an instance of the "TestSort" class
     *     The method calls other methods to get user input, generate an array of that size and sort it
     *     The array is displayed before and after it is sorted so the user can verify the sort functions correctly
     * </p>
     */
    private static void TestSort() {

        int[] arr1;
        int[] arr2;

        //create array of the size from getInput()
        arr1 = createArray(getInput(1));
        //prints the array
        printArr(arr1);
        System.out.println();
        //sorts the array with the sort method from Sorter
        Main.bubble(arr1);
        printArr(arr1);
        System.out.println();
        System.out.println();
        arr2 = createArray(getInput(2));
        printArr(arr2);
        System.out.println();
        Main.selection(arr2);
        printArr(arr2);
    }

    /**
     * prints the array
     * @param array the array to be printed
     */
    private static void printArr(int[] array) {
        for (int num : array) {
            System.out.print(num + "\s");
        }
    }

    /**
     * gets a valid user input to use when creating array
     * @param n sets if the sort is a bubble or selection
     * @return the size the user wants to use
     */
    private static int getInput(int n) {
        Scanner scan = new Scanner(System.in);
        String choice;
        int x = 0;
        boolean loop;
        String sort;

        if (n == 1) {
            sort = "Bubble";

        } else {
            sort = "Selection";
        }

        do {
            System.out.print("Enter the number of items to sort with the " + sort + " sort: ");
            choice = scan.next();
            try {
                x = parseInt(choice);
                loop = false;
            } catch (NumberFormatException e) {
                loop = true;
            }
        } while (loop);

        return x;
    }


    //methods that contain the sorts

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
