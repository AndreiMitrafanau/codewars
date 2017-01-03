import org.junit.Test;

import java.util.stream.LongStream;

import static org.junit.Assert.*;

public class Kata001Test {

    // set of given tests
    @Test
    public void test1() {
        assertEquals("[[1, 1], [42, 2500], [246, 84100]]", Kata001.listSquared(1, 250));
    }

    @Test
    public void test2() {
        assertEquals("[[42, 2500], [246, 84100]]", Kata001.listSquared(42, 250));
    }

    @Test
    public void test3() {
        assertEquals("[[287, 84100]]", Kata001.listSquared(250, 500));
    }

    // set of additional tests
    @Test
    public void findDivisors() {
        long[] expected = LongStream.of(1L, 2L, 3L, 6L, 7L, 14L, 21L, 42L).toArray();
        assertArrayEquals(expected, Kata001.findDivisors(42).toArray());
    }

    @Test
    public void isSquareFail() {
        assertFalse(Kata001.isSquare(3));
    }

    @Test
    public void isSquare() {
        assertTrue(Kata001.isSquare(4));
    }
}
