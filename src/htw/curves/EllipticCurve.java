package htw.curves;

import java.math.BigInteger;
import java.util.LinkedList;

public class EllipticCurve implements EllipticCurves {

    private BigInteger a = BigInteger.ZERO;
    private BigInteger b = BigInteger.ZERO;
    private BigInteger p = BigInteger.ZERO;

    // y^2 = x^3 * Ax + B
    // ->
    // y^2 * z = x^3 + Axz^2 + Bz^3
    // 0 4 1
    //16 =  0 + 0 + 5

    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger FOUR = new BigInteger("4");
    static final BigInteger TWENTY_SEVEN = new BigInteger("27");
    static final BigInteger NEG_SIXTEEN = new BigInteger("-16");
    FiniteFields ff;

    /**
     * Standardkonstruktor
     * @param a
     * @param b
     * @param p
     */
    public EllipticCurve(BigInteger a, BigInteger b, BigInteger p){
        this.a = a;
        this.b = b;
        this.p = p;
        ff = new FiniteFields(p);
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getA() {
        return this.a;
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getB() {
        return this.b;
    }

    /**
     * dieser Punkt (0|1|0) ist nie auf einer Kurve
     * @return
     */
    @Override
    public Point getInf() {
        return new ProjectivePoint(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);
    }

    /**
     *
     * @return
     */
    @Override
    public BigInteger getP() {
        return this.p;
    }

    /**
     * ist die Kurve geeignet
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean isNonSingular(BigInteger a, BigInteger b) {
        return !ff.add(ff.multiply(NEG_SIXTEEN, ff.multiply(FOUR, ff.multiply(a,a))),ff.multiply(TWENTY_SEVEN, ff.multiply(b,b))).equals(ZERO);
        //return !NEG_SIXTEEN.multiply((FOUR.multiply(a.multiply(a).multiply(a)).add((TWENTY_SEVEN.multiply(b.multiply(b)))))).equals(ZERO);
    }

    /**
     *
     * @param p
     * @return
     */
    @Override
    public boolean onCurve(Point p){
        if(p instanceof AffinePoint){
            return ff.pow(p.getY(), BigInteger.TWO).equals(ff.add(ff.pow(p.getX(), BigInteger.valueOf(3)), ff.add(ff.multiply(this.a, p.getX()), this.b)));
            //return p.getY().multiply(p.getY()).mod(this.p).equals(((p.getX().multiply(p.getX().multiply(p.getX()))).add(this.a.multiply(p.getX())).add(this.b)).mod(this.p));
        }
        if(p instanceof ProjectivePoint){
            if(p.getZ().equals(BigInteger.ZERO))
                return false;
            return ff.multiply(ff.pow(p.getY(), BigInteger.TWO), p.getZ()).equals(ff.add(ff.pow(p.getX(), BigInteger.valueOf(3)), ff.add(ff.multiply(ff.multiply(this.a, p.getX()), ff.pow(p.getZ(), BigInteger.TWO)), ff.multiply(this.b, ff.pow(p.getZ(), BigInteger.valueOf(3))))));
            //return p.getY().multiply(p.getY().multiply(p.getZ())).mod(this.p).equals(((p.getX().multiply(p.getX().multiply(p.getX()))).add(this.a.multiply(p.getX()).multiply(p.getZ().multiply(p.getZ()))).add(this.b.multiply(p.getZ().multiply(p.getZ().multiply(p.getZ()))))).mod(this.p));
        }
        if(p instanceof JacobianPoint){
            if(p.getZ().equals(BigInteger.ZERO))
                return false;
            return ff.pow(p.getY(), BigInteger.TWO).equals(ff.add(ff.pow(p.getX(), BigInteger.valueOf(3)), ff.add(ff.multiply(ff.multiply(this.a, p.getX()), ff.pow(p.getZ(), FOUR)), ff.multiply(this.b, ff.pow(p.getZ(), BigInteger.valueOf(6))))));
            //return p.getY().multiply(p.getY().multiply(p.getZ())).mod(this.p).equals(((p.getX().multiply(p.getX().multiply(p.getX()))).add(this.a.multiply(p.getX()).multiply(p.getZ().multiply(p.getZ()))).add(this.b.multiply(p.getZ().multiply(p.getZ().multiply(p.getZ()))))).mod(this.p));
        }
        return false;   //evtl modPow?
    }

    /**
     * kann bei großen p lange dauern
     * @return
     */
    public LinkedList<Point> getAllPoints(){
        LinkedList <Point> points = new LinkedList<>();
        FiniteFields field = new FiniteFields(this.p);
        for(BigInteger i = BigInteger.ZERO; i.compareTo(this.p) < 0; i = i.add(BigInteger.ONE)){
            BigInteger ys = ((i.multiply(i.multiply(i))).add(this.a.multiply(i)).add(this.b)).mod(this.p);
            BigInteger y = null;
            try {
                y = field.squareRoot(ys);
                points.add(new AffinePoint(i, y));
                points.add(new AffinePoint(i, y.multiply(BigInteger.valueOf(-1))));
            } catch (Exception e) {
            }
        }
        return points;
    }

    /**
     * kann bei langen Kruven dauern und funktionert nur mit p = Prim
     * @return
     */
    public Point findRoot(){
        LinkedList<Point> points = this.getAllPoints();
        BigInteger ml = BigInteger.valueOf(points.size() + 1); //INF ist +1
        BasicTheoreticMethods basic = new BasicTheoreticMethods();
        BigInteger gcd = basic.gcdExtended(ml, BigInteger.ONE);
        if(gcd.equals(BigInteger.ONE))
            return points.getFirst();
        else
            return null; //TODO whatever - hier nur Prim, was aber sonst
    }
}
