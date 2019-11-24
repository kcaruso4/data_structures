// Deque226Test.java
// Keilani Caruso kcaruso4 kcaruso4@jhu.edu

package hw4.tests;

import exceptions.EmptyException;
import hw4.Deque226;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public abstract class Deque226Test {

    // Subclasses implement this to return the instance of the Deque226
    // they are testing.
    protected abstract Deque226<String> createUnit();

    // The unit being tested
    private Deque226<String> deque;

    @Before
    public void setupDequeue() {
        this.deque = createUnit();
    }

    @Test
    public void newDequeueEmpty() {
       assertTrue(deque.empty());
       assertEquals(0, deque.length());
    }

    @Test(expected=EmptyException.class)
    public void removeFrontOnEmpty() {
        deque.removeFront();
    }

    // TODO - Add more tests!
    @Test(expected=EmptyException.class)
    public void removeBackOnEmpty() {
        deque.removeBack();
    }

    /**
    * this function tests the front function.
    */
    @Test
    public void testFrontFail() {
        try {
            deque.front();
            assertTrue(false);
        }
        catch (EmptyException e) {
            //should catch exception
        }
    }

    /**
    * this function tests the back function.
    */
    @Test
    public void testBackFail() {
        try {
            deque.back();
            assertTrue(false);
        }
        catch (EmptyException e) {
            //should catch exception
        }
    }

    /**
    * this function tests empty.
    */
    @Test
    public void testEmpty() {
        assert(deque.empty());
        deque.insertFront("hello");
        assert(!deque.empty());
    }

    /**
    * this function tests first insertFront.
    */
    @Test
    public void testFirstInsertFront() {
        deque.insertFront("hello");
        assertEquals("hello", deque.front());
    }

    /**
    * this function tests first insertBack.
    */
    @Test
    public void testFirstInsertBack() {
        deque.insertBack("hello");
        assertEquals("hello", deque.back());
    }

    /**
    * this function test resize and insertFront.
    */
    @Test
    public void testResizeInsertFront() {
        deque.insertFront("first");
        deque.insertFront("second");
        assertEquals("second", deque.front());
        assertEquals("first", deque.back());
        deque.insertFront("third");
        assertEquals("third", deque.front());
        assertEquals("first", deque.back());
        deque.insertFront("fourth");
        assertEquals("fourth", deque.front());
    }

    /**
    * This function tests insertBack and resize.
    */
    @Test
    public void testResizeInsertBack() {
        deque.insertBack("first");
        deque.insertBack("second");
        assertEquals("second", deque.back());
        deque.insertBack("third");
        assertEquals("third", deque.back());
        deque.insertBack("fourth");
        assertEquals("fourth", deque.back());
    }

    /**
    * This function tests removeFront.
    */
    @Test
    public void testRemoveFront() {
        try {
            deque.removeFront();
            assertTrue(false);
        }
        catch (EmptyException e) {
            //should fail
        }
        deque.insertFront("first");
        assertEquals("first", deque.front());
        deque.removeFront();
        assertEquals(0, deque.length());
        deque.insertFront("first");
        deque.insertFront("second");
        deque.removeFront();
        assertEquals("first", deque.front());
        deque.insertFront("second");
        assertEquals("second", deque.front());
        deque.insertFront("third");
        deque.insertFront("fourth");
        deque.removeFront();
        assertEquals("third", deque.front());
    }

    /**
    * This function tests removeBack.
    */
    @Test
    public void testRemoveBack() {
        try {
            deque.removeBack();
            assertTrue(false);
        }
        catch (EmptyException e) {
            //shoudl fail
        }
        deque.insertBack("first");
        deque.removeBack();
        assertEquals(0, deque.length());
        deque.insertBack("first");
        deque.insertBack("second");
        deque.removeBack();
        assertEquals("first", deque.back());
        deque.insertBack("second");
        deque.insertBack("thrid");
        deque.insertBack("fourth");
        deque.removeFront();
        deque.insertBack("fifth");
        assertEquals("fifth", deque.back());
        deque.removeBack();
        assertEquals("fourth", deque.back());
    }

    /**
    * This function tests length.
    */
    @Test
    public void testLength() {
        deque.insertFront("first");
        assertEquals(1, deque.length());
        deque.insertFront("second");
        assertEquals(2, deque.length());
        deque.insertFront("third");
        assertEquals(3, deque.length());
    }

    /**
    * This function tests toString.
    */
    @Test
    public void testToString() {
        assertEquals("[]", deque.toString());
        deque.insertFront("first");
        assertEquals("[first]", deque.toString());
        deque.insertFront("second");
        assertEquals("[second, first]", deque.toString());
        deque.insertBack("third");
        assertEquals("[second, first, third]", deque.toString());
    }


}
