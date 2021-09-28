import java.util.Random;
import java.util.ArrayList;

//use the same arrays for each sort for a better test
//multi thread to run both sorts at once
//do all iterations with a single run of the main
public class Main {

    //method to create all 1000 arrays at once so that they can be used with each sort
    public static ArrayList<int[]> returnArrays(int amt, int n) {
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
            array[i] = rand.nextInt(15000);
        }
        return array;
    }


    public static void main(String[] args) {
        //***ask the user if the want to test the sorts***

        //test sorts
        new Bubble();
        System.out.println();
        System.out.println();
        new Selection();
        System.out.println();
        System.out.println();

        //***ask user for size or default***

        //num of arrays in each arrayList
        int arrCnt = 1000;

        //***ask user for size or default***

        //elements count(elCnt) for small(Sm), medium(Md), and large(Lg) tests
        int elCntSm = 500;
        int elCntMd = 2500;
        int elCntLg = 5000;

        //create arrCnt number of arrays of each size
        ArrayList<int[]> arrays1 = returnArrays(arrCnt, elCntSm);
        ArrayList<int[]> arrays2 = returnArrays(arrCnt, elCntMd);
        ArrayList<int[]> arrays3 = returnArrays(arrCnt, elCntLg);

        //adds the arraylists to an arraylist that can be incremented through
        ArrayList<ArrayList<int[]>> allArrays = new ArrayList<>(3);
        allArrays.add(arrays1);
        allArrays.add(arrays2);
        allArrays.add(arrays3);

        //copies arrrays to be used by the selection sort
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

        //try block to catch SortTypeException
        try {

            //intializes the sorts
            Sort[] sorts = new Sort[6];
            for (int i = 1; i < 7; i++) {
                if (i < 4) {
                    sorts[i-1] = new Sort(allArrays.get(i-1), 1);
                } else {
                    sorts[i-1] = new Sort(allArraysAgain.get(i-4), 2);
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
            while (tg1.activeCount() > 0) {}

            //***print arrCnt***

            //displays the info for each sort in the console
            //will probably output to a csv here as well
            for (Sort s : sorts) {
                System.out.print(s);
                System.out.println("******************************");
            }

            //***ask user if they want to output to a csv***

          //prints the stacktrace if a SortTypeException occurs
        } catch (SortTypeException e) {
           e.printStackTrace();
        }
    }
}
