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