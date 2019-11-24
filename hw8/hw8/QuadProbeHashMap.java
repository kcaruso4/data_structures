package hw8;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Array;

/**
 * Map from arbitrary keys to arbitrary values, implemented
 * with a Quadratic Probing Open Addressing HashMap.
 * Each iterator operates on a copy of the keys.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class QuadProbeHashMap<K, V> implements Map<K, V> {

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

    private int[] primes = {17, 29, 53, 97, 193, 389, 769, 1543,
        3079, 6151, 12289, 24593, 49157, 98317, 196613, 393241, 786433, 1572869,
        3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611,
        402653189, 805306457, 1610612741};
    private Node[] hashtable;
    private int sizeM; //size of the hashtable
    private int sizeN; //num of ele in hashtable
    private int sizeT;

    /**
    * Create an empty hashtable.
    */
    public QuadProbeHashMap() {
        this.hashtable =
            (Node[]) Array.newInstance(Node.class, 11);
        this.sizeM = 11;
    }

    @Override
    public int size() {
        return this.sizeN;
    }

    private Node find(K k) throws IllegalArgumentException {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        int index = this.compress(k.hashCode());
        int newIndex;
        for (int i = 0; i < sizeM; i++) {
            newIndex = (int) Math.abs((index + Math.pow(i, 2)) % sizeM);
            if (hashtable[newIndex] == null) {
                return null;
            }
            else if (k.equals(hashtable[newIndex].key)) {
                if (hashtable[newIndex].val == null) {
                    return null;
                }
                return hashtable[newIndex];
            }
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

    private Node findForSure(K k) throws IllegalArgumentException {
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
        int nadded = 0;
        int bench = sizeM * 2;
        int curr = primes[0];
        int index = 0;
        while (curr < bench) {
            curr = primes[++index];
        }
        int oldsize = sizeM;
        sizeM = curr;
        Node[] temp = hashtable;
        hashtable = (Node[]) Array.newInstance(Node.class, sizeM);
        for (int i = 0; i < oldsize; i++) {
            if (temp[i] != null) {
                this.insert(temp[i]);
                nadded++;
            }
            if (nadded == sizeN + sizeT) {
                break;
            }
        }
    }

    private int compress(int hashcode) {
        return Math.abs(hashcode % sizeM);
    }

    private void insert(Node n) throws IllegalArgumentException {
        int index = this.compress((n.key).hashCode());
        int newIndex;
        for (int i = 0; i < sizeM; i++) {
            newIndex = (int) Math.abs((index + Math.pow(i, 2)) % sizeM);
            if (hashtable[newIndex] == null ||
                hashtable[newIndex].val == null) {
                hashtable[newIndex] = n;
                break;
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
        n.val = null; //creates the tombstone
        sizeT++;
        sizeN--;
        return v;
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        for (int i = 0; i < sizeM; i++) {
            if (hashtable[i] != null &&
                hashtable[i].val != null) {
                keys.add(hashtable[i].key);
            }
        }
        return keys.iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("{");
        int count = 0;
        int newIndex;
        for (int i = 0; i < sizeM; i++) {
            if (hashtable[i] != null &&
                hashtable[i].val != null) {
                s.append("" + hashtable[i].key
                    + ": " + hashtable[i].val);
                if (count != sizeN - 1) {
                    s.append(", ");
                }
                count++;
            }
        }
        s.append("}");
        return s.toString();
    }


}
