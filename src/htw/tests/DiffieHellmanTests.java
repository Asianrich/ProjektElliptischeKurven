package htw.tests;

import htw.curves.*;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class DiffieHellmanTests {

    EllipticCurve curve2 = new EllipticCurve(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(17));
    Point erz = new ProjectivePoint(BigInteger.valueOf(5), BigInteger.ONE, BigInteger.ONE);
    DiffieHellman dh = new DiffieHellman(curve2, erz);
    BigInteger alice = BigInteger.valueOf(88);
    BigInteger bob = BigInteger.valueOf(77);
    BigInteger key = BigInteger.valueOf(81);

    @Test
    public void testKey(){
        dh.setAliceKey(alice);
        dh.setBobKey(bob);
        dh.calcAlice();
        dh.calcBob();
        Point erg = dh.commonKey();
        assertTrue(erg.equals(new AffinePoint(BigInteger.ZERO, BigInteger.valueOf(11))));
    }

}
