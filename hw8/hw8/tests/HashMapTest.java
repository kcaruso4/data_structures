package hw8.tests;

import hw8.Map;
import hw8.HashMap;

/**
* This tests the LinearProbeHashMap implementation of the Map interface.
*/
public class HashMapTest implements MapTest {
    protected Map<String, String> creatUnit() {
        return new HashMap<>();
    }
}
