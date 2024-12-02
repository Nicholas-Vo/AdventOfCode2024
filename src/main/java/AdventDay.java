package main.java;

import java.util.function.Supplier;

public class AdventDay<R> {
    private int year;
    private int day;
    private int part;

    private InputReader reader;

    public AdventDay(int year, int day) {
        construct(year, day, false);
    }

    public AdventDay(int year, int day, boolean useTestFile) {
        construct(year, day, useTestFile);
    }

    private void construct(int year, int day, boolean useTestFile) {
        this.year = year;
        this.day = day;

        reader = new InputReader(new AdventDate(this.year, this.day, this.part), useTestFile);
    }

    public void doAnswer(int part, Supplier<R> action) {
        this.part = part;
        R result = action.get();

        System.out.println(
                "Result for day " + this.day + ", part " + this.part + ": " + result);
    }

    public String[] getInputLines() {
        return reader.getInputLines();
    }

    public int year() {
        return year;
    }

    public int day() {
        return day;
    }
}
