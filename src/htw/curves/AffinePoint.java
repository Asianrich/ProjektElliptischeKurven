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
        if(p instanceof AffinePoint){

            // maybe all in Projective?
            //return this.toProjective().add(p.toProjective(), e);

            if(this.equals(p)){
                BigInteger m = BigInteger.valueOf(3).multiply(this.getX().multiply(this.getX())).add(e.getA()).divide(BigInteger.TWO.multiply(this.getY()));
                m = m.mod(e.getP());
                //m = 3x^2+A / 2y
                BigInteger x =  m.multiply(m).subtract(BigInteger.TWO.multiply(this.getX()));
                x = x.mod(e.getP());
                //m^2-2x
                BigInteger y = m.multiply(this.getX().subtract(x)).subtract(this.getY());
                y = y.mod(e.getP());
                //y = m(this x - x)+y
                return new AffinePoint(x,y);
            } else {
                if(this.isInf() || p.isInf()){
                    if(this.isInf() && p.isInf()){
                        return new AffinePoint(BigInteger.ZERO, BigInteger.ONE); //inf
                    } else {
                        if(this.isInf())
                            return p;
                        else
                            return this;
                    }
                } else {
                    if(this.getX() == p.getX()){
                        return new AffinePoint(BigInteger.ZERO, BigInteger.ONE); //inf
                    }
                    BigInteger m = (p.getY().subtract(this.getY())).divide(p.getX().subtract(this.getX()));
                    m = m.mod(e.getP());
                    BigInteger x = m.multiply(m).subtract(this.getX()).subtract(p.getX());
                    x = x.mod(e.getP());
                    BigInteger y = m.multiply(this.getX().subtract(x)).subtract(this.getY());
                    y = y.mod(e.getP());
                    return new AffinePoint(x,y);
                }
            }
        } else
            return this.toProjective().add(p,e);
    }

    @Override
    public Point negate(EllipticCurves e) {
        return new AffinePoint(this.x, new BigInteger("-1").multiply(this.y).add(e.getP()));
    }

    @Override
    public Point kMul(BigInteger k, EllipticCurves e) {

        // projective Points?
        // return this.toProjective().kMul(k, e);

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
        return BigInteger.ONE;
    }

    @Override
    public boolean isInf() {
        return false;
        //there is no inf in this point type... https://crypto.stackexchange.com/questions/6464/how-to-represent-point-at-infinity-in-affine-coordinate
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

    @Override
    public Point toAffine() {
        return this;
    }

    public Point toProjective(){
        return new ProjectivePoint(this.getX(), this.getY(), BigInteger.ONE);
    }
}
