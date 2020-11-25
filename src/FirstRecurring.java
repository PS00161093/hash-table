import java.util.Hashtable;

public class FirstRecurring {

    public static void main(String[] args) {

        int[] array = {3, 4, 5, 2, 3, 4};

        System.out.println(getFirstRecurringItem(array));
    }

    private static int getFirstRecurringItem(int[] array) {

        int duplicate = 0;
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();

        for (int item : array) {
            if (hashtable.containsKey(item)) {
                duplicate = item;
            } else {
                hashtable.put(item, 1);
            }
        }
        return duplicate;
    }
}