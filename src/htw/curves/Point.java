package htw.curves;

import java.math.BigInteger;

public interface Point {
    public Point add(Point p, EllipticCurves e);
    public Point mul(Point p, EllipticCurves e);
    public Point sub(Point p, EllipticCurves e);
    public BigInteger getX();
    public BigInteger getY();
    public BigInteger getZ();
    public String toString();
    public boolean isInf();
    public boolean equals(Point p);
}
