package hw5.tests;

import hw5.ListSet;
import hw5.Set;

/** Instantiate the ListSet to test. */
public class ListSetTest extends SetTest {
    @Override
    protected Set<String> createUnit() {
        return new ListSet<>();
    }
}
