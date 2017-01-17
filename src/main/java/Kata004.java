/*
 data and data1 are two strings with rainfall records of a few cities for months from January to December.
 The records of towns are separated by \n. The name of each town is followed by :.
 data and towns can be seen in "Your Test Cases:".

 Task:
        - function: mean(town, strng) should return the average of rainfall
          for the city `town` and the `strng` `data` or `data1`.
        - function: variance(town, strng) should return the variance of rainfall
          for the city `town` and the `strng` `data` or `data1`.

 Examples:
        mean("London", data), 51.19(9999999999996)
        variance("London", data), 57.42(833333333374)

 Notes:
        - if functions `mean`or `variance` have as parameter `town` a city which has no records return `-1`

        - Don't truncate or round: the tests will pass if `abs(your_result - test_result) <= 1e-2`
        - or `abs((your_result - test_result) / test_result) <= 1e-6` depending on the language. (see function assertFuzzyEquals).

        - <http://www.mathsisfun.com/data/standard-deviation.html>
        - `data` and `data1` are adapted from <http://www.worldclimate.com>

   https://www.codewars.com/kata/56a32dd6e4f4748cc3000006/train/java
*/


import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

public class Kata004 {

    public static double mean(String town, String strng) {
        if (!strng.contains(town + ":")) return -1.0;

        List<Double> tempValues = extract(town, strng);
        return tempValues.stream().collect(averagingDouble(n -> n));
    }


    public static double variance(String town, String strng) {
        if (!strng.contains(town + ":")) return -1.0;

        double avg = mean(town, strng);
        List<Double> collect = extract(town, strng);
        Double sumOfSquares = collect.stream()
                .map(d -> Math.pow(d, 2))
                .reduce(0D, (a, b) -> a + b);
        return sumOfSquares / collect.size() - Math.pow(avg, 2);
    }

    protected static List<Double> extract(String town, String strng) {
        Pattern p = Pattern.compile(town + ".*");
        Matcher m = p.matcher(strng);
        String result = "";
        while (m.find()) {
            result = result.concat(m.group());
        }

        result = result.replaceAll("[^.?,?0-9]+", "");
        String[] doubleValues = result.trim().split(",");
        return Arrays.stream(doubleValues).map(Double::parseDouble).collect(Collectors.toList());
    }
}
