import static org.junit.Assert.*;

import org.junit.Test;


public class Test2 {
    public void testflik() {
        int a = 1, b = 1;
        int c = 3, d = 3;
        assertTrue(Flik.isSameNumber(a,b));
        assertTrue(Flik.isSameNumber(d,c));
        assertTrue(Flik.isSameNumber(100,100));
        assertTrue(Flik.isSameNumber(500,500));
    }
    public static void main(String[] args){

    }
}
