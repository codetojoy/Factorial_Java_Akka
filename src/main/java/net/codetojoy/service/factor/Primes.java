
package net.codetojoy.service.factor;

import java.util.*;

public class Primes {
    private List<Integer> primes = new ArrayList<>();
    private Map<Integer, Integer> indexMap = new HashMap<>();

    public Primes(int max) {
        initialize(max);
    }

    protected void initialize(int max) {
        if (max < 2) {
            throw new IllegalArgumentException("internal error for max value");
        }
        var isDone = (max == 2);

        primes.add(2);
        indexMap.put(2, 0);

        var counter = 3;
        var ordinal = 1;
        while (! isDone) {
            var isPrime = org.apache.commons.math3.primes.Primes.isPrime(counter);

            if (isPrime) {
                // System.out.println(String.format("TRACER isPP c: %d o: %d", counter, ordinal));
                primes.add(counter);
                indexMap.put(counter, ordinal);
                ordinal++;
            }

            if (counter == max) {
                isDone = true;
            }

            counter++;
        }
    }

    protected int getHighestPrime() {
        return primes.get(primes.size() - 1);
    }

    // p[2] = 0
    // p[3] = 1
    // etc
    protected int getIndexForPrime(int prime) {
        return indexMap.get(prime);
    }

    // p[0] = 2
    // p[1] = 3
    // etc
    protected int getPrimeForIndex(int index) {
        return primes.get(index);
    }
}
