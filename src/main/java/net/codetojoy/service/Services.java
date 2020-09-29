
package net.codetojoy.service;

import net.codetojoy.service.factor.*;
import net.codetojoy.service.factorial.*;

// TODO: use Spring
public class Services {
    private static FactorizationService factorizationService;
    private static FactorialService factorialService;

    public static void init(int max) {
        Primes primes = new Primes(max);
        factorizationService = new FactorizationServiceImpl(primes);
        factorialService = new FactorialServiceImpl();
    }   

    public static FactorizationService getFactorizationService() {
        return factorizationService;         
    }

    public static FactorialService getFactorialService() {
        return factorialService;         
    }
}
