package htw.tests;

import htw.curves.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiffieHellmanTests {

    EllipticCurve curve2 = new EllipticCurve(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(17));
    Point erz = new ProjectivePoint(BigInteger.valueOf(5), BigInteger.ONE, BigInteger.ONE);
    DiffieHellman dh = new DiffieHellman(curve2, erz);
    BigInteger alice = BigInteger.valueOf(88);
    BigInteger bob = BigInteger.valueOf(77);

    EllipticCurve curve = new EllipticCurve(BigInteger.valueOf(1), BigInteger.valueOf(4), BigInteger.valueOf(7));
    AffinePoint p1 = new AffinePoint(BigInteger.valueOf(4), BigInteger.valueOf(3));
    BigInteger a1 = BigInteger.valueOf(1000);
    BigInteger b1 = BigInteger.valueOf(10000);
    DiffieHellman dh1 = new DiffieHellman(curve, p1);

    @Test
    public void testKey(){
        dh.setAliceKey(alice);
        dh.setBobKey(bob);
        dh.calcAlice();
        dh.calcBob();
        Point erg = dh.commonKey();
        assertTrue(erg.equals(new AffinePoint(BigInteger.ZERO, BigInteger.valueOf(11))));
        assertTrue(dh.getKey().equals(BigInteger.ZERO));
    }

    @Test
    public void testKey1(){
        dh1.setAliceKey(a1);
        dh1.setBobKey(b1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> dh1.calcAlice());
        Assertions.assertThrows(IllegalArgumentException.class, () ->dh1.calcBob());
    }

}
