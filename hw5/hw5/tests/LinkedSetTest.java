package hw5.tests;

import hw5.LinkedSet;
import hw5.Set;

/** Instantiate the LinkedSet to test. */
public class LinkedSetTest extends SetTest {
    @Override
    protected Set<String> createUnit() {
        return new LinkedSet<>();
    }
}
