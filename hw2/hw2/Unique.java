/* Keilani Caruso kcaruso4 kcaruso4@jhu.edu
 * Unique.java
 */

package hw2;

import java.util.Scanner;

/** Unique problem using a SparseArray and processing from standard in. */
public final class Unique {

    // make checkstyle happy
    private Unique() {}

    /**
     * Print only unique integers out of entered numbers.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //creates the Scanner
        Scanner sc = new Scanner(System.in);
        int element;
        int count = 0;
        boolean found = false;

        //creates the an array
        SimpleArray<Integer> store = new SimpleArray<Integer>(3, null);

        //goes through the iterator
        while (sc.hasNext()) {
            try {
                element = sc.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < count; i++) {
                if (element == store.get(i)) {
                    found = true;
                }
            }
            if (count == store.length()) {
                SimpleArray<Integer> temp = new SimpleArray<>(count * 2, null);
                for (int i = 0; i < count; i++) {
                    temp.put(i, store.get(i));
                }
                store = temp;
            }
            if (!found) {
                store.put(count, element);
                count++;
            }
            found = false;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(store.get(i));
        }
    }
}
