import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Kata005Test {

    private static void testing(boolean actual, boolean expected) {
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests scramble");
        testing(Kata005.scramble("rkqodlw", "world"), true);
        testing(Kata005.scramble("cedewaraaossoqqyt", "codewars"), true);
        testing(Kata005.scramble("katas", "steak"), false);
        testing(Kata005.scramble("scriptjavx", "javascript"), false);
        testing(Kata005.scramble("scriptingjava", "javascript"), true);
        testing(Kata005.scramble("scriptsjava", "javascripts"), true);
        testing(Kata005.scramble("javscripts", "javascript"), false);
        testing(Kata005.scramble("aabbcamaomsccdd", "commas"), true);
        testing(Kata005.scramble("commas", "commas"), true);
        testing(Kata005.scramble("sammoc", "commas"), true);
    }
}