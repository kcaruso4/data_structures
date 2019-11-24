/* MeasuredArray.java
* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
*/

package hw3;

import exceptions.IndexException;
import hw2.SimpleArray;

/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {


    //private variables
    private int len;
    private int mcount;
    private int acount;

    /**
     * Constructor for a MeasuredArray that calls the SimpleArray constructor.
     * @param n The size of the array.
     * @param t The initial value to set every object to in the array..
     */
    public MeasuredArray(int n, T t) {
        super(n, t);
        // TODO
        len = n;
        mcount = 0;
        acount = 0;
    }

    @Override
    public int length() {
        acount++;
        return len;
    }

    @Override
    public T get(int i) throws IndexException {
        acount++;
        return super.get(i);
    }

    @Override
    public void put(int i, T t) throws IndexException {
        mcount++;
        super.put(i, t);
    }

    @Override
    public void reset() {
        mcount = 0;
        acount = 0;
    }

    @Override
    public int accesses() {
        return acount;
    }

    @Override
    public int mutations() {
        return mcount;
    }

    @Override
    public int count(T t) {
        int tcount = 0;
        for (int i = 0; i < len; i++) {
            if (this.get(i) == t) {
                tcount++;
            }
            acount++;
        }
        return tcount;
    }
}
