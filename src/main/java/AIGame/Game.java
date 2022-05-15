package AIGame;

import utils.AIGameUtils;

public class Game {

    public void play(){
        do {
            if (AIGameUtils.turn % 2 == 0) {
//                ----------------------------;
            } else {
                Minimax minimax = new Minimax();
                minimax.getBestMove(AIGameUtils.getBoard().getGrid(), AIGameUtils.getBoard().getPlayers().get(1));
            }
            AIGameUtils.turn++;
        } while (AIGameUtils.getNumberOfFreePositions() != 0);
    }
}
