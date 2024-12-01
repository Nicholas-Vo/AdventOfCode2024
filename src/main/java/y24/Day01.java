package main.java.y24;

import main.java.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        var reader = new InputReader(2024, 1);
        final String[] inputLines = reader.getInputLines();

        for (String line : inputLines) {
            var lineArray = line.trim().split(" {3}");

            left.add(Integer.decode(lineArray[0]));
            right.add(Integer.decode(lineArray[1]));
        }

        final List<Integer> leftSorted = left.stream().sorted().toList();
        final List<Integer> rightSorted = right.stream().sorted().toList();

        int sum = IntStream.range(0, left.size())
                .map(i -> Math.abs(rightSorted.get(i) - leftSorted.get(i)))
                .sum();

        System.out.println("Answer: " + sum);
    }
}