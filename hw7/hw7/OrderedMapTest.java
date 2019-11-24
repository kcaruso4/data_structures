/** Keilani Caruso kcaruso4 kcaruso4@jhu.edu
*/
package hw7.tests;

import hw7.Map;
import hw7.OrderedMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.lang.Comparable;

/**
* Testing implementation of the Map interface.
*/
public abstract class OrderedMapTest extends MapTest {
    private OrderedMap<String, String> unit;

    @Override
    protected abstract OrderedMap<String, String> createUnit();

}
