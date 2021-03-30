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
		String path = "dh.txt";
		DiffieHellman dh = new DiffieHellman(null, null);
		FiniteFields Field = new FiniteFields();
		BigInteger prim;
		Scanner sc = new Scanner(System.in);
		Scanner file = null;
		if(args.length > 0){
			path = args[0];
		}
		try {
			file = new Scanner(new File(path), "UTF-8");
			file.useLocale(Locale.GERMANY);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("Datei war wohl nicht vorhanden!");
		}
		do {
			System.out.println("Willkommen im Menü!");
			System.out.println("1. Primzahl generieren");
			System.out.println("2. DH Datei einlesen");
			System.out.println("3. Alice Key berechnen");
			System.out.println("4. Bobs Key berechnen");
			System.out.println("5. Common Key berechnen");
			System.out.println("6 - 9 reserviert");
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
						BigInteger p;
						BigInteger g;
						BigInteger a;
						BigInteger b;
						BigInteger x;
						BigInteger y;
						BigInteger al;
						BigInteger bo;
						if (file.hasNext()){
							read = file.nextLine();
							p = new BigInteger(read);
						} else {
							System.out.println("Geben Sie die Primzahl für DH ein:");
							read = sc.nextLine();
							p = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							a = new BigInteger(read);
						} else {
							System.out.println("Geben Sie den Parameter a der Kurve an:");
							read = sc.nextLine();
							a = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							b = new BigInteger(read);
						} else {
							System.out.println("Geben Sie den Parameter b der Kurve an:");
							read = sc.nextLine();
							b = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							x = new BigInteger(read);
						} else {
							System.out.println("Geben Sie x des Erzeuger für DH ein:");
							read = sc.nextLine();
							x = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							y = new BigInteger(read);
						} else {
							System.out.println("Geben Sie y des Erzeuger für DH ein:");
							read = sc.nextLine();
							y = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							al = new BigInteger(read);
						} else {
							System.out.println("Geben Sie Alices Schluessel für DH ein:");
							read = sc.nextLine();
							al = new BigInteger(read);
						}
						if (file.hasNext()) {
							read = file.nextLine();
							bo = new BigInteger(read);
						} else {
							System.out.println("Geben Sie Bobs Schluessel für DH ein:");
							read = sc.nextLine();
							bo = new BigInteger(read);
						}
						EllipticCurve curve = new EllipticCurve(a, b, p);
						Point erz = new ProjectivePoint(x, y, BigInteger.ONE);
						dh = new DiffieHellman(curve, erz);
						dh.setBobKey(bo);
						dh.setAliceKey(al);
						System.out.println("Datei erfolgreich eingelesen! Sie koennen nun mit Schritt 3 - x weitermachen.");
					}
					break;
				case 3:
					System.out.println("Alice Key: ");
					dh.calcAlice();
					dh.aliceK.print();
					break;
				case 4:
					System.out.println("Bobs Key: ");
					dh.calcBob();
					dh.bobK.print();
					break;
				case 5:
					System.out.println("Common Key: ");
					Point com = dh.commonKey();
					com.print();
					break;
				default:
					System.out.println("Keine oder ungültige Wahl!");
					break;
			}
		} while(choice != 0);
		System.out.println("Vielen Dank und bis bald!");
	
	}

}
