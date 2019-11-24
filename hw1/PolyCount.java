/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * PolyCount.java
 */

package hw1;

/**
 * Basic tests for {@link hw1.ResetableCounter}s. Notes from phf's
 * PolyCount.java for this assignment.
 *
 * This is really just an example of subtype polymorphism. Note how most of
 * the code is written in terms of interfaces, not classes. The only time
 * classes are mentioned is when we have to actually instantiate the
 * counters.
 */
final class PolyCount {

    // For checkstyle to be happy.
    private PolyCount() {}

    // Test any instance of a ResetableCounter
    private static void testAnyCounter(ResetableCounter counter) {
        int x1 = counter.value();
        assert x1 >= 0;

        counter.up();
        int x2 = counter.value();
        assert x2 >= x1;
        counter.down(); //resets the counter to initial value
        // testing resetable counter more
        int value = counter.value();
        counter.up();

        //tests BasicCounter
        if (value + 1 == counter.value()) {
            counter.down();
            assert value == counter.value(); //test down
            counter.reset();
            assert 0 == counter.value(); //test reset
        }
        //tests TenCounter
        else if (value * 10 == counter.value()) {
            counter.down();
            assert value == counter.value(); //test down
            counter.reset();
            assert 1 == counter.value(); //test reset
            counter.down();
            assert 1 == counter.value(); //check for rounding
        }
        //testing FlexibleCounter
        else {
            assert value < counter.value();
            counter.down();
            assert value == counter.value(); //test down
            counter.up();
            counter.reset();
            assert value == counter.value(); //test reset
        }

        testException();


    }

    private static void testException() {
        boolean fails = false;
        try{
            new FlexibleCounter(5, -1);
        }
        catch (IllegalArgumentException e){
            fails = true;
        }
        assert fails;
    }

    /**
     * Run tests on the counters using Java assertions. This means you must
     * run this with -enableassertions (-ea). Assertion testing will do for
     * now, but soon we'll learn about JUnit which is a much better approach!
     * @param args Ignored.
     */
    public static void main(String[] args) {

        ResetableCounter[] counters = {
            new BasicCounter(),
            new TenCounter(),
            new FlexibleCounter(5, 7),
        };

        for (ResetableCounter counter : counters) {
            testAnyCounter(counter);
        }
    }
}
