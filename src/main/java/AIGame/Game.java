package AIGame;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.AIGameUtils;

import java.util.Map;


public class Game {
    private Image imageX = new Image(getClass().getResourceAsStream("../images/x.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/zero.png"));

    public void userMove(Button button){
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
//                        message.setText("Player " + checkForWinning.getIndex() + " has won!");
                        AIGameUtils.setEndGame(true);
                    } else {
//                        message.setText("Bot's turn!");
                    }
////
//                    for(var it: AIGameUtils.getBoard().getGrid().entrySet()){
//                        System.out.println("Key: " + it.getKey() + ", value: " + it.getValue());
//                    }
//                    System.out.println();

                    AIGameUtils.turn++;

                    Game game = new Game();
                    game.playHard(AIGameUtils.getBoard());
                }
            } else {
//                message.setText(resultMove);
            }
        }
    }

    public void playHard(Board board) {
        if (!AIGameUtils.isEndGame()) {
            if (AIGameUtils.turn % 2 == 1) {
//
//                for (var it : board.getGrid().entrySet()) {
//                    System.out.println("Key: " + it.getKey() + ", value: " + it.getValue());
//                }
//                System.out.println();


                Minimax minimax = new Minimax();
                int bestMove = minimax.getBestMove(board.getGrid(), board.getPlayers().get(1));
                for (var iterator : AIGameUtils.getBoard().getGrid().entrySet()) {

                    if (iterator.getKey().getId().equals(String.valueOf(bestMove))) {
                        AIGameUtils.getBoard().getGrid().put(iterator.getKey(), board.getPlayers().get(1));
                        ImageView img = new ImageView(imageX);
                        img.setFitWidth(20);
                        img.setFitHeight(20);
                        iterator.getKey().setGraphic(img);


//                        for (var it : board.getGrid().entrySet()) {
//                            System.out.println("Key: " + it.getKey() + ", value: " + it.getValue());
//                        }
//                        System.out.println();

//                        Board updatedBoard = new Board(board.getGrid(), board.getPlayers());
//                        updatedBoard.getGrid().put(iterator.getKey(), board.getPlayers().get(0));
//                        AIGameUtils.setBoard(board);



                        var checkForWinning = CheckVictory.checkWin(AIGameUtils.getBoard().getGrid());
                        if (checkForWinning != null) {
//                        message.setText("Player " + checkForWinning.getIndex() + " has won!");
                            AIGameUtils.setEndGame(true);
                        } else {
//                        message.setText("Player's " + AIGameUtils.turn % 2 + " turn!");
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
