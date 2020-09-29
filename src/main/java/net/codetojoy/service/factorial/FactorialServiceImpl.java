
package net.codetojoy.service.factorial;

import net.codetojoy.service.Services;
import net.codetojoy.service.factor.FactorizationService;
import net.codetojoy.service.factor.Factors;

import java.util.*;

public class FactorialServiceImpl implements FactorialService {
    private Map<Integer,Factors> factorialMap = new HashMap<>();

    @Override
    public boolean isEqual(int a, int b, int c) {
        var aFactors = factor(a);
        var bFactors = factor(b);
        var cFactors = factor(c);

        var result = aFactors.isProductMatch(bFactors, cFactors);

        return result;
    }

    Factors factor(int nFactorial) {
        var result = factorialMap.get(nFactorial);

        if (result == null) {
            result = computeFactorial(nFactorial);
            factorialMap.put(nFactorial, result);
        }

        return result;
    }

    Factors computeFactorial(int n) {
        var factorizationService = Services.getFactorizationService();
        var result = factorizationService.factor(2);

        for (int i = 3; i <= n; i++) {
            Factors thisFactors = factorizationService.factor(i);
            result = result.multiply(thisFactors);
        }

        return result;
    }
}
