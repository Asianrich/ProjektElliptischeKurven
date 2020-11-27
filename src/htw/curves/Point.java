package htw.curves;

import java.math.BigInteger;

public interface Point {
    public Point add(Point p, EllipticCurves e);
    public Point negate(EllipticCurves e);
    public Point kMul(BigInteger k, EllipticCurves e);
    public BigInteger getX();
    public BigInteger getY();
    public BigInteger getZ();
    public String toString();
    public boolean isInf();
    public boolean equals(Point p);
    Point toAffine();
    Point toProjective();
}
