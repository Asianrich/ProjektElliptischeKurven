package htw.curves;

import java.math.BigInteger;

public interface Fields {
    BigInteger generatePrime(int len, int trials);
    BigInteger squareRoot(BigInteger number);

}
