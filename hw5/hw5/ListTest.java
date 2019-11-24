/**
* Keilani Caruso, kcaruso4, kcaruso4@jhu.edu
*/
package hw5.tests;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw5.Position;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the List interface.
 *
 * The tests defined here apply to all implementations of the List
 * interface. However, they cannot be run directly as we don't know
 * which implementation to test or how to create an instance of it.
 *
 * The solution is to define a "template method" called createList()
 * that subclasses of this test override. The LinkedListTest.java class,
 * for example, creates a suitable LinkedList instance to be tested.
 *
 * Note that we (somewhat arbitrarily) choose to test lists of strings.
 * We could have gone for lists of integers or lists of whatever, but
 * strings seem convenient in any case: You can pick strings in such a
 * way as to make your test cases more readable.
 */
public abstract class ListTest {
    private List<String> list;

    protected abstract List<String> createList();

    @Before
    public void setupListTests() {
        list = this.createList();
    }

    @Test
    public void newListEmpty() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test(expected=EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    @Test(expected=EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

    @Test
    public void insertFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertBackWorks() {
        list.insertBack("One");
        list.insertBack("Two");
        list.insertBack("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[One, Two, Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertFrontBackConsistent() {
        Position<String> f = list.insertFront("Front");
        assertEquals("Front", f.get());
        Position<String> b = list.insertBack("Back");
        assertEquals("Back", b.get());

        assertNotEquals(f, b);

        assertTrue(list.first(f));
        assertTrue(list.last(b));

        Position<String> x;

        x = list.front();
        assertEquals(f, x);

        x = list.back();
        assertEquals(b, x);
    }

    @Test
    public void removeFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeFront();
        list.removeFront();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test
    public void removeBackWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeBack();
        list.removeBack();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    // TODO You need to add *many* more test cases here, ideally before you
    // even start working on LinkedList!

    /**
    * Test insertBefore on an empty ListTest.
    */
    @Test(expected=PositionException.class)
    public void newListInsertBefore() {
        Position<String> f = list.insertFront("hello");
        list.removeFront();
        assertTrue(list.empty());
        Position<String> here = list.insertBefore(f, "bye");
    }

    /**
    * Test insertAfter on an empty ListTest.
    */
    @Test(expected=PositionException.class)
    public void newListInsertAfter() {
        Position<String> f = list.insertFront("hello");
        list.removeFront();
        assertTrue(list.empty());
        Position<String> here = list.insertAfter(f, "bye");
    }

    /**
    * Test remove on an empty ListTest.
    */
    @Test(expected=PositionException.class)
    public void newListRemove() {
        Position<String> f = list.insertFront("hello");
        list.removeFront();
        assertTrue(list.empty());
        list.remove(f);
    }

    /**
    * Tests insertBefore on nonempty list.
    */
    @Test
    public void insertBeforeWorks() {
        Position<String> firstnode = list.insertFront("one");
        assertFalse(list.empty());
        Position<String> newfirst = list.insertBefore(firstnode, "two");
        assertEquals(newfirst, list.front());
        assertEquals(2, list.length());
        assertEquals("[two, one]", list.toString());
        list.insertBefore(firstnode, "one and a half");
        assertEquals("[two, one and a half, one]", list.toString());
    }

    /**
    * Tests insertAfter on nonempty list.
    */
    @Test
    public void insertAfterWorks() {
        Position<String> firstnode = list.insertFront("one");
        assertFalse(list.empty());
        Position<String> lastnode = list.insertAfter(firstnode, "two");
        assertEquals(lastnode, list.back());
        assertEquals(2, list.length());
        assertEquals("[one, two]", list.toString());
        list.insertAfter(firstnode, "one and a half");
        assertEquals("[one, one and a half, two]", list.toString());
    }

    /**
    * Tests remove on nonempty list.
    */
    @Test
    public void removeWorks() {
        Position<String> firstnode = list.insertFront("hello");
        assertEquals(1, list.length());
        assertEquals("[hello]", list.toString());
        list.remove(firstnode);
        assertTrue(list.empty());
        assertEquals(0, list.length());

        firstnode = list.insertFront("goodbye");
        Position<String> secondnode = list.insertBack("here");
        assertEquals(2, list.length());
        assertEquals("[goodbye, here]", list.toString());
        list.remove(firstnode);
        assertEquals(1, list.length());
        assertEquals("[here]", list.toString());
    }

    /**
    * Test removeFront on empty list.
    */
    @Test(expected=EmptyException.class)
    public void newListRemoveFront() {
        list.removeFront();
    }

    /**
    * Tests removeBack on empty list.
    */
    @Test(expected=EmptyException.class)
    public void newListRemoveBack() {
        list.removeBack();
    }

    /**
    * Tests first when position is not front.
    */
    @Test
    public void frontWorks() {
        Position<String> firstnode = list.insertFront("one");
        assertEquals(1, list.length());
        Position<String> second = list.insertBack("two");
        assertEquals(2, list.length());
        assertEquals("[one, two]", list.toString());
        assertFalse(list.first(second));
    }

    /**
    * Tests last when position is not back.
    */
    @Test
    public void lastWorks() {
        Position<String> firstnode = list.insertFront("one");
        assertEquals(1, list.length());
        Position<String> second = list.insertBack("two");
        assertEquals(2, list.length());
        assertEquals("[one, two]", list.toString());
        assertFalse(list.last(firstnode));
    }

    /**
    * Tests first when position is not valid.
    */
    @Test(expected=PositionException.class)
    public void firstNoWork() {
        Position<String> firstnode = list.insertFront("hello");
        Position<String> p = list.insertBack("goodbye");
        assertEquals(2, list.length());
        list.remove(firstnode);
        assertEquals(1, list.length());
        list.first(firstnode);
    }

    /**
    * Tests last when position is not valid.
    */
    @Test(expected=PositionException.class)
    public void lastNoWork() {
        Position<String> firstnode = list.insertFront("hello");
        Position<String> p = list.insertBack("goodbye");
        assertEquals(2, list.length());
        list.remove(firstnode);
        assertEquals(1, list.length());
        list.last(firstnode);
    }


    /**
    * Tests next when position is invalid.
    */
    @Test(expected=PositionException.class)
    public void invalidNext() {
        Position<String> firstnode = list.insertFront("hello");
        list.remove(firstnode);
        assertTrue(list.empty());
        Position<String> p = list.next(firstnode);
    }

    /**
    * Tests next when position is the last position.
    */
    @Test(expected=PositionException.class)
    public void lastNext() {
        Position<String> lastnode = list.insertFront("hello");
        Position<String> firstnode = list.insertFront("bye");
        assertEquals(2, list.length());
        Position<String> p = list.next(lastnode);
    }

    /**
    * Tests next when position is valid for function.
    */
    @Test
    public void nextWorks() {
        Position<String> secondnode = list.insertFront("bye");
        Position<String> firstnode = list.insertFront("hello");
        assertEquals(2, list.length());
        assertEquals(secondnode, list.next(firstnode));
        Position<String> middle = list.insertBefore(secondnode, "middle");
        assertEquals("[hello, middle, bye]", list.toString());
        assertEquals(middle, list.next(firstnode));
        assertEquals(secondnode, list.next(middle));
    }

    /**
    * Tests previous when position is invalid.
    */
    @Test(expected=PositionException.class)
    public void invalidPrevious() {
        Position<String> firstnode = list.insertFront("hello");
        list.remove(firstnode);
        assertTrue(list.empty());
        Position<String> p = list.previous(firstnode);
    }

    /**
    * Tests previous when position is first.
    */
    @Test(expected=PositionException.class)
    public void firstPrevious() {
        Position<String> firstnode = list.insertFront("hello");
        assertEquals(1, list.length());
        Position<String> p = list.previous(firstnode);
    }

    /**
    * Tests previous when position is valid for function.
    */
    @Test
    public void previousWorks() {
        Position<String> firstnode = list.insertFront("hello");
        Position<String> secondnode = list.insertBack("bye");
        assertEquals("[hello, bye]", list.toString());
        assertEquals(firstnode, list.previous(secondnode));
        Position<String> middle = list.insertBefore(secondnode, "middle");
        assertEquals(3, list.length());
        assertEquals(middle, list.previous(secondnode));
        assertEquals(firstnode, list.previous(middle));
    }

    /**
    * Tests forward on empty list.
    */
    @Test
    public void newListForward() {
        Iterator<String> it = list.forward();
        try {
            it.next();
            assertTrue(false);
        }
        catch(NoSuchElementException e) {
            //if caught successful
        }
    }

    /**
    * Test forward on full list.
    */
    @Test
    public void forwardWorks() {
        list.insertFront("hello");
        list.insertFront("goodbye");
        assertEquals(2, list.length());
        Iterator<String> it = list.forward();
        assertEquals("goodbye", it.next());
        assertEquals("hello", it.next());
        try {
            it.next();
            assertTrue(false);
        }
        catch (NoSuchElementException e) {
            //should catch an exception
        }
    }

    /**
    * Tests backward on empty list.
    */
    @Test
    public void newListBackward() {
        Iterator<String> it = list.backward();
        try {
            it.next();
            assertTrue(false);
        }
        catch(NoSuchElementException e) {
            //if caught successful
        }
    }

    /**
    * Test backward on full list.
    */
    @Test
    public void backwardWorks() {
        list.insertBack("hello");
        list.insertFront("goodbye");
        assertEquals(2, list.length());
        Iterator<String> it = list.backward();
        assertEquals("hello", it.next());
        assertEquals("goodbye", it.next());
        try {
            it.next();
            assertTrue(false);
        }
        catch (NoSuchElementException e) {
            //should catch an exception
        }
    }

}
