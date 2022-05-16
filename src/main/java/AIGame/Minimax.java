package AIGame;

import javafx.scene.control.Button;
import utils.AIGameUtils;

import java.util.*;
import java.util.stream.IntStream;


public class Minimax {
    private int bestMove = 0;
    private int c = 0;

    public int getBestMove(Map<Button, Player> board, Player player) {
        bestMove = 0;
        minimax(board, player, true, 0);
        return bestMove;
    }

    private int minimax(Map<Button, Player> board, Player player, boolean isAI, int depth) {
        if (CheckVictory.checkWin(board) != null || getFreeSpots(board).size() == 0 || c == AIGameUtils.getNumberOfRows()*AIGameUtils.getNumberOfRows()) {
            return getScore(board, player, depth);
        }
        c++;

        List<Integer> scores = new ArrayList<>();
        List<Integer> freeSpots = getFreeSpots(board);
        int score;

        for (int i = 0; i < freeSpots.size(); i++) {

            if (!isAI) {
                depth++;
            }

            Player newPlayer;
            if (isAI) {
                newPlayer = player;
            } else {
                newPlayer = AIGameUtils.getBoard().getPlayers().get(0);
            }
            updateBoard(board, i, newPlayer);
            score = minimax(board, player, !isAI, depth);
            scores.add(score);
            updateBoard(board, i, null);
        }

        int scoreIndex;
        if (isAI) {
            int maxValue = scores
                    .stream()
                    .mapToInt(v -> v)
                    .max().orElseThrow(NoSuchElementException::new);
            int[] indices = IntStream.range(0, scores.size())
                    .filter(i -> scores.get(i) == maxValue)
                    .toArray();
            scoreIndex = indices[indices.length - 1];
        } else {
            int minValue = scores
                    .stream()
                    .mapToInt(v -> v)
                    .min().orElseThrow(NoSuchElementException::new);
            int[] indices = IntStream.range(0, scores.size()).filter(i -> scores.get(i) == minValue).toArray();
            scoreIndex = indices[0];
        }
        bestMove = freeSpots.get(scoreIndex);
        return scores.get(scoreIndex);
    }

    private Map<Button, Player> updateBoard(Map<Button, Player> board, int i, Player player) {
        int count = 0;
        for (var iterator : board.entrySet()) {
            if (count == i) {
                board.put(iterator.getKey(), player);
                break;
            }
            count++;
        }
        return board;
    }

    private int getScore(Map<Button, Player> board, Player player, int depth) {
        Player winner = CheckVictory.checkWin(board);
        if (winner == null) {
            return 0;
        }
        if (winner.equals(player)) {
            return AIGameUtils.getBoard().getGrid().size() - depth;
        }
        return (AIGameUtils.getBoard().getGrid().size() * -1) + depth;
    }

    private List<Integer> getFreeSpots(Map<Button, Player> board) {
        List<Integer> freeSpots = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < AIGameUtils.getNumberOfRows() * AIGameUtils.getNumberOfRows(); i++) {
            if (board.values().toArray()[counter] == null) {
                freeSpots.add(counter);
            }
            counter++;
        }
        return freeSpots;
    }
}
