public class Main {

    public static int[] createArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }
        return array;
    }
    public static int[] bubble(int[] array) {
        int[] temp = {0};
        return temp;
    }

    public static int[] selection( int[] array) {
        int[] temp = {0};
        return temp;
    }

    public static void main(String[] args) {
        int[] temp = {1,2,3};
        System.out.println(bubble(temp)[0]);
        System.out.println(selection(temp)[0]);
    }
}
