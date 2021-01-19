package htw.curves;

import java.math.BigInteger;

public interface Fields {
    BigInteger generatePrime(int len, int trials);
    BigInteger squareRoot(BigInteger number) throws Exception;
    BigInteger add(BigInteger sum1, BigInteger sum2);
    BigInteger subtract(BigInteger sum1, BigInteger sum2);
    BigInteger multiply(BigInteger sum1, BigInteger sum2);
    BigInteger divide(BigInteger sum1, BigInteger sum2);
    BigInteger pow(BigInteger sum1, BigInteger sum2);
}
