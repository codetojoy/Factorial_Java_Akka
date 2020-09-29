
package net.codetojoy.service.factorial;

import net.codetojoy.service.Services;

import org.junit.*;
import static org.junit.Assert.*;

public class FactorialServiceImplTest {
    private FactorialServiceImpl factorialService = null;

    @Before
    public void setup() {
        var max = 20;
        Services.init(max);
        factorialService = (FactorialServiceImpl) Services.getFactorialService();
    }

    @Test
    public void testIsEqual_basic() {
        var a = 10;
        var b = 6;
        var c = 7;

        // test
        var result = factorialService.isEqual(a, b, c);

        assertTrue(result);
    }

    @Test
    public void testFactors_no_cache() {
        var n = 6;

        // test
        var result = factorialService.factor(n).getFactors();

        assertEquals((int) 4, (int) result[0]);
        assertEquals((int) 2, (int) result[1]);
        assertEquals((int) 1, (int) result[2]);
    }

    @Test
    public void testFactors_with_cache() {
        var n = 6;
        var dummy = factorialService.factor(n);

        // test
        var result = factorialService.factor(n).getFactors();

        assertEquals((int) 4, (int) result[0]);
        assertEquals((int) 2, (int) result[1]);
        assertEquals((int) 1, (int) result[2]);
    }
}
