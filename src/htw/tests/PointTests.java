package htw.tests;
import htw.curves.AffinePoint;
import htw.curves.EllipticCurve;
import htw.curves.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointTests {

    // ##### AFFFINE ####

    EllipticCurve curve = new EllipticCurve(BigInteger.valueOf(1),BigInteger.valueOf(4),BigInteger.valueOf(7));
    AffinePoint p1 = new AffinePoint(BigInteger.valueOf(4), BigInteger.valueOf(3));
    AffinePoint p2 = new AffinePoint(BigInteger.valueOf(5), BigInteger.valueOf(6));

    EllipticCurve curve1 = new EllipticCurve(BigInteger.valueOf(3),BigInteger.valueOf(5),BigInteger.valueOf(7));
    AffinePoint p3 = new AffinePoint(BigInteger.valueOf(6), BigInteger.valueOf(1));
    AffinePoint p4 = new AffinePoint(BigInteger.valueOf(1), BigInteger.valueOf(4));

    // ##### PROJECTIVE ####



    // ##### AFFFINE ####

    @Test
    public void testAdd1(){
        Point erg = p1.add(p2, curve);
        BigInteger x = erg.getX();
        assertTrue(x.equals(BigInteger.ZERO));
    }

    @Test
    public void testAdd2(){
        Point erg = p1.add(p2, curve);
        BigInteger y = erg.getY();
        assertTrue(y.equals(BigInteger.TWO));
    }

    @Test
    public void testAdd3(){
        Point erg = p3.add(p4, curve);
        BigInteger x = erg.getX();
        assertTrue(x.equals(BigInteger.valueOf(4)));
    }

    @Test
    public void testAdd4(){
        Point erg = p3.add(p4, curve);
        BigInteger y = erg.getY();
        assertTrue(y.equals(BigInteger.TWO));
    }

    @Test
    public void onCurve(){
        Point erg = p1.add(p2, curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void onCurve2(){
        Point erg = p3.add(p4, curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void notOnCurve(){
        Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void notOnCurve2(){
        Point erg = new AffinePoint(BigInteger.ZERO, BigInteger.ZERO);
        assertFalse(curve1.onCurve(erg));
    }


    // ##### PROJECTIVE ####



}
