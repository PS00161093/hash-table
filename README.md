# hash-table
1. A hash table, which maps keys to values.<p>
2. Any <code>non-null</code> object can be used as a key or as a value.<p>
3. To successfully store and retrieve objects from a hashtable, 
the objects used as keys must implement the <code>hashCode</code> method 
and the <code>equals</code> method.<p>
4. An instance of <code>Hashtable</code> has two parameters that affect its performance: 
    * <i>initial capacity</i> and <i>load factor</i>.
    * The <i>capacity</i> is the number of 
<i>buckets</i> in the hash table, and the <i>initial capacity</i> is simply the capacity 
at the time the hash table is created.<p>
5. The hash table is <i>open</i>: in the case of a "hash collision", a single bucket stores 
multiple entries, which must be searched sequentially.<p>
6. The <i>load factor</i> is a measure of how full the hash table is allowed to get before its capacity is automatically increased.<p>
7. Generally, the default load factor (.75) offers a good tradeoff between time and space costs.<p>
8. Higher values decrease the space overhead but increase the time cost to look up an entry 
(which is reflected in most <tt>Hashtable</tt> operations, including <tt>get</tt> and <tt>put</tt>).<p>
9. The initial capacity controls a tradeoff between wasted space and the 
need for <code>rehash</code> operations, which are time-consuming. No <code>rehash</code> operations will <i>ever</i> occur if the initial
capacity is greater than the maximum number of entries the
<tt>Hashtable</tt> will contain divided by its load factor.  However,
setting the initial capacity too high can waste space.<p>

10. If many entries are to be made into a <code>Hashtable</code>,
   creating it with a sufficiently large capacity may allow the
   entries to be inserted more efficiently than letting it perform
   automatic rehashing as needed to grow the table. <p>
   
11. <code>public class Hashtable<K,V><p>
extends Dictionary<K,V><p><p>
implements Map<K,V>, Cloneable, java.io.Serializable { } 
</code>

Class fields:
* <code>private transient Entry<?,?>[] table </code> - The hash table data. It's an Array.
* <code>private transient int count</code> - The total number of entries in the hash table.
* <code>private int threshold</code> - The table is rehashed when its size exceeds this threshold = (int)(capacity * loadFactor).
* <code>private transient int modCount = 0</code> - The number of times this Hashtable has been structurally modified. Structural modifications are those that change the number of entries in the Hashtable or otherwise modify its internal structure (e.g.,rehash). <p>

4 Constructors
* <code>public Hashtable(int initialCapacity, float loadFactor)</code>
* <code>public Hashtable(int initialCapacity)</code>
* <code>public Hashtable() {
                this(11, 0.75f);
            }</code>
* <code>public Hashtable(Map<? extends K, ? extends V> t)</code>

Commonly used methods: <p>
* <code>public synchronized int size() { return count; }</code> - Returns the number of keys in this hashtable.
* <code>public synchronized boolean isEmpty() { return count == 0; }</code> - Tests if this hashtable maps no keys to values.
* <code>public synchronized boolean contains(Object value) {

                if (value == null) {
                    throw new NullPointerException();
                }
        
                Entry<?,?> tab[] = table;
                for (int i = tab.length ; i-- > 0 ;) {
                    for (Entry<?,?> e = tab[i] ; e != null ; e = e.next) {
                        if (e.value.equals(value)) {
                            return true;
                        }
                    }
                }
                return false;
            }
* <code>public boolean containsValue(Object value) { return contains(value); }</code> - Returns true if this hashtable maps one or more keys to this value.


* <code>Tests if the specified object is a key in this hashtable.

    public synchronized boolean containsKey(Object key) {
    
                Entry<?,?> tab[] = table;
                
                int hash = key.hashCode();
                
                int index = (hash & 0x7FFFFFFF) % tab.length;
                
                for (Entry<?,?> e = tab[index] ; e != null ; e = e.next) {
                    if ((e.hash == hash) && e.key.equals(key)) {
                        return true;
                    }
                }
                return false;
            }
            
* <code>Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the key.

    public synchronized V get(Object key) {
    
                <Entry<?,?> tab[] = table;
                
                int hash = key.hashCode();
                
                int index = (hash & 0x7FFFFFFF) % tab.length;
                
                for (Entry<?,?> e = tab[index] ; e != null ; e = e.next) {
                    if ((e.hash == hash) && e.key.equals(key)) {
                        return (V)e.value;
                    }
                }
                return null;
    }
