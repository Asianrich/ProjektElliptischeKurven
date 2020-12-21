package htw.tests;

import htw.curves.DiffieHellman;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class DiffieHellmanTests {

    DiffieHellman dh = new DiffieHellman(BigInteger.valueOf(101), BigInteger.valueOf(2));
    BigInteger alice = BigInteger.valueOf(88);
    BigInteger bob = BigInteger.valueOf(77);
    BigInteger key = BigInteger.valueOf(81);
    BigInteger msg1 = BigInteger.valueOf(42);

    @Test
    public void testKey(){
        dh.setAliceKey(alice);
        dh.setBobKey(bob);
        dh.commonKey();
        assertTrue(dh.getKey().equals(key));
    }

}
