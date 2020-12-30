package htw.tests;



import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import htw.curves.BasicTheoreticMethods;

public class arithmeticsTests {
	
		
	BasicTheoreticMethods test = new BasicTheoreticMethods();
				
	@Test
	public void testModCalculation() {
		
 	    BigInteger result_1 = test.modCalculation(BigInteger.valueOf(0), BigInteger.valueOf(3));
		BigInteger result_2 = test.modCalculation(BigInteger.valueOf(15), BigInteger.valueOf(9));
		BigInteger result_3 = test.modCalculation(BigInteger.valueOf(4), BigInteger.valueOf(4));
		BigInteger result_4 = test.modCalculation(BigInteger.valueOf(9), BigInteger.valueOf(15));
		BigInteger result_5 = test.modCalculation(BigInteger.valueOf(-12), BigInteger.valueOf(4));
		BigInteger result_6 = test.modCalculation(BigInteger.valueOf(-12), BigInteger.valueOf(20));
		BigInteger result_7 = test.modCalculation(BigInteger.valueOf(-4), BigInteger.valueOf(3));
				
		assertEquals(BigInteger.ZERO, result_1);
		assertEquals(BigInteger.valueOf(6), result_2);
		assertEquals(BigInteger.ZERO, result_3);
		assertEquals(BigInteger.valueOf(9), result_4);
		assertEquals(BigInteger.ZERO, result_5);
		assertEquals(BigInteger.valueOf(8), result_6);
		assertEquals(BigInteger.valueOf(2), result_7);
		
	}
	
	@Test
	public void whenModuloByZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modCalculation(BigInteger.valueOf(3), BigInteger.ZERO));
	}
	
	@Test
	public void whenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modCalculation(BigInteger.valueOf(5), BigInteger.valueOf(-2)));
	}
	
	@Test
	public void whenNumberAndModAreZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modCalculation(BigInteger.valueOf(0), BigInteger.ZERO));
	}
	
	@Test
	public void testIsCongruent() {
		
		Boolean result_1 = test.isCongruent(BigInteger.valueOf(12), BigInteger.valueOf(20), BigInteger.valueOf(23));
		Boolean result_2 = test.isCongruent(BigInteger.valueOf(45), BigInteger.valueOf(15), BigInteger.valueOf(10));
		Boolean result_3 = test.isCongruent(BigInteger.valueOf(-4), BigInteger.valueOf(5), BigInteger.valueOf(3));
		
		assertTrue(result_1.equals(false));
		assertTrue(result_2.equals(true));
		assertTrue(result_3.equals(true));
		
	} 
	@Test
	public void testIscongruentWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.isCongruent(BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.ZERO));
	}
	
	@Test
	public void testModAddition() {
		
		BigInteger add_1 = test.modAddition(BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(5));
		BigInteger add_2 = test.modAddition(BigInteger.valueOf(11), BigInteger.valueOf(10), BigInteger.valueOf(12));
		BigInteger add_3 = test.modAddition(BigInteger.valueOf(10), BigInteger.valueOf(-22), BigInteger.valueOf(20));
		
		assertEquals(BigInteger.valueOf(0), add_1);
		assertEquals(BigInteger.valueOf(9), add_2);
		assertEquals(BigInteger.valueOf(8), add_3);
	}
	@Test
	public void testModAdditionWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modAddition(BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.ZERO));
	}
	
	@Test
	public void testModSubtraction() {
		
		BigInteger sub_1 = test.modSubtraction(BigInteger.valueOf(0), BigInteger.valueOf(0), BigInteger.valueOf(5));
		BigInteger sub_2 = test.modSubtraction(BigInteger.valueOf(50), BigInteger.valueOf(11), BigInteger.valueOf(15));
		BigInteger sub_3 = test.modSubtraction(BigInteger.valueOf(3), BigInteger.valueOf(50), BigInteger.valueOf(12));
		BigInteger sub_4 = test.modSubtraction(BigInteger.valueOf(14), BigInteger.valueOf(77), BigInteger.valueOf(45));
		
		assertEquals(BigInteger.valueOf(0), sub_1);
		assertEquals(BigInteger.valueOf(9), sub_2);
		assertEquals(BigInteger.valueOf(1), sub_3);
		assertEquals(BigInteger.valueOf(27), sub_4);
		
	}
	
	@Test
	public void testModMultiplication() {
		
		BigInteger mul_1 = test.modMultiplication(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(5));
		BigInteger mul_2 = test.modMultiplication(BigInteger.valueOf(5), BigInteger.valueOf(0), BigInteger.valueOf(15));
		BigInteger mul_3 = test.modMultiplication(BigInteger.valueOf(74), BigInteger.valueOf(93), BigInteger.valueOf(13));
		BigInteger mul_4 = test.modMultiplication(BigInteger.valueOf(2590), BigInteger.valueOf(5253), BigInteger.valueOf(26));
		BigInteger mul_5 = test.modMultiplication(BigInteger.valueOf(-25), BigInteger.valueOf(14), BigInteger.valueOf(17));
		
		assertEquals(BigInteger.valueOf(0), mul_1);
		assertEquals(BigInteger.valueOf(0), mul_2);
		assertEquals(BigInteger.valueOf(5), mul_3);
		assertEquals(BigInteger.valueOf(16), mul_4);
		assertEquals(BigInteger.valueOf(7), mul_5);
	}
	
	@Test
	public void testGcdExtended() {
		
	    BigInteger gcd_1 = test.gcdExtended(BigInteger.valueOf(0), BigInteger.valueOf(0));
	    BigInteger gcd_2 = test.gcdExtended(BigInteger.valueOf(11), BigInteger.valueOf(0));
	    BigInteger gcd_3 = test.gcdExtended(BigInteger.valueOf(0), BigInteger.valueOf(6));
	    BigInteger gcd_4 = test.gcdExtended(BigInteger.valueOf(245), BigInteger.valueOf(459));
	    BigInteger gcd_5 = test.gcdExtended(BigInteger.valueOf(-5), BigInteger.valueOf(15));
	
	    assertEquals(BigInteger.valueOf(0), gcd_1);
	    assertEquals(BigInteger.valueOf(11), gcd_2);
	    assertEquals(BigInteger.valueOf(6), gcd_3);
	    assertEquals(BigInteger.valueOf(1),gcd_4);
	    assertEquals(BigInteger.valueOf(5),gcd_5);
	}
	
	@Test
	public void testHasInverse() {
		
	/*	Boolean value_1 = test.hasInverse(BigInteger.valueOf(0), BigInteger.valueOf(20));
		Boolean value_2 = test.hasInverse(BigInteger.valueOf(45), BigInteger.valueOf(0));*/
		Boolean value_3 = test.hasInverse(BigInteger.valueOf(72), BigInteger.valueOf(35));
		Boolean value_4 = test.hasInverse(BigInteger.valueOf(25), BigInteger.valueOf(65));
		
		
	/*	assertTrue(value_1.equals(false));
		assertTrue(value_2.equals(false));*/
		assertTrue(value_3.equals(true));
		assertTrue(value_4.equals(false));
		
	}
	@Test
	public void testHasInvWhenNumberIszero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.hasInverse(BigInteger.valueOf(0), BigInteger.valueOf(20)));
	}
	@Test
	public void testHasInvWhenModIsZero_thenArithemticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.hasInverse(BigInteger.valueOf(45), BigInteger.valueOf(0)));
	}
	@Test
	public void testHasInvWhenModIsLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.hasInverse(BigInteger.valueOf(45), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testMultiplicativeInverse() {
		
		BigInteger inverse_1 = test.multiplicativeInverse(BigInteger.valueOf(5), BigInteger.valueOf(1));
		BigInteger inverse_2 = test.multiplicativeInverse(BigInteger.valueOf(1), BigInteger.valueOf(6));
		BigInteger inverse_3 = test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(11));
		BigInteger inverse_4 = test.multiplicativeInverse(BigInteger.valueOf(10), BigInteger.valueOf(21));
		BigInteger inverse_5 = test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(7));
		BigInteger inverse_6 = test.multiplicativeInverse(BigInteger.valueOf(-4), BigInteger.valueOf(17));
	    
		
		assertEquals(BigInteger.valueOf(0), inverse_1);
		assertEquals(BigInteger.valueOf(1), inverse_2);
		assertEquals(BigInteger.valueOf(4), inverse_3);
		assertEquals(BigInteger.valueOf(19), inverse_4);
		assertEquals(BigInteger.valueOf(5), inverse_5);
		assertEquals(BigInteger.valueOf(4), inverse_6);
		
		
	}
	@Test
	public void testMulInvWhenNumberIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(0), BigInteger.valueOf(4)));
	}
	@Test
	public void testMulInvWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(0)));
	}
	@Test
	public void testMulInvWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.multiplicativeInverse(BigInteger.valueOf(3), BigInteger.valueOf(-7)));
	}
	
	@Test
	public void testModDivision() {
		
		BigInteger div_1 = test.modDivision(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(34));
		BigInteger div_2 = test.modDivision(BigInteger.valueOf(8), BigInteger.valueOf(4), BigInteger.valueOf(5));
		BigInteger div_3 = test.modDivision(BigInteger.valueOf(8), BigInteger.valueOf(3), BigInteger.valueOf(5));
		BigInteger div_4 = test.modDivision(BigInteger.valueOf(11), BigInteger.valueOf(4), BigInteger.valueOf(5));
		
		assertEquals(BigInteger.valueOf(0), div_1);
		assertEquals(BigInteger.valueOf(2), div_2);
		assertEquals(BigInteger.valueOf(1), div_3);
		assertEquals(BigInteger.valueOf(4), div_4);
	}
	
	@Test
	public void testModDivisionWhenDividendIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(0), BigInteger.valueOf(8)));
	}
	
	@Test
	public void testModDivisionWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(7), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testModDivisionWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modDivision(BigInteger.valueOf(3), BigInteger.valueOf(6), BigInteger.valueOf(0)));
	}
	
	@Test
	public void testModExponentiation() {
		
		BigInteger modExp_1 = test.modExponentiation(BigInteger.valueOf(4), BigInteger.valueOf(0), BigInteger.valueOf(11));
		BigInteger modExp_2 = test.modExponentiation(BigInteger.valueOf(0), BigInteger.valueOf(4), BigInteger.valueOf(5));
		BigInteger modExp_3 = test.modExponentiation(BigInteger.valueOf(2), BigInteger.valueOf(5), BigInteger.valueOf(13));
		BigInteger modExp_4 = test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(333), BigInteger.valueOf(15));
		BigInteger modExp_5 = test.modExponentiation(BigInteger.valueOf(82), BigInteger.valueOf(7), BigInteger.valueOf(20));
		BigInteger modExp_6 = test.modExponentiation(BigInteger.valueOf(-15), BigInteger.valueOf(4), BigInteger.valueOf(17));
		BigInteger modExp_7 = test.modExponentiation(BigInteger.valueOf(15), BigInteger.valueOf(-4), BigInteger.valueOf(17));
		
		assertEquals(BigInteger.valueOf(1), modExp_1);
		assertEquals(BigInteger.valueOf(0), modExp_2);
		assertEquals(BigInteger.valueOf(6), modExp_3);
		assertEquals(BigInteger.valueOf(3), modExp_4);
		assertEquals(BigInteger.valueOf(8), modExp_5);
		assertEquals(BigInteger.valueOf(16), modExp_6);
		assertEquals(BigInteger.valueOf(1), modExp_7);
		
	}
	
	@Test
	public void testModExpWhenModLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(7), BigInteger.valueOf(-3)));
	}
	
	@Test
	public void testModExpWhenModIsZero_thenArithmeticException() {
		
		Assertions.assertThrows(ArithmeticException.class, () -> test.modExponentiation(BigInteger.valueOf(3), BigInteger.valueOf(6), BigInteger.valueOf(0)));
	}
	
	@Test
	public void testPhiWhenNumberLessThanZero_thenIllegalArgumentException() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> test.phiFunction(BigInteger.valueOf(-10)));
	}
	@Test
	public void testPhiFunction() {
		
		BigInteger phi_1 = test.phiFunction(BigInteger.valueOf(7));
		BigInteger phi_2 = test.phiFunction(BigInteger.valueOf(8));
		BigInteger phi_3 = test.phiFunction(BigInteger.valueOf(10));
		BigInteger phi_4 = test.phiFunction(BigInteger.valueOf(4));
		BigInteger phi_5 = test.phiFunction(BigInteger.valueOf(35));
		BigInteger phi_6 = test.phiFunction(BigInteger.valueOf(143));
		BigInteger phi_7 = test.phiFunction(BigInteger.valueOf(345)); 
		
        assertEquals(BigInteger.valueOf(6), phi_1);
        assertEquals(BigInteger.valueOf(4), phi_2);
        assertEquals(BigInteger.valueOf(4), phi_3);
        assertEquals(BigInteger.valueOf(2), phi_4);
        assertEquals(BigInteger.valueOf(24), phi_5);
        assertEquals(BigInteger.valueOf(120), phi_6);
        assertEquals(BigInteger.valueOf(176), phi_7);

	}
}
