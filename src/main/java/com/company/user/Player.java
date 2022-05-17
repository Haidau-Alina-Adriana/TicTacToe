package com.company.user;

public class Player {
    private int index;

    public Player() {
    }

    public Player(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Player{" +
                "index=" + index +
                '}';
    }
}
