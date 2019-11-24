package hw5.tests;

import hw5.Set;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the Set interface.
 */
public abstract class SetTest {
    private Set<String> unit;

    protected abstract Set<String> createUnit();

    @Before
    public void setupTests() {
        unit = this.createUnit();
    }

    @Test
    public void newSetEmpty() {
        assertEquals(0, unit.size());
    }

    @Test
    public void newSetEmptyIterator() {
        int c = 0;
        for (String s: unit) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void insertNotEmpty() {
        unit.insert("Magda");
        assertEquals(1, unit.size());
    }

    @Test
    public void insertDuplicateSizeConsistent() {
        unit.insert("Magda");
        assertEquals(1, unit.size());
        unit.insert("Magda");
        assertEquals(1, unit.size());
        unit.insert("John");
        assertEquals(2, unit.size());
        unit.insert("Magda");
        assertEquals(2, unit.size());
        unit.insert("John");
        assertEquals(2, unit.size());
    }

    @Test
    public void insertHas() {
        unit.insert("Paul");
        assertTrue(unit.has("Paul"));
        assertFalse(unit.has("Peter"));
    }

    @Test
    public void insertRemoveHas() {
        unit.insert("Paul");
        unit.insert("Peter");

        assertTrue(unit.has("Paul"));
        assertTrue(unit.has("Peter"));
        assertFalse(unit.has("Mary"));

        unit.remove("Paul");

        assertFalse(unit.has("Paul"));
        assertTrue(unit.has("Peter"));
        assertFalse(unit.has("Mary"));
    }

    @Test
    public void manyInsertOneRemove() {
        unit.insert("Paul");
        unit.insert("Mary");

        assertFalse(unit.has("Peter"));
        assertTrue(unit.has("Paul"));
        assertTrue(unit.has("Mary"));

        unit.insert("Paul");

        assertFalse(unit.has("Peter"));
        assertTrue(unit.has("Paul"));
        assertTrue(unit.has("Mary"));

        unit.insert("Paul");

        assertFalse(unit.has("Peter"));
        assertTrue(unit.has("Paul"));
        assertTrue(unit.has("Mary"));

        unit.remove("Paul");

        assertFalse(unit.has("Peter"));
        assertFalse(unit.has("Paul"));
        assertTrue(unit.has("Mary"));
    }

    @Test
    public void insertManySizeConsistent() {
        for (int i = 0; i < 2000; i++) {
            unit.insert(String.valueOf(i * i));
        }
        assertEquals(2000, unit.size());
    }

    @Test
    public void iteratorWorks() {
        String[] data = {"Peter", "Paul", "Mary", "Beverly"};
        for (String d: data) {
            unit.insert(d);
        }
        for (String s: unit) {
            int count = 0;
            for (String d: data) {
                if (s.equals(d)) {
                    count += 1;
                }
            }
            assertEquals(1, count);
        }
    }
}
