package main.java.y24;

import java.util.Arrays;

import main.java.AdventDay;

public class Day04 {
    public static void main(String[] args) {
        var day3 = new AdventDay<Integer>(2024, 4, true);
        // MMMSXXMASM
        // MSAMXMSMSA
        // AMXSXMAAMM
        // MSAMASMSMX
        // XMASAMXAMM
        // XXAMMXXAMA
        // SMSMSASXSS
        // SAXAMASAAA
        // MAMMMXMMMM
        // MXMXAXMASX
        final String[] inputLinesLeftToRight = day3.getInputLines();
        final int size = inputLinesLeftToRight.length;
        final char[] linesUpToDown = new char[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                linesUpToDown[i] = inputLinesLeftToRight[i].charAt(i);
            }
        }

        System.out.println("Up/down: " + Arrays.toString(linesUpToDown));
    }

    @SuppressWarnings("unused")
    private static void diagonalDownRight(int size, char[] linesUpToDown, String[] inputLinesLeftToRight) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                linesUpToDown[i] = inputLinesLeftToRight[i].charAt(i);
            }
        }
    }
}