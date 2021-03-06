package AIGame;

import com.company.user.Player;
import javafx.scene.control.Button;
import utils.GameUtils;
import utils.CheckVictory;

import java.util.*;
import java.util.stream.IntStream;


public class Minimax {
    private int bestMove = 0;

    public int getBestMove(Map<Button, Player> board, Player player) {
        bestMove = 0;
        minimax(board, player, true, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

    private int minimax(Map<Button, Player> board, Player player, boolean isAI, int depth, int alpha, int beta) {

        if (CheckVictory.checkWin(board) != null || getFreeSpots(board).size() == 0) {
            return getScore(board, player, depth);
        }

        List<Integer> scores = new ArrayList<>();
        List<Integer> freeSpots = getFreeSpots(board);
        int score;

        for (int i = 0; i < freeSpots.size(); i++) {
            Player newPlayer;
            if (isAI) {
                newPlayer = player;
            } else {
                newPlayer = GameUtils.getBoard().getPlayers().get(0);
            }
            updateBoard(board, freeSpots.get(i), newPlayer);
            score = minimax(board, player, !isAI, depth + 1, alpha, beta);
            scores.add(score);
            updateBoard(board, freeSpots.get(i), null);

            if (isAI) {
                int maxim = Math.max(Integer.MIN_VALUE, score);
                alpha = Math.max(alpha, maxim);
                if (alpha > beta) {
                    return maxim;
                }
            } else {
                int minim = Math.min(Integer.MAX_VALUE, score);
                beta = Math.min(beta, minim);
                if (alpha > beta) {
                    return minim;
                }
            }
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
            return GameUtils.getBoard().getGrid().size() - depth;
        }
        return (GameUtils.getBoard().getGrid().size() * -1) + depth;
    }

    private List<Integer> getFreeSpots(Map<Button, Player> board) {
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