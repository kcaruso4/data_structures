/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * PolyArray.java
 */

package hw2;

import exceptions.LengthException;

import java.util.ArrayList; // see note in main() below
import java.util.Iterator;
import exceptions.IndexException;

/**
 * Simple polymorphic test framework for arrays.
 * See last week's PolyCount. You need to add more test cases (meaning more
 * methods like testNewLength and testNewWrongLength below) to make sure all
 * preconditions and axioms are indeed as expected from the specification.
*/
public final class PolyArray {
    private static final int LENGTH = 113;
    private static final int INITIAL = 7;

    private PolyArray() {}

    private static void testNewLength(Array<Integer> a) {
        assert a.length() == LENGTH;
    }

    // TODO - Add more axiom tests
    //test test
    private static void testGet(Array<Integer> a) {
        assert a.get(3) == INITIAL;
    }

    //test put
    private static void testPut(Array<Integer> a) {

        a.put(2, 12);
        assert a.get(2) == 12;
        a.put(2, INITIAL);
        assert a.get(2) == INITIAL;
        a.put(LENGTH - 1, 3);
        assert a.get(LENGTH - 1) == 3;
    }

    //test iterator
    private static void testIterator(Array<Integer> a) {
        a.put(0, 4);
        a.put(1, 3);
        Iterator<Integer> it = a.iterator();
        assert it.hasNext();
        assert it.next() == 4;
        it.next();
        if (it.hasNext()) {
            for (int i = 0; i < 111; i++) {
                it.next();
            }
            assert !it.hasNext();
        }
    }


    private static void testNewWrongLength() {
        try {
            Array<Integer> a = new SimpleArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
    }

    // TODO - Add more exception tests
    /**
    * this methods test wrong in puts.
    * @param a is an object of the array class we are testing
    */
    public static void testWrongInputs(Array<Integer> a) {
        //test puts
        try {
            a.put(LENGTH, 3);
            assert false;
        }
        catch (IndexException e) {
            //if exception is properly thrown
        }
        try {
            a.put(-1, 3);
            assert false;
        }
        catch (IndexException e) {
            //if exception is properly thrown
        }

        //test get
        try {
            a.get(LENGTH);
            assert false;
        }
        catch (IndexException e) {
            //will catch is proper exception is thrown
        }
        try {
            a.get(-1);
            assert false;
        }
        catch (IndexException e) {
            //will catch is proper exception is thrown
        }

        //test iterator
        Iterator<Integer> it = a.iterator();
        try {
            it.remove();
            assert false;
        }
        catch (UnsupportedOperationException e) {
            //will only catch is proper exception is thrown
        }
    }

    /**
    * test improper constructor calls.
    * @param a object of the array class we are testing.
    */
    public static void testWrongConstructors(Array<Integer> a) {
        try {
            Array<Integer> b = new SimpleArray<Integer>(-1, 2);
            assert false;
        }
        catch (LengthException e) {
            //will catch if proper exception is thrown
        }
        try {
            Array<Integer> b = new ListArray<Integer>(-1, 2);
            assert false;
        }
        catch (LengthException e) {
            //will catch if proper exception is thrown
        }
        try {
            Array<Integer> b = new SparseArray<Integer>(-1, 2);
            assert false;
        }
        catch (LengthException e) {
            //will catch if proper exception is thrown
        }
    }

    /**
     * Run (mostly polymorphic) tests on various array implementations.
     * Make sure you run this with -enableassertions! We'll learn a much
     * better approach to unit testing later.
     *
     * @param args Command line arguments (ignored).
    */
    public static void main(String[] args) {
        // For various technical reasons, we cannot use a plain Java array here
        // like we did in PolyCount. Sorry.
        ArrayList<Array<Integer>> arrays = new ArrayList<>();
        arrays.add(new SimpleArray<>(LENGTH, INITIAL));
        arrays.add(new ListArray<>(LENGTH, INITIAL));
        arrays.add(new SparseArray<>(LENGTH, INITIAL));

        // Test all the axioms. We can do that nicely in a loop. In the test
        // methods, keep in mind that you are handed the same object over and
        // over again! I.e., order matters!
        for (Array<Integer> a: arrays) {
            testNewLength(a);
            // TODO - Call your axiom test methods
            testGet(a);
            testPut(a);
            testIterator(a);
        }

        // Exception testing. Sadly we have to code each one of these
        // out manually, not even Java's reflection API would help...
        testNewWrongLength();
        // TODO - Call your exception test methods
        for (Array<Integer> a: arrays) {
            testWrongInputs(a);
        }
    }
}
