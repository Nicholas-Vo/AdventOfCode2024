package main.java.y24;

import main.java.AdventDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            int size = lines.length;
            char[][] grid = new char[size][];

            // Populate grid
            for (int i = 0; i < size; i++) {
                grid[i] = lines[i].toCharArray();
            }

            var whatAWeAt = 0;
            long sum = 0;

            // Loop through grid
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < grid[row].length; column++) {
                    if (grid[row][column] != 'A') {
                        continue;
                    }

                    boolean x = checkForX(grid, row, column);
                    if (x) {
                        sum++;
                    }

                    //System.out.println("A #" + ++whatAWeAt + " Is X: " + x + "\n");
                }
            }
            return sum;
        });
    }

    private static boolean checkForX(char[][] grid, int row, int column) {
        char tLeft = getChar(grid, /* row */ row - 1, /* column */ column - 1);
        char tRight = getChar(grid, /* row */ row - 1, /* column */ column + 1);
        char bLeft = getChar(grid, /* row */ row + 1, /* column */ column - 1);
        char bRight = getChar(grid, /* row */ row + 1, /* column */ column + 1);

        boolean diag1Satisfied = (tLeft == 'M' && bRight == 'S') || (tLeft == 'S' && bRight == 'M');
        boolean diag2Satisfied = (bLeft == 'M' && tRight == 'S') || (bLeft == 'S' && tRight == 'M');

        return diag1Satisfied && diag2Satisfied;
    }

    /* Part 2 */
    private static char getChar(char[][] grid, int row, int col) {
        char BAD_INDEX = '0';
        if (row < 0 || col < 0) return BAD_INDEX;
        if (row >= grid.length) return BAD_INDEX;
        if (col >= grid[row].length) return BAD_INDEX;
        return grid[row][col];
    }

    /* Part 1 */
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

    /* Part 1 */
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
     *
     * @param line a line
     * @return number of found XMAS strings
     */
    private static long check(String line) {
        return getMatches(line) + getMatches(new StringBuilder(line).reverse().toString());
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
