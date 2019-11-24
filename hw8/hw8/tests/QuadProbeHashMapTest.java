package hw8.tests;

import hw8.Map;
import hw8.QuadProbeHashMap;

/**
* This tests the QuadProbeHashMap implementation of the Map interface.
*/
public class QuadProbeHashMapTest extends MapTest {

    protected Map<String, String> createUnit() {
        return new QuadProbeHashMap<>();
    }
}
