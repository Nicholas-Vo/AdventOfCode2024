package main.java.y24;

import main.java.AdventDay;

import java.util.Arrays;

public class Day02 {

    public static void main(String[] args) {
        var day2 = new AdventDay<Integer>(2024, 2, /* Use test file */ false);
        final String[] inputLines = day2.getInputLines();

        day2.doAnswer(/* part */ 1, () -> {
            int sum = 0;
            for (String report : inputLines) {
                var isSafe = check(report);

                if (day2.testing()) {
                    System.out.print(" is " + (isSafe ? "safe!" : "unsafe!") + "\n");
                }

                if (isSafe) {
                    sum++;
                }
            }

            return sum;
        });
    }

    private static boolean check(String report) {
        int[] nums = Arrays.stream(report.split(" ")).mapToInt(Integer::parseInt).toArray();

        int increasing = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            int curr = nums[i];
            int next = nums[i + 1];

            // increasing is true
            if (increasing == 1 && next < curr) {
                return false;
            }

            // decreasing
            if (increasing == 0 && next > curr) {
                return false;
            }

            int diff = Math.abs(next - curr);
            if (diff > 3 || diff < 1) {
                return false;
            }

            increasing = next > curr ? 1 : 0;
        }
        return true;
    }

}
