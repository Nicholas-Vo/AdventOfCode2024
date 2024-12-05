package main.java.y24;

import main.java.AdventDay;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    public static void main(String[] args) {
        var day4 = new AdventDay<Long>(2024, 4, false);
        String[] lines = day4.getInputLines();

        day4.doAnswer(1, () -> {
            long sum = 0;

            // horizontally
            for (String line : lines) {
                sum += check(line); // Left/Right
            }

            // vertically
            int size = lines[0].length(); // Amount of columns
            for (int column = 0; column < size; column++) {
                var builder = new StringBuilder();
                for (String line : lines) {
                    builder.append(line.charAt(column));
                }

                sum += check(builder.toString());
            }

            return sum + diag1(lines) + diag2(lines);
        });

        day4.doAnswer(2, () -> {
            return -1L;
        });
    }

    private static long diag1(String[] lines) {
        int columns = lines[0].length();
        int size = lines.length;
        long sum = 0;

        // Top-left to bottom-right?
        for (int start = 0; start < size + columns - 1; start++) {
            var builder = new StringBuilder();
            for (int row = 0; row < size; row++) {
                int column = start - row;
                if (column >= 0 && column < columns) {
                    builder.append(lines[row].charAt(column));
                }
            }
            sum += check(builder.toString());
        }
        return sum;
    }

    private static long diag2(String[] lines) {
        int columns = lines[0].length();
        int size = lines.length;
        long sum = 0;

        // Top-right to bottom-left
        for (int start = 0; start < size + columns - 1; start++) {
            var builder = new StringBuilder();
            for (int row = 0; row < size; row++) {
                int column = start - (size - 1 - row);
                if (column >= 0 && column < columns) {
                    builder.append(lines[row].charAt(column));
                }
            }
            sum += check(builder.toString());
        }

        return sum;
    }

    /**
     * Each line must be checked in each direction;
     * this method reverses each line to do that
     * @param line a line
     * @return number of found XMAS strings
     */
    private static long check(String line) {
        return getMatches(line) + getMatches(
                new StringBuilder(line)
                        .reverse()
                        .toString()
        );
    }

    private static long getMatches(String line) {
        Matcher matcher = Pattern.compile("(?=XMAS)").matcher(line);
        long count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
