// Divisors of 42 are : 1, 2, 3, 6, 7, 14, 21, 42.
// These divisors squared are: 1, 4, 9, 36, 49, 196, 441, 1764.
// The sum of the squared divisors is 2500 which is 50 * 50, a square!
//    Given two integers m, n (1 <= m <= n)
//    we want to find all integers between m and n
//    whose sum of squared divisors is itself a square.
//    42 is such a number.
//
//   The result will be an array of arrays(in C an array of Pair),
//   each subarray having two elements,
//   first the number whose squared divisors is a square
//   and then the sum of the squared divisors.
//
//   Examples:
//
//        list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
//        list_squared(42, 250) --> [[42, 2500], [246, 84100]]
//
//        The form of the examples may change according to the language,
//        see Example Tests: for more details.

import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Kata001 {

    public static String listSquared(long m, long n) {
        LongStream range = LongStream.rangeClosed(m, n);
        Stream<String> longStream = range.mapToObj(a -> {
            long sum = findDivisors(a).map(b -> b * b).sum();
            return isSquare(sum) ? String.format("[%d, %d]", a, sum) : "";
        }).filter(str -> !str.isEmpty());

        return longStream.collect(toList()).toString();
    }

    protected static boolean isSquare(long c) {
        long tst = (long) (Math.sqrt(c));
        return tst * tst == c;
    }

    protected static LongStream findDivisors(long n) {
        return LongStream.rangeClosed(1, n).filter(x -> n % x == 0);
    }

}
