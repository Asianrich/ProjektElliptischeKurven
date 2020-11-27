package htw.curves;

import java.math.BigInteger;

public interface FiniteFields {
    BigInteger generatePrime();
    BigInteger generatePrime(BigInteger max);
    BigInteger generatePrime(BigInteger min, BigInteger max);

}
