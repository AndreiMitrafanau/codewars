/*
My little sister came back home from school with the following task:
given a squared sheet of paper she has to cut it in pieces which,
when assembled, give squares the sides of which form an increasing sequence of numbers.
At the beginning it was lot of fun but little by little we were tired of seeing the pile of torn paper.
So we decided to write a program that could help us and protects trees.
Task
Given a positive integral number n, return a strictly increasing sequence
(list/array/string depending on the language) of numbers, so that the sum of the squares is equal to n².

If there are multiple solutions (and there will be), return the result with the largest possible values:

Examples

decompose(11) must return [1,2,4,10].
Note that there are actually two ways to decompose 11², 11² = 121 = 1 + 4 + 16 + 100 = 1² + 2² + 4² + 10²
but don't return [2,6,9], since 9 is smaller than 10.
For decompose(50) don't return [1, 1, 4, 9, 49] but [1, 3, 5, 8, 49]
since [1, 1, 4, 9, 49] doesn't form a strictly increasing sequence.

Note

Neither [n] nor [1,1,1,…,1] are valid solutions. If no valid solution exists, return nil, null,
Nothing, None (depending on the language) or "" (Java, C#) or {} (C++).

The function "decompose" will take a positive integer n and return the decomposition of N = n² as:

"x1 ... xk"
Hint
Very often xk will be n-1.

 */

public class Kata010 {
    public String decompose(long n) {
        String result = "";
        long sq = n * n;
        while ((n = n - 1) > 0 && sq != 0) {
            if (n * n <= sq && check(n, sq - n * n)) {
                result = n + " " + result;
                sq = sq - n * n;
            }
        }
        if (sq != 0) return "";
        return result.substring(0, result.length() - 1);
    }

    static boolean check(long n, long sq) {
        while ((n = n - 1) > 0 && sq != 0) {
            if (n * n <= sq && check(n, sq - n * n)) {
                sq = sq - n * n;
            }
        }
        return sq == 0;
    }
}
