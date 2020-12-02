package htw.curves;
import java.math.BigInteger;

public interface ModularArithmetic {

	
	//All Methods are implicitly public and abstract
	
    BigInteger modCalculation(BigInteger num, BigInteger mod);
    Boolean    isCongruent(BigInteger num_1, BigInteger num_2, BigInteger mod);
    BigInteger modAddition(BigInteger addend_1, BigInteger addend_2, BigInteger mod);
    BigInteger modSubtraction(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modMultiplication(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger gcdExtended(BigInteger num_1, BigInteger num_2);
	BigInteger multiplicativeInverse(BigInteger num, BigInteger mod);
	BigInteger modDivision(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modExponentiation(BigInteger num, BigInteger exp, BigInteger mod);
	BigInteger phiFunction(BigInteger num);
	
	
	
}
