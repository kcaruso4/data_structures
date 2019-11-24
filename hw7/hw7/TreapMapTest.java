package hw7.tests;

import hw7.TreapMap;
import hw7.BinarySearchTreeMap;
import hw7.OrderedMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TreapMapTest extends BinarySearchTreeMapTest {
    private TreapMap<String, String> u;

    @Override
    protected OrderedMap<String, String> createUnit() {
        return new TreapMap<>();
    }

    @Before
    public void setUpTesting() {
        u = new TreapMap<String, String>();
    }

    @Test
    public void SpecialinsertWorks() {
        u.insert("first", "hello");
        assertEquals(1, u.size());
        //test to make sure cannot insert a null key
        try {
            u.insert(null, "bye");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot insert an already mapped key
        try {
            u.insert("first", "different");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        int origpriority = u.getRootPriority();
        u.insert("second", "bye");
        assertEquals(2, u.size());
        int newpriority = u.getRootPriority();
        assertTrue(newpriority >= origpriority);
        u.insert("third", "maybe");
        u.insert("fourth", "yes");
        origpriority = newpriority;
        newpriority = u.getRootPriority();
        assertTrue(newpriority >= origpriority);
    }

    @Test
    public void SpecialremoveWorks() {
        u.insert("first", "hello");
        assertEquals(1, u.size());
        String s1 = u.remove("first");
        assertEquals(0, u.size());
        assertEquals("hello", s1);
        //test to make sure cannot remove a null key
        try {
            u.remove(null);
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        //test to make sure cannot remove a non mapped key
        try {
            u.remove("first");
            assertTrue(false);
        }
        catch (IllegalArgumentException e) {
            //should be caught
        }
        u.insert("first", "hello");
        u.insert("A", "B");
        u.insert("B", "C");
        u.insert("C", "D");
        int origpriority = u.getRootPriority();
        u.remove("first");
        int newpriority = u.getRootPriority();
        assertTrue(origpriority >= newpriority);
        u.remove("A");
        origpriority = newpriority;
        newpriority = u.getRootPriority();
        assertTrue(origpriority >= newpriority);
        u.remove("B");
        origpriority = newpriority;
        newpriority = u.getRootPriority();
        assertTrue(origpriority >= newpriority);
    }
}
