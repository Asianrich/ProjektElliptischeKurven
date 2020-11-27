package htw.curves;

import java.math.BigInteger;

public interface EllipticCurves {
    public BigInteger getA();
    public BigInteger getB();
    public Point getInf();
    public BigInteger getP();
    public boolean isNonSingular(BigInteger a, BigInteger b);
}
