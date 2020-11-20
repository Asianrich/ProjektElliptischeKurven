package htw.curves;

import java.math.BigInteger;

public class ProjectivePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;
    BigInteger z = BigInteger.ZERO;

    ProjectivePoint(BigInteger x, BigInteger y, BigInteger z){
        this.x = x;
        this.y = y;
        this.z = z;

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
    public Point kMul(BigInteger k, EllipticCurves e) {
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
        return this.z;
    }

    @Override
    public boolean isInf() {
        return false;
    }

    @Override
    public boolean equals(Point p) {
        if(this.isInf() && p.isInf())
            return true;
        if(p instanceof ProjectivePoint){
            return this.x == p.getX() && this.y == p.getY() && this.z == p.getZ();
        } else {
            return false;
        }
    }

    public Point toAffine(){
        return null;
    }
}