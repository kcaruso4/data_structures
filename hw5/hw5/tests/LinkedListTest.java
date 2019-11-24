package hw5.tests;

import hw5.LinkedList;
import hw5.List;

/** Instantiate the LinkedList to test. */
public class LinkedListTest extends ListTest {
    @Override
    protected List<String> createList() {
        return new LinkedList<>();
    }
}
