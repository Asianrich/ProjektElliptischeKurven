package htw.curves;

import java.math.BigInteger;
import java.util.Random;

public class FiniteFields implements Fields {

    @Override
    public BigInteger generatePrime() {
        Random rnd = new Random();
        BigInteger rndPrime = BigInteger.probablePrime(8192, rnd);

        return rndPrime;
    }




}
