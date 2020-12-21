package htw.curves;

import java.math.BigInteger;

public class DiffieHellman {

    BigInteger g = BigInteger.ZERO; //Erzeuger
    BigInteger p = BigInteger.ZERO; //Prime
    BigInteger a = BigInteger.ZERO; //Alice
    BigInteger b = BigInteger.ZERO; //Bob
    BigInteger k = BigInteger.ZERO; //Key


    public DiffieHellman(BigInteger p, BigInteger g){
        this.p = p;
        this.g = g;
        //test if g generates p
        //wait for Richard
    }

    public void setAliceKey(BigInteger a){
        this.a = a;
    }

    public void setBobKey(BigInteger b){
        this.b = b;
    }

    public BigInteger getAliceMessage(BigInteger aliceSecretNumber){
        return this.g.modPow(aliceSecretNumber, this.p);
    }

    public BigInteger getBobMessage(BigInteger bobSecretNumber){
        return this.g.modPow(bobSecretNumber, this.p);
    }

    public BigInteger aliceCalculationOfKey(BigInteger bobMessage, BigInteger aliceSecretNumber){
        return bobMessage.modPow(aliceSecretNumber, this.p);
    }

    public void commonKey(){
        this.k = g.modPow(a.multiply(b), p);
    }

    public BigInteger getKey(){
        return k;
    }

    public BigInteger bobCalculationOfKey(BigInteger aliceMessage, BigInteger bobSecretNumber){
        return aliceMessage.modPow(bobSecretNumber, this.p);
    }
}
