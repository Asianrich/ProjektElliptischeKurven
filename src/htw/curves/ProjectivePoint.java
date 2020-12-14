package htw.curves;

import java.math.BigInteger;

public class ProjectivePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;
    BigInteger z = BigInteger.ZERO;

    public ProjectivePoint(BigInteger x, BigInteger y, BigInteger z){
        this.x = x;
        this.y = y;
        this.z = z;

    }

    @Override
    public Point add(Point p, EllipticCurves e) {
        if(p instanceof AffinePoint){
            return p.toProjective().add(p, e);
        }
        if(this.equals(p)){
            BigInteger t = e.getA().multiply(this.getZ().multiply(this.getZ())).add(new BigInteger("3").multiply(this.getX().multiply(this.getX())));
            t = t.mod(e.getP());
            // t = A * z^2 + 3 * x^2
            BigInteger u = this.getY().multiply(this.getZ());
            u = u.mod(e.getP());
            // u = y * z
            BigInteger v = u.multiply(this.getX().multiply(this.getY()));
            v = v.mod(e.getP());
            // v = u * x * y
            BigInteger w = t.multiply(t).subtract(new BigInteger("8").multiply(v));
            w = w.mod(e.getP());
            // w = t^2 - 8 * u
            BigInteger x = BigInteger.TWO.multiply(u).multiply(w);
            x = x.mod(e.getP());
            // x = 2 * u * w
            BigInteger y = t.multiply(new BigInteger("4").multiply(v).subtract(w)).subtract(new BigInteger("8").multiply(this.getY().multiply(this.getY()).multiply(u.multiply(u))));
            y = y.mod(e.getP());
            // y = t * ( 4 * u - w ) - 8 * y^2 * u^2
            BigInteger z = new BigInteger("8").multiply(u.multiply(u).multiply(u));
            z = z.mod(e.getP());
            // z = 8 * u^3
            return new ProjectivePoint(x, y, z);
        }
        if(this.equals(p.negate(e))){
            return new ProjectivePoint(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);
        } else {
            BigInteger u = p.getY().multiply(this.getZ()).subtract(this.getY().multiply(p.getZ()));
            u = u.mod(e.getP());
            // u = y2 * z1 - y1 * z2
            BigInteger v = p.getX().multiply(this.getZ()).subtract(this.getX().multiply(p.getZ()));
            v = v.mod(e.getP());
            // v = x2 * z1 - x1 * z2
            BigInteger w = u.multiply(u).multiply(this.getZ().multiply(p.getZ())).subtract(v.multiply(v.multiply(v))).subtract(BigInteger.TWO.multiply(v).multiply(v).multiply(this.getX().multiply(p.getZ())));
            w = w.mod(e.getP());
            BigInteger x = v.multiply(w);
            x = x.mod(e.getP());
            // x = v * w
            BigInteger y = u.multiply(v.multiply(v.multiply(this.getX().multiply(p.getZ()))).subtract(w)).subtract(v.multiply(v.multiply(v.multiply(this.getY().multiply(p.getZ())))));
            y = y.mod(e.getP());
            // y = u * (v^2 * x1 * z2 - w) - v^3 * y1 * z2
            BigInteger z = v.multiply(v.multiply(v)).multiply(this.getZ().multiply(p.getZ()));
            z = z.mod(e.getP());
            // z = v^3 * z1 * z2
            return new ProjectivePoint(x, y, z);
        }
    }

    @Override
    public Point negate(EllipticCurves e) {
        return this.toAffine().negate(e).toProjective();
    }

    @Override
    public Point kMul(BigInteger k, EllipticCurves e) {
        Point c = this;
        BigInteger a = k;
        Point b = e.getInf();
        while(a.compareTo(BigInteger.ZERO) > 0){
            if(a.mod(BigInteger.TWO) == BigInteger.ZERO){
                a = a.divide(BigInteger.TWO);
                c = c.add(c, e);
            } else {
                a = a.subtract(BigInteger.ONE);
                b = b.add(c, e);
            }
        }
        return b;
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
        return this.z == BigInteger.ZERO && this.x == BigInteger.ZERO;
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
        return new AffinePoint(this.x.divide(this.z), this.y.divide(this.z));
    }

    @Override
    public Point toProjective() {
        return this;
    }
}