package main.java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InputReader {
    private final String[] inputLines;
    private final String input;

    public InputReader(AdventDate date, boolean useTestFile) {
        // todo: this'll break after day 9, and it's ugly. make this better
        final String path = "main/resources/day0" + date.day() + "/aoc-" + date.year() + "-" + date.day();
        final String fileName = useTestFile ? path + "-test" + ".txt" : path + ".txt";

        // vscode/codespace friendly path
        //final String fileName = "main/resources/day02/aoc-2024-2-test.txt";

        try {
            var resource = this.getClass().getClassLoader().getResource(fileName);
            var p = Paths.get(resource.toURI());
            var file = Files.readAllLines(p);

            inputLines = file.toArray(new String[0]);
            input = Files.readString(p);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getInputLines() {
        return inputLines;
    }

    public String getInput() {
        return input;
    }

}