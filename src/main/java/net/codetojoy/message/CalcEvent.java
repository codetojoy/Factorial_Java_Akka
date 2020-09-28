package net.codetojoy.message;

public final class CalcEvent {
    public final int a;
    public final int b;
    public final int c;
    public final boolean isMatch;

    public CalcEvent(int a, int b, int c, boolean isMatch) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.isMatch = isMatch;
    }

    public String toString() {
        final String format = "a: %d b: %d c: %d isMatch: %b";
        return String.format(format, a, b, c, isMatch);
    }
}
