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
        if(prim.compareTo(BigInteger.TWO) == 0){
            this.prim = prim;
            return;
        }

        if(checkPrime(prim, 5))
            this.prim = prim;
    }

    @Override
    public BigInteger getPrim(){
        return this.prim;
    }

    public FiniteFields(BigInteger prim, int gradient) {
        math = new BasicTheoreticMethods();
        if(prim.compareTo(BigInteger.TWO) == 0){
            this.prim = prim;
        }
        else if(checkPrime(prim, 5))
            this.prim = prim;

        createIrreducible(gradient);
    }

    private void createIrreducible(int gradient){
        if(gradient <= 0)
            return;
        switch(gradient){
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


    /*
    * Generate a Prime number
    * @len = bit-Length
    * @trials = how many times you want to check
     */
    @Override
    public BigInteger generatePrime(int len, int trials) {
        BigInteger rndPrime = BigInteger.valueOf(4); //Irgendeine Zahl, das nicht Prim ist.
        while(!checkPrime(rndPrime, trials)){
            rndPrime = math.random(len);
        }
        return rndPrime;
    }

    /**
     * SquareRoot in Finite Field
     * @param number
     * @return
     * @throws Exception
     */
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

    /**
     * Addition in Finite Field
     * @param sum1
     * @param sum2
     * @return
     */
    @Override
    public BigInteger add(BigInteger sum1, BigInteger sum2) {
        return math.modAddition(sum1,sum2,prim);
    }

    /**
     * Subtraktion in finite Field
     * @param subtrahend
     * @param minuend
     * @return
     */
    @Override
    public BigInteger subtract(BigInteger subtrahend, BigInteger minuend) {
        return math.modSubtraction(subtrahend, minuend, prim);
    }

    /**
     * Multiplication in Finite Field
     * @param fact1
     * @param fact2
     * @return
     */
    @Override
    public BigInteger multiply(BigInteger fact1, BigInteger fact2) {
        return math.modMultiplication(fact1, fact2, prim);
    }

    /***
     * Division in Finite Field
     * @param divisor
     * @param dividend
     * @return
     */
    @Override
    public BigInteger divide(BigInteger divisor, BigInteger dividend) {
        return math.modDivision(divisor,dividend,prim);
    }

    /**
     * pow in in Finite Field
     * @param num
     * @param exponent
     * @return
     */
    @Override
    public BigInteger pow(BigInteger num, BigInteger exponent) {
        return math.modExponentiation(num, exponent, prim);
    }


    /**
     * polynomial Addition in Finite Field
     * @param sum1
     * @param sum2
     * @return
     */
    @Override
    public ArrayList<BigInteger> add(ArrayList<BigInteger> sum1, ArrayList<BigInteger> sum2) {
        return math.modAddition(sum1, sum2, polynom, prim);
    }

    /**
     * Polynomial Subtraction in Finite Field
     * @param subtrahend
     * @param minuend
     * @return
     */
    @Override
    public ArrayList<BigInteger> subtract(ArrayList<BigInteger> subtrahend, ArrayList<BigInteger> minuend) {
        return math.modSubtraction(subtrahend, minuend, polynom, prim);
    }

    /**
     * Multiplication in in Finite Field
     * @param factor1
     * @param factor2
     * @return
     */
    @Override
    public ArrayList<BigInteger> multiply(ArrayList<BigInteger> factor1, ArrayList<BigInteger> factor2) {
        return math.modMultiply(factor1, factor2, polynom, prim);
    }

    /**
     * Division in Finite Field
     * @param divisor
     * @param dividend
     * @return
     */
    @Override
    public ArrayList<BigInteger> divide(ArrayList<BigInteger> divisor, ArrayList<BigInteger> dividend) {
        return math.modDivision(divisor, dividend, polynom, prim);
    }

    // Prüft ob es eine Primzahl ist
    private boolean checkPrime(BigInteger prim, int trials){
        BigInteger rndNumber;
        BigInteger temp;
        int tryCounter = trials;
        int success = 0;
        
        do{
            //generiere eine Zahl kleiner als primzahl und überprüfe nach ob es einen Teiler hat
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

            //F-Zeugen erhöhen
            if(rndNumber.compareTo(BigInteger.valueOf(1)) == 0){
                success++;
            }
        } while(tryCounter-- != 0);

        //F-Zeuge > F-Lügner
        if(success >= (trials/2))
            return true;
        else
            return false;
    }
}
