
package net.codetojoy.service.factor;

import java.util.*;

public class FactorizationServiceImpl implements FactorizationService {
    private Primes primes;
    private Map<Integer,Factors> factorMap = new HashMap<>();

    public FactorizationServiceImpl(Primes primes) {
        this.primes = primes;
    }

    Integer[] translateFactors(List<Integer> factorsList) {
        int highestPrime = primes.getHighestPrime();
        Integer[] factors = new Integer[highestPrime];

        for (int i = 0; i < factors.length; i++) {
            factors[i] = 0;
        }

        for (Integer primeFactor : factorsList) {
            int count = Collections.frequency(factorsList, primeFactor);
            int index = primes.getIndexForPrime(primeFactor);
            factors[index] = count;
        }

        return factors;
    }

    @Override
    public Factors factor(int n) {
        Factors result = factorMap.get(n);

        if (result == null) {
            var apacheFactors = org.apache.commons.math3.primes.Primes.primeFactors(n);
            var factors = translateFactors(apacheFactors);
            result = new Factors(factors);
            factorMap.put(n, result);
        }

        return result;
    }
}
