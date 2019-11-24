/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * TenCounter.java
 */

package hw1;

/** A counter for powers of 10. */
public class TenCounter implements ResetableCounter {
    /** variable holds the value of the counter */
    private int count;

    /** Construct a new TenCounter. */
    public TenCounter() {
        count = 1;
    }

    @Override
    public void reset() {
        count = 1;
    }

    @Override
    public int value() {
        return count;
    }

    @Override
    public void up() {
        count *= 10;
    }

    @Override
    public void down() {
        count /= 10;
        if (count < 1) {
            count = 1;
        }
    }
}
