package htw.curves;

import java.math.BigInteger;

public class DiffieHellman {

    Point g = null; //Erzeuger
    EllipticCurve p = null; //Kurve
    BigInteger a = BigInteger.ZERO; //Alice
    Point aliceK = null;
    BigInteger b = BigInteger.ZERO; //Bob
    Point bobK = null;
    BigInteger k = BigInteger.ZERO; //Key


    /**
     * langer Konstruktor
     * @param a
     * @param b
     * @param p
     * @param x
     * @param y
     */
    public DiffieHellman(BigInteger a, BigInteger b, BigInteger p, BigInteger x, BigInteger y){
        this.p = new EllipticCurve(a,b,p);
        this.g = new ProjectivePoint(x,y,BigInteger.ONE);
    }

    /**
     * kurzer Konstruktor
     * @param p
     * @param g
     */
    public DiffieHellman(EllipticCurve p, Point g){
        this.p = p;
        this.g = g;
    }

    /**
     *
     * @param a
     */
    public void setAliceKey(BigInteger a){
        this.a = a;
    }

    /**
     *
     * @param b
     */
    public void setBobKey(BigInteger b){
        this.b = b;
    }

    /**
     * errechnet Alice public Key
     * wenn Punkt nicht auf Kurve, dann Sicherheitsrisiko
     */
    public void calcAlice(){
        if(a == null)
            throw new IllegalCallerException();
        aliceK = g.kMul(a, p);
        if(!p.onCurve(aliceK)){
            aliceK = null;
            throw new IllegalArgumentException();
        }
        k = aliceK.getX();
    }

    /**
     * errechnet Bob public Key
     * wenn Punkt nicht auf Kurve, dann Sicherheitsrisiko
     */
    public void calcBob(){
        if(b == null)
            throw new IllegalCallerException();
        bobK = g.kMul(b, p);
        if(!p.onCurve(bobK)){
            bobK = null;
            throw new IllegalArgumentException();
        }
        k = bobK.getX();
    }

    /**
     * wenn man sowohl mit Bobs als auch mit Alice Informationen den Key bekommt, dann ist alles korrekt
     * @return
     */
    public Point commonKey(){
        if(a == null || b == null)
            throw new IllegalCallerException();
        Point a = aliceK.kMul(this.b, p).toAffine(p);
        Point b = bobK.kMul(this.a, p).toAffine(p);
        if(a.equals(b)) {
            this.k = a.getX();
            return a;
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * meistens reicht die x-Koordinate
     * @return
     */
    public BigInteger getKey(){
        return k;
    }

}
