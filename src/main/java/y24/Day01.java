package main.java.y24;

import main.java.AdventDay;
import main.java.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 {
    public static void main(String[] args) {
        var adventDay = new AdventDay<Integer>(2024, 1);

        adventDay.doAnswer(1, () -> {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();

            final String[] inputLines = adventDay.getInputLines();

            for (String line : inputLines) {
                var lineArray = line.trim().split(" {3}");
                left.add(Integer.decode(lineArray[0]));
                right.add(Integer.decode(lineArray[1]));
            }

            final List<Integer> leftSorted = left.stream().sorted().toList();
            final List<Integer> rightSorted = right.stream().sorted().toList();

            return IntStream.range(0, left.size())
                    .map(i -> Math.abs(rightSorted.get(i) - leftSorted.get(i)))
                    .sum();
        });

        adventDay.doAnswer(2, () -> {
            return 5;
        });
    }
}
