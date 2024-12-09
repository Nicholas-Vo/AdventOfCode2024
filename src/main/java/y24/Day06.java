package main.java.y24;

import main.java.AdventDay;

import java.util.HashMap;

public class Day06 {
    private static final char NULL_INDEX = '\0';

    public static void main(String[] args) {
        var day6 = new AdventDay<Integer>(2024, 6, false);

        day6.doAnswer(1, () -> {
            var input = day6.getInputLines();
            int size = input.length;
            char[][] grid = new char[size][size];

            Guard guard = new Guard(-1, -1);
            for (int row = 0; row < size; row++) {
                grid[row] = input[row].toCharArray();
                for (int column = 0; column < size; column++) {
                    if (grid[row][column] == '^') {
                        guard.update(row, column, Direction.NORTH);
                    }
                }
            }

            var posMap = new HashMap<Position, Integer>();

            while (true) {
                var direction = guard.direction();
                var next = getNextCell(grid, guard);

                if (next == '#') {
                    System.out.println(direction.symbol() + " next: " + next);
                    System.out.println("Turning... ");
                    guard.turn();
                    continue;
                }

                System.out.println(direction.symbol() + " next: " + next);
                Position position = guard.gallivant();

                if () {
                    posMap.merge(position, 1, Integer::sum);
                }

                // end of gallivant
                if (next == NULL_INDEX) {
                    break;
                }
            }

            System.out.println("Answer = " + posMap.values().stream().count());
            return 1;
        });
    }

    private static char getNextCell(char[][] grid, Guard guard) {
        int row = guard.row() + guard.direction().getRowChange();
        int col = guard.column() + guard.direction().getColumnChange();

        if (row < 0 || col < 0) {
            return NULL_INDEX;
        }
        if (row >= grid.length) {
            return NULL_INDEX;
        }
        if (col >= grid[row].length) {
            return NULL_INDEX;
        }
        return grid[row][col];
    }

}

class Guard {
    private int row;
    private int column;
    private Direction direction;

    public Guard(int row, int column) {
        this.row = row;
        this.column = column;
        this.direction = Direction.NORTH;
    }

    public void update(int row, int column, Direction direction) {
        this.row = row;
        this.column = column;
        this.direction = direction;
    }

    public Position gallivant() {
        this.row = this.row + this.direction.getRowChange();
        this.column = this.column + this.direction.getColumnChange();
        return new Position(this.row, this.column);
    }

    public void turn() {
        this.direction = direction.turn();
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public Direction direction() {
        return direction;
    }
}

class Position {
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Position pos2 = (Position) object;

        return this.row == pos2.row && this.column == pos2.column;
    }
}

enum Direction {
    /* -1, 0 moves you up one, over 0  */
    NORTH(-1, 0, '^'), SOUTH(1, 0, 'v'), EAST(0, 1, '>'), WEST(0, -1, '<');

    private final int row;
    private final int column;
    private final char symbol;

    Direction(int row, int column, char symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    public int getRowChange() {
        return row;
    }

    public int getColumnChange() {
        return column;
    }

    public char symbol() {
        return symbol;
    }

    public Direction turn() {
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }
}


