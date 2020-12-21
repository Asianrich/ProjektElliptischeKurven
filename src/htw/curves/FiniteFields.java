package htw.curves;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
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

    private BigInteger generateNumberRange(BigInteger biginteger){
        Random rnd = new Random();
        BigInteger rndNumber =  new BigInteger(biginteger.bitLength(), rnd);
        if(rndNumber.compareTo(biginteger) >= 0)
        {
            rndNumber = rndNumber.mod(biginteger);
        }
        return rndNumber;
    }
    private boolean checkPrime(BigInteger prim){
        BigInteger rndNumber;

        do {
            rndNumber = generateNumberRange(prim);
        } while (ggT(rndNumber, prim) != BigInteger.valueOf(1));


        if(rndNumber.modPow(prim.subtract(BigInteger.valueOf(1)), prim) != BigInteger.valueOf(1)){
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

    public boolean isPrimRoot(BigInteger g, BigInteger p){
        LinkedList<BigInteger> list = new LinkedList<>();
        for(BigInteger i = BigInteger.ZERO; i.compareTo(p.subtract(BigInteger.ONE)) > 0; i = i.add(BigInteger.ONE)){
            BigInteger tmp = g.modPow(i, p);
            //System.out.println(tmp);
            list.add(tmp);
        }
        /*Arrays.sort(result);
        for(int i = 0; i < p-1; i++){
            //System.out.println(result[i]);
            if(result[i] != i+1)
                return false;
        }*/
        return true;
    }

}
