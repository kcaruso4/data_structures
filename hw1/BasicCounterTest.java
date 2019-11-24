 package hw1;

 import hw1.BasicCounter;
 import hw1.ResetableCounter;

/*
 * Testing the BasicCounter implementation.
 *
 * This is a first example of using JUnit4 for testing. The JUnit framework
 * expects us to @annotate our testing code so it can run it for us. Below
 * we use only a few @annotations:
 *
 * @Test marks test cases. Each test case should focus on a single property
 * we would like to verify, so ideally there is only one assertion in each of
 * these methods. Writing many small test cases is better than writing a few
 * big ones: the more fine-grained our tests, the easier it is to focus on
 * exactly what the problem is should a test case fail. We try to give test
 * cases long and descriptive names for the same reason.
 *
 * @Before marks per-test setup code. These methods are run once before each
 * @Test method and ensure that we always start testing in the same state.
 * This makes test cases more independent: the order in which they run does
 * not matter.
 *
 * @BeforeClass marks per-class setup code. These methods are run once before
 * all @Test methods. They are reserved for expensive setup code and we will
 * not have much use for them. Since the same instances are retained for all
 * test cases, the order in which we run tests matters again. However, if
 * using @Before would take too long, we have to pay that price.
 *
 * We do not usually require that JUnit test cases be checkstyle-compliant.
 * The main reason for this is that we want you to focus on writing the test
 * cases, not on writing Javadoc for them. However, we will still take a look
 * at your tests. If the code is simply too chaotic, then we will still take
 * points off. (Your best bet would be to still run checkstyle on test cases
 * but to ignore warnings regarding Javadoc.)
 */


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class BasicCounterTest {

    private ResetableCounter unit;

    @Before
    public void createUnit() {
        unit = new BasicCounter();
    }

    @Test
    public void initialValueZero() {
        assertEquals(0, unit.value());
    }

    @Test
    public void upWorks() {
        unit.up();
        assertEquals(1, unit.value());
        unit.up();
        assertEquals(2, unit.value());
    }

    @Test
    public void downWorks() {
        unit.down();
        assertEquals(-1, unit.value());
        unit.down();
        assertEquals(-2, unit.value());
    }

    @Test
    public void upAndDown() {
        for (int i = 0; i < 10; i++) {
            unit.up();
        }
        assertTrue(10 == unit.value());
        for (int i = 0; i < 5; i++) {
            unit.down();
        }
        assertTrue(5 == unit.value());
    }

    @Test
    public void canReset() {
        for (int i = 0; i < 15; i++) {
            unit.up();
        }
        assertNotEquals(0, unit.value());
        unit.reset();
        assertEquals(0, unit.value());
    }
    
    @Test(expected=RuntimeException.class)
    public void somethingBad() {
        // this code should throw RuntimeException
    }
}
