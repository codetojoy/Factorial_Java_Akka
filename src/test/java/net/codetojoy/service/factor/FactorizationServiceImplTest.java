
package net.codetojoy.service.factor;

import net.codetojoy.service.Services;

import org.junit.*;
import static org.junit.Assert.*;

public class FactorizationServiceImplTest {
    private FactorizationServiceImpl factorizationService = null;

    @Before
    public void setup() {
        var max = 11000;
        Services.init(max);
        factorizationService = (FactorizationServiceImpl) Services.getFactorizationService();
    }

    @Test
    public void testTranslateFactors_lowBoundary() {
        var n = 2;
        var factors = org.apache.commons.math3.primes.Primes.primeFactors(n);

        // test
        var result = factorizationService.translateFactors(factors);

        assertEquals((int) 1, (int) result[0]);
    }

    @Test
    public void testTranslateFactors_basic() {
        var n = 10800;
        var factors = org.apache.commons.math3.primes.Primes.primeFactors(n);

        // test
        var result = factorizationService.translateFactors(factors);

        assertEquals((int) 4, (int) result[0]);
        assertEquals((int) 3, (int) result[1]);
        assertEquals((int) 2, (int) result[2]);
    }

    @Test
    public void testFactors_no_cache() {
        var n = 10800;

        // test
        var result = factorizationService.factor(n).getFactors();

        assertEquals((int) 4, (int) result[0]);
        assertEquals((int) 3, (int) result[1]);
        assertEquals((int) 2, (int) result[2]);
    }

    @Test
    public void testFactors_with_cache() {
        var n = 10800;
        var dummy = factorizationService.factor(n);

        // test
        var result = factorizationService.factor(n).getFactors();

        assertEquals((int) 4, (int) result[0]);
        assertEquals((int) 3, (int) result[1]);
        assertEquals((int) 2, (int) result[2]);
    }
}
