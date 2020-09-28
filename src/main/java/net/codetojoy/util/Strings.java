package net.codetojoy.util;

public class Strings {
    public static String clean(String s) {
        String result = s;

        if (result.startsWith(Constants.DOUBLE_QUOTE)) {
            result = result.substring(1);
        }

        if (result.endsWith(Constants.DOUBLE_QUOTE)) {
            int lastIndex = result.length() - 1;
            result = result.substring(0, lastIndex);
        }

        return result;
    }
}
