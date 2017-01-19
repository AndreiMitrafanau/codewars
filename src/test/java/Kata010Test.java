import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Kata010Test {
    @Test
    public void test1() {
        Kata010 d = new Kata010();
        long n = 11;
        assertEquals("1 2 4 10", d.decompose(n));
    }
}