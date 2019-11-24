// ListDeque226Test.java

package hw4.tests;


import hw4.Deque226;
import hw4.ListDeque226;

public class ListDeque226Test extends Deque226Test {
    @Override
    protected Deque226<String> createUnit() {
        return new ListDeque226<>();
    }
}
