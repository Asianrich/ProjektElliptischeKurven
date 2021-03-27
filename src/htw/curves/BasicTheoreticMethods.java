package htw.curves;
import java.math.BigInteger;

import java.util.ArrayList;

import java.util.Random;

public class BasicTheoreticMethods implements ModularArithmetic {
     
	//Constructor
	public BasicTheoreticMethods (){ }
	
	
	/* Modulo calculation
	 * @param num number
	 * @param mod modulo
	 * @param temp, value temporary value
	 * @return result
	*/
	
	
	public BigInteger modCalculation(BigInteger num, BigInteger mod){
		BigInteger temp, value, result;
		
		if (num.equals(BigInteger.ZERO)){
			result = BigInteger.ZERO;
	    }
		if (mod.equals(BigInteger.ZERO)) {
			throw new ArithmeticException("We can't compute with modulo zero");
		}
		if (mod.compareTo(BigInteger.ZERO) < 0){
			throw new IllegalArgumentException("We can't compute with modulo less than zero");
		}
		if (num.equals(BigInteger.ZERO) && mod.equals(BigInteger.ZERO)) {
			throw new ArithmeticException("It's not possible to find the modulo with number zero and modulo zero");
		}
		
		if (num.equals(mod)){
			result = BigInteger.ZERO;
		}
		//if the number is a negative number
		
		if (num.compareTo(BigInteger.ZERO) < 0){
		
		   do{			    	
			    temp = num.add(mod);
			   		  
		        value = temp.divide(mod);
		        result = temp.subtract(value.multiply(mod));
		        num = result;
		         
		    } while (result.compareTo(BigInteger.ZERO) < 0);
		} //check if the number is a positive number smaller or greater than modulo	
		
		else
		   do 
			{				
			  value = num.divide(mod);
		      result = num.subtract(value.multiply(mod));
		      num = result;
		  
			} while(result.compareTo(mod) > 0);
			
		return result;
	}
	
	/* Congruent calculation with modCalculation method
	 * @param  r1 remainder_1 
	 * @param r2 remainder_2  
	 * @param mod 
	 * @param rest 
	 * return boolean
	 * */
	

	public Boolean isCongruent(BigInteger num_1, BigInteger num_2, BigInteger mod){
		BigInteger r1, r2;
		BigInteger rest;
		
		r1 = modCalculation(num_1, mod);
			
		r2 = modCalculation(num_2, mod);
	
		if (r1.equals(r2)){
			rest = r1;
			System.out.println(num_1 + " und " + num_2 + " are congruent modulo " + mod + ","
					+ " because the division of a/mod and b/mod have the same residue: " + rest);
			return true;
		} else 	System.out.println(num_1 + " und " + num_2+ " are not congruent modulo " + mod );
		return false;
	
	}
	
	/*Modular addition of big numbers with 3 parameters
	 * @param addend_1
	 * @param addend_2
	 * @param sum temporary value
	 * @param mod
	 * @return result
	 * */
	public BigInteger modAddition(BigInteger addend_1, BigInteger  addend_2, BigInteger mod){
	    BigInteger sum, result;
	    
	    sum = addend_1.add(addend_2);
	    
	    if (sum.equals(BigInteger.ZERO)){
			return result = BigInteger.ZERO;
		}
		 else			
			result = modCalculation(sum, mod);
		
		return result;
	}
	
	
	/*Modular subtraction of big numbers with 3 parameters
	 * @param num_1 is subtracted  
	 * @param num_2 minus
	 * @param sub temporary value
	 * @param mod
	 * @return result
	 * */
	public BigInteger modSubtraction(BigInteger num_1, BigInteger  num_2, BigInteger mod){
		BigInteger result;
		BigInteger sub = num_1.subtract(num_2);
		
		if (sub.equals(BigInteger.ZERO)){
		    result = BigInteger.ZERO;
		}
		 else
		
		    result = modCalculation(sub, mod);
		
		return result;
	}
	
	/*Modular multiplication of big numbers with 3 parameters
	 * @param num_1 multiplier 
	 * @param num_2 multiplier
	 * @param mul temporary value
	 * @param mod
	 * @return result
	 * */
	public BigInteger modMultiplication(BigInteger num_1, BigInteger num_2, BigInteger mod){
		BigInteger mul, result;
		if (num_1.equals(BigInteger.ZERO) || (num_2.equals(BigInteger.ZERO))){
			return BigInteger.ZERO;
		}
		
		mul = num_1.multiply(num_2);
		result = modCalculation(mul,mod);
		
		return result;
	}
	
	/*Using extended Euclidean Algorithm to compute integers x, y so that the greatest common divisor gcd(a,b)= ax+by
	 * The subset of a given number z consists of all natural numbers n, for which the following applies: n divides z.
	 * @param num_1 positive number 
	 * @param num_2 positive number
	 * @param x , y coefficients 
	 * @result gcd the last non-zero remainder 
	 * */
	   
	BigInteger x, y;
	
	public BigInteger gcdExtended(BigInteger num_1, BigInteger num_2) {
		
		
		// if num_1 or num_2 are negative numbers, we simply apply the absolute value
		num_1 = num_1.abs();
		num_2 = num_2.abs();
		
		// Base case : if the first number is zero return second number 
		
		if (num_1.equals(BigInteger.ZERO)){
			x = BigInteger.ZERO;
			y = BigInteger.ONE;
			return num_2;
      	}
       //	if the second number is zero return first number
		if (num_2.equals(BigInteger.ZERO)){
			x = BigInteger.ONE;
			y = BigInteger.ZERO;
			return num_1;
		}
		//yet we've to store the results of recursive call
		//we will divide the divisor by the remainder until the remainder is zero
		
		BigInteger x1 = BigInteger.ONE;
		BigInteger y1 = BigInteger.ONE;
		
		BigInteger gcd = gcdExtended(modCalculation(num_2, num_1), num_1);
		
		// we now update x and y, with x = y1 - (b/a) * x1 
		x = y1.subtract(num_2.divide(num_1).multiply(x1));
		y = x1;
		
		return gcd;
	}
      
	/*Method to check if a given number has an inverse
	 * @param num number
	 * @param mod modulo
	 * return boolean
	 * */
	public Boolean hasInverse (BigInteger num, BigInteger mod){
	
		// gcd != 1
		if (num.equals(BigInteger.ZERO) || mod.equals(BigInteger.ZERO)) {
			throw new ArithmeticException("There is no modular multiplicative inverse for the number: " + num);
		}
		if (mod.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("The BigInteger " + num + " has no multiplicative inverse mod " + mod);
		}
			
		else if (gcdExtended(num,mod).compareTo(BigInteger.ONE) != 0 ){
		return false;
			
		} else //gcdExtended(num,mod).equals(BigInteger.ONE)
		
		return true;

	}
	
	/* Iterative Modular Multiplicative Inverse a modulo m with extended Euclidean algorithm 1= a.x + m.y
	 * @param num number
	 * @param mod  modulo 
	 * @param x = 0, y = 1 at the beginning , last_x = 1 gcd at end, last_y = 0
	 * @param m0 = mod , temp temporary values
	 * @param q quotient
	 * @return x: result from the division between x and p (divisor) or the multiplicative Inverse
	 * Algorithm Assumption: a and m are coprimes, i.e., gcd(a, m) = 1 
	 * */
	
	public BigInteger multiplicativeInverse(BigInteger num, BigInteger mod){
		 //initialize variables
		BigInteger m0 = mod, q, temp; 
		
		BigInteger x= BigInteger.ZERO, y = BigInteger.ONE;
		BigInteger last_y = BigInteger.ZERO, last_x = BigInteger.ONE;
			
	    Boolean check = hasInverse(num,mod);
		if(check.equals(false)) {
			 throw new ArithmeticException("BigInteger isn't invertible !");
		}
		
		// continue only if the given number has an multiplicative inverse 
		//if modulo is one return zero
				
		if(mod.equals(BigInteger.ONE)){
			x = BigInteger.ZERO;
		}
				
		if (num.equals(BigInteger.ONE)){
			x = BigInteger.ONE;
		}
		// inverse exists only if num and mod are relatively prime, i. e. gcdExtended = 1
				
		 while (mod.compareTo(BigInteger.ONE) > 0){
			 q = num.divide(mod);
		     temp = num;
			 num = mod;
			 mod = modCalculation(temp, mod);
					
			//update x and y
		     temp = x;
			 x = last_x.subtract(q.multiply(x));
			 last_x = temp;
					
			 temp = y;
		     y = last_y.subtract(q.multiply(x));
			 last_y = temp;		
				
		 }     
	   //Check if x is positive,if not add with the modulo
	     if(x.compareTo(BigInteger.ZERO) < 0)
					
		     x = x.add(m0); 
					
			return x ;
	}

	
	/*Modular division of big numbers with 3 parameters
	 * @param num_1 dividend  
	 * @param num_2 divisor
	 * @param mul temporary value
	 * @param mod
	 * @return result,the result by the multiplication of num_1 with the inverse of num_2 modulo mod
	 * */
	
	public BigInteger modDivision(BigInteger num_1, BigInteger num_2, BigInteger mod) {
		
		//base case
		if(num_1.equals(BigInteger.ZERO)) {
			return BigInteger.ZERO;
		}
		if (num_2.equals(BigInteger.ZERO) || mod.equals(BigInteger.ZERO)) {
		//	System.out.println("We can't calculate modular multiplicative inverse of this number");
			throw new ArithmeticException("We can't calculate modular multiplicative inverse of this number");			
		}
		if (mod.compareTo(BigInteger.ZERO) < 0) {
			//	System.out.println("We can't calculate modular multiplicative inverse of this number");
				throw new IllegalArgumentException("Wrong arguments, change them !");			
			}
			
		BigInteger inverseNum_2 = multiplicativeInverse (num_2, mod);
		BigInteger result = modMultiplication(num_1, inverseNum_2, mod);
		
			
		return result;
	}

	/*Function implementing Modular power 
	 * @param num number 
	 * @param exp exponent
	 * @param mod modulo
	 * @return pow, the result
	 * */
	
	public BigInteger modExponentiation(BigInteger num, BigInteger exp, BigInteger mod) {
		
		// Initialize the result 
        BigInteger pow = BigInteger.ONE; 
        
        //if the exponent is zero, then return one or if the number is zero return zero
        if (exp.equals(BigInteger.ZERO))
			return pow;
        if(num.equals(BigInteger.ZERO)){
        	return BigInteger.ZERO;
        }
        // if the modulo is zero or less than zero
        if (mod.equals(BigInteger.ZERO)) {
        	throw new ArithmeticException("Operation can't be done");
        } 
        if (mod.compareTo(BigInteger.ZERO) < 0) {
        	throw new IllegalArgumentException("Operation can't be done");
        } 
        // if the exponent is less than zero, first find the modular multiplicative inverse of num modulo mod
        if (exp.compareTo(BigInteger.ZERO) < 0) {
        	exp = exp.abs();
        	num = modCalculation (multiplicativeInverse(num, mod), mod) ;
        	
        }
        // Update the number num if it is more than or equal to mod 
        
        num = modCalculation(num, mod);       
        while (exp.compareTo(BigInteger.ZERO) > 0) 
        { 
            // If exponent is odd, multiply number with result 
        	
            if((exp.and(BigInteger.ONE).equals(BigInteger.ONE))) 
                pow = modCalculation((pow.multiply(num)), mod); 
      
            // exponent must be even now  n = n / 2 
            
            exp = exp.shiftRight(1);  
            num = modCalculation((num.multiply(num)), mod);  
        } 
        return pow; 
	}

	/*Method to calculate the Euler'sche function for a natural number n
	 * It correspond to the count of numbers x in {1, 2, 3,..., n} that are relatively 
	 * prime to n, i.e., the numbers whose gcd(x,n) are 1.
	 * Using the product formula we multiply n by product of (1 1/p) for all prime factors p of n.
	 * @param number num
	 * @param prim prime number
	 * We count all prime numbers and their multiples and subtract this count from num to get the result
	 * Prime factors and multiples of prime factors won't have gcd as 1
	 * return result, the totient at the end of the sample
	 * */

	public BigInteger phiFunction(BigInteger num) {
		
	//  Initialize result as number
		
		BigInteger phiResult = num;
		
		// check if it's a negative number
		
		if (num.compareTo(BigInteger.ZERO) < 0) {
			
			throw new IllegalArgumentException("You need to enter a positive number");
		}
		// https://mathworld.wolfram.com/TotientFunction.html
		// By convention, phi(0)=1, although the Wolfram Language defines EulerPhi[0] equal to 0 for consistency 
		
		if(num.equals(BigInteger.ONE) || num.equals(BigInteger.ZERO)) {
			return BigInteger.ONE;
		}
		
		//Check every prime number of num and their multiples with gcd more than 1
		
		for (BigInteger prim = BigInteger.TWO; (prim.multiply(prim)).compareTo(num) <= 0; prim = prim.add(BigInteger.ONE) ) {
			
		// Check if prim divides num (==0), i.e. prim is a prime factor
					
			if (modCalculation(num,prim).equals(BigInteger.ZERO)) {
				
				//update result as long as prim divides num: num*(1 � 1/p) = num - (num/p)
				// subtract multiples of prim from result
				
				phiResult = phiResult.subtract(phiResult.divide(prim));
							
				//Remove all occurrences of prim in num
				while(modCalculation(num,prim).equals(BigInteger.ZERO)) {
					
				num = num.divide(prim);
			
				}
			}		 
		}
		// If the reduced number is greater than 1, i.e when num has a prime factor greater  
	   // than sqrt(num), then remove all multiples of num from result.
  
		if (num.compareTo(BigInteger.ONE) > 0) {
			
			phiResult = phiResult.subtract((phiResult.divide(num)));
		}
		return phiResult;
	}

	public BigInteger random(int length) {
		Random rnd = new Random();
		BigInteger rndNumber;
		do {
			rndNumber =  new BigInteger(length, rnd);
		}while(rndNumber.compareTo(BigInteger.valueOf(1)) < 1);

		return rndNumber;
	}

	public BigInteger random(BigInteger range) {
		Random rnd = new Random();
		BigInteger rndNumber;

		do {
			rndNumber  =  new BigInteger(range.bitLength(), rnd);
			if(rndNumber.compareTo(range) >= 0)
			{
				rndNumber = rndNumber.mod(range);
			}
		}while(rndNumber.compareTo(BigInteger.valueOf(1)) < 1);


		return rndNumber;
	}
  
	/*Function implementing the Chinese remainder theorem 
	* @param remainderList_R contains the remainders of the equations
	* @param modulList_M contains all the moduli, i.e positive integers m[] that are pairwise coprime (gcd for every pair is 1)
    * @param p, tmp temporary values
    * @param product, to store the product of all moduli
    * @return sum, the sum of x which is also the solution after x % prod
    * sum is the smallest number x such that: 
    * x % m[0] = rem[0], 
    * x % m[1] = rem[1], 
    * .................. 
    * x % m[k-1] = rem[k-1] 
    * k the size of m[], rem[]     	
    */	
     public BigInteger chineseRemainder(ArrayList<BigInteger> remainderList_R, ArrayList<BigInteger> modulList_M) {
    	
	 // Initialize temporary values
	
		BigInteger p, tmp;
		BigInteger product = BigInteger.ONE; 
		BigInteger sum = BigInteger.ZERO;  
		
		int k = modulList_M.size();
		
	 // check the size of the two ArrayList, they must have the same size
		
		if (k > remainderList_R.size()) { k = remainderList_R.size();}

	 // Compute product of all moduli  N = n[1]*...*n[k]
		
		for (int i = 0; i < k; i ++) 
						
			product = product.multiply(modulList_M.get(i)); 
        
		// For each i, the integers n[i] and N/n[i] are co-prime
		// so divide N by current modulus to get product excluding said modulus
		
		for (int i = 0; i < k; i++) {
					
			p = product.divide(modulList_M.get(i));	
			
		//  then calculate multiplicativeInverse x such that x*p == 1 % modulList_N.get(i)
			
			tmp = multiplicativeInverse(p, modulList_M.get(i)); 
			
			sum = sum.add(remainderList_R.get(i).multiply(tmp).multiply(p)); 
		
		}		
		// compute sum modulo product to get smallest/unique BigInteger x
		
		return modCalculation(sum, product); 
    }
}
