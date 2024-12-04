package main.java.y24;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
        final String[] linesLeftToRight = day3.getInputLines();

        char[] charsDown = getCharsDown(linesLeftToRight);
        char[] charsUp = reverse(charsDown);
        char[] charsRight = getCharsRight(linesLeftToRight);
        char[] charsLeft = reverse(charsRight);
        char[] charsDiagonalDownRight = diagonalDownRight(linesLeftToRight);

        System.out.println("Chars down: " + Arrays.toString(charsDown));
        System.out.println("Chars up:   " + Arrays.toString(charsUp));
        System.out.println("Chars right:   " + Arrays.toString(charsRight));
        System.out.println("Chars left:   " + Arrays.toString(charsLeft));
        System.out.println("Chars diagonal down right:   " + Arrays.toString(charsDiagonalDownRight));

        System.out.println("Matches in charsDiagonalDownRight: " + findMatches(charsDiagonalDownRight));
    }

    private static long findMatches(char[] array) {
        String line = String.valueOf(array);
        return Stream.of(Pattern.compile("XMAS"), Pattern.compile("SAMX"))
                .flatMap(p -> p.matcher(line).results())
                .count();
    }

    private static char[] getCharsRight(String[] linesLeftToRight) {
        int size = linesLeftToRight.length;
        char[] res = new char[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i] = linesLeftToRight[0].charAt(i);
            }
        }
        return res;
    }

    private static char[] getCharsDown(String[] linesLeftToRight) {
        int size = linesLeftToRight.length;
        char[] res = new char[size];
        for (int i = 0; i < size; i++) {
            res[i] = linesLeftToRight[i].charAt(0);
        }

        return res;
    }

    private static char[] diagonalDownRight(String[] inputLinesLeftToRight) {
        int size = inputLinesLeftToRight.length;
        char[] res = new char[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i] = inputLinesLeftToRight[i].charAt(i);
            }
        }
        return res;
    }

    private static char[] reverse(char[] inputArr) {
        int size = inputArr.length;
        char[] reversed = new char[size];

        int startIndex = size - 1; // Starting index
        for (int i = startIndex; i >= 0; i--) {
            reversed[startIndex - i] = inputArr[i];
        }

        return reversed;
    }
}