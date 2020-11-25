package htw.curves;
import java.math.BigInteger;

public interface ModularArithmetic {

	
	//All Methods in  this Interface are implicitly public and abstract
	
    BigInteger modCalculation(BigInteger num, BigInteger mod);
    Boolean    isCongruent(BigInteger num_1, BigInteger num_2, BigInteger mod);
    BigInteger modAddition(BigInteger addend_1, BigInteger addend_2, BigInteger mod);
    BigInteger modSubtraction(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modMultiplication(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger gcdExtended(BigInteger num_1, BigInteger num_2);
	BigInteger multiplicativeInverse(BigInteger num, BigInteger mod);
	BigInteger modDivision(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modExponentiation(BigInteger num, int exp, BigInteger mod);
	
	
	
}
