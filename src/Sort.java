import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import static java.time.Duration.ZERO;

public class Sort implements Runnable {

    //arraylist of all arrays to be sorted by the instance
    private ArrayList<int[]> arrays;

    //type of sort to perform, 1 for bubble, 2 for select
    private int sortType;

    //total time to sort all arrays
    private Duration totalTime = ZERO;

    //average time of sorting arrays
    private float avgTime;

    //intializes the object to the chosen sort type with the arrays to be sorted
    public Sort(ArrayList<int[]> arrs, int n) throws SortTypeException {
        arrays = arrs;
        if (n < 1 || n > 2) {
            throw new SortTypeException( n + ": Not a valid sort");
        } else {
            sortType = n;
        }
    }

    //sets the average time
    private void setAvg() {
        if (arrays != null) {
            avgTime = (float)totalTime.toMillis() / arrays.size();
        }
    }

    //methods to return class attributes
    public float getAvgTime() {
        return avgTime;
    }

    public int getSortType() {
        return sortType;
    }

    public Duration getTotalTime() {
        return totalTime;
    }

    //method to bubble sort
    private void bubble(int[] array) {
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
    }

    //method to selection sort
    private void selection(int[] array) {
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

    //run method for multithreading,
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
        setAvg();
    }

    //toString to ensure all sort objects have the same output format
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

