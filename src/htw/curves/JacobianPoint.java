package htw.curves;

import java.math.BigInteger;

public class JacobianPoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;
    BigInteger z = BigInteger.ZERO;

    /**
     * Standardkonstruktor
     * @param x
     * @param y
     * @param z
     */
    public JacobianPoint(BigInteger x, BigInteger y, BigInteger z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    /**
     * deckt alle Faelle der Addition ab
     * @param p
     * @param e
     * @return
     */
    @Override
    public Point add(Point p, EllipticCurves e) {
        if (!(p instanceof JacobianPoint)) {
            return this.add(p.toJacobian(e), e);
        }
        FiniteFields ff = new FiniteFields(e.getP());
        if (this.equals(p)) {
            BigInteger v = ff.multiply(ff.multiply(BigInteger.valueOf(4), this.getX()), ff.pow(this.getY(), BigInteger.TWO));
            BigInteger w;
            if(e.getA().equals(BigInteger.valueOf(-3))){
                w = ff.multiply(BigInteger.valueOf(3), ff.subtract(ff.pow(this.getX(), BigInteger.TWO),ff.pow(this.getZ(), BigInteger.valueOf(4))));
            } else {
                w = ff.add(ff.multiply(BigInteger.valueOf(3), ff.pow(this.getX(), BigInteger.TWO)), ff.multiply(e.getA(), ff.pow(this.getZ(), BigInteger.valueOf(4))));
            }
            BigInteger x = ff.add(ff.multiply(BigInteger.valueOf(-2), v), ff.pow(w, BigInteger.TWO));
            BigInteger y = ff.add(ff.multiply(BigInteger.valueOf(-8),ff.pow(this.getY(), BigInteger.valueOf(4))),ff.multiply(ff.subtract(v,x),w));
            BigInteger z = ff.multiply(BigInteger.TWO, ff.multiply(this.getY(), this.getZ()));
            return new JacobianPoint(x, y, z);
        } else {
            if (this.isInf(e) || p.isInf(e)) {
                if (this.isInf(e) && p.isInf(e)) {
                    return e.getInf(); //inf
                } else {
                    if (this.isInf(e))
                        return p;
                    else
                        return this;
                }
            } else {
                if (this.equals(p.negate(e))) {
                    return new JacobianPoint(BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);
                } else {
                    BigInteger r = ff.multiply(this.getX(), ff.pow(p.getZ(), BigInteger.TWO));
                    BigInteger s = ff.multiply(p.getX(), ff.pow(this.getZ(), BigInteger.TWO));
                    BigInteger t = ff.multiply(this.getY(), ff.pow(p.getZ(), BigInteger.valueOf(3)));
                    BigInteger u = ff.multiply(p.getY(), ff.pow(this.getZ(), BigInteger.valueOf(3)));
                    BigInteger v = ff.subtract(s, r);
                    BigInteger w = ff.subtract(u, t);
                    BigInteger x = ff.add(ff.subtract(ff.multiply(BigInteger.valueOf(-1),ff.pow(v, BigInteger.valueOf(3))),ff.multiply(ff.multiply(BigInteger.TWO, r), ff.pow(v, BigInteger.TWO))),ff.pow(w, BigInteger.TWO));
                    BigInteger y = ff.add(ff.multiply(BigInteger.valueOf(-1), ff.multiply(t, ff.pow(v, BigInteger.valueOf(3)))),ff.multiply(ff.subtract(ff.multiply(r, ff.pow(v, BigInteger.TWO)),x),w));
                    BigInteger z = ff.multiply(v, ff.multiply(this.getZ(), p.getZ()));
                    return new JacobianPoint(x, y, z);
                }
            }
        }
    }

    /**
     * einfachster Weg
     * @param e
     * @return
     */
    @Override
    public Point negate(EllipticCurves e) {
        return this.toAffine(e).negate(e).toProjective(e);
    }

    /**
     * Punktverdopplung ist oft am schnellsten
     * @param e
     * @return
     */
    @Override
    public Point doubleP(EllipticCurves e) {
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
        Point b = new JacobianPoint(BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);
        while (a.compareTo(BigInteger.ZERO) > 0) {
            if (a.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                a = a.divide(BigInteger.TWO);
                c = c.add(c, e);
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
        return this.z;
    }

    /**
     * wenn z = 0 dann ist der Punkt nie auf der Kurve -> vergleich wenn toAffine()
     * @return
     */
    @Override
    public boolean isInf() {
        return this.getZ().equals(BigInteger.ZERO) && this.getX().equals(BigInteger.ONE) && this.getY().equals(BigInteger.ONE);
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean isInf(EllipticCurves e) {
        return !e.onCurve(this);
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean equals(Point p) {
        if (this.isInf() && p.isInf())
            return true;
        if (p instanceof JacobianPoint) {
            return this.x.equals(p.getX()) && this.y.equals(p.getY()) && this.z.equals(p.getZ());
        } else {
            return false;
        }
    }

    /**
     * bei unendlich kann man nicht durch 0 teilen, also muss man einen Punkt ausserhalb der Kurve finden
     * @param e
     * @return
     */
    public Point toAffine(EllipticCurves e) {
        if(this.isInf(e)){
            Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO);
            while(e.onCurve(erg)){
                erg = new AffinePoint(erg.getX(), erg.getY().add(BigInteger.ONE));
            }
            return erg;
        }
        FiniteFields ff = new FiniteFields(e.getP());
        return new AffinePoint(ff.divide(this.getX(), ff.pow(this.getZ(), BigInteger.TWO)), ff.divide(this.getY(), ff.pow(this.getZ(), BigInteger.valueOf(3))));
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public Point toJacobian(EllipticCurves e) {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public Point toProjective(EllipticCurves e) {
        return this.toAffine(e).toProjective(e);
    }

    /**
     *
     */
    @Override
    public void print(){
        System.out.println(this.x + "|" + this.y + "|" + this.z);
    }
}