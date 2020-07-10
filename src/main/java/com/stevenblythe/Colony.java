package com.stevenblythe;

import java.util.ArrayList;

public class Colony {
    private Cell[][] colony;
    private int width;
    private int height;
    private int age;

    public Colony(int width, int height) {
        this.width = width;
        this.height = height;
        this.colony = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                colony[x][y] = new Cell(x, y);
            }
        }
        this.age = 0;
    }

    public void showColony() {
        System.out.println("Colony age: " + age);
        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder("|");
            for (int x = 0; x < width; x++) {
                Cell cell = colony[x][y];
                if (cell.isAlive()) {
                    line.append("*");
                } else {
                    line.append(".");
                }
            }
            line.append("|");
            System.out.println(line);
        }
    }

    public void spawnCellAt(int x, int y) {
        if (cellIsInColony(x, y)) {
            Cell cell = colony[x][y];
            if (!cell.isAlive()) {
                cell.spawn();
            }
        }
    }

    public void killCellAt(int x, int y) {
        if (cellIsInColony(x, y)) {
            Cell cell = colony[x][y];
            if (cell.isAlive()) {
                cell.kill();
            }
        }
    }

    private boolean cellIsInColony(int x, int y) {
        return (x < this.width && x >= 0 && y < this.height && y >= 0);
    }

    public void evolve() {
        ArrayList<Cell> cellsToKill = new ArrayList<>();
        ArrayList<Cell> cellsToSpawn = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = colony[x][y];
                int cellAliveNeighbours = countAliveNeighbours(x, y);
                if (cell.isAlive()) {
                    if (cellAliveNeighbours < 2 || cellAliveNeighbours > 3) {
                        cellsToKill.add(cell);
                    }
                } else {
                    if (cellAliveNeighbours == 3) {
                        cellsToSpawn.add(cell);
                    }
                }
            }
        }

        cellsToKill.forEach(Cell::kill);
        cellsToSpawn.forEach(Cell::spawn);
        age++;
    }

    public int countAliveNeighbours(int x, int y) {
        int count = 0;
        if (cellIsInColony(x, y)) {
            // NW NN NE
            if (y != 0) {
                if (x != 0)
                    count += colony[x - 1][y - 1].isAlive() ? 1 : 0;

                count += colony[x][y - 1].isAlive() ? 1 : 0;

                if (x != width - 1)
                    count += colony[x + 1][y - 1].isAlive() ? 1 : 0;
            }

            // WW EE
            if (x != 0) {
                count += colony[x - 1][y].isAlive() ? 1 : 0;
            }
            if (x != width - 1) {
                count += colony[x + 1][y].isAlive() ? 1 : 0;
            }

            // SW SS SE
            if (y != height - 1) {
                if (x != 0)
                    count += colony[x - 1][y + 1].isAlive() ? 1 : 0;

                count += colony[x][y + 1].isAlive() ? 1 : 0;

                if (x != width - 1) {
                    count += colony[x + 1][y + 1].isAlive() ? 1 : 0;
                }
            }
        }
        return count;
    }
}
