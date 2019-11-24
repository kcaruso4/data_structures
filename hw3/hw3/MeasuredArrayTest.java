/* MeasuredArrayTest.java */


package hw3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the MeasuredArray class and function.
 */
public class MeasuredArrayTest {

    private static final int SIZE = 20;
    private static final String VAL = "test";

    //this variable stores measure array of strings
    private MeasuredArray<String> array;

    /**
    * This function creates a new array of
    * the static size and val and stores it
    * in the private field array.
    */
    @Before
    public void createArray() {
        this.array = new MeasuredArray<>(SIZE, VAL);
    }

    /**
    * This function checks to makse sure a new
    * array has no mutations.
    */
    @Test
    public void newArrayZeroMutations() {
        assertEquals(0, array.mutations());
    }

    /**
    * This function checks to makse sure a new
    * array has no accesses.
    */
    @Test
    public void newArrayZeroAccess() {
        assertEquals(0, array.accesses());
    }

    /**
    * This function tests to make sure the
    * mutations are properly counted.
    */
    @Test
    public void arrayMutations() {
        array.put(2, "hello");
        array.put(7, "coolio");
        assertEquals(2, array.mutations());
    }

    /**
    * This function tests to make sure the
    * accesses are properly counted.
    */
    @Test
    public void arrayAccess() {
        array.get(0);
        array.get(5);
        array.length();
        assertEquals(3, array.accesses());
    }

    /**
    * This function tests to make sure that
    * reset() properly resets the access and mutations
    * count when called.
    */
    @Test
    public void arrayReset() {
        array.get(0);
        array.length();
        array.put(3, "Yuh");
        assertEquals(2, array.accesses());
        assertEquals(1, array.mutations());
        array.reset();
        assertEquals(0, array.accesses());
        assertEquals(0, array.mutations());
    }

    /**
    * This function checks to make sure the count
    * function records the counts of a given value.
    */
    @Test
    public void arrayCount() {
        assertEquals(20, array.count(VAL));
        array.put(3, "hello");
        array.put(17, "hello");
        assertEquals(2, array.count("hello"));
    }
}
