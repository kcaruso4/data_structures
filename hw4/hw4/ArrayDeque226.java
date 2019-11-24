// ArrayDeque226.java

package hw4;

import exceptions.EmptyException;
import hw2.SimpleArray;

/**
 * An implementation of Deque226 using an Array.
 * @param <T> The type of the queue
 */
public class ArrayDeque226<T> implements Deque226<T> {

    //keeps track of front;
    private int head;
    //keeps track of back;
    private int tail;
    //keeps track of how many variables are in the deck
    private int count;
    //Private SimpleArray
    private SimpleArray<T> arr;

    //NEED TO COMPLETE!!!
    /**
     * Constructor to create a new ArrayDeque226.
     */
    public ArrayDeque226() {
        // TODO
        arr = new SimpleArray<T>(1, null);
        head = 0;
        tail = 0;
        count = 0;
    }

    @Override
    public boolean empty() {
        return count == 0;
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public T front() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        }
        return arr.get(head);
    }

    @Override
    public T back() throws EmptyException {
        if (empty()) {
            throw new EmptyException();
        }
        return arr.get(tail);
    }

    @Override
    public void insertFront(T t) {
        if (count == arr.length()) {
            resizeArray();
        }
        if (count == 0) {
            arr.put(head, t);
            count++;
        }
        else {
            head = (head - 1 + arr.length()) % arr.length();
            arr.put(head, t);
            count++;
        }
    }

    @Override
    public void insertBack(T t) {
        if (count == arr.length()) {
            resizeArray();
        }
        if (count == 0) {
            arr.put(tail, t);
            count++;
        }
        else {
            tail = (tail + 1 + arr.length()) % arr.length();
            arr.put(tail, t);
            count++;
        }
    }

    @Override
    public void removeFront() throws EmptyException {
        if (count == 0) {
            throw new EmptyException();
        }
        head = (head + 1 + arr.length()) % arr.length();
        count--;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (count == 0) {
            throw new EmptyException();
        }

        tail = (tail - 1 + arr.length()) % arr.length();
        count--;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < count; i++) {
            str += arr.get((head + i) % count);
            if (i != count - 1) {
                str += ", ";
            }
        }
        str += "]";
        return str;
    }

    /**
    * This function resizes the array.
    */
    private void resizeArray() {
        SimpleArray<T> temp = new SimpleArray<T>(this.arr.length() * 2, null);
        for (int i = 0; i < count; i++) {
            temp.put(i, this.arr.get((head + i) % count));
        }
        this.arr = temp;
        this.head = 0;
        this.tail = count - 1;
    }
}
