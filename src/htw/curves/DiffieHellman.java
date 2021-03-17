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


    public DiffieHellman(BigInteger a, BigInteger b, BigInteger p, BigInteger x, BigInteger y){
        this.p = new EllipticCurve(a,b,p);
        this.g = new ProjectivePoint(x,y,BigInteger.ONE);
    }

    public DiffieHellman(EllipticCurve p, Point g){
        this.p = p;
        this.g = g;
    }

    public void setAliceKey(BigInteger a){
        this.a = a;
    }

    public void setBobKey(BigInteger b){
        this.b = b;
    }

    public void calcAlice(){
        if(a == null)
            throw new IllegalCallerException();
        aliceK = g.kMul(a, p);
        k = aliceK.getX();
    }

    public void calcBob(){
        if(b == null)
            throw new IllegalCallerException();
        bobK = g.kMul(b, p);
        k = bobK.getX();
    }

    public Point commonKey(){
        if(a == null || b == null)
            throw new IllegalCallerException();
        Point a = aliceK.kMul(this.b, p).toAffine(p);
        Point b = bobK.kMul(this.a, p).toAffine(p);
        if(a.equals(b))
            return a;
        else
            throw new IllegalArgumentException();
    }

    public BigInteger getKey(){
        return k;
    }

}
