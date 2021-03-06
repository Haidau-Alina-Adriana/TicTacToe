package utils;

import AIGame.Board;
import com.company.user.Player;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameUtils {
    private static Board board = new Board(new LinkedHashMap<>(), new ArrayList<>());
    public static int turn = 0;
    private static boolean endGame = false;

    private static String difficulty;
    private static int numberOfRows = 3;

    public static String validateMove(Button button) {
        String message = "";
        for (Map.Entry<Button, Player> iterator : GameUtils.getBoard().getGrid().entrySet()) {
            if (button.equals(iterator.getKey())) {
                if (iterator.getValue() != null) {
                    return "Spot taken!";
                } else {
                    return "ok";
                }
            }
        }
        return message;
    }

    public static int getNumberOfFreePositions() {
        int freeSpots = 0;
        for (Player p : GameUtils.getBoard().getGrid().values()) {
            if (p == null) {
                freeSpots++;
            }
        }
        return freeSpots;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        GameUtils.difficulty = difficulty;
    }

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        GameUtils.numberOfRows = numberOfRows;
    }

    public static void setBoard(Board gameBoard) {
        board = gameBoard;
    }

    public static Board getBoard() {
        return board;
    }

    public static boolean isEndGame() {
        return endGame;
    }

    public static void initializeGrid(){
        board.setGrid(new LinkedHashMap<>());
    }

    public static void setEndGame(boolean endGame) {
        GameUtils.endGame = endGame;
    }

    public static List<Integer> getFreeSpots(Map<Button, Player> board) {
        List<Integer> freeSpots = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < GameUtils.getNumberOfRows() * GameUtils.getNumberOfRows(); i++) {
            if (board.values().toArray()[counter] == null) {
                freeSpots.add(counter);
            }
            counter++;
        }
        return freeSpots;
    }
}