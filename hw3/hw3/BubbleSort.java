/* BubbleSort.java
* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
*/

package hw3;

import hw2.Array;

/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

    @Override
    public void sort(Array<T> array) {
        int len = array.length();
        while (len > 1) {
            boolean swap = false;
            for (int i = 0; i < len - 1; i++) {

                if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                    //checks to see if the curr val is > than the next value
                    T temp = array.get(i);
                    array.put(i, array.get(i + 1));
                    array.put(i + 1, temp);
                    swap = true;
                }
            }
            if (!swap) {
                //if a pass is made without changes then the sort is complete
                break;
            }


            len--;
        }
    }

    @Override
    public String name() {
        return "Bubble Sort";
    }
}
