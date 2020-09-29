
package net.codetojoy.service.factor;

import java.util.*;

public class Factors {
    private Integer[] factors;

    public Factors(List<Integer> factors) {
        this.factors = factors.toArray(Integer[]::new);
    }

    public Factors(Integer[] factors) {
        this.factors = factors;
    }

    public Factors(int numFactors) {
        this.factors = new Integer[numFactors];
    }

    public Integer[] getFactors() { return this.factors; }

    public boolean isEqual(Factors b) {
        var result = (this.factors.length == b.factors.length);

        var index = 0;
        while (result && (index < this.factors.length)) {
            result = (this.factors[index] == b.factors[index]);
            index++;
        }

        return result;
    }

    public Factors multiply(Factors b) {
        var result = new Factors(this.factors.length);

        if (this.factors.length == b.factors.length) {
            for (int i = 0; i < this.factors.length; i++) {
                result.factors[i] = this.factors[i] + b.factors[i];
            }
        } else {
            throw new IllegalArgumentException("internal error");
        }

        return result;
    }

    public boolean isProductMatch(Factors a, Factors b) {
        boolean result = true;

        boolean check1 = (this.factors.length == a.factors.length);
        boolean check2 = (this.factors.length == b.factors.length);

        if (check1 && check2) {
            for (int i = 0; i < this.factors.length; i++) {
                if (this.factors[i] != a.factors[i] + b.factors[i]) {
                    result = false;
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("internal error");
        }

        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("[" + factors.length + "] ");
        for (Integer i : factors) {
            builder.append(i + " ");
        }

        return builder.toString();
    }
}
