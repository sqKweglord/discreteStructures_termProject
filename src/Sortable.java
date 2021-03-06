import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import static java.time.Duration.ZERO;

/**
 * Class: Sortable
 * @author Harrison Brown
 * Class: CSE 2300 Section 1
 * This class implements Runnable so that the sorts can be multithreaded
 */
public class Sortable implements Runnable {

    /**
     * An ArrayList of all the arrays that need to be sorted
     */
    private final ArrayList<int[]> arrays;

    /**
     * The length of the arrays that will be sorted
     */
    private final int arrLen;

    /**
     * The type of sort the instance is
     * 1 for bubble, 2 for selection
     */
    private final int sortType;

    /**
     * The total time for the sort to complete
     */
    private Duration totalTime = ZERO;

    /**
     * average time for all arrays to be sorted
     */
    private float avgTime;

    /**
     * Constructor to initialize an instance of Sortable
     * <p>
     *     creates an instance of the class using an arraylist and an int
     *     the arraylist is the collection of arrays for the instance to sort
     *     the int is the type of sort that needs to be preformed
     *     1 = bubble sort
     *     2 = Selection sort
     * </p>
     * @param arrs the arrayList containing the arrays to be sorted
     * @param n an int designating the type of sort to be performed
     */
    public Sortable(ArrayList<int[]> arrs, int n) {
        arrays = arrs;
        arrLen = arrays.get(0).length;
        sortType = n;

    }

    /**
     * sets the avgTime based on total time and the number of arrays that were sorted
     */
    private void setAvg() {
        if (arrays != null) {
            avgTime = (float)totalTime.toMillis() / arrays.size();
        }
    }

    /**
     * returns the average time
     * @return avgTime
     */
    public float getAvgTime() {
        return avgTime;
    }

    /**
     * returns the type of sort
     * @return sortType
     */
    public int getSortType() {
        return sortType;
    }

    /**
     * returns the total time
     * @return totalTime
     */
    public Duration getTotalTime() {
        return totalTime;
    }

    /**
     * returns the size of length of arrays that were sorted
     * @return arrLen
     */
    // a getter that is never used, written to maintain consistency of methods
    public int getArrLen() {
        return arrLen;
    }

    /**
     * overrides the run method from interface Runnable
     * <p>
     *     This method allows instances of this class to be run in parallel
     *     This method is where the primary sort takes place
     *     By multithreading the sorts, the total program execution is only limited by the longest sort
     * </p>
     */
    @Override
    public void run() {
        switch (sortType) {
            case 1:
                for (int[] arr : arrays) {
                    Instant start = Instant.now();
                    Main.bubble(arr);
                    Instant end = Instant.now();
                    totalTime = totalTime.plus(Duration.between(start, end));
                }
                break;
            case 2:
                for (int[] arr : arrays) {
                    Instant start = Instant.now();
                    Main.selection(arr);
                    Instant end = Instant.now();
                    totalTime = totalTime.plus(Duration.between(start, end));
                }
                break;
        }
        setAvg();
    }

    /**
     * The toString method for this class
     * <p>
     *     Overriding the toString allows the data to be displayed by printing the objects instead of calling the
     *     getters.
     * </p>
     * @return a string that contains the needed info from the class
     */
    @Override
    public String toString() {
        String msg;
        if (sortType == 1) {
            msg = "BUBBLE";
        } else {
            msg = "SELECT";
        }
        msg = msg + ("\nElements in a single array: " + (arrays.get(0).length)) + ("\nTotal time: " + totalTime.toMillis() + " milliseconds") + ("\nAverage Time: " + avgTime + " milliseconds\n");

        return msg;
    }
}

