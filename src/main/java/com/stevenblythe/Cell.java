package com.stevenblythe;

public class Cell {
    private int x;
    private int y;
    private boolean isAlive;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isAlive = false;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void spawn() {
        this.isAlive = true;
    }

    public void kill() {
        this.isAlive = false;
    }
}
