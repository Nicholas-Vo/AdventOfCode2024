package main.java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader {
    private final String[] inputLines;

    public InputReader(AdventDate date, boolean useTestfile) {
        final String path = "aoc-" + date.year() + "-" + date.day();
        final String fileName = useTestfile ? path + "-test" + ".txt" : path + ".txt";

        try {
            var resource = this.getClass().getClassLoader().getResource(fileName);
            var file = Files.readAllLines(Paths.get(resource.toURI()));

            inputLines = file.toArray(new String[0]);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public String[] getInputLines() {
        return inputLines;
    }

}