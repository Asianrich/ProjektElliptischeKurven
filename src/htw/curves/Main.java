import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		
		//Create BigInteger Objects for tests
		BigInteger b1, b2, b3; 
		BigInteger b4, b5, b6, b7, b8, b9, b10, b11, b12;
		b1 = new BigInteger("15");
		b2 = new BigInteger("20");
		
		b3 = new BigInteger("23");
		
		b4 = new BigInteger("10");
		b5 = new BigInteger("21");
		
		b6 = new BigInteger("42823");
		b7 = new BigInteger("6409");
		
		b8 = new BigInteger("2");
		b9 = new BigInteger("13");
        b10 = new BigInteger("8");
		
		b11 = new BigInteger("4");
		b12 = new BigInteger("5");

	    int n = 5;
		
		//Call all Methods
		BasicTheoreticMethods basicMethod = new BasicTheoreticMethods();
		
	   /* BigInteger result = basicMethod.modCalculation(b1, b2);
		Boolean b = basicMethod.isCongruent(b1, b2, b3);
		BigInteger c = basicMethod.modAddition(b1, b2, b3);
		BigInteger d = basicMethod.modSubtraction(b1, b2, b3);
		BigInteger e = basicMethod.modMultiplication(b1, b2, b3); */
		BigInteger f = basicMethod.multiplicativeInverse(b4, b5); //false must give 19
		/*BigInteger g = basicMethod.gcdExtended(b7, b6); //17
		BigInteger h = basicMethod.modExponentiation(b8, n, b9); //6*/
		BigInteger i = basicMethod.modDivision(b10, b11, b12);
		
		//print all the different results
		/*System.out.println("Resultat der Modularoperation zwischen " + b1 
                + " und " + b2 + 
                " ist: " + result); 
		System.out.println("------------------------------------------------");
		System.out.println("Resultat der Kongruenz Methode lautet: " + b); 
		
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Resultat der ModAddition lautet: " + c); 
		System.out.println("Resultat der ModSubtraction lautet: " + d); 
		System.out.println("Resultat der ModMultiplication lautet: " + e); */
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Resultat der MultiplicativInverse lautet: " + f);
		System.out.println("\n------------------------------------------------");
		System.out.println("Resultat der Div lautet: " + i);
		/*System.out.println("\n------------------------------------------------");
		System.out.println("Resultat der extendedGCD lautet: " + g);
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Resultat der modExponentiation lautet: " + h);*/
	}

}
