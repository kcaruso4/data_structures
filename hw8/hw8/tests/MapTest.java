package hw8.tests;

import hw8.Map;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
* Testing implementation of the Map interface.
*/
public abstract class MapTest {
    private Map<String, String> unit;

    protected abstract Map<String, String> createUnit();

    @Before
    public void setupTests() {
        unit = this.createUnit();
    }

    @Test
    public void newMapEmpty() {
        assertEquals(0, unit.size());
        assertFalse(unit.has("1"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void newMapNoRemove() {
        unit.remove("1");
    }

    @Test(expected=IllegalArgumentException.class)
    public void newMapNoPut() {
        unit.put("1", "one");
    }

    @Test(expected=IllegalArgumentException.class)
    public void newMapNoGet() {
        unit.get("1");
    }

    @Test
    public void insertWorks() {
        unit.insert("first", "hello");
        assertEquals(1, unit.size());
        //test to make sure cannot insert a null key
        try {
            unit.insert(null, "bye");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot insert an already mapped key
        try {
            unit.insert("first", "different");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        unit.insert("second", "bye");
        assertEquals(2, unit.size());
    }

    @Test
    public void removeWorks() {
        unit.insert("first", "hello");
        assertEquals(1, unit.size());
        String s1 = unit.remove("first");
        assertEquals(0, unit.size());
        assertEquals("hello", s1);
        //test to make sure cannot remove a null key
        try {
            unit.remove(null);
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot remove a non mapped key
        try {
            unit.remove("first");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
    }

    @Test
    public void putWorks() {
        unit.insert("first", "hello");
        assertEquals(1, unit.size());
        unit.put("first", "bye");
        assertEquals(1, unit.size());
        String s1 = unit.get("first");
        assertEquals("bye", s1);
        //test to make sure cannot put a new val at a null key
        try {
            unit.put(null, "bye");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot put at a key that isn't mapped
        try {
            unit.put("second", "wrong");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
    }

    @Test
    public void getWorks() {
        unit.insert("first", "hello");
        assertEquals(1, unit.size());
        String s1 = unit.get("first");
        assertEquals("hello", s1);
        //test to make sure cannot get with a null key
        try {
            unit.get(null);
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot get with a nonmapped key
        try {
            unit.get("second");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }

    }

    @Test
    public void hasWorks() {
        assertEquals(0, unit.size());
        assertFalse(unit.has("first"));
        unit.insert("first", "hello");
        assertTrue(unit.has("first"));
        assertFalse(unit.has("second"));
    }

    @Test
    public void iteratorWorks() {
        unit.insert("1", "hello");
        unit.insert("2", "bye");
        unit.insert("3", "next");
        Iterator<String> it = unit.iterator();
        for (int i = 1; it.hasNext(); i++) {
            assertEquals(i + "", it.next());
        }
        System.out.println();
    }

//need to figure out the order of printing the key/value pairs
//will there be an order to how we print the map
    @Test
    public void toStringWorks() {
        assertEquals(0, unit.size());
        assertEquals("{}", unit.toString());
        unit.insert("A", "hello");
        assertEquals("{A: hello}", unit.toString());
        unit.insert("B", "goodbye");
        unit.insert("D", "goodbye");
        unit.insert("Z", "goodbye");
        assertNotEquals(null, unit.toString());
    }
}
