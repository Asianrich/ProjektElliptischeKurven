package htw.curves;
//import java.math.BigInteger;

import java.io.File;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int choice = 1;
		int tmp = 0;
		String read = "";
		DiffieHellman dh;
		FiniteFields Field = new FiniteFields();
		BigInteger prim;
		Scanner sc = new Scanner(System.in);
		Scanner file = null;
		try {
			file = new Scanner(new File("dh.txt"), "UTF-8");
			file.useLocale(Locale.GERMANY);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Datei war wohl nicht vorhanden!");
		}
		do {
			System.out.println("Willkommen im Menü!");
			System.out.println("1. Primzahl generieren");
			System.out.println("2. DH Datei einlesen");
			System.out.println("3 - 9 reserviert");
			System.out.println("0. beenden");
			choice = sc.nextInt();
			switch (choice){
				case 0:
					break;
				case 1:
					System.out.println("Geben Sie die Bitlaenge ein:");
					tmp = sc.nextInt();
					System.out.println("Bitte warten, das kann dauern...");
					prim = Field.generatePrime(tmp, 4);
					System.out.println("Ihre Zahl lautet:");
					System.out.println(prim);
					System.out.println();
					break;
				case 2:
					if(file != null){
						read = file.nextLine();
						BigInteger p = new BigInteger(read);
						read = file.nextLine();
						BigInteger g = new BigInteger(read);
						read = file.nextLine();
						BigInteger a = new BigInteger(read);
						read = file.nextLine();
						BigInteger b = new BigInteger(read);
						dh = new DiffieHellman(p, g);
						dh.setBobKey(b);
						dh.setAliceKey(a);
						System.out.println("Datei erfolgreich eingelesen! Sie koennen nun mit Schritt 3 - x weitermachen.");
					}
					break;
				default:
					System.out.println("Keine oder ungültige Wahl!");
					break;
			}
		} while(choice != 0);
		System.out.println("Vielen Dank und bis bald!");

		
		//Create BigInteger Objects for tests
/*		BigInteger b1, b2, b3, n; 
		BigInteger b4, b5, b6, b7, b8, b9, b10, b11, b12, num, b13, b14, b15,b16,b17, b18;
		b1 = new BigInteger("12");
		b2 = new BigInteger("20");
		
		b3 = new BigInteger("23");
		
		b4 = new BigInteger("1");
		b5 = new BigInteger("21");
		
		b6 = new BigInteger("-17"); //42823
		b7 = new BigInteger("-45");   //6409
		
		b8 = new BigInteger("2");
		b9 = new BigInteger("13");
        b10 = new BigInteger("14");
		
		b11 = new BigInteger("0");
		b12 = new BigInteger("5");

	    n = new BigInteger("5");
	    num = new BigInteger("-4");
	    b13 = new BigInteger("0");
	    b14 = new BigInteger("1");
	    b15 = new BigInteger("51");
	    b16 = new BigInteger("91");
	    b17 = new BigInteger("101");
	    b18 = new BigInteger("975000"); //123456
		
		//Call all Methods
		BasicTheoreticMethods basicMethod = new BasicTheoreticMethods(); */
		
	//    BigInteger result = basicMethod.modCalculation(b1, b2);
	   
	/*	Boolean b = basicMethod.isCongruent(b1, b2, b3);
		BigInteger c = basicMethod.modAddition(b1, b2, b3);
		BigInteger d = basicMethod.modSubtraction(b1, b2, b3);
		BigInteger e = basicMethod.modMultiplication(b1, b2, b3); 
		BigInteger f = basicMethod.multiplicativeInverse(b4, b5); //19
		BigInteger g = basicMethod.gcdExtended(b7, b6); //17
		BigInteger h = basicMethod.modExponentiation(b8, n, b9); //6
		BigInteger i = basicMethod.modDivision(b10, b11, b12);
		BigInteger j = basicMethod.phiFunction(num);
		BigInteger k = basicMethod.phiFunction(b13);
		BigInteger l = basicMethod.phiFunction(b14);
		BigInteger m = basicMethod.phiFunction(b15);
		BigInteger r = basicMethod.phiFunction(b16);
		BigInteger o = basicMethod.phiFunction(b17);
		BigInteger p = basicMethod.phiFunction(b18); //41088*/
		
		//print all the different results
	/*	System.out.println("Result of Modulo operation between " + b1 
                + " and " + b2 + 
                " is: " + result); 
		System.out.println("------------------------------------------------");
		System.out.println("Result of congruent Method is: " + b); 
		
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Result ModAddition: " + c); 
		System.out.println("Result ModSubtraction: " + d); 
		System.out.println("Result ModMultiplication: " + e); 
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Result MultiplicativInverse: " + f);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result Div: " + i);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result extendedGCD: " + g);
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Result modExponentiation: " + h);
		
		System.out.println("\n*************************************************");
		
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + j);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + k);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + l);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + m);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + r);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + o);
		System.out.println("\n------------------------------------------------");
		System.out.println("Result phi: " + p);*/
	    
		
	}

}
