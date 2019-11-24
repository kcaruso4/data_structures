package hw8.tests;

import hw8.Map;
import hw8.LinearProbeHashMap;

/**
* This tests the LinearProbeHashMap implementation of the Map interface.
*/
public class LinearProbeHashMapTest extends MapTest {

    protected Map<String, String> createUnit() {
        return new LinearProbeHashMap<>();
    }
}
