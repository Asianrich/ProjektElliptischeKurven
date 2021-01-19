package htw.curves;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FiniteFields implements Fields {
    private BigInteger prim;
    private ModularArithmetic math;


    public  FiniteFields() {
        math = new BasicTheoreticMethods();
    }


    public FiniteFields(BigInteger prim) {
        this.prim = prim;
        math = new BasicTheoreticMethods();
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

    @Override
    public BigInteger add(BigInteger sum1, BigInteger sum2) {
        return math.modAddition(sum1,sum2,prim);
    }

    @Override
    public BigInteger subtract(BigInteger sum1, BigInteger sum2) {
        return math.modSubtraction(sum1, sum2, prim);
    }

    @Override
    public BigInteger multiply(BigInteger sum1, BigInteger sum2) {
        return math.modMultiplication(sum1, sum2, prim);
    }

    @Override
    public BigInteger divide(BigInteger sum1, BigInteger sum2) {
        return math.modDivision(sum1,sum2,prim);
    }

    @Override
    public BigInteger pow(BigInteger sum1, BigInteger sum2) {
        return math.modExponentiation(sum1, sum2, prim);
    }

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
