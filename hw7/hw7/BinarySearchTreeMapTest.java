package hw7.tests;

import hw7.BinarySearchTreeMap;
import hw7.OrderedMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class BinarySearchTreeMapTest extends OrderedMapTest {
    private OrderedMap<String, String> u;

    protected OrderedMap<String, String> createUnit() {
        return new BinarySearchTreeMap<>();
    }

    @Before
    public void setupTesting() {
        u = this.createUnit();
    }

    @Test
    public void orderTest() {
        u.insert("C", "hello");
        u.insert("A", "second val");
        u.insert("B", "val");
        assertEquals("{A: second val, B: val, C: hello}", u.toString());
        //this checks values are ordered in the map by keys
    }

}
