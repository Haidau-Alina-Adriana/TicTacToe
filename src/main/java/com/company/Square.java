package com.company;

public class Square {
    private int x, y;
    private int index;

    public Square() {
    }

    public Square(int index, int x, int y) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", index=" + index +
                '}';
    }
}
