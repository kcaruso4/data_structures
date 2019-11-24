/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * BasicCounter.java
 */

package hw1;

/** A counter that increments and decrements by 1. */
public class BasicCounter implements ResetableCounter {
    /** field that stores value of the counter */
    private int count;

    /** Construct a new BasicCounter. */
    public BasicCounter() {
        count = 0;
    }

    @Override
    public void reset() {
        count = 0;
    }

    @Override
    public int value() {
        return count;
    }

    @Override
    public void up() {
        count++;
    }

    @Override
    public void down() {
        count--;
    }
}
