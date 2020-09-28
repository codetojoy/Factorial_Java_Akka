package net.codetojoy.util;

import org.junit.*;
import static org.junit.Assert.*;

public class StringsTest {
    @Test
    public void testClean_none() {
        String s = "mozart";

        // test
        String result = Strings.clean(s);

        assertEquals(s, result);
    }

    @Test
    public void testClean_begin() {
        String s = "\"mozart";

        // test
        String result = Strings.clean(s);

        assertEquals("mozart", result);
    }

    @Test
    public void testClean_end() {
        String s = "mozart\"";

        // test
        String result = Strings.clean(s);

        assertEquals("mozart", result);
    }

    @Test
    public void testClean_both() {
        String s = "\"mozart\"";

        // test
        String result = Strings.clean(s);

        assertEquals("mozart", result);
    }
}
