package AIGame;

import controllers.PlayWithoutLoginController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AIGameUtils;

public class Game {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/x.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/zero.png"));
    private int delayTime = 0;

    public void userMove(Button button) {
        if (!AIGameUtils.isEndGame()) {
            String resultMove = AIGameUtils.validateMove(button);
            if (resultMove.equals("ok")) {
                if (AIGameUtils.turn % 2 == 0) {
                    ImageView img = new ImageView(image0);
                    img.setFitWidth(20);
                    img.setFitHeight(20);
                    button.setGraphic(img);

                    AIGameUtils.getBoard().getGrid().put(button, AIGameUtils.getBoard().getPlayers().get(0));

                    var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                    if (checkForWinning != null) {
//                        new PlayWithoutLoginController().changeMessage("Player " + checkForWinning.getIndex() + " has won!");
                        AIGameUtils.setEndGame(true);
                    } else {
//                        new PlayWithoutLoginController().changeMessage("Bot's turn!");
                    }
                    AIGameUtils.turn++;

                    if (AIGameUtils.getNumberOfFreePositions() != 0) {
                        Game game = new Game();
                        game.playHard(AIGameUtils.getBoard());
                    }
                }
            } else {
//                new PlayWithoutLoginController().changeMessage(resultMove);
            }
        }
    }

    public void playHard(Board board) {
        if (!AIGameUtils.isEndGame()) {
            if (AIGameUtils.turn % 2 == 1) {


                Minimax minimax = new Minimax();
                int bestMove = minimax.getBestMove(board.getGrid(), board.getPlayers().get(1));
                for (var iterator : AIGameUtils.getBoard().getGrid().entrySet()) {

                    if (iterator.getKey().getId().equals(String.valueOf(bestMove))) {
                        AIGameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
//                            new PlayWithoutLoginController().changeMessage("Player " + checkForWinning.getIndex() + " has won!");
                            AIGameUtils.setEndGame(true);
                        } else {
//                            new PlayWithoutLoginController().changeMessage("Player's " + AIGameUtils.turn % 2 + " turn!");
                        }
                        AIGameUtils.turn++;
                        for (var it : AIGameUtils.getBoard().getGrid().entrySet()) {
                            System.out.println("Key: " + it.getKey() + ", value: " + it.getValue());
                        }
                        System.out.println();

                        break;
                    }
                }
            }
        }
    }
}