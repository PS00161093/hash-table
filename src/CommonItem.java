import java.util.Arrays;
import java.util.Hashtable;

public class CommonItem {

    public static void main(String[] args) {

        int[] array1 = {2, 5, 5, 2, 3};
        int[] array2 = {0, 4, 2, 5, 3};
        System.out.println(getFirstCommonItem(array1, array2));

    }

    private static int getFirstCommonItem(int[] array1, int[] array2) {

        int commonItem = 0;
        Hashtable<Integer, Integer> table = new Hashtable<>();

        Arrays.stream(array1).forEach(item -> table.put(item, 1));
        for (int i : array2) {
            if(table.containsKey(i)){
                commonItem = i;
                break;
            }
        }

        return commonItem;
    }

}