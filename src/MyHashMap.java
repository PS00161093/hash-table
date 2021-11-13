import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

class HashMap<K, V> {

    private int size;
    private int numBuckets;
    private List<HashNode<K, V>> bucketArray;

    public HashMap() {
        size = 0;
        numBuckets = 16;
        bucketArray = new ArrayList<>();
        IntStream.range(0, numBuckets).forEach(i -> bucketArray.add(null));
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;

        return index;
    }

    public void add(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.75) {
            List<HashNode<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            IntStream.range(0, numBuckets).forEach(i -> bucketArray.add(null));
            for (HashNode<K, V> node : temp) {
                while (node != null) {
                    add(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> node = bucketArray.get(bucketIndex);
        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode)
                return node.value;
            node = node.next;
        }

        return null;
    }

    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> node = bucketArray.get(bucketIndex);

        HashNode<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode)
                break;
            prev = node;
            node = node.next;
        }

        if (node == null) return null;

        size--;

        if (prev != null) prev.next = node.next;
        else bucketArray.set(bucketIndex, node.next);

        return node.value;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}

class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}

public class MyHashMap {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(map.isEmpty());
        map.add(1, 1);
        map.add(2, 2);
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        map.add(1, 1);
        System.out.println(map.size());
        System.out.println(map.get(2));
        System.out.println(map.remove(1));
        System.out.println(map.size());
        System.out.println(map.get(1));
        IntStream.range(0, 20).forEach(i -> map.add(i, i));
        System.out.println(map.size());
    }

}
