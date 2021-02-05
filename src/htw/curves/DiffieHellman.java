package htw.curves;

import java.math.BigInteger;

public class DiffieHellman {

    Point g = null; //Erzeuger
    EllipticCurve p = null; //Prime
    BigInteger a = BigInteger.ZERO; //Alice
    BigInteger b = BigInteger.ZERO; //Bob
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

    public void commonKey(){
        this.k = a.multiply(b);
    }

    public BigInteger getKey(){
        return k;
    }

}
