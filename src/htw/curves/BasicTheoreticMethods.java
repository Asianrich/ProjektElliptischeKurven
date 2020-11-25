import java.math.BigInteger;

public class BasicTheoreticMethods implements ModularArithmetic {
     
	BigInteger result;
	//Constructor
	
	public BasicTheoreticMethods (){ }
	
	
	/* Modulo calculation
	 * @param num number
	 * @param mod modulo
	 * @param temp, value temporary value
	 * @return result
	*/
	
	
	public BigInteger modCalculation(BigInteger num, BigInteger mod){
		BigInteger temp, value;
		
		if (num.equals(BigInteger.ZERO)){
			result = num;
	    }
		else if(mod.equals(BigInteger.ZERO)){
			result = BigInteger.ZERO;
		}
		
		else if (num.equals(mod)){
			result = BigInteger.ZERO;
		}
		
		else //if the number is a negative number
			
		    if (num.compareTo(BigInteger.ZERO) < 0){
		    	
			  do {
			    	
			    temp = num.add(mod);
			    value = temp.divide(mod);
			    result = temp.subtract(value.multiply(mod));
			
			  }while(result.compareTo(mod) > 0);
		}
		
		else  //check if the number is a positive number smaller or greater than modulo
			
			do{
				
			value = num.divide(mod);
		    result = num.subtract(value.multiply(mod));
		    
			}while(result.compareTo(mod) > 0);
			
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
			System.out.println(num_1 + " und " + num_2 + " sind kongruent modulo " + mod + ","
					+ "weil die Division von a/mod und b/mod den gleichen Rest: " + rest + " haben");
			return true;
		} else 	System.out.println(num_1 + " und " + num_2+ " sind nicht kongruent modulo " + mod );
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
	    BigInteger sum;
	    
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
		
		if (num_1.equals(BigInteger.ZERO) || num_2.equals(BigInteger.ZERO)){
			return BigInteger.ZERO;
		}
		
		BigInteger mul = num_1.multiply(num_2);
		BigInteger result = modCalculation(mul,mod);
		
		return result;
	}
	
	/*extended Euclidean Algorithm to compute integers x, y so that the greatest common divisor gcd(a,b)= ax+by
	 * @param num_1 number
	 * @param num_2 number
	 * @param x , y coefficients 
	 * @result gcd the last non-zero remainder 
	 * */
	
	public BigInteger gcdExtended(BigInteger num_1, BigInteger num_2) {
		
		BigInteger x, y;
		// Base case : if the first number is zero return second number 
		if (num_1.equals(BigInteger.ZERO)){
			x = BigInteger.ZERO;
			y = BigInteger.ONE;
			return num_2;
      	}
//		if the second number is zero return first number
		if (num_2.equals(BigInteger.ZERO)){
			x = BigInteger.ONE;
			y = BigInteger.ZERO;
			return num_1;
		}
		//yet we've to store the results of recursive call
		BigInteger x1 = BigInteger.ONE;
		BigInteger y1 = BigInteger.ONE;
		
		//we will divide the divisor by the remainder until the remainder is zero
		BigInteger gcd = gcdExtended(modCalculation(num_2, num_1), num_1);
		
		// we now update x and y, with x = y1 - (b/a) * x1 
		x = y1.subtract(num_2.divide(num_1).multiply(x1));
		y = x1;
		
		return gcd;
	}
      
	
	public Boolean hasInverse (BigInteger num, BigInteger mod){
		
		if (gcdExtended(num,mod).equals(BigInteger.ONE)){
			return true;
			
		}else
			return false;
		
	}
	
	/* Iterative Modular Multiplicative Inverse a modulo m with extended Euclidean algorithm 1= a.x + m.y
	 * @param num number
	 * @param mod  modulo 
	 * @param x = 0, y = 1  gcd at the beginning , last_x = 1 gcd at end, last_y = 0
	 * @param m0 = mod , temp temporary values
	 * @param q quotient
	 * @return x: result from the Division between x and p (divisor) or the multiplicative Inverse
	 * Algorithm Assumption: a and m are coprimes, i.e., gcd(a, m) = 1 
	 * */
	
	public BigInteger multiplicativeInverse(BigInteger num, BigInteger mod){
		 //initialize variables
		BigInteger m0 = mod, q, temp; 
		
		BigInteger x= BigInteger.ZERO, y = BigInteger.ONE;
		BigInteger last_y = BigInteger.ZERO, last_x = BigInteger.ONE;
	
		//if modulo is one return zero
		
		if(mod.equals(BigInteger.ONE)|| mod.equals(BigInteger.ZERO)){
			return BigInteger.ZERO;
		}			
		if (num.equals(BigInteger.ONE)){
			return BigInteger.ONE;
		}
//		inverse exists only if num and mod are relatively prime, i. e. gcdExtended = 1
		
		if (hasInverse(num,mod) == false){
			return null;
		}
	    while (mod.compareTo(BigInteger.ONE) > 0){
			     q = num.divide(mod);
			     temp = num;
			     num = mod;
			     mod = modCalculation(temp, mod);
			System.out.println(q + ","+ temp +","+ num + ","+ mod);
			     //update x and y
			     temp = x;
			     x = last_x.subtract(q.multiply(x));
			     last_x = temp;
			System.out.println(temp + ","+ x+","+ last_x + ","+ mod);
			     temp = y;
		         y = last_y.subtract(q.multiply(x));
		         last_y = temp;
					
		    System.out.println(temp+ ","+ y + "," + last_y);
		
		
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
		//base case needed
		
		BigInteger inverseNum_2 = multiplicativeInverse (num_2, mod);
		
		BigInteger result = modMultiplication(num_1, inverseNum_2, mod);
				
		return result;
	}


	
	public BigInteger modExponentiation(BigInteger num, int exp, BigInteger mod) {
		
		// Initialize result 
        BigInteger pow = BigInteger.ONE; 
        
        //if the exponent is zero, then the power or if the number is zero return zero
        if (exp == 0)
			return pow;
        if(num.equals(BigInteger.ZERO)){
        	return BigInteger.ZERO;
        }
                
        // Update the number num if it is more than or equal to mod 
        num = modCalculation(num, mod);       
        while (exp > 0) 
        { 
            // If n is odd, multiply x with result 
            if((exp & 1)==1) 
                pow = modCalculation((pow.multiply(num)), mod); 
      
            // n must be even now  n = n / 2 
            exp = exp >> 1;  
            num = modCalculation((num.multiply(num)), mod);  
        } 
        return pow; 
	}


	
	
}
