package hw8.tests;

import hw8.Map;
import hw8.ChainingHashMap;

/**
* This tests the ChainingHashMap implementation of the Map interface.
*/
public class ChainingHashMapTest extends MapTest {

    protected Map<String, String> createUnit() {
        return new ChainingHashMap<>();
    }
}
