package AIGame;

import controllers.PlayWithoutLoginController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AIGameUtils;
import utils.CheckVictory;

import java.util.List;
import java.util.Random;

public class GameForPlayerVsBot {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/ex.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/0.png"));
    private static boolean bestMoveTime = false;

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
                        PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                        AIGameUtils.setEndGame(true);
                    } else {
                        PlayWithoutLoginController.accessMessageLabel.setText("Bot's turn!");
                    }
                    AIGameUtils.turn++;

                    if (AIGameUtils.getNumberOfFreePositions() != 0) {
                        if (AIGameUtils.getDifficulty().equals("hard")) {
                            new GameForPlayerVsBot().playHardMode(AIGameUtils.getBoard());
                        } else if (AIGameUtils.getDifficulty().equals("medium")) {
                            new GameForPlayerVsBot().playMediumMode(AIGameUtils.getBoard());
                        } else {
                            new GameForPlayerVsBot().playEasyMode(AIGameUtils.getBoard());
                        }
                    } else {
                        checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            AIGameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("It's a tie!");
                        }

                    }
                }
            } else {
                PlayWithoutLoginController.accessMessageLabel.setText(resultMove);
            }
        }
    }

    public void playHardMode(Board board) {
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
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            AIGameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        AIGameUtils.turn++;
                        break;
                    }
                }
            }
        }
    }

    public void playMediumMode(Board board) {
        if (!AIGameUtils.isEndGame()) {
            if (AIGameUtils.turn % 2 == 1) {
                int positionMove;
                if (isBestMoveTime()) {
                    Minimax minimax = new Minimax();
                    positionMove = minimax.getBestMove(board.getGrid(), board.getPlayers().get(1));
                    setBestMoveTime(false);
                } else {
                    Random getRandomPosition = new Random();
                    List<Integer> freePositions = AIGameUtils.getFreeSpots(AIGameUtils.getBoard().getGrid());
                    positionMove = freePositions.get(getRandomPosition.nextInt(freePositions.size()));
                    setBestMoveTime(true);
                }
                for (var iterator : AIGameUtils.getBoard().getGrid().entrySet()) {

                    if (iterator.getKey().getId().equals(String.valueOf(positionMove))) {
                        AIGameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            AIGameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        AIGameUtils.turn++;
                        break;
                    }
                }
            }
        }
    }

    public void playEasyMode(Board board) {
        if (!AIGameUtils.isEndGame()) {
            if (AIGameUtils.turn % 2 == 1) {

                Random getRandomPosition = new Random();
                List<Integer> freePositions = AIGameUtils.getFreeSpots(AIGameUtils.getBoard().getGrid());
                int randomPosition = freePositions.get(getRandomPosition.nextInt(freePositions.size()));
                for (var iterator : AIGameUtils.getBoard().getGrid().entrySet()) {
                    if (iterator.getKey().getId().equals(String.valueOf(randomPosition))) {
                        AIGameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            AIGameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        AIGameUtils.turn++;
                        break;
                    }
                }
            }
        }
    }

    public boolean isBestMoveTime() {
        return bestMoveTime;
    }

    public void setBestMoveTime(boolean bestMoveTime) {
        this.bestMoveTime = bestMoveTime;
    }
}