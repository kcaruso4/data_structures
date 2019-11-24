package hw7.tests;

import hw7.AvlTreeMap;
import hw7.BinarySearchTreeMap;
import hw7.OrderedMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AvlTreeMapTest extends BinarySearchTreeMapTest {
    private AvlTreeMap<String, String> u;

    @Override
    protected OrderedMap<String, String> createUnit() {
        return new AvlTreeMap<>();
    }

    @Before
    public void setUpTesting() {
        u = new AvlTreeMap<String, String>();
    }

    @Test
    public void singleLeftWorks() {
        assertEquals(0, u.size());
        u.insert("D", "hello");
        u.insert("C", "bye");
        u.insert("B", "next");
        assertEquals("C", u.getRoot());
        u.insert("A", "this");
        u.remove("D");
        assertEquals("B", u.getRoot());
    }

    @Test
    public void singleRightWorks() {
        assertEquals(0, u.size());
        u.insert("A", "next");
        u.insert("B", "hello");
        u.insert("C", "bye");
        assertEquals("B", u.getRoot());
        u.insert("D", "this");
        assertEquals(4, u.size());
        u.remove("A");
        assertEquals("C", u.getRoot());
    }

    @Test
    public void doubleLeftRightWorks() {
        assertEquals(0, u.size());
        u.insert("G", "first");
        u.insert("B", "second");
        u.insert("O", "third");
        u.insert("L", "fourth");
        u.insert("Z", "fith");
        u.insert("K", "sixth");
        assertEquals("L", u.getRoot());
        u.insert("N", "next");
        u.insert("M", "yep");
        u.remove("B");
        u.remove("K");
        assertEquals("N", u.getRoot());
    }

    @Test
    public void doubleRightLeftWorks() {
        assertEquals(0, u.size());
        u.insert("G", "first");
        u.insert("B", "second");
        u.insert("H", "third");
        u.insert("E", "fourth");
        u.insert("A", "fith");
        u.insert("F", "sixth");
        assertEquals("E", u.getRoot());
        u.insert("C", "next");
        u.insert("D", "this");
        u.remove("H");
        u.remove("F");
        assertEquals("C", u.getRoot());
    }
}
