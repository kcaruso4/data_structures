package hw8;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array;

/**
 * Map from arbitrary keys to arbitrary values, implemented
 * with a Linked Chaining HashMap.
 * Each iterator operates on a copy of the keys.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class ChainingHashMap<K, V> implements Map<K, V> {

    //Inner node class, each holds a key and a value corresponding to the keys
    private class Node {
        K key;
        V val;

        //constructor to make node creation easier to read.
        Node(K k, V v) {
            this.key = k;
            this.val = v;
        }

        //Just for debugging purposes.
        public String toString() {
            return "Node<key: " + this.key.toString()
                + "; value: " + this.val.toString()
                + ">";
        }
    }

    private int[] primes = {17, 29, 53, 97, 193, 389, 769, 1543, 3079, 6151,
        12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869, 3145739,
        6291469, 12582917, 25165843, 50331653, 100663319, 201326611, 402653189,
        805306457, 1610612741};
    private Node[][] hashtable =
        (Node[][]) Array.newInstance(Node.class, 10, 11);
    private int sizeM = 11; //size of the hashtable
    private int sizeN; //num of ele in hashtable

    @Override
    public int size() {
        return this.sizeN;
    }

    //returns the node for a given key
    private Node find(K k) {
        if (k == null) {
            throw new  IllegalArgumentException("cannot handle null key");
        }
        int index = this.compress(k.hashCode());
        Node curr = hashtable[0][index];
        for (int i = 1; i <= 10; i++) {
            if (curr == null) {
                return null;
            }
            else if (k.equals(curr.key)) {
                if (curr.val == null) {
                    return null;
                }
                return curr;
            }
            curr = hashtable[i][index];
        }
        return null;
    }

    @Override
    public boolean has(K k) {
        if (k == null) {
            return false;
        }
        return this.find(k) != null;
    }

    //this function returns the node containing k
    private Node findForSure(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key" + k);
        }
        return n;
    }

    @Override
    public void put(K k, V v) throws IllegalArgumentException {
        Node n = this.findForSure(k);
        n.val = v;
    }

    @Override
    public V get(K k) throws IllegalArgumentException {
        Node n = this.findForSure(k);
        return n.val;
    }

    private void resize() {
        int bench = sizeM * 2;
        int curr = primes[0];
        int index = 0;
        while (curr < bench) {
            curr = primes[++index];
        }
        int oldsize = sizeM;
        sizeM = curr;
        Node[][] temp = hashtable;
        hashtable = (Node[][]) Array.newInstance(Node.class, 10, sizeM);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < oldsize; j++) {
                if (temp[i][j] != null) {
                    this.insert(temp[i][j]);
                }
            }
        }
    }

    //compresses the hashcode
    private int compress(int hashcode) {
        return Math.abs(hashcode % sizeM);
    }

    private void insert(Node n) throws IllegalArgumentException {
        int index = this.compress((n.key).hashCode());
        for (int i = 0; i < 10; i++) {
            if (hashtable[i][index] == null ||
                hashtable[i][index].val == null) {
                hashtable[i][index] = n;
                break;
            }
            else if (i == 9) {
                this.resize();
                this.insert(n);
            }
        }
    }

    @Override
    public void insert(K k, V v) throws IllegalArgumentException {
        if (k == null || this.has(k)) {
            throw new IllegalArgumentException("cannot handle this key");
        }
        if ((sizeN + 1.0) / sizeM >= .5) {
            this.resize();
        }
        this.insert(new Node(k, v));
        sizeN++;
    }

    @Override
    public V remove(K k) throws IllegalArgumentException {
        Node n = this.findForSure(k);
        V v = n.val;
        n.val = null; //makes the node a tombstone
        sizeN--;
        return v;
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < sizeM; j++) {
                if (hashtable[i][j] != null && hashtable[i][j].val != null) {
                    keys.add(hashtable[i][j].key);
                }
            }
        }
        return keys.iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < sizeM; j++) {
                if (hashtable[i][j] != null && hashtable[i][j].val != null) {
                    s.append("" + hashtable[i][j].key +
                        ": " + hashtable[i][j].val);
                    if (count != sizeN - 1) {
                        s.append(", ");
                    }
                    count++;
                }
            }
        }
        s.append("}");
        return s.toString();
    }


}
