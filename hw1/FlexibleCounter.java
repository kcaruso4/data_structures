/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * FlexibleCounter.java
 */

package hw1;

/** Class for a counter with flexible starting and incrementing values. */
public class FlexibleCounter implements ResetableCounter {
    /** stores value of the counter and change in value*/
    private int count;
    private int difference;
    private int original; //stores the orig value
    /**
     * Construct a new FlexibleCounter.
     * @param initialValue The value to start at.
     * @param incrementValue The value to increment the counter by.
     * @throws IllegalArgumentException If incrementValue is negative.
     */

    public FlexibleCounter(int initialValue, int incrementValue) {
        if (incrementValue < 0) {
            throw new IllegalArgumentException();
        }
        count = initialValue;
        original = initialValue;
        difference = incrementValue;
    }

    @Override
    public void reset() {
        count = original;
    }

    @Override
    public int value() {
        return count;
    }

    @Override
    public void up() {
        count += difference;
    }

    @Override
    public void down() {
        count -= difference;
    }

}
