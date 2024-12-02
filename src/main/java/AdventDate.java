package main.java;

public class AdventDate {
    private int year;
    private int day;
    private int part;

    public AdventDate(int year, int day, int part) {
        this.year = year;
        this.day = day;
        this.part = part;
    }

    public int year() {
        return year;
    }

    public int day() {
        return day;
    }

    public int part() {
        return part;
    }
}
