package network;

import com.company.user.UserRepository;
import controllers.PlayController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AIGameUtils;
import utils.CheckVictory;
import utils.SessionUtils;
import java.util.Random;

public class GameForPlayerVsPlayer {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/x.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/0.png"));

    public void userMove(Button button) {
        if (!AIGameUtils.isEndGame()) {
            String resultMove = AIGameUtils.validateMove(button);
            if (resultMove.equals("ok")) {
                if (AIGameUtils.turn % 2 == 0) {
                    updateGame(button, image0, 0);
                } else {
                    updateGame(button, imageX, 1);
                }
            } else {
                PlayController.accessMessageLabel.setText(resultMove);
            }
        }
    }

    private void updateGame(Button button, Image image, int player) {
        ImageView img = new ImageView(image);
        img.setFitWidth(20);
        img.setFitHeight(20);
        button.setGraphic(img);

        AIGameUtils.getBoard().getGrid().put(button, AIGameUtils.getBoard().getPlayers().get(player));

        var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
        if (checkForWinning != null) {
            PlayController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Player 2 won!");
            updateScore(checkForWinning.getIndex());
            AIGameUtils.setEndGame(true);
        } else {
            PlayController.accessMessageLabel.setText(AIGameUtils.turn % 2 == 0 ? "Player 2's turn" : "Your turn");
            AIGameUtils.turn++;
        }
    }

    private void updateScore(int winner){
        long userID;
        if(winner == 0){
            userID = SessionUtils.getUser().getId();
        }else{
            Random getRandomPosition = new Random();
            userID = getRandomPosition.nextInt(UserRepository.getNumberOfUsersFromDatabase()) + 1;
        }
        UserRepository userRepo = new UserRepository();
        userRepo.updateScore(userID);
    }
}
