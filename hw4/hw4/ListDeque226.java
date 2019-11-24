// ListDeque226.java

package hw4;

import exceptions.EmptyException;

/**
 * The straightforward implementation of a Deque226 using a doubly linked
 * list of nodes. Note this is the same as NodeList (in future homeworks)
 * but without the position overhead since you can only access at the
 * ends of the queue. This is intentionally "poorly" written code since
 * a better solution could give hints to future homeworks.
 *
 * You don't need to worry about this, it's mainly just to demo how
 * our testing setup works with multiple implementations of the same
 * interface. We will cover lists as a data structure next.
 *
 * @param <T> Type for elements.
 */
public class ListDeque226<T> implements Deque226<T> {

    // Just a node to hold some data
    private static final class Node<T> {
        // The usual doubly-linked list stuff.
        Node<T> next;
        Node<T> prev;
        T data;
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    /**
     * Create an empty queue.
     */
    public ListDeque226() {
        // nothing to do
    }

    @Override
    public boolean empty() {
        return this.length == 0;
    }

    @Override
    public int length() {
        return this.length;
    }


    @Override
    public T front() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.head.data;
    }

    @Override
    public T back() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.tail.data;
    }

    @Override
    public void insertFront(T t) {
        Node<T> n = new Node<>();
        n.data = t;
        n.next = this.head;

        if (this.head != null) {
            this.head.prev = n;
        }

        this.head = n;
        if (this.tail == null) {
            this.tail = n;
        }
        this.length += 1;
    }

    @Override
    public void insertBack(T t) {
        Node<T> n = new Node<>();
        n.data = t;
        n.prev = this.tail;
        if (this.tail != null) {
            this.tail.next = n;
        }
        this.tail = n;
        if (this.head == null) {
            this.head = n;
        }
        this.length += 1;
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        Node<T> n = this.head;
        assert (n.prev == null);
        if (n.next != null) {
            n.next.prev = null;
        }
        this.head = n.next;
        if (this.tail == n) {
            this.tail = null;
        }
        this.length -= 1;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        Node<T> n = this.tail;
        assert (n.next == null);
        if (n.prev != null) {
            n.prev.next = null;
        }
        this.tail = n.prev;
        if (this.head == n) {
            this.head = null;
        }
        this.length -= 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Node<T> p = this.head; p != null; p = p.next) {
            sb.append(p.data.toString());
            if (p.next != null) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}