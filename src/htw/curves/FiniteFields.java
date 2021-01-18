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
    public BigInteger squareRoot(BigInteger number) {
        return null;
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
