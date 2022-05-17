package network;

import controllers.PlayController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AIGameUtils;
import utils.CheckVictory;

public class GameForPlayerVsPlayer {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/ex.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/0.png"));

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
                        PlayController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Other player won!");
                        AIGameUtils.setEndGame(true);
                    } else {
                        PlayController.accessMessageLabel.setText("Player 2's turn!");
                        AIGameUtils.turn++;
                    }

                } else {
                    ImageView img = new ImageView(imageX);
                    img.setFitWidth(20);
                    img.setFitHeight(20);
                    button.setGraphic(img);

                    AIGameUtils.getBoard().getGrid().put(button, AIGameUtils.getBoard().getPlayers().get(1));

                    var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                    if (checkForWinning != null) {
                        PlayController.accessMessageLabel.setText(checkForWinning.getIndex() == 1 ? "You won!" : "Other player won!");
                        AIGameUtils.setEndGame(true);
                    } else {
                        PlayController.accessMessageLabel.setText("Player 1's turn!");
                        AIGameUtils.turn++;
                    }
                }
            } else {
                PlayController.accessMessageLabel.setText(resultMove);
            }
        }

    }
}
