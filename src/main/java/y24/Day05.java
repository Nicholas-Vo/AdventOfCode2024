package main.java.y24;

import main.java.AdventDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day05 {
    private static final Map<Integer, Set<Integer>> ruleMap = new HashMap<>();

    public static void main(String[] args) {
        var day5 = new AdventDay<Integer>(2024, 5, false);
        String[] input = day5.getInput().split("\n\r");

        String rules = input[0];
        String updates = input[1];

        String[] ruleList = rules.split("\n");

        day5.doAnswer(1, () -> {
            for (String rule : ruleList) {
                int[] ruleArray = Arrays.stream(rule.split("\\|"))
                        .map(s -> s.trim())
                        .mapToInt(s -> Integer.parseInt(s))
                        .toArray();

                int keyPage = ruleArray[0];
                int valPage = ruleArray[1];

                ruleMap.putIfAbsent(keyPage, new HashSet<>(valPage));

                Set<Integer> pageSet = ruleMap.get(keyPage);
                pageSet.add(valPage);
            }

            int sum = 0;
            String[] updateList = updates.trim().split("\n");
            for (String update : updateList) {
                int[] pages = Arrays.stream(update.split(","))
                        .map(s -> s.trim())
                        .mapToInt(s -> Integer.parseInt(s))
                        .toArray();

                if (isValidUpdate(pages)) {
                    sum += /* middle number: */ pages[pages.length / 2];
                }
            }

            return sum;
        });
    }

    // X|Y = Page number X must be printed before Y
    // rule = 47|53
    // pages = [75,47,61,53,29]
    private static boolean isValidUpdate(int[] pages) {
        for (int i = 0; i < pages.length; i++) {
            var currentPage = pages[i];
            var disallowed = ruleMap.get(currentPage);
            if (disallowed == null) {
                continue;
            }

            for (int j = 0; j < i; j++) {
                int previousPage = pages[j];
                if (disallowed.contains(previousPage)) {
                    return false;
                }
            }
        }
        return true;
    }

}

