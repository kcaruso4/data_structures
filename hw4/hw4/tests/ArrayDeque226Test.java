/* 601.226
 * ArrayDeque226Test.java
 */

package hw4.tests;


import hw4.ArrayDeque226;
import hw4.Deque226;

public class ArrayDeque226Test extends Deque226Test {
    @Override
    protected Deque226<String> createUnit() {
        return new ArrayDeque226<>();
    }
}
