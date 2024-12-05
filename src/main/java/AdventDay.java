package main.java;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Represents a day of the Advent of Code challenge
 * @param <R>: The type of result returned (Long, String, etc.)
 */
public class AdventDay<R> {
    private AdventDate adventDate;
    private InputReader reader;
    private boolean testing;

    public AdventDay(int year, int day) {
        construct(year, day, false);
    }

    public AdventDay(int year, int day, boolean testing) {
        construct(year, day, testing);
    }

    private void construct(int year, int day, boolean testing) {
        adventDate = new AdventDate(year, day);
        reader = new InputReader(this.adventDate, testing);
        this.testing = testing;
    }

    public void doAnswer(int part, Supplier<R> action) {
        this.adventDate.setPart(part);
        R result = action.get();

        if (Objects.equals(result.toString(), "-1")) {
            return;
        }

        System.out.println(
                "Result for day " + this.adventDate.day() + ", part " + this.adventDate.part() + ": " + result);
    }

    public String[] getInputLines() {
        return reader.getInputLines();
    }

    public boolean testing() { return this.testing; }

}
