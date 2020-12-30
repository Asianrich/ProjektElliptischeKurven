package htw.curves;

import java.math.BigInteger;

public class AffinePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;

    public AffinePoint(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Point add(Point p, EllipticCurves e) {
        if(p instanceof AffinePoint){

            // maybe all in Projective?
            //return this.toProjective().add(p.toProjective(), e);

            if(this.equals(p)){
                if(this.getY().equals(BigInteger.ZERO)){
                    return this;
                    // inf is not there... I have issues with that. Can someone give me inf!?!??!
                }
                BigInteger m = BigInteger.valueOf(3).multiply(this.getX().modPow(BigInteger.TWO, e.getP())).add(e.getA()).divide(BigInteger.TWO.multiply(this.getY()));
                m = m.mod(e.getP());
                //m = 3x^2+A / 2y
                BigInteger x =  m.modPow(BigInteger.TWO, e.getP()).subtract(BigInteger.TWO.multiply(this.getX()));
                x = x.mod(e.getP());
                //m^2-2x
                BigInteger y = m.multiply(this.getX().subtract(x)).subtract(this.getY());
                y = y.mod(e.getP());
                //y = m(this x - x)+y
                return new AffinePoint(x,y);
            } else {
                if(this.isInf(e) || p.isInf()){
                    if(this.isInf(e) && p.isInf()){
                        return new AffinePoint(BigInteger.valueOf(-1), BigInteger.valueOf(-1)); //inf
                    } else {
                        if(this.isInf(e))
                            return p;
                        else
                            return this;
                    }
                } else {
                    if(this.getX().equals(p.getX())){
                        return new AffinePoint(BigInteger.ZERO, BigInteger.ONE); //inf
                    }
                    BigInteger mo = (p.getY().subtract(this.getY())).mod(e.getP());
                    BigInteger mu = p.getX().subtract(this.getX());
                    if(mu.compareTo(BigInteger.ZERO) == -1){
                        mu = mu.multiply(BigInteger.valueOf(-1));
                        mo = mo.multiply(BigInteger.valueOf(-1));
                    }
                    BigInteger m = BigInteger.ZERO;
                    if(mo.mod(mu).equals(BigInteger.ZERO)){
                        m = (mo.divide(mu)).mod(e.getP());
                    } else {
                        for(BigInteger i = BigInteger.ZERO; i.compareTo(e.getP()) <= 0; i = i.add(BigInteger.ONE)){
                            BigInteger tmp = (mu.multiply(i)).mod(e.getP());
                            if(tmp.equals(BigInteger.ONE)){
                                m = (i.multiply(mo)).mod(e.getP());
                                break;
                            }
                        }
                    }
                    //BigInteger x = m.multiply(m).subtract(this.getX()).subtract(p.getX());
                    BigInteger x = m.modPow(BigInteger.TWO, e.getP()).subtract(this.getX()).subtract(p.getX());
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
            if(a.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
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

    public boolean isInf(EllipticCurves e) {
        return !e.onCurve(this);
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
            return this.x.equals(p.getX()) && this.y.equals(p.getY());
        } else {
            return false;
        }
    }

    @Override
    public Point toAffine() {
        return this;
    }

    public Point toProjective(){
        ProjectivePoint tmp = new ProjectivePoint(this.getX(), this.getY(), BigInteger.ONE);
        return tmp;
    }
}
