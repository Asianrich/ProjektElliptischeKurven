package htw.curves;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FiniteFields implements Fields {

    @Override
    public BigInteger generatePrime(int len, int trials) {
        BigInteger rndPrime = BigInteger.valueOf(4); //Irgendeine Zahl, das nicht Prim ist.
        ModularArithmetic mA = new BasicTheoreticMethods();
        while(!checkPrime(rndPrime, trials)){
            rndPrime = mA.random(len);
        }
        return rndPrime;
    }

    private boolean checkPrime(BigInteger prim, int trials){
        BigInteger rndNumber;
        BigInteger temp;
        int tryCounter = trials;
        int success = 0;
        ModularArithmetic btm = new BasicTheoreticMethods();
        
        do{
            do {
                rndNumber = btm.random(prim);
                temp = btm.gcdExtended(rndNumber, prim); //ggT(rndNumber, prim);
                //Falls eine Zahl genommen wurde, dass diese Zahl teilen kann.
                if(temp.compareTo(rndNumber) == 0) {
                    return false;
                }

            } while (temp.compareTo(BigInteger.valueOf(1)) != 0);

            BigInteger primExponent = prim.subtract(BigInteger.valueOf(1));
            rndNumber = btm.modExponentiation(rndNumber, primExponent, prim); //rndNumber.modPow(primExponent, prim);

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
