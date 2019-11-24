/**
* Keilani Caruso, kcaruso4, kcaruso4@jhu.edu
*/

package hw5;

/**
 * Set implemented using plain Java arrays.
 *
 * incorporate the tranpose heuristic each time a value is
 * accessed. Override the relevant method(s) from ArraySet.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

    @Override
    protected int find(T t) {
        for (int i = 0; i < this.used; i++) {
            if (this.data[i].equals(t)) {
                if (i != 0) {
                    T temp = this.data[i - 1];
                    this.data[i - 1] = this.data[i];
                    this.data[i] = temp;
                    return i-1;
                }
                return 0;
            }
        }
        return -1;
    }
}
