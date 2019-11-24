package hw5.tests;

import hw5.ArraySet;
import hw5.Set;

/** Instantiate the ArraySet to test. */
public class ArraySetTest extends SetTest {
    @Override
    protected Set<String> createUnit() {
        return new ArraySet<>();
    }
}
