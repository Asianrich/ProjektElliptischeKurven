package htw.curves;
import java.math.BigInteger;
import java.util.ArrayList;

  public interface ModularArithmetic {

	
  // All Methods are implicitly public and abstract
	
    BigInteger modCalculation(BigInteger num, BigInteger mod);
    Boolean    isCongruent(BigInteger num_1, BigInteger num_2, BigInteger mod);
    BigInteger modAddition(BigInteger addend_1, BigInteger addend_2, BigInteger mod);
    BigInteger modSubtraction(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modMultiplication(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger gcdExtended(BigInteger num_1, BigInteger num_2);
	Boolean    hasInverse(BigInteger num, BigInteger mod);
	BigInteger multiplicativeInverse(BigInteger num, BigInteger mod);
	BigInteger modDivision(BigInteger num_1, BigInteger num_2, BigInteger mod);
	BigInteger modExponentiation(BigInteger num, BigInteger exp, BigInteger mod);
	BigInteger phiFunction(BigInteger num);
	BigInteger chineseRemainder(ArrayList<BigInteger> remainderList_R, ArrayList<BigInteger> modulList_M);
	BigInteger random(int length);
	BigInteger random(BigInteger range);


    ArrayList<BigInteger> modAddition(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2, ArrayList<BigInteger> modPolynom, BigInteger modNumber);
    ArrayList<BigInteger> modSubtraction(ArrayList<BigInteger> subtrahend, ArrayList<BigInteger> minuend, ArrayList<BigInteger> mod, BigInteger modNumber);
    ArrayList<BigInteger> modMultiply(ArrayList<BigInteger> factor1, ArrayList<BigInteger> factor2, ArrayList<BigInteger> mod, BigInteger modNumber);
    ArrayList<BigInteger> modDivision(ArrayList<BigInteger> divisor, ArrayList<BigInteger> dividend, ArrayList<BigInteger> mod, BigInteger modNumber);

}
