package main.java;

import java.util.function.Supplier;

public class AdventDay<R> {
    private int year;
    private int day;
    private int part;

    private InputReader reader;

    public AdventDay(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public void doAnswer(int part, Supplier<R> action) {
        this.part = part;
        this.reader = new InputReader(this.year, this.day);
        R result = action.get();

        System.out.println("Result for year " + this.year + ", day " + this.day + ": " + result);
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
