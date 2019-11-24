/* InsertionSort.java
* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
*/

package hw3;

import hw2.Array;


/**
 * The Insertion Sort algorithm.
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {


    @Override
    public void sort(Array<T> array) {
        int len = array.length();
        for (int i = 1; i < len; i++) {
            T temp = array.get(i);
            int j = i;
            while (j > 0 && temp.compareTo(array.get(j - 1)) < 0) {
                //checks to see if the curr val is < than the val at j
                //if the value is less then j is decreased
                array.put(j, array.get(j - 1));
                array.put(j - 1, temp);
                j--;
            }
        }
    }

    @Override
    public String name() {
        return "Insertion Sort";
    }
}
