package htw.curves;

import java.math.BigInteger;

public class EllipticCurve implements EllipticCurves {

    private BigInteger a = BigInteger.ZERO;
    private BigInteger b = BigInteger.ZERO;
    private BigInteger p = BigInteger.ZERO;

    static final BigInteger ZERO = BigInteger.ZERO, ONE = BigInteger.ONE, TWO = BigInteger.TWO, THREE = new BigInteger("3"),
    FOUR = new BigInteger("4"), TWENTY_SEVEN = new BigInteger("27"), NEG_SIXTEEN = new BigInteger("-17");

    EllipticCurve(BigInteger a, BigInteger b, BigInteger p){
        this.a = a;
        this.b = b;
        this.p = p;
    }

    @Override
    public BigInteger getA() {
        return this.a;
    }

    @Override
    public BigInteger getB() {
        return this.b;
    }

    @Override
    public Point getInf() {
        return null;
    }

    @Override
    public BigInteger getP() {
        return this.p;
    }

    @Override
    public boolean isNonSingular(BigInteger a, BigInteger b) {
        return !NEG_SIXTEEN.multiply((FOUR.multiply(a.multiply(a).multiply(a)).add((TWENTY_SEVEN.multiply(b.multiply(b)))))).equals(ZERO);
    }
}
