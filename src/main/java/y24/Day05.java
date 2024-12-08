package main.java.y24;

import main.java.AdventDay;

import java.util.*;

public class Day05 {
    private static final Map<Integer, Set<Integer>> ruleMap = new HashMap<>();

    public static void main(String[] args) {
        var day5 = new AdventDay<Integer>(2024, 5, false);
        String[] input = day5.getInput().split("\n\r");

        String rules = input[0];
        String updates = input[1];

        String[] ruleList = rules.split("\n");

        day5.doAnswer(1, () -> {
            int sum = 0;
            for (String update : getUpdateList(ruleList, updates)) {
                int[] pages = Arrays.stream(update.split(","))
                        .map(s -> s.trim())
                        .mapToInt(s -> Integer.parseInt(s))
                        .toArray();

                if (isValidUpdate(pages)) {
                    sum += /* middle number */ pages[pages.length / 2];
                }
            }

            return sum;
        });

        day5.doAnswer(2, () -> {
            int sum = 0;
            for (String update : getUpdateList(ruleList, updates)) {
                int[] pages = Arrays.stream(update.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (!isValidUpdate(pages)) {
                    int[] sorted = sortPages(pages);
                    sum += sorted[sorted.length / 2];
                }
            }

            return sum;
        });
    }

    private static int[] sortPages(int[] pages) {
        Integer[] sortedPages = Arrays.stream(pages).boxed().toArray(Integer[]::new);

        Arrays.sort(sortedPages, (a, b) -> {
            var disallowedA = ruleMap.get(a);
            var disallowedB = ruleMap.get(b);

            // b must come after a
            if (disallowedA.contains(b)) {
                return 1;
            }
            // a must come after b
            if (disallowedB.contains(a)) {
                return -1;
            }
            return 0;
        });

        return Arrays.stream(sortedPages).mapToInt(i -> i).toArray();
    }

    private static String[] getUpdateList(String[] ruleList, String updates) {
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

        return updates.trim().split("\n");
    }

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

