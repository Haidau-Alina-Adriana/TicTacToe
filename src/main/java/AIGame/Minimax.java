package AIGame;

import javafx.scene.control.Button;

import java.util.Map;

public class Minimax {
    private int bestMove = 0;

    public int getBestMove(Map<Button, Player> board, Player player) {
        bestMove = 0;
        minimax(board, player, true, 0);
        return bestMove;
    }

    private int minimax(Map<Button, Player> board, Player player, boolean maximizer, int depth) {

        return 0;
    }
}
