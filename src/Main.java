import java.util.Random;
import java.util.ArrayList;

//use the same arrays for each sort for a better test
//multi thread to run both sorts at once
//do all iterations with a single run of the main
public class Main {

    //method to create all 1000 arrays at once so that they can be used with each sort
    public static ArrayList<int[]> returnArrays(int a, int n) {
        int amt = a;
        ArrayList<int[]> arrays = new ArrayList<>(amt);
        for (int i = 0; i < amt; i++) {
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


    public static void main(String[] args) {
        int arrCnt = 1000;

        ArrayList<int[]> arrays1 = returnArrays(arrCnt, 500);
        ArrayList<int[]> arrays2 = returnArrays(arrCnt, 2500);
        ArrayList<int[]> arrays3 = returnArrays(arrCnt, 5000);

        ArrayList<ArrayList<int[]>> allArrays = new ArrayList<>(3);
        allArrays.add(arrays1);
        allArrays.add(arrays2);
        allArrays.add(arrays3);

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

        /*
        for (int x = 0; x < 10; x++) {
            System.out.print(allArrays.get(2).get(980)[x] + "\s");
        }
        System.out.println();

        for (int x = 0; x < 10; x++) {
            System.out.print(allArraysAgain.get(2).get(980)[x] + "\s");
        }
        System.out.println();
        System.out.println();
        */


        try {

            //intializes the sorts
            Sort[] sorts = new Sort[6];
            for (int i = 1; i < 7; i++) {
                if (i < 4) {
                    sorts[i-1] = new Sort((ArrayList<int[]>) allArrays.get(i-1), 1);
                } else {
                    sorts[i-1] = new Sort((ArrayList<int[]>) allArraysAgain.get(i-4), 2);
                }

            }

            ThreadGroup tg1 = new ThreadGroup("Sorts");

            Thread[] threads = new Thread[6];
            for (int i = 0; i < 6; i++) {
                threads[i] = new Thread(tg1, sorts[i]);
            }


            /*
            Sort[] sorts = new Sort[2];
            for (int i = 1; i < 3; i++) {
                if (i == 1) {
                    sorts[i-1] = new Sort(allArrays.get(0), 1);
                } else {
                    sorts[i-1] = new Sort((ArrayList<int[]>) allArraysAgain.get(0), 2);
                }

            }

            Thread[] threads = new Thread[2];
            for (int i = 0; i < 2; i++) {
                threads[i] = new Thread(sorts[i]);
            }
            */



            for (Thread t : threads) {
                t.start();
            }

            while (tg1.activeCount() > 0) {}

            /*
            int count;
            do {
                count = 0;
                for (Thread t : threads) {
                    if (!t.isAlive()) {
                        count++;
                    }
                }
            } while (count != 6);
            */

            for (Sort s : sorts) {
                System.out.print(s);
                System.out.println("******************************");
            }

        } catch (SortTypeException e) {
           e.printStackTrace();
        }
    }
}
