import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertEquals;

// All test from codewars site for Kata006

@RunWith(Parameterized.class)
public class FibonacciTest {

    @Parameters(name = "{0}")
    public static Collection<Object[]> tests() {
        Builder tests = new Builder();
        tests.add(0);
        tests.add(1);
        tests.add(2);
        tests.add(3);
        tests.add(4);
        tests.add(5);
        tests.add(-6);
        tests.add(-96);
        tests.add(1000);
        tests.add(1001);
        Random rnd = new Random();
        rnd.longs(10, -100, 0).forEach(tests::add);
        rnd.longs(1, 10000, 100000).forEach(tests::add);
        rnd.longs(1, 1000000, 1500000).forEach(tests::add);
        return tests.args;
    }

    private static class Builder {
        final Collection<Object[]> args = new ArrayList<>();

        void add(long n) {
            add(BigInteger.valueOf(n));
        }

        void add(BigInteger n) {
            args.add(new Object[]{n});
        }
    }

    @Parameter
    public BigInteger n;

    @Test
    public void testFib() {
        BigInteger found;
        try {
            found = Kata006.fib(n);
        } catch (Throwable e) {
            // see https://github.com/Codewars/codewars.com/issues/21
            throw new AssertionError("exception during test: " + e, e);
        }
        assertEquals(fib(n), found);
    }

    private static BigInteger fib(BigInteger n) {

        boolean m = n.signum() < 0;
        if (m) n = n.negate();

        if (n.equals(BigInteger.ZERO)) return BigInteger.ZERO;
        if (n.equals(BigInteger.ONE)) return BigInteger.ONE;

        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;
        BigInteger c = null;

        int i = n.bitLength() - 2;
        for (; ; ) {
            if (n.testBit(i)) {
                c = b.add(a);
                a = b;
                b = c;
            }
            if (i-- == 0) break;
            c = a.shiftLeft(1).add(b).multiply(b); // (a * 2 + b) * b
            a = b.pow(2).add(a.pow(2)); // b^2 + a^2
            b = c;
        }

        return m && !n.testBit(0) ? b.negate() : b;
    }

}