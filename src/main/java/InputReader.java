package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputReader {
    private static final Logger logger = Logger.getLogger(InputReader.class.getName());
    private String[] inputLines;
    private final AdventDate date;
    private final boolean useTestfile;

    public InputReader(AdventDate date, boolean useTestfile) {
        var classloader = Thread.currentThread().getContextClassLoader();

        this.date = date;
        this.useTestfile = useTestfile;

        final String path = "aoc-" + this.date.year() + "-" + this.date.day();
        final String fileName = this.useTestfile ? path + "-test" + ".txt" : path + ".txt";

        try (var inputStream = classloader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                logger.log(Level.SEVERE, "File not found: " + fileName);
                return;
            }

            try (Scanner scanner = new Scanner(inputStream)) {
                var linesList = new ArrayList<String>();
                while (scanner.hasNextLine()) {
                    linesList.add(scanner.nextLine());
                }
                inputLines = linesList.toArray(new String[0]);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading the file.", e);
        }
    }

    public String[] getInputLines() {
        return inputLines;
    }

}