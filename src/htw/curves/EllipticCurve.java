package htw.curves;

import java.math.BigInteger;
import java.util.LinkedList;

public class EllipticCurve implements EllipticCurves {

    private BigInteger a = BigInteger.ZERO;
    private BigInteger b = BigInteger.ZERO;
    private BigInteger p = BigInteger.ZERO;

    // y^2 = x^3 * Ax + B
    // ->
    // y^2 * z = x^3 + Axz^2 + Bz^3

    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger FOUR = new BigInteger("4");
    static final BigInteger TWENTY_SEVEN = new BigInteger("27");
    static final BigInteger NEG_SIXTEEN = new BigInteger("-16");

    public EllipticCurve(BigInteger a, BigInteger b, BigInteger p){
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
        return new ProjectivePoint(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);
    }

    @Override
    public BigInteger getP() {
        return this.p;
    }

    @Override
    public boolean isNonSingular(BigInteger a, BigInteger b) {
        return !NEG_SIXTEEN.multiply((FOUR.multiply(a.multiply(a).multiply(a)).add((TWENTY_SEVEN.multiply(b.multiply(b)))))).equals(ZERO);
    }

    public boolean onCurve(Point p){
        if(p instanceof AffinePoint){
            return p.getY().multiply(p.getY()).mod(this.p).equals(((p.getX().multiply(p.getX().multiply(p.getX()))).add(this.a.multiply(p.getX())).add(this.b)).mod(this.p));
        }
        return false;
    }
}
