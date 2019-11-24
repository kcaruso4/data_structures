/* 601.226
 * ArrayStack.java
 */

package hw4;

import exceptions.EmptyException;

/**
 * Stack implemented using a growing array.
 *
 * All operations except push() take O(1) time in the worst
 * case; push() takes O(1) amortized time because the array
 * may need to be resized; however, compared to ListStack,
 * fewer push() operations result in objects being allocated.
 * @param <T> Element type.
*/
@SuppressWarnings("unchecked")  // We are allowed to do this, you are not :)
public class ArrayStack<T> implements Stack<T> {
    private T[] data;
    private int used;

    /**
     * Create an empty stack. Note the inevitable unchecked cast
     * warning for declaring a generic array.
     */
    public ArrayStack() {
        this.data = (T[]) new Object[1];
    }

    @Override
    public T top() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.data[this.used - 1];
    }

    @Override
    public void pop() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.used -= 1;
    }

    private boolean full() {
        return this.data.length == this.used;
    }

    private void grow() {
        T[] bigger = (T[]) new Object[this.data.length * 2];
        for (int i = 0; i < this.used; i++) {
            bigger[i] = this.data[i];
        }
        this.data = bigger;
    }

    @Override
    public void push(T t) {
        if (this.full()) {
            this.grow();
        }
        this.data[this.used] = t;
        this.used += 1;
    }

    @Override
    public boolean empty() {
        return this.used == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = this.used - 1; i >= 0; i--) {
            sb.append(this.data[i].toString());
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
