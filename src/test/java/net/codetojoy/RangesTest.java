package net.codetojoy;

import org.junit.*;
import static org.junit.Assert.*;

public class RangesTest {
    private Ranges ranges = new Ranges();
    private static final int RANGE_SIZE = 10;
    private static final int MAX = 85;

    @Test
    public void testGetRange_lowBoundary() {
        var index = 1;

        // test
        var result = ranges.getRange(index, RANGE_SIZE, MAX);

        assertEquals(2, result.low);
        assertEquals(10, result.high);
    }

    @Test
    public void testGetRange_middleBoundary() {
        var index = 2;

        // test
        var result = ranges.getRange(index, RANGE_SIZE, MAX);

        assertEquals(10, result.low);
        assertEquals(20, result.high);
    }

    @Test
    public void testGetRange_highBoundary() {
        var index = 9;

        // test
        var result = ranges.getRange(index, RANGE_SIZE, MAX);

        assertEquals(80, result.low);
        assertEquals(85, result.high);
    }
}
