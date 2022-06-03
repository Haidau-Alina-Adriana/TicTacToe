package AIGame;

import controllers.PlayWithoutLoginController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.GameUtils;
import utils.CheckVictory;

import java.util.List;
import java.util.Random;

public class GameForPlayerVsBot {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/x.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/0.png"));
    private static boolean bestMoveTime = false;

    public void userMove(Button button) {
        if (!GameUtils.isEndGame()) {
            String resultMove = GameUtils.validateMove(button);
            if (resultMove.equals("ok")) {
                if (GameUtils.turn % 2 == 0) {
                    ImageView img = new ImageView(image0);
                    img.setFitWidth(20);
                    img.setFitHeight(20);
                    button.setGraphic(img);

                    GameUtils.getBoard().getGrid().put(button, GameUtils.getBoard().getPlayers().get(0));

                    var checkForWinning = CheckVictory.checkWin(GameUtils.getBoard().getGrid());
                    if (checkForWinning != null) {
                        PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                        GameUtils.setEndGame(true);
                    } else {
                        PlayWithoutLoginController.accessMessageLabel.setText("Bot's turn!");
                    }
                    GameUtils.turn++;

                    if (GameUtils.getNumberOfFreePositions() != 0) {
                        if (GameUtils.getDifficulty().equals("hard")) {
                            new GameForPlayerVsBot().playHardMode(GameUtils.getBoard());
                        } else if (GameUtils.getDifficulty().equals("medium")) {
                            new GameForPlayerVsBot().playMediumMode(GameUtils.getBoard());
                        } else {
                            new GameForPlayerVsBot().playEasyMode(GameUtils.getBoard());
                        }
                    } else {
                        checkForWinning = CheckVictory.checkWin(GameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            GameUtils.setEndGame(true);
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
        if (!GameUtils.isEndGame()) {
            if (GameUtils.turn % 2 == 1) {


                Minimax minimax = new Minimax();
                int bestMove = minimax.getBestMove(board.getGrid(), board.getPlayers().get(1));
                for (var iterator : GameUtils.getBoard().getGrid().entrySet()) {

                    if (iterator.getKey().getId().equals(String.valueOf(bestMove))) {
                        GameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(GameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            GameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        GameUtils.turn++;
                        break;
                    }
                }
            }
        }
    }

    public void playMediumMode(Board board) {
        if (!GameUtils.isEndGame()) {
            if (GameUtils.turn % 2 == 1) {
                int positionMove;
                if (isBestMoveTime()) {
                    Minimax minimax = new Minimax();
                    positionMove = minimax.getBestMove(board.getGrid(), board.getPlayers().get(1));
                    setBestMoveTime(false);
                } else {
                    Random getRandomPosition = new Random();
                    List<Integer> freePositions = GameUtils.getFreeSpots(GameUtils.getBoard().getGrid());
                    positionMove = freePositions.get(getRandomPosition.nextInt(freePositions.size()));
                    setBestMoveTime(true);
                }
                for (var iterator : GameUtils.getBoard().getGrid().entrySet()) {

                    if (iterator.getKey().getId().equals(String.valueOf(positionMove))) {
                        GameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(GameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            GameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        GameUtils.turn++;
                        break;
                    }
                }
            }
        }
    }

    public void playEasyMode(Board board) {
        if (!GameUtils.isEndGame()) {
            if (GameUtils.turn % 2 == 1) {

                Random getRandomPosition = new Random();
                List<Integer> freePositions = GameUtils.getFreeSpots(GameUtils.getBoard().getGrid());
                int randomPosition = freePositions.get(getRandomPosition.nextInt(freePositions.size()));
                for (var iterator : GameUtils.getBoard().getGrid().entrySet()) {
                    if (iterator.getKey().getId().equals(String.valueOf(randomPosition))) {
                        GameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);
                        var checkForWinning = CheckVictory.checkWin(GameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
                            PlayWithoutLoginController.accessMessageLabel.setText(checkForWinning.getIndex() == 0 ? "You won!" : "Bot won!");
                            GameUtils.setEndGame(true);
                        } else {
                            PlayWithoutLoginController.accessMessageLabel.setText("Your turn!");
                        }
                        GameUtils.turn++;
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