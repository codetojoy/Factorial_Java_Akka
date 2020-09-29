package net.codetojoy.service.factor;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

@RunWith(value = Parameterized.class)
public class PrimesGetIndexForPrimeTest {
    private int max = 0;
    private int n = 0;
    private int expected = 0;

    public PrimesGetIndexForPrimeTest(int max, int n, int expected) {
        this.max = max;
        this.n = n;
        this.expected = expected;
    }

    @Parameters(name = "#{index}  __  max: {0}  __  n: {1}  __  expected: {2}")
    public static Collection<Object[]> data() {
        // { max, n, expected }

        return Arrays.asList(new Object[][]{
            {20, 2, 0},
            {20, 3, 1},
            {20, 5, 2},
            {20, 7, 3},
            {20, 11, 4},
            {20, 13, 5},
            {20, 17, 6},
            {20, 19, 7},
            {100, 23, 8},
            {100, 29, 9},
        });
    }

    // p[2] = 0
    // p[3] = 1
    // etc
    @Test
    public void testGetIndexForPrime() {
        var primes = new Primes(max);

        // test
        var result = primes.getIndexForPrime(n);

        assertEquals(expected, result);
    }
}
