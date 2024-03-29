package htw.tests;
        import htw.curves.*;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.Test;

        import java.math.BigInteger;

        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

public class EllipticCurveTests {

    // ##### AFFFINE ####

    EllipticCurve curve = new EllipticCurve(BigInteger.valueOf(1), BigInteger.valueOf(4), BigInteger.valueOf(7));
    AffinePoint p1 = new AffinePoint(BigInteger.valueOf(4), BigInteger.valueOf(3));
    AffinePoint p2 = new AffinePoint(BigInteger.valueOf(5), BigInteger.valueOf(6));

    EllipticCurve curve1 = new EllipticCurve(BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(7));
    AffinePoint p3 = new AffinePoint(BigInteger.valueOf(6), BigInteger.valueOf(1));
    AffinePoint p4 = new AffinePoint(BigInteger.valueOf(1), BigInteger.valueOf(4));

    // ##### PROJECTIVE ####

    ProjectivePoint p5 = new ProjectivePoint(BigInteger.valueOf(4), BigInteger.valueOf(3), BigInteger.ONE);
    ProjectivePoint p6 = new ProjectivePoint(BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.ONE);

    ProjectivePoint p7 = new ProjectivePoint(BigInteger.valueOf(6), BigInteger.valueOf(1), BigInteger.ONE);
    ProjectivePoint p8 = new ProjectivePoint(BigInteger.valueOf(1), BigInteger.valueOf(4), BigInteger.ONE);

    EllipticCurve curve2 = new EllipticCurve(BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(17));

    // #### JACOBIAN ####
    JacobianPoint p13 = new JacobianPoint(BigInteger.valueOf(4), BigInteger.valueOf(3), BigInteger.ONE);
    JacobianPoint p14 = new JacobianPoint(BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.ONE);


    // ##### AFFFINE ####

    @Test
    public void testkMulAffine1() {
        Point erg = p1.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine2() {
        Point erg = p1.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine3() {
        Point erg = p2.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine2f() {
        Point erg = p1.kMul(BigInteger.valueOf(10000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine3f() {
        Point erg = p2.kMul(BigInteger.valueOf(1000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine4() {
        Point erg = p2.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulAffine5() {
        Point erg = p3.kMul(BigInteger.valueOf(1000), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulAffine6() {
        Point erg = p3.kMul(BigInteger.valueOf(10001), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulAffine7() {
        Point erg = p4.kMul(BigInteger.valueOf(1000), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulAffine8() {
        Point erg = p4.kMul(BigInteger.valueOf(10001), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    // ### PROJECTIVE ###

    @Test
    public void testkMulProjective1() {
        Point erg = p5.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective2() {
        Point erg = p5.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective3() {
        Point erg = p6.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective2f() {
        Point erg = p5.kMul(BigInteger.valueOf(10000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective3f() {
        Point erg = p6.kMul(BigInteger.valueOf(1000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective4() {
        Point erg = p6.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulProjective5() {
        Point erg = p7.kMul(BigInteger.valueOf(1000), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulProjective6() {
        Point erg = p7.kMul(BigInteger.valueOf(10001), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulProjective7() {
        Point erg = p8.kMul(BigInteger.valueOf(1000), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testkMulProjective8() {
        Point erg = p8.kMul(BigInteger.valueOf(10001), curve1);
        assertTrue(curve1.onCurve(erg));
    }

    @Test
    public void testPoints() {
        assertTrue(curve.getAllPoints().size() == 10);
    }

    @Test
    public void testPoints1() {
        assertTrue(curve1.getAllPoints().size() == 6);
    }

    @Test
    public void testPoints2() {
        assertTrue(curve2.getAllPoints().size() == 18);
    }

    @Test
    public void testRoot() {
        assertTrue(curve2.findRoot().equals(new AffinePoint(BigInteger.valueOf(0), BigInteger.valueOf(6))));
    }

    @Test
    public void testkMulJacobian1() {
        Point erg = p13.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulJacobian2() {
        Point erg = p13.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulJacobian3() {
        Point erg = p14.kMul(BigInteger.valueOf(1001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void testkMulJacobian2f() {
        Point erg = p13.kMul(BigInteger.valueOf(10000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMulJacobian3f() {
        Point erg = p14.kMul(BigInteger.valueOf(1000), curve);
        assertFalse(curve.onCurve(erg));
    }

    @Test
    public void testkMuljacobian4() {
        Point erg = p14.kMul(BigInteger.valueOf(10001), curve);
        assertTrue(curve.onCurve(erg));
    }

    @Test
    public void find(){
        Point erg = curve.findRoot();
        assertTrue(curve.isNonSingular(curve.getA(), curve.getB()));
        assertTrue(curve.onCurve(erg));
    }
}