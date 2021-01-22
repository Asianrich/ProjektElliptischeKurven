package htw.tests;

import htw.curves.DiffieHellman;
import htw.curves.EllipticCurve;
import htw.curves.Point;
import htw.curves.ProjectivePoint;
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
    BigInteger msg1 = BigInteger.valueOf(42);

    @Test
    public void testKey(){
        dh.setAliceKey(alice);
        dh.setBobKey(bob);
        dh.commonKey();
        assertTrue(dh.getKey().equals(key));
    }

}
