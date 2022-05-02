package com.company;

public class Player implements Runnable {
    private static int index = 1;
    private final int id;
    private String username;
    private int score;
    private boolean isAI;


    public Player(String username, int score, boolean isAI) {
        this.id = index;
        index++;
        this.username = username;
        this.score = score;
        this.isAI = isAI;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    @Override
    public void run() {

    }
}
