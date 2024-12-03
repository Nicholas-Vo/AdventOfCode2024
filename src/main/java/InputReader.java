package main.java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader {
    private final String[] inputLines;

    public InputReader(AdventDate date, boolean useTestFile) {
        // todo: this'll break after day 9, and it's ugly. make this better
        final String path = "main/resources/day0" + date.day() + "/aoc-" + date.year() + "-" + date.day();
        final String fileName = useTestFile ? path + "-test" + ".txt" : path + ".txt";

        // vscode/codespace friendly path
        //final String fileName = "main/resources/day02/aoc-2024-2-test.txt";

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