package htw.curves;

import java.math.BigInteger;

public class ProjectivePoint implements Point {

    BigInteger x = BigInteger.ZERO;
    BigInteger y = BigInteger.ZERO;
    BigInteger z = BigInteger.ZERO;

    /**
     * Standardkonstruktor
     * @param x
     * @param y
     * @param z
     */
    public ProjectivePoint(BigInteger x, BigInteger y, BigInteger z) {
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
        if (!(p instanceof ProjectivePoint)) {
            return this.add(p.toProjective(e), e);
        }
        FiniteFields ff = new FiniteFields(e.getP());
        if (this.equals(p)) {
            //BigInteger t = e.getA().multiply(this.getZ().multiply(this.getZ())).add(new BigInteger("3").multiply(this.getX().multiply(this.getX())));
            BigInteger t = ff.add(ff.multiply(e.getA(), ff.pow(this.getZ(), BigInteger.TWO)), ff.multiply(BigInteger.valueOf(3), ff.pow(this.getX(), BigInteger.TWO)));
            //BigInteger t = e.getA().multiply(this.getZ().modPow(BigInteger.TWO, e.getP())).add(new BigInteger("3").multiply(this.getX().modPow(BigInteger.TWO, e.getP())));
            //t = t.mod(e.getP());
            // t = A * z^2 + 3 * x^2
            BigInteger u = ff.multiply(this.getY(), this.getZ());
            //BigInteger u = this.getY().multiply(this.getZ());
            //u = u.mod(e.getP());
            // u = y * z
            BigInteger v = ff.multiply(u, ff.multiply(this.getX(), this.getY()));
            //BigInteger v = u.multiply(this.getX().multiply(this.getY()));
            //v = v.mod(e.getP());
            // v = u * x * y
            //BigInteger w = t.multiply(t).subtract(new BigInteger("8").multiply(v));
            BigInteger w = ff.subtract(ff.pow(t, BigInteger.TWO), ff.multiply(BigInteger.valueOf(8), v));
            //BigInteger w = t.modPow(BigInteger.TWO, e.getP()).subtract(new BigInteger("8").multiply(v));
            //w = w.mod(e.getP());
            // w = t^2 - 8 * u
            BigInteger x = ff.multiply(BigInteger.TWO, ff.multiply(u, w));
            //BigInteger x = BigInteger.TWO.multiply(u).multiply(w);
            //x = x.mod(e.getP());
            // x = 2 * u * w
            //BigInteger y = t.multiply(new BigInteger("4").multiply(v).subtract(w)).subtract(new BigInteger("8").multiply(this.getY().multiply(this.getY()).multiply(u.multiply(u))));
            BigInteger y = ff.subtract(ff.multiply(t, ff.subtract(ff.multiply(BigInteger.valueOf(4), v), w)), ff.multiply(BigInteger.valueOf(8), ff.multiply(ff.pow(this.getY(), BigInteger.TWO), ff.pow(u, BigInteger.TWO))));
            //BigInteger y = t.multiply(new BigInteger("4").multiply(v).subtract(w)).subtract(new BigInteger("8").multiply(this.getY().modPow(BigInteger.TWO, e.getP()).multiply(u.modPow(BigInteger.TWO, e.getP()))));
            //y = y.mod(e.getP());
            // y = t * ( 4 * u - w ) - 8 * y^2 * u^2
            //BigInteger z = new BigInteger("8").multiply(u.multiply(u).multiply(u));
            BigInteger z = ff.multiply(BigInteger.valueOf(8), ff.pow(u, BigInteger.valueOf(3)));
            //BigInteger z = new BigInteger("8").multiply(u.modPow(BigInteger.valueOf(3), e.getP()));
            //z = z.mod(e.getP());
            // z = 8 * u^3
            return new ProjectivePoint(x, y, z);
        } else {
            if (this.isInf(e) || p.isInf(e)) {
                if (this.isInf(e) && p.isInf(e)) {
                    return e.getInf(); //inf
                } else {
                    if (this.isInf())
                        return p;
                    else
                        return this;
                }
            } else {
                if (this.equals(p.negate(e))) {
                    return new ProjectivePoint(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);
                } else {
                    BigInteger u = ff.subtract(ff.multiply(p.getY(), this.getZ()), ff.multiply(this.getY(), p.getZ()));
                    //BigInteger u = p.getY().multiply(this.getZ()).subtract(this.getY().multiply(p.getZ()));
                    //u = u.mod(e.getP());
                    // u = y2 * z1 - y1 * z2
                    BigInteger v = ff.subtract(ff.multiply(p.getX(), this.getZ()), ff.multiply(this.getX(), p.getZ()));
                    //BigInteger v = p.getX().multiply(this.getZ()).subtract(this.getX().multiply(p.getZ()));
                    //v = v.mod(e.getP());
                    // v = x2 * z1 - x1 * z2
                    BigInteger w = ff.subtract(ff.subtract(ff.multiply(ff.pow(u, BigInteger.TWO), ff.multiply(this.getZ(), p.getZ())), ff.pow(v, BigInteger.valueOf(3))), ff.multiply(ff.multiply(BigInteger.TWO, ff.pow(v, BigInteger.TWO)), ff.multiply(this.getX(), p.getZ())));
                    //BigInteger w = u.multiply(u).multiply(this.getZ().multiply(p.getZ())).subtract(v.multiply(v.multiply(v))).subtract(BigInteger.TWO.multiply(v).multiply(v).multiply(this.getX().multiply(p.getZ())));
                    //w = w.mod(e.getP());
                    BigInteger x = ff.multiply(v, w);
                    //BigInteger x = v.multiply(w);
                    //x = x.mod(e.getP());
                    // x = v * w
                    //BigInteger y = u.multiply(v.multiply(v.multiply(this.getX().multiply(p.getZ()))).subtract(w)).subtract(v.multiply(v.multiply(v.multiply(this.getY().multiply(p.getZ())))));
                    BigInteger y = ff.subtract(ff.multiply(u, ff.subtract(ff.multiply(ff.pow(v, BigInteger.TWO), ff.multiply(this.getX(), p.getZ())), w)), ff.multiply(ff.pow(v, BigInteger.valueOf(3)), ff.multiply(this.getY(), p.getZ())));
                    //BigInteger y = (u.multiply(v.modPow(BigInteger.TWO, e.getP()).multiply(this.getX().multiply(p.getZ())).subtract(w))).subtract(v.modPow(BigInteger.valueOf(3), e.getP()).multiply(this.getY().multiply(p.getZ())));
                    //y = y.mod(e.getP());
                    // y = u * (v^2 * x1 * z2 - w) - v^3 * y1 * z2
                    //BigInteger z = v.multiply(v.multiply(v)).multiply(this.getZ().multiply(p.getZ()));
                    BigInteger z = ff.multiply(ff.pow(v, BigInteger.valueOf(3)), ff.multiply(this.getZ(), p.getZ()));
                    //BigInteger z = v.modPow(BigInteger.valueOf(3), e.getP()).multiply(this.getZ().multiply(p.getZ()));
                    //z = z.mod(e.getP());
                    // z = v^3 * z1 * z2
                    return new ProjectivePoint(x, y, z);
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
        Point b = e.getInf();
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
        return this.z.equals(BigInteger.ZERO);
    }

    /**
     *
     * @param e
     * @return
     */
    @Override
    public boolean isInf(EllipticCurves e) {
        return isInf();
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
        if (p instanceof ProjectivePoint) {
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
        if(this.isInf()){
            Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO);
            while(e.onCurve(erg)){
                erg = new AffinePoint(erg.getX(), erg.getY().add(BigInteger.ONE));
            }
            return erg;
        }
        FiniteFields ff = new FiniteFields(e.getP());
        return new AffinePoint(ff.divide(this.getX(), this.getZ()), ff.divide(this.getY(), this.getZ()));
    }

    @Override
    public Point toJacobian(EllipticCurves e) {
        return this.toAffine(e).toJacobian(e);
    }

    /**
     *
     * @return
     */
    @Override
    public Point toProjective(EllipticCurves e) {
        return this;
    }
}