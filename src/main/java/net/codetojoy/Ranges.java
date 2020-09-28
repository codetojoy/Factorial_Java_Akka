package net.codetojoy;

public class Ranges {
    Range getRange(int index, int rangeSize, int max) {
        int low = rangeSize * (index - 1);

        if (low < 2) {
            low = 2;
        }

        int high = rangeSize * index;

        if (high > max) {
            high = max;
        }

        var result = new Range(low, high);
        return result;
    }
}
