package main.java.y24;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.AdventDay;

public class Day03 {
    public static void main(String[] args) {
        var day3 = new AdventDay<Integer>(2024, 3, false);
        final String[] inputLines = day3.getInputLines();

        day3.doAnswer(1, () -> {
            var functionList = new ArrayList<String>();
            Pattern pattern = Pattern.compile("mul\\(\\d*,\\d*\\)", Pattern.CASE_INSENSITIVE);

            for (String line : inputLines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    // store each function string; ex. "mul(25, 135)"
                    functionList.add(matcher.group());
                }
            }

            var sum = 0;
            for (String function : functionList) {
                // parse out to an array of elements; ex. "[25, 135]"
                String[] nums = function
                        .replace("mul(", "")
                        .replace(")", "")
                        .split((","));

                sum += Integer.decode(nums[0]) * Integer.decode(nums[1]);
            }

            return sum;
        });

        day3.doAnswer(2, () -> {
            var functionList = new ArrayList<String>();
            Pattern pattern = Pattern.compile("mul\\(\\d*,\\d*\\)|do\\(\\)|don't\\(\\)", Pattern.CASE_INSENSITIVE);

            for (String line : inputLines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    // store each function string; ex. "mul(25, 135)"
                    functionList.add(matcher.group());
                }
            }

            var sum = 0;
            var enabled = true;

            // mul(25, 135)
            // don't()
            // mul(15, 236)
            // do()
            // mul(5, 532)

            for (String function : functionList) {
                if (function.contains("don't")) {
                    enabled = false;
                    continue;
                }

                if (function.contains("do()")) {
                    enabled = true;
                    continue;
                }

                if (enabled) {
                    sum += calculate(function);
                }
            }

            return sum;
        });
    }

    private static long calculate(String function) {
        if (!function.startsWith("mul")) {
            return 0L;
        }
        // parse out to an array of elements; ex. "[25, 135]"
        String[] nums = function
                .replace("mul(", "")
                .replace(")", "")
                .split((","));

        return Integer.decode(nums[0]) * Integer.decode(nums[1]);
    }
}
