/*
        Write function scramble(str1,str2) that returns true
        if a portion of str1 characters can be rearranged to match str2,
        otherwise returns false.

        For example:
            str1 is 'rkqodlw' and str2 is 'world' the output should return true.
            str1 is 'cedewaraaossoqqyt' and str2 is 'codewars' should return true.
            str1 is 'katas' and str2 is 'steak' should return false.

        Only lower case letters will be used (a-z). No punctuation or digits will be included.
        Performance needs to be considered

        https://www.codewars.com/kata/scramblies/train/java
*/

public class Kata005 {
    public static boolean scramble(String str1, String str2) {
        String[] strings = str2.split("");
        for (String s : strings) {
            if (str1.contains(s)) {
                str1 = str1.replaceFirst(s, "");
            } else {
                return false;
            }
        }
        return true;
    }
}

