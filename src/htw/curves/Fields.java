package htw.curves;

import java.math.BigInteger;
import java.util.ArrayList;

public interface Fields {

    BigInteger getPrim();
    BigInteger generatePrime(int len, int trials);
    BigInteger squareRoot(BigInteger number) throws Exception;
    BigInteger add(BigInteger sum1, BigInteger sum2);
    BigInteger subtract(BigInteger sum1, BigInteger sum2);
    BigInteger multiply(BigInteger sum1, BigInteger sum2);
    BigInteger divide(BigInteger sum1, BigInteger sum2);
    BigInteger pow(BigInteger sum1, BigInteger sum2);


    ArrayList<BigInteger> add(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2);
    ArrayList<BigInteger> subtract(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2);
    ArrayList<BigInteger> multiply(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2);
    ArrayList<BigInteger> divide(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2);
}
