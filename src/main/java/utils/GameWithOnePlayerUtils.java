package utils;

import javafx.scene.paint.Color;

public class GameWithOnePlayerUtils {
    private static String difficulty;
    private static int numberOfRows = 3;
    private static int numberOfColumns = 3;
    private static Color playerColor;

    public GameWithOnePlayerUtils() {
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameWithOnePlayerUtils.difficulty = difficulty;
    }

    public static int getNumberOfColumns() {
        return numberOfColumns;
    }

    public static void setNumberOfColumns(int numberOfColumns) {
        GameWithOnePlayerUtils.numberOfColumns = numberOfColumns;
    }

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        GameWithOnePlayerUtils.numberOfRows = numberOfRows;
    }

    public static Color getPlayerColor() {
        return playerColor;
    }

    public static void setPlayerColor(Color playerColor) {
        GameWithOnePlayerUtils.playerColor = playerColor;
    }

    @Override
    public String toString() {
        return "GameWithOnePlayer{" +
                "difficulty='" + difficulty + '\'' +
                ", numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", playerColor=" + playerColor +
                '}';
    }

    public void minimax(){

    }
}
