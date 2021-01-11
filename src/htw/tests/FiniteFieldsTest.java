package htw.tests;
import htw.curves.Fields;
import htw.curves.FiniteFields;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigInteger;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class FiniteFieldsTest {

    @Test
    public void FermatTest_4_bit() throws IOException {

        FileWriter myFile = new FileWriter("%username%\\Desktop\\prim.txt");
        for(int i = 0; i < 4; i++){
            StringBuilder sb = new StringBuilder();
            BigInteger prim = getPrim(4, 4);
            sb.append("Primzahl ist ");
            sb.append(prim);
            sb.append("\n");
            myFile.write(sb.toString());
        }
        myFile.close();


        Assert.assertTrue(true);
    }
    @Test
    public void FermatTest_128_bit(){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(128, 5);
        BigInteger test = prim;
        Assert.assertTrue(true);
    }

    @Test
    public void FermatTest_512_bit(){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(512, 5);
        BigInteger test = prim;
        Assert.assertTrue(true);
    }

    @Test
    public void FermatTest_1024_bit(){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(1024, 5);
        BigInteger test = prim;
        Assert.assertTrue(true);
    }

    @Test
    public void FermatTest_2048_bit(){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(2048, 5);
        BigInteger test = prim;
        Assert.assertTrue(true);
    }

    @Test
    public void FermatTest_4096_bit(){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(4096, 4);
        BigInteger test = prim;
        Assert.assertTrue(true);
    }

    @Test
    public void FermatTest_8192_bit() throws IOException {
        FileWriter myFile = new FileWriter("%username%\\Desktop\\prim.txt");


        for(int i = 0; i < 4; i++){
            StringBuilder sb = new StringBuilder();
            BigInteger prim = getPrim(8192, 4);
            sb.append("Primzahl ist ");
            sb.append(prim);
            sb.append("\n");
            myFile.write(sb.toString());
        }

        myFile.close();
        Assert.assertTrue(true);
    }

    private BigInteger getPrim(int len, int trials){
        FiniteFields Field = new FiniteFields();
        BigInteger prim = Field.generatePrime(len, trials);
        return prim;
    }
}
