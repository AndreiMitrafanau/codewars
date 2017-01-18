/*
Given two strings s1 and s2, we want to visualize how different the two strings are.
We will only take into account the lowercase letters (a to z).
First let us count the frequency of each lowercase letters in s1 and s2.
        s1 = "A aaaa bb c"
        s2 = "& aaa bbb c d"

        s1 has 4 'a', 2 'b', 1 'c'
        s2 has 3 'a', 3 'b', 1 'c', 1 'd'

So the maximum for 'a' in s1 and s2 is 4 from s1; the maximum for 'b' is 3 from s2.
In the following we will not consider letters when the maximum of their occurrences is less than or equal to 1.
We can resume the differences between s1 and s2 in the following string:
"1:aaaa/2:bbb"
where 1 in 1:aaaa stands for string s1 and aaaa because the maximum for a is 4.
In the same manner 2:bbb stands for string s2 and bbb because the maximum for b is 3.
The task is to produce a string in which each lowercase letters of s1 or s2 appears as many times as its maximum
if this maximum is strictly greater than 1; these letters will be prefixed by the number of the string where they appear
with their maximum value and :. If the maximum is in s1 as well as in s2 the prefix is =:.
In the result, substrings will be in decreasing order of their length and when they have the same length sorted alphabetically
(more precisely sorted by codepoint); the different groups will be separated by '/'.

        Hopefully other examples can make this clearer.

        s1 = "my&friend&Paul has heavy hats! &"
        s2 = "my friend John has many many friends &"
        mix(s1, s2) --> "2:nnnnn/1:aaaa/1:hhh/2:mmm/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

        s1 = "mmmmm m nnnnn y&friend&Paul has heavy hats! &"
        s2 = "my frie n d Joh n has ma n y ma n y frie n ds n&"
        mix(s1, s2) --> "1:mmmmmm/=:nnnnnn/1:aaaa/1:hhh/2:yyy/2:dd/2:ff/2:ii/2:rr/=:ee/=:ss"

        s1="Are the kids at home? aaaaa fffff"
        s2="Yes they are here! aaaaa fffff"

        mix(s1, s2) --> "=:aaaaaa/2:eeeee/=:fffff/1:tt/2:rr/=:hh"

https://www.codewars.com/kata/strings-mix/train/java
*/

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Kata007 {

    public static String mix(String s1, String s2) {
        String str1 = extractLowerCase(s1);
        String str2 = extractLowerCase(s2);
        Map<String, Integer> firstWithMinOcc = calcOccurrences(str1);
        Map<String, Integer> secondWithMinOcc = calcOccurrences(str2);
        Map<String, Integer> first = removeOneOccurrence(firstWithMinOcc);
        Map<String, Integer> second = removeOneOccurrence(secondWithMinOcc);
        Map<String, String> collect = mergeMaps(first, second);
        List<String> strings = collect.entrySet().stream()
                .map(e -> e.getValue() + e.getKey()).collect(toList());

        strings.sort((o1, o2) -> {
            return (o1.length() > o2.length()) ? -1 :
                    (o1.length() < o2.length()) ? 1 :
                            o1.compareTo(o2);
        });

        String str = strings.stream().collect(Collectors.joining(""));
        return str.isEmpty() ? "" : str.substring(0, str.length() - 1);
    }

    private static Map<String, String> mergeMaps(Map<String, Integer> first, Map<String, Integer> second) {
        Map<String, String> result = new TreeMap<>();
        first.entrySet().forEach(e -> {
            String key = e.getKey();
            String str = buildStr(key, e.getValue());
            if (second.get(key) != null) {
                if (second.get(key) > e.getValue()) {
                    result.put(buildStr(key, second.get(key)), "2:");
                } else if (second.get(key) < e.getValue()) {
                    result.put(buildStr(key, first.get(key)), "1:");
                } else {
                    result.put(buildStr(key, second.get(key)), "=:");
                }
            } else result.put(str, "1:");
        });
        second.entrySet().forEach(e -> {
            String key = e.getKey();
            if (first.get(key) == null) {
                result.put(buildStr(key, e.getValue()), "2:");
            }
        });
        return result;
    }

    private static String buildStr(String ch, Integer n) {
        return Stream.generate(() -> ch).limit(n)
                .collect(Collectors.joining("", "", "/"));
    }

    private static Map<String, Integer> calcOccurrences(String str) {
        String[] split1 = str.split("");
        Map<String, Integer> map = new TreeMap<>();
        for (String ch : split1) {
            map.merge(ch, 1, (oldVal, newVal) -> oldVal + newVal);
        }
        return map;
    }

    private static String extractLowerCase(String str) {
        return str.replaceAll("[^a-z]+", "");
    }

    private static Map<String, Integer> removeOneOccurrence(Map<String, Integer> map) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
