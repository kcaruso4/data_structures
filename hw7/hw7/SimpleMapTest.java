package hw7.tests;

import hw7.SimpleMap;
import hw7.Map;

public class SimpleMapTest extends MapTest {

    protected Map<String, String> createUnit() {
        return new SimpleMap<>();
    }
}
