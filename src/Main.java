import java.util.Random;
import java.util.ArrayList;

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


    public static void main(String[] args) {

        ArrayList<int[]> arrays1 = returnArrays(500);
        ArrayList<int[]> arrays2 = returnArrays(2500);
        ArrayList<int[]> arrays3 = returnArrays(5000);

        ArrayList<ArrayList<int[]>> allArrays = new ArrayList<>(3);
        allArrays.add(arrays1);
        allArrays.add(arrays2);
        allArrays.add(arrays3);



        ArrayList<Object> cloned = new ArrayList<>(allArrays);


        try {


            //intializes the sorts
            Sort[] sorts = new Sort[6];
            for (int i = 1; i < 7; i++) {
                if (i < 4) {
                    sorts[i-1] = new Sort(allArrays.get(i-1), 1);
                } else {
                    sorts[i-1] = new Sort((ArrayList<int[]>) cloned.get(i-4), 2);
                }

            }

            Thread[] threads = new Thread[6];
            for (int i = 0; i < 6; i++) {
                threads[i] = new Thread(sorts[i]);
            }

            for (Thread t : threads) {
                t.start();
            }

            int count;
            do {
                count = 0;
                for (Thread t : threads) {
                    if (!t.isAlive()) {
                        count++;
                    }
                }
            } while (count != 6);


            for (Sort s : sorts) {
                System.out.print(s);
                System.out.println("******************************");
            }

        } catch (SortTypeException e) {
           e.printStackTrace();
        }
    }
}
