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
            return 1;
        });
    }
}
