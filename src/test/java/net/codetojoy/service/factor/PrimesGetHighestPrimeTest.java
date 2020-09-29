package net.codetojoy.service.factor;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

@RunWith(value = Parameterized.class)
public class PrimesGetHighestPrimeTest {
    private int max = 0;
    private int expected = 0;

    public PrimesGetHighestPrimeTest(int max, int expected) {
        this.max = max;
        this.expected = expected;
    }

    @Parameters(name = "#{index}  __  max: {0}  __  expected: {1}")
    public static Collection<Object[]> data() {
        // { max, expected }

        return Arrays.asList(new Object[][]{
            {2, 2},
            {3, 3},
            {6, 5},
            {10, 7},
            {23, 23},
            {50, 47},
            {100, 97},
        });
    }

    @Test
    public void testGetHighestPrime() {
        var primes = new Primes(max);

        // test
        var result = primes.getHighestPrime();

        assertEquals(expected, result);
    }
}
