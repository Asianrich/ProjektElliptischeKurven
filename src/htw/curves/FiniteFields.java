package htw.curves;

import java.math.BigInteger;
import java.util.Random;

public class FiniteFields implements Fields {

    @Override
    public BigInteger generatePrime() {
        BigInteger rndPrime = BigInteger.valueOf(4); //Irgendeine Zahl, das nicht Prim ist.
        boolean check = false;
        while(checkPrime(rndPrime)){
            rndPrime = generateNumber(8192);
        }
        return rndPrime;
    }

    private BigInteger generateNumber(int len){
        Random rnd = new Random();
        BigInteger rndNumber =  new BigInteger(len, rnd);
        return rndNumber;
    }
    private boolean checkPrime(BigInteger prim){
        Random rnd = new Random();

        BigInteger rndNumber =  new BigInteger(prim.bitLength(), rnd);
        if(rndNumber.compareTo(prim) >= 0)
        {
            rndNumber = rndNumber.mod(prim);
        }

        if(ggT(rndNumber, prim) != BigInteger.valueOf(1)){
            return false;
        }
        return true;
    }

    private BigInteger ggT(BigInteger a, BigInteger b){
        BigInteger solution = BigInteger.valueOf(2);
        BigInteger n = a, r = b;
        while(n.compareTo(BigInteger.valueOf(0)) != 0){
            solution = r.mod(n);
            r = n;
            n = solution;
        }

        return solution;
    }

}
