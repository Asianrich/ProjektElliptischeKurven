package htw.curves;

import java.math.BigInteger;
import java.util.*;

public class FiniteFields implements Fields {
    private BigInteger prim = null;
    private ModularArithmetic math;
    private ArrayList<BigInteger> polynom = new ArrayList<BigInteger>();

    public  FiniteFields() {
        math = new BasicTheoreticMethods();
    }

    public FiniteFields(BigInteger prim) {
        math = new BasicTheoreticMethods();
        if(checkPrime(prim, 5))
            this.prim = prim;
    }

    public FiniteFields(BigInteger prim, int gradient) {
        math = new BasicTheoreticMethods();
        if(checkPrime(prim, 5))
            this.prim = prim;
        createIrreducible(gradient);
    }

    private void createIrreducible(int gradient){
        if(gradient <= 0)
            return;
        switch(gradient){
            case 1:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                break;
            case 2:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                break;
            case 3:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ZERO);
                    polynom.add(BigInteger.ONE);
                break;
            case 4:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                break;
            case 5:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                break;
            case 6:
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                    polynom.add(BigInteger.ONE);
                break;
            default: polynom = null;
            break;
        }
    }


    @Override
    public BigInteger generatePrime(int len, int trials) {
        BigInteger rndPrime = BigInteger.valueOf(4); //Irgendeine Zahl, das nicht Prim ist.
        while(!checkPrime(rndPrime, trials)){
            rndPrime = math.random(len);
        }
        return rndPrime;
    }

    @Override
    public BigInteger squareRoot(BigInteger number) throws Exception {
        BigInteger root[];
        // Überprüfe, ob man so die Wurzel ziehen kann.
        root = number.sqrtAndRemainder();
        if(root[1].compareTo(BigInteger.ZERO) != 0) {
            BigInteger check = prim.sqrt();
            while(root[0].compareTo(number) != 0){
                check = check.add(BigInteger.ONE);
                root[0] = math.modExponentiation(check, BigInteger.TWO, prim);

                if(check.compareTo(prim) == 0)
                    throw new Exception("Something went wrong. SqrRoot is out of Fields");
            }
            root[0] = check;
        }
        return root[0];
    }

    private BigInteger polynomize(BigInteger number){
        BigInteger sum = BigInteger.ZERO;
        for(int i = 0; i < polynom.size(); i++){
            sum = math.modAddition(sum, math.modExponentiation(number, BigInteger.valueOf(i).multiply(polynom.get(i)), prim), prim);
        }
        return sum;
    }


    @Override
    public BigInteger add(BigInteger sum1, BigInteger sum2) {
        BigInteger temp1 = sum1, temp2 = sum2;
        if(polynom.size() > 0){
            temp1 = polynomize(temp1);
            temp2 = polynomize(temp2);
        }

        return math.modAddition(temp1,temp2,prim);
    }

    @Override
    public BigInteger subtract(BigInteger sum1, BigInteger sum2) {
        BigInteger temp1 = sum1, temp2 = sum2;
        if(polynom.size() > 0){
            temp1 = polynomize(temp1);
            temp2 = polynomize(temp2);
        }
        return math.modSubtraction(temp1, temp2, prim);
    }

    @Override
    public BigInteger multiply(BigInteger sum1, BigInteger sum2) {
        BigInteger temp1 = sum1, temp2 = sum2;
        if(polynom.size() > 0){
            temp1 = polynomize(temp1);
            temp2 = polynomize(temp2);
        }
        return math.modMultiplication(temp1, temp2, prim);
    }

    @Override
    public BigInteger divide(BigInteger sum1, BigInteger sum2) {
        BigInteger temp1 = sum1, temp2 = sum2;
        if(polynom.size() > 0){
            temp1 = polynomize(temp1);
            temp2 = polynomize(temp2);
        }
        return math.modDivision(temp1,temp2,prim);
    }

    @Override
    public BigInteger pow(BigInteger sum1, BigInteger sum2) {
        BigInteger temp1 = sum1, temp2 = sum2;
        if(polynom.size() > 0){
            temp1 = polynomize(temp1);
            temp2 = polynomize(temp2);
        }
        return math.modExponentiation(temp1, temp2, prim);
    }

/*
    public int[] irreduciblePolynom(int number){
        int[] array;
        switch(number) {
            case 0: 
                break;

            default:
                break;
        }
    }
*/

    private boolean checkPrime(BigInteger prim, int trials){
        BigInteger rndNumber;
        BigInteger temp;
        int tryCounter = trials;
        int success = 0;
        
        do{
            do {
                rndNumber = math.random(prim);
                temp = math.gcdExtended(rndNumber, prim); //ggT(rndNumber, prim);
                //Falls eine Zahl genommen wurde, dass diese Zahl teilen kann.
                if(temp.compareTo(rndNumber) == 0) {
                    return false;
                }

            } while (temp.compareTo(BigInteger.valueOf(1)) != 0);

            BigInteger primExponent = prim.subtract(BigInteger.valueOf(1));
            rndNumber = math.modExponentiation(rndNumber, primExponent, prim); //rndNumber.modPow(primExponent, prim);

            if(rndNumber.compareTo(BigInteger.valueOf(1)) == 0){
                success++;
            }
        } while(tryCounter-- != 0);

        if(success >= (trials/2))
            return true;
        else
            return false;
    }
}
