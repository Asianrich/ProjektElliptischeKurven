package htw.curves;

import java.math.BigInteger;

public class AffinePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;

    AffinePoint(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Point add(Point p, EllipticCurves e) {

        return null;
    }

    @Override
    public Point mul(Point p, EllipticCurves e) {
        return null;
    }

    @Override
    public Point sub(Point p, EllipticCurves e) {
        return null;
    }

    @Override
    public BigInteger getX() {
        return this.x;
    }

    @Override
    public BigInteger getY() {
        return this.y;
    }

    @Override
    public BigInteger getZ() {
        return BigInteger.ONE;
    }

    @Override
    public boolean isInf() {
        return false;
    }

    @Override
    public boolean equals(Point p) {
        if(this.isInf() && p.isInf())
            return true;
        if(p instanceof AffinePoint){
            return this.x == p.getX() && this.y == p.getY();
        } else {
            return false;
        }
    }

    public Point toProjective(){
        return null;
    }
}
