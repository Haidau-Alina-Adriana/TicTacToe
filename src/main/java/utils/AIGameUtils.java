package utils;

import AIGame.Board;
import AIGame.Player;
import javafx.scene.control.Button;

import java.util.Map;

public class AIGameUtils {
    public static Board board;
    public static int turn = 0; //normal user (numar par) -> 0, ai (numar impar) -> X

    private static String difficulty;
    private static int numberOfRows = 3;

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        AIGameUtils.difficulty = difficulty;
    }

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        AIGameUtils.numberOfRows = numberOfRows;
    }


    public static void setBoard(Board gameBoard) {
        board = gameBoard;
    }

    public static Board getBoard() {
        return board;
    }


    public static String validateMove(Button button, int turn) {
        String message = "";
        for (Map.Entry<Button, Player> iterator : AIGameUtils.getBoard().getGrid().entrySet()) {
            if (button.equals(iterator.getKey())) {
                if (iterator.getValue() != null) {
                    return "Spot taken!";
                } else {
                    board.getGrid().put(iterator.getKey(), board.getPlayers().get(turn % 2));
                    return "ok";
                }
            }
        }
        return message;
    }

    public static int getNumberOfFreePositions() {
        int freeSpots = 0;
        for (Player p : AIGameUtils.getBoard().getGrid().values()) {
            if (p == null) {
                freeSpots++;
            }
        }
        return freeSpots;
    }

}
