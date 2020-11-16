# hash-table
1. A hash table, which maps keys to values.
2. Any <code>non-null</code> object can be used as a key or as a value.
3. To successfully store and retrieve objects from a hashtable, 
the objects used as keys must implement the <code>hashCode</code> method 
and the <code>equals</code> method.
4. An instance of <code>Hashtable</code> has two parameters that affect its performance: 
    * <i>initial capacity</i> and <i>load factor</i>.
    * The <i>capacity</i> is the number of 
<i>buckets</i> in the hash table, and the <i>initial capacity</i> is simply the capacity 
at the time the hash table is created.
5. The hash table is <i>open</i>: in the case of a "hash collision", a single bucket stores 
multiple entries, which must be searched sequentially.
6. The <i>load factor</i> is a measure of how full the hash table is allowed to get before its capacity is automatically increased.
7. 