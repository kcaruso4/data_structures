package hw8.tests;

import hw8.Map;
import hw8.LinkedChainingHashMap;

/**
* This tests the LinkedChainingHashMap implementation of the Map interface.
*/
public class LinkedChainingHashMapTest extends MapTest {

    protected Map<String, String> createUnit() {
        return new LinkedChainingHashMap<>();
    }
}
