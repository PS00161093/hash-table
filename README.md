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

12. Class fields:
* <code>private transient Entry<?,?>[] table </code> - The hash table data. It's an Array.
* <code>private transient int count</code> - The total number of entries in the hash table.
* <code>private int threshold</code> - The table is rehashed when its size exceeds this threshold = (int)(capacity * loadFactor).
* <code>private transient int modCount = 0</code> - The number of times this Hashtable has been structurally modified. Structural modifications are those that change the number of entries in the Hashtable or otherwise modify its internal structure (e.g.,rehash). <p>
* <code>private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;</code> - The maximum size of array to allocate.<p>

4 Constructors
* <code>public Hashtable(int initialCapacity, float loadFactor)</code>
* <code>public Hashtable(int initialCapacity)</code>
* <code>public Hashtable() {
                this(11, 0.75f);
            }</code>
* <code>public Hashtable(Map<? extends K, ? extends V> t)</code>

Commonly used methods: <p>
* <code><b>public synchronized int size() { return count; }</b></code> - Returns the number of keys in this hashtable.
* <code><b>public synchronized boolean isEmpty() { return count == 0; }</b></code> - Tests if this hashtable maps no keys to values.
* <b>boolean contains(Object value) - Tests if some key maps into the specified value in this hashtable.</b>
            
            public synchronized boolean contains(Object value) {

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
            
* <b><code>public boolean containsValue(Object value) {
                   return contains(value);
               }</code></b>
            
* <b>boolean containsKey(Object key) - Tests if the specified object is a key in this hashtable.</b>
            
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

* <b>V get(Object key) - Returns the value to which the specified key is mapped, or <code>null</code> if this map contains no mapping for the key.</b>

            public synchronized V get(Object key) {
            
                Entry<?,?> tab[] = table;
                
                int hash = key.hashCode();
                
                int index = (hash & 0x7FFFFFFF) % tab.length;
                
                for (Entry<?,?> e = tab[index] ; e != null ; e = e.next) {
                
                    if ((e.hash == hash) && e.key.equals(key)) {
                        return (V)e.value;
                    }
                }
                
                return null;
            }
            
* <b>protected void rehash()</b> - Increases the capacity of and internally reorganizes this hashtable, in order to accommodate and access its entries more efficiently.  This method is called automatically when the number of keys in the hashtable exceeds this hashtable's capacity and load factor. <p>

* <b>private void addEntry(int hash, K key, V value, int index) </b>

            private void addEntry(int hash, K key, V value, int index) {
            
                modCount++;
        
                Entry<?,?> tab[] = table;
                
                if (count >= threshold) {
                    // Rehash the table if the threshold is exceeded
                    rehash();
        
                    tab = table;
                    hash = key.hashCode();
                    index = (hash & 0x7FFFFFFF) % tab.length;
                }
        
                // Creates the new entry.
                Entry<K,V> e = (Entry<K,V>) tab[index];
                tab[index] = new Entry<>(hash, key, value, e);
                count++;
            }
            
* <b>public synchronized V put(K key, V value) - Maps the specified <code>key</code> to the specified <code>value</code> in this hashtable. Neither the key nor the value can be <code>null</code>. Returns the previous value of the specified key in this hashtable, or <code>null</code> if it did not have one.</b>

            public synchronized V put(K key, V value) {
            
                // Make sure the value is not null
                if (value == null) {
                    throw new NullPointerException();
                }
        
                // Makes sure the key is not already in the hashtable.
                Entry<?,?> tab[] = table;
                
                int hash = key.hashCode();
                
                int index = (hash & 0x7FFFFFFF) % tab.length;
                
                Entry<K,V> entry = (Entry<K,V>)tab[index];
                
                for(; entry != null ; entry = entry.next) {
                
                    if ((entry.hash == hash) && entry.key.equals(key)) {
                    
                        V old = entry.value;
                        entry.value = value;
                        return old;
                    }
                }
        
                addEntry(hash, key, value, index);
                
                return null;
            }
            
* <b>public synchronized V remove(Object key) - Removes the key (and its corresponding value) from this hashtable. This method does nothing if the key is not in the hashtable. Return the value to which the key had been mapped in this hashtable, or <code>null</code> if the key did not have a mapping.</b>

            public synchronized V remove(Object key) {
            
                Entry<?,?> tab[] = table;
                
                int hash = key.hashCode();
                
                int index = (hash & 0x7FFFFFFF) % tab.length;
                
                Entry<K,V> e = (Entry<K,V>)tab[index];
                
                for(Entry<K,V> prev = null ; e != null ; prev = e, e = e.next) {
                
                    if ((e.hash == hash) && e.key.equals(key)) {
                    
                        modCount++;
                        
                        if (prev != null) {
                            prev.next = e.next;
                        } else {
                            tab[index] = e.next;
                        }
                        
                        count--;
                        
                        V oldValue = e.value;
                        
                        e.value = null;
                        
                        return oldValue;
                    }
                }
                
                return null;
            }
            
* <b>public synchronized void clear() - Clears this hashtable so that it contains no keys.</b>

            public synchronized void clear() {
            
                Entry<?,?> tab[] = table;
                
                modCount++;
                
                for (int index = tab.length; --index >= 0; ){
                
                    tab[index] = null;
                }
                
                count = 0;
            }
            
* <b>public synchronized boolean equals(Object o) - Compares the specified Object with this Map for equality, as per the definition in the Map interface. Return true if the specified Object is equal to this Map</b>

            public synchronized boolean equals(Object o) {
            
                if (o == this)
                    return true;
        
                if (!(o instanceof Map))
                    return false;
                    
                Map<?,?> t = (Map<?,?>) o;
                
                if (t.size() != size())
                    return false;
        
                try {
                
                    Iterator<Map.Entry<K,V>> i = entrySet().iterator();
                    
                    while (i.hasNext()) {
                    
                        Map.Entry<K,V> e = i.next();
                        
                        K key = e.getKey();
                        
                        V value = e.getValue();
                        
                        if (value == null) {
                        
                            if (!(t.get(key)==null && t.containsKey(key)))
                                return false;
                                
                        } else {
                        
                            if (!value.equals(t.get(key)))
                                return false;
                        }
                        
                    }
                } catch (ClassCastException unused)   {
                    return false;
                } catch (NullPointerException unused) {
                    return false;
                }
        
                return true;
            }
            
* <b>public synchronized int hashCode() - Returns the hash code value for this Map as per the definition in the Map interface.</b>

            public synchronized int hashCode() {
        
                int h = 0;
                
                if (count == 0 || loadFactor < 0)
                    return h;  // Returns zero
        
                loadFactor = -loadFactor;  // Mark hashCode computation in progress
                
                Entry<?,?>[] tab = table;
                
                for (Entry<?,?> entry : tab) {
                
                    while (entry != null) {
                    
                        h += entry.hashCode();
                        entry = entry.next;
                        
                    }
                }
        
                loadFactor = -loadFactor;  // Mark hashCode computation complete
        
                return h;
            }

Inner Classes that stores the data:

* <b>private class KeySet extends AbstractSet<K></b>

            private class KeySet extends AbstractSet<K> {
            
                public Iterator<K> iterator() {
                    return getIterator(KEYS);
                }
                
                public int size() {
                    return count;
                }
                
                public boolean contains(Object o) {
                    return containsKey(o);
                }
                
                public boolean remove(Object o) {
                    return Hashtable.this.remove(o) != null;
                }
                
                public void clear() {
                    Hashtable.this.clear();
                }
                
            }
            
* <b>private class EntrySet extends AbstractSet<Map.Entry<K,V>> </b>

            private class EntrySet extends AbstractSet<Map.Entry<K,V>> {
            
                    public Iterator<Map.Entry<K,V>> iterator() {
                        return getIterator(ENTRIES);
                    }
            
                    public boolean add(Map.Entry<K,V> o) {
                        return super.add(o);
                    }
            
                    public boolean contains(Object o) {
                    
                        if (!(o instanceof Map.Entry))
                            return false;
                            
                        Map.Entry<?,?> entry = (Map.Entry<?,?>)o;
                        
                        Object key = entry.getKey();
                        
                        Entry<?,?>[] tab = table;
                        
                        int hash = key.hashCode();
                        
                        int index = (hash & 0x7FFFFFFF) % tab.length;
            
                        for (Entry<?,?> e = tab[index]; e != null; e = e.next)
                            if (e.hash==hash && e.equals(entry))
                                return true;
                                
                        return false;
                    }
            
                    public boolean remove(Object o) {
                    
                        if (!(o instanceof Map.Entry))
                            return false;
                            
                        Map.Entry<?,?> entry = (Map.Entry<?,?>) o;
                        
                        Object key = entry.getKey();
                        
                        Entry<?,?>[] tab = table;
                        
                        int hash = key.hashCode();
                        
                        int index = (hash & 0x7FFFFFFF) % tab.length;
            
                        Entry<K,V> e = (Entry<K,V>)tab[index];
                        
                        for(Entry<K,V> prev = null; e != null; prev = e, e = e.next) {
                        
                            if (e.hash==hash && e.equals(entry)) {
                            
                                modCount++;
                                
                                if (prev != null)
                                    prev.next = e.next;
                                else
                                    tab[index] = e.next;
            
                                count--;
                                e.value = null;
                                return true;
                            }
                        }
                        return false;
                    }
            
                    public int size() {
                        return count;
                    }
            
                    public void clear() {
                        Hashtable.this.clear();
                    }
                }