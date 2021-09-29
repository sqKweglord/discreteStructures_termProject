import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Class: Main
 * @Author Harrison Brown
 * @Version 3.0?
 * Class: CSE 2300 Section 1
 * Started Writing: 09/15/2021
 * Version Date: 09/29/2021
 *
 * Required system classes
 * SortTypeException
 * Bubble
 * Selection
 * Sorter
 * Sortable
 *
 * Description:
 * A program to compare the efficiency of the Bubble Sort to the efficiency of the Selection Sort
 * The program allows the user to test the sort algorithms before executing the primary test
 * The program will compute the total time for each sort as well as the average
 * The user will be able to send the results to a csv if they choose to
 */

public class Main {

    public static void main(String[] args) {
        //PrintWriter if needed to output to csv
        PrintWriter pw = null;

        //num of arrays in each arrayList
        int arrCnt;

        //elements count(elCnt) for small(Sm), medium(Md), and large(Lg) tests
        int elCntSm;
        int elCntMd;
        int elCntLg;

        String ch1;
        boolean loop = true;
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

        if (ch1.equals("y")) {
            //test sorts
            new Bubble();
            System.out.println();
            System.out.println();
            new Selection();
            System.out.println();
        }
        System.out.println();

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
            System.out.print("Enter the number of arrays to use: ");
            arrCnt = scan.nextInt();
            scan.reset();
            System.out.print("Enter the length for the small arrays: ");
            elCntSm = scan.nextInt();
            scan.reset();
            System.out.print("Enter the length for the medium arrays: ");
            elCntMd = scan.nextInt();
            scan.reset();
            System.out.print("Enter the length for the large arrays: ");
            elCntLg = scan.nextInt();
            scan.reset();
        } else {
            arrCnt = 1000;
            elCntSm = 500;
            elCntMd = 2500;
            elCntLg = 5000;
        }
        System.out.println();

        //***generate all arrays and arraylists for test***

        // create arrCnt number of arrays of each size
        ArrayList<int[]> arrays1 = Sorter.returnArrays(arrCnt, elCntSm);
        ArrayList<int[]> arrays2 = Sorter.returnArrays(arrCnt, elCntMd);
        ArrayList<int[]> arrays3 = Sorter.returnArrays(arrCnt, elCntLg);

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
                int[] newArray = new int[y.length];
                for (int i = 0; i < y.length; i++) {
                    newArray[i] = y[i];
                }
                newList.add(newArray);
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

            //creates the threads and assigns them the the thread group
            Thread[] threads = new Thread[6];
            for (int i = 0; i < 6; i++) {
                threads[i] = new Thread(tg1, sorts[i]);
            }

            //starts the threads
            for (Thread t : threads) {
                t.start();
            }

            //loop to make the program wait for all threads to finish
            while (tg1.activeCount() > 0) {
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

                Sorter.bubble(bubTot);
                Sorter.bubble(selTot);
                Sorter.bubble(bubAvg);
                Sorter.bubble(selAvg);


                File results = new File("results.csv");
                pw = new PrintWriter(results);
                pw.println("Array Size,"+elCntSm+","+elCntMd+","+elCntLg);
                pw.print("bubble total,");
                for (long x : bubTot) {
                    pw.print(x+",");
                }
                pw.println();
                pw.print("Selection Total,");
                for (long x : selTot) {
                    pw.print(x+",");
                }
                pw.println();
                pw.print("bubble Average,");
                for (float x : bubAvg) {
                    pw.print(x+",");
                }
                pw.println();
                pw.print("Selection Average,");
                for (float x : selAvg) {
                    pw.print(x+",");
                }
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
}
