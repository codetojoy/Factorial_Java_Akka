package net.codetojoy;

import net.codetojoy.service.Services;
import net.codetojoy.service.factorial.*;

public class ServiceRunner {
    public static void main(String[] args) {
        try {
            var rangeSize = parseIntArg(args, 0);
            var max = parseIntArg(args, 1);
            Services.init(max);
            var factorialService = Services.getFactorialService();
            var range = new Range(2, max);

            for (int a = range.low; a <= range.high; a++) {
                for (int b = range.low; b <= range.high; b++) {
                    for (int c = range.low; c <= range.high; c++) {
                        boolean isMatch = factorialService.isEqual(a, b, c);
                        if (isMatch) {
                            System.out.println(String.format("TRACER match %d! = %d x %d", a, b, c));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("ERROR caught exception ex: " + ex.getMessage());
        }
    }

    static int parseIntArg(String[] args, int index) {
        int result = -1;
        boolean ok = false;

        try {
            result = Integer.parseInt(args[index]);
            ok = true;
        } catch (Exception ex) {
        }

        if (! ok) {
            System.err.println("error on arguments");
            System.exit(-1);
        }

        return result;
    }
}
