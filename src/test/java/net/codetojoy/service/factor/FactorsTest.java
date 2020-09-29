
package net.codetojoy.service.factor;

import org.junit.*;
import static org.junit.Assert.*;

public class FactorsTest {
    @Test
    public void testIsProductMatch_basic() {
        var a = new Factors(new Integer[]{2,0,2,0});
        var b = new Factors(new Integer[]{1,1,2,0});
        var c = new Factors(new Integer[]{3,1,4,0});

        // test
        var result = c.isProductMatch(a, b);

        assertTrue(result);
    }

    @Test
    public void testMultiply_basic() {
        var a = new Factors(new Integer[]{0,1,1,1});
        var b = new Factors(new Integer[]{1,1,1,0});

        // test
        var result = a.multiply(b).getFactors();

        assertEquals((int) 1, (int) result[0]);
        assertEquals((int) 2, (int) result[1]);
        assertEquals((int) 2, (int) result[2]);
        assertEquals((int) 1, (int) result[3]);
    }

    @Test
    public void testIsEqual_basic_yes() {
        var a = new Factors(new Integer[]{1,1,1});
        var b = new Factors(new Integer[]{1,1,1});

        // test
        var result = a.isEqual(b);

        assertTrue(result);
    }

    @Test
    public void testIsEqual_basic_no() {
        var a = new Factors(new Integer[]{1,0,1});
        var b = new Factors(new Integer[]{1,1,1});

        // test
        var result = a.isEqual(b);

        assertFalse(result);
    }
}
