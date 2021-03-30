package htw.curves;

import java.math.BigInteger;

public class AffinePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;

    /**
     * Standardkonstruktor
     * @param x
     * @param y
     */
    public AffinePoint(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
    }

    /**
     * Deckt alle Faelle der Addition ab
     * @param p
     * @param e
     * @return
     */
    @Override
    public Point add(Point p, EllipticCurves e) {
        FiniteFields ff = new FiniteFields(e.getP());
        if(p instanceof AffinePoint){
            // maybe all in Projective?
            //return this.toProjective().add(p.toProjective(), e);

            if(this.equals(p)){
                if(this.getY().equals(BigInteger.ZERO)){
                    return this;
                    // inf is not there... I have issues with that. Can someone give me inf!?!??!
                }
                BigInteger m = ff.divide(ff.add(ff.multiply(BigInteger.valueOf(3), ff.pow(this.getX(), BigInteger.TWO)), e.getA()), ff.multiply(BigInteger.TWO, this.getY()));
                //BigInteger m = BigInteger.valueOf(3).multiply(this.getX().modPow(BigInteger.TWO, e.getP())).add(e.getA()).divide(BigInteger.TWO.multiply(this.getY()));
                //m = m.mod(e.getP());
                //m = 3x^2+A / 2y
                BigInteger x = ff.subtract(ff.pow(m, BigInteger.TWO), ff.multiply(BigInteger.TWO, this.getX()));
                //BigInteger x =  m.modPow(BigInteger.TWO, e.getP()).subtract(BigInteger.TWO.multiply(this.getX()));
                //x = x.mod(e.getP());
                //m^2-2x
                BigInteger y = ff.subtract(ff.multiply(m, ff.subtract(this.getX(), x)), this.getY());
                //BigInteger y = m.multiply(this.getX().subtract(x)).subtract(this.getY());
                //y = y.mod(e.getP());
                //y = m(this x - x)+y
                return new AffinePoint(x,y);
            } else {
                if(this.isInf(e) || p.isInf(e)){
                    if(this.isInf(e) && p.isInf(e)){
                        Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO); //inf
                        while(e.onCurve(erg)){
                            erg = new AffinePoint(erg.getX(), ff.add(erg.getY(), BigInteger.ONE));
                        }
                        return erg;
                    } else {
                        if(this.isInf(e))
                            return p;
                        else
                            return this;
                    }
                } else {
                    if(this.getX().equals(p.getX())){
                        Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO); //inf
                        while(e.onCurve(erg)){
                            erg = new AffinePoint(erg.getX(), ff.add(erg.getY(), BigInteger.ONE));
                        }
                        return erg;
                    }
                    BigInteger mo = ff.subtract(p.getY(), this.getY());
                    //BigInteger mo = (p.getY().subtract(this.getY())).mod(e.getP());
                    BigInteger mu = ff.subtract(p.getX(), this.getX());
                    //BigInteger mu = p.getX().subtract(this.getX());
                    if(mu.compareTo(BigInteger.ZERO) == -1){
                        mu = ff.multiply(mu, BigInteger.valueOf(-1));
                        //mu = mu.multiply(BigInteger.valueOf(-1));
                        mo = ff.multiply(mo, BigInteger.valueOf(-1));
                        //mo = mo.multiply(BigInteger.valueOf(-1));
                    }
                    BigInteger m = BigInteger.ZERO;
                    if(mo.mod(mu).equals(BigInteger.ZERO)){
                        //m = (mo.divide(mu)).mod(e.getP());
                        m = ff.divide(mo, mu);
                    } else {
                        for(BigInteger i = BigInteger.ZERO; i.compareTo(e.getP()) <= 0; i = i.add(BigInteger.ONE)){
                            BigInteger tmp = ff.multiply(mu, i); //(mu.multiply(i)).mod(e.getP());
                            if(tmp.equals(BigInteger.ONE)){
                                m = ff.multiply(i, mo);
                                //m = (i.multiply(mo)).mod(e.getP());
                                break;
                            }
                        }
                    }
                    //BigInteger x = m.multiply(m).subtract(this.getX()).subtract(p.getX());
                    BigInteger x = ff.subtract(ff.subtract(ff.pow(m, BigInteger.TWO), this.getX()), p.getX());
                    //BigInteger x = m.modPow(BigInteger.TWO, e.getP()).subtract(this.getX()).subtract(p.getX());
                    //x = x.mod(e.getP());
                    BigInteger y = ff.subtract(ff.multiply(m, ff.subtract(this.getX(), x)), this.getY());
                    //BigInteger y = m.multiply(this.getX().subtract(x)).subtract(this.getY());
                    //y = y.mod(e.getP());
                    return new AffinePoint(x,y);
                }
            }
        } else
            return this.add(p.toAffine(e),e);
    }

    /**
     * negation des Punktes -> negation der y-Koordinate
     * @param e
     * @return
     */
    @Override
    public Point negate(EllipticCurves e) {
        return new AffinePoint(this.x, new BigInteger("-1").multiply(this.y).add(e.getP()));
    }

    /**
     * Punktverdopplung ist oft am schnellsten
     * @param e
     * @return
     */
    @Override
    public Point doubleP(EllipticCurves e){
        return this.add(this, e);
    }

    /**
     * intelligentes k-faches multiplizieren durch verdoppeln
     * @param k
     * @param e
     * @return
     */
    @Override
    public Point kMul(BigInteger k, EllipticCurves e) {
        Point c = this;
        BigInteger a = k;
        Point b = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO);//e.getInf();
        while(e.onCurve(b)){
            b = new AffinePoint(BigInteger.ZERO, b.getY().add(BigInteger.ONE));
        }
        while(a.compareTo(BigInteger.ZERO) > 0){
            if(a.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
                a = a.divide(BigInteger.TWO);
                c = c.doubleP(e);
            } else {
                a = a.subtract(BigInteger.ONE);
                b = b.add(c, e);
            }
        }
        return b;
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getX() {
        return this.x;
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getY() {
        return this.y;
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getZ() {
        return BigInteger.ONE;
    }

    /**
     * alles was nicht auf der Kurve liegt
     * @param e
     * @return
     */
    public boolean isInf(EllipticCurves e) {
        return !e.onCurve(this);
    }

    /**
     * ohne Kruve gibt es kein Unendlich
     * @return
     */
    @Override
    public boolean isInf() {
        return false;
        //there is no inf in this point type... https://crypto.stackexchange.com/questions/6464/how-to-represent-point-at-infinity-in-affine-coordinate
    }

    /**
     *
     * @param p
     * @return
     */
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

    /**
     *
     * @param e
     * @return
     */
    @Override
    public Point toAffine(EllipticCurves e) {
        return this;
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public Point toJacobian(EllipticCurves e) {
        return new JacobianPoint(this.getX(), this.getY(), BigInteger.ONE);
    }

    /**
     * z = 1 und man hat projektive Punkte
     * @return
     */
    public Point toProjective(EllipticCurves e){
        return new ProjectivePoint(this.getX(), this.getY(), BigInteger.ONE);
    }

    /**
     *
     */
    @Override
    public void print(){
        System.out.println(this.x + "|" + this.y);
    }
}
