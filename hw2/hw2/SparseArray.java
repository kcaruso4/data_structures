/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * SparseArray.java
 */

package hw2;

import exceptions.IndexException;
import exceptions.LengthException;

import java.util.Iterator;


/**
 * SparseArray implements the Array interface.
 * use a linked list to store value and position of changed elements
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

    private Node<T> list;
    private int len;
    private T defaultValue;

    private static class Node<T> {
        T data;
        int position;
        Node<T> next;
    }

    /**
     * An array that is meant to be filled primarily with a default value
     * that is not going to change - with the benefit of that default
     * value not being stored numerous times as opposed to once.
     * @param length The number of indexes the array should have.
     * @param defaultValue The default value for the array.
     * @throws LengthException if n &le; 0.
    */
    public SparseArray(int length, T defaultValue) throws LengthException {
        if (length <= 0) {
            throw new LengthException();
        }
        len = length;
        this.defaultValue = defaultValue;
    }

    // Implementation of the iterator
    private class SparseArrayIterator implements Iterator<T> {
        private Node<T> current;

        SparseArrayIterator() {
            current = SparseArray.this.list;
        }

        @Override
        public boolean hasNext() {
            if (current != null) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (current != null) {
                T temp = current.data;
                current = current.next;
                return temp;
            }
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SparseArrayIterator();
    }

    @Override
    public int length() {
        return len;
    }

    private Node<T> find(int index) {
        Node<T> temp = list;
        while (temp != null) {
            if (temp.position == index) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public T get(int i) throws IndexException {
        if (i < 0 || i >= this.len) {
            throw new IndexException();
        }
        Node<T> temp = this.find(i);
        if (temp == null) {
            return defaultValue;
        }
        return temp.data;
    }


    @Override
    public void put(int i, T t) throws IndexException {
        if (i < 0 || i >= len) {
            throw new IndexException();
        }
        if (t == defaultValue) {
            this.remove(i);
        }
        else {
            Node<T> curr = this.find(i);
            if (curr == null) {
                Node<T> newnode = new Node<T>();
                newnode.data = t;
                newnode.position = i;
                if (list != null) {
                    curr = list;
                    while (curr.next != null) {
                        curr = curr.next;
                    }
                    curr.next = newnode;
                }
                else {
                    list = newnode;
                }
            }
            else {
                curr.data = t;
            }
        }
    }

    /** will remove a changed node at this position.
    * @param i The index of the element we are removing.
    */
    public void remove(int i) {
        //Node<T> curr = list;
        Node<T> previous;
        Node<T> curr = list;
        if (curr.position == i) {
            if (curr.next == null) {
                list = new Node<T>();
            }
            else {
                list = curr.next;
            }
        }
        previous = curr;
        curr = curr.next;
        while (curr != null) {
            if (curr.position == i) {
                previous.next = curr.next;
                break;
            }
            else {
                previous = curr;
                curr = curr.next;
            }
        }
        if (curr == null) {
            previous.next = null;
        }
    }

}
