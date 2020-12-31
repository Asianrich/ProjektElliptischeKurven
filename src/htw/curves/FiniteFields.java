package htw.curves;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FiniteFields implements Fields {

    @Override
    public BigInteger generatePrime(int len, int trials) {
        BigInteger rndPrime = BigInteger.valueOf(4); //Irgendeine Zahl, das nicht Prim ist.
        boolean check = false;
        while(!checkPrime(rndPrime, trials)){
            rndPrime = generateNumber(len);
        }
        return rndPrime;
    }

    private BigInteger generateNumber(int len){
        Random rnd = new Random();
        BigInteger rndNumber;
        do {
            rndNumber =  new BigInteger(len, rnd);
        }while(rndNumber.compareTo(BigInteger.valueOf(1)) < 1);

        return rndNumber;
    }

    private BigInteger generateNumberRange(BigInteger biginteger){
        Random rnd = new Random();
        BigInteger rndNumber;

        do {
            rndNumber  =  new BigInteger(biginteger.bitLength(), rnd);
            if(rndNumber.compareTo(biginteger) >= 0)
            {
                rndNumber = rndNumber.mod(biginteger);
            }
        }while(rndNumber.compareTo(BigInteger.valueOf(1)) < 1);


        return rndNumber;
    }
    private boolean checkPrime(BigInteger prim, int trials){
        BigInteger rndNumber;
        BigInteger temp;
        int tryCounter = trials;
        int success = 0;
        do{
            do {
                rndNumber = generateNumberRange(prim);
                temp = ggT(rndNumber, prim);
                //Falls eine Zahl genommen wurde, dass diese Zahl teilen kann.
                if(temp.compareTo(rndNumber) == 0) {
                    return false;
                }

            } while (temp.compareTo(BigInteger.valueOf(1)) != 0);

            BigInteger primExponent = prim.subtract(BigInteger.valueOf(1));
            rndNumber = rndNumber.modPow(primExponent, prim);

            if(rndNumber.compareTo(BigInteger.valueOf(1)) == 0){
                success++;
            }
        } while(tryCounter-- != 0);

        if(success >= (trials/2))
            return true;
        else
            return false;
    }

    private BigInteger ggT(BigInteger a, BigInteger b){
        BigInteger temp = BigInteger.valueOf(2);
        BigInteger n = a, r = b;
        while(n.compareTo(BigInteger.valueOf(0)) != 0){
            temp = r.mod(n);
            r = n;
            n = temp;
        }

        return r;
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
