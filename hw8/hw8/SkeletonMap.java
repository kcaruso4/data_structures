package hw8;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;



public class ChainingHashMap<K , V> implements Map<K, V> {

    //Inner node class, each holds a key and a value corresponding to the keys
    private class Node {
        K key;
        ArrayList<V> val;

        //constructor to make node creation easier to read.
        Node(K k V v) {
            this.key = k;
            this.val = new ArrayList<V>();
            this.val.add(v);
        }

        //Just for debugging purposes.
        public String toString() {
            return "Node<key: " + this.key
                + "; value: " + this.value.toString()
                + ">";
        }
    }

    private Node[][] hashtable;
    private int sizeM; //size of the hashtable
    private int sizeN; //num of ele in hashtable
    private StringBuilder stringBuilder;

    @Override
    public int size() {
        return this.sizeN;
    }

    @Override
    public boolean has(K k) {
        if(k == null) {
            return false;
        }
        return this.find(k) != null;
    }

    @Override
    public void put(K k, V v) {

    }

    @Override
    public V get(K k) {

    }

    @Override
    public void insert(K k, V v) {

    }

    @Override
    public V remove(K k) {

    }

    @Override
    public Iterator<K> iterator() {

    }

    @Override
    public String toString() {

    }


}
