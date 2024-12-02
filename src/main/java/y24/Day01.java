package main.java.y24;

import main.java.AdventDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 {

    public static void main(String[] args) {
        var adventDay = new AdventDay<Long>(2024, 1, /* Use test file */ true);

        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        final String[] inputLines = adventDay.getInputLines();

        for (String line : inputLines) {
            // Create a two-element pair; Example: [1205, 1931]
            var linePair = line.trim().split(" {3}");

            left.add(Long.decode(linePair[0]));
            right.add(Long.decode(linePair[1]));
        }

        /* Day 1, part 1 */
        adventDay.doAnswer(1, () -> {
            final List<Long> leftSorted = left.stream().sorted().toList();
            final List<Long> rightSorted = right.stream().sorted().toList();

            // We know that each list is of the same size
            int size = left.size();

            return IntStream.range(0, size)
                    .mapToLong(i -> Math.abs(rightSorted.get(i) - leftSorted.get(i)))
                    .sum();
        });

        /* Day 1, part 2 */
        adventDay.doAnswer(2, () -> {
            long similarity = 0;

            for (long leftNum : left) {
                long appearsInRight = right.stream().filter(n -> n.equals(leftNum)).count();

                similarity += leftNum * appearsInRight;
            }

            return similarity;
        });
    }

}
