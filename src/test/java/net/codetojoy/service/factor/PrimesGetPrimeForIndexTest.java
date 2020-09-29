package net.codetojoy.service.factor;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

@RunWith(value = Parameterized.class)
public class PrimesGetPrimeForIndexTest {
    private int max = 0;
    private int index = 0;
    private int expected = 0;

    public PrimesGetPrimeForIndexTest(int max, int expected, int index) {
        this.max = max;
        this.expected = expected;
        this.index = index;
    }

    @Parameters(name = "#{index}  __  max: {0}  __  index: {2}  __  expected: {1}")
    public static Collection<Object[]> data() {
        // order is unusual, to match the other test
        // { max, expected, index }

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

    // p[0] = 2
    // p[1] = 3
    // etc
    @Test
    public void testGetPrimeForIndex() {
        var primes = new Primes(max);

        // test
        var result = primes.getPrimeForIndex(index);

        assertEquals(expected, result);
    }
}
