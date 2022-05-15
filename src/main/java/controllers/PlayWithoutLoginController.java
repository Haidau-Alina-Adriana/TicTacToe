package controllers;

import AIGame.CheckVictory;
import AIGame.Game;
import AIGame.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import utils.AIGameUtils;
import AIGame.Board;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class PlayWithoutLoginController implements Initializable {
    @FXML
    private Label message;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Spinner<Integer> rowSpinner = new Spinner<>();
    @FXML
    private Button startBtn;

    private Image imageX = new Image(getClass().getResourceAsStream("../images/x.png"));
    private Image image0 = new Image(getClass().getResourceAsStream("../images/zero.png"));

    public void drawGrid(Event event) {
        startBtn.setDisable(true);

        message.setText("Player's " + AIGameUtils.turn % 2 + " turn!");
        int n = AIGameUtils.getNumberOfRows();
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10, 10, 10, 10));


        Board board = new Board();
        AIGameUtils.setBoard(board);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Button button = new Button();
                button.setMinWidth(30);
                button.setMinHeight(30);
                button.setOnAction(event1 -> {
                    String resultMove = AIGameUtils.validateMove(button, AIGameUtils.turn % 2);
                    if (resultMove.equals("ok")) {
                        if (AIGameUtils.turn % 2 == 0) {
                            ImageView img = new ImageView(image0);
                            img.setFitWidth(20);
                            img.setFitHeight(20);
                            button.setGraphic(img);
                        } else {
                            ImageView img = new ImageView(imageX);
                            img.setFitWidth(20);
                            img.setFitHeight(20);
                            button.setGraphic(img);
                        }
                        AIGameUtils.turn++;

                        var checkForWinning = CheckVictory.checkWin(board.getGrid());
                        if (checkForWinning != null) {
                            message.setText("Player " + checkForWinning.getIndex() + " has won!");
                        } else {
                            message.setText("Player's " + AIGameUtils.turn % 2 + " turn!");
                        }
                    } else {
                        message.setText(resultMove);
                    }
                });
                gameGrid.add(button, c, r);
                board.addPiece(button);
            }
        }
        board.addPlayer(new Player(0));
        board.addPlayer(new Player(1));
        AIGameUtils.setBoard(board);
        Game game = new Game();
//        game.play();
//
//        new Thread(AIGameUtils.getBoard().getPlayers().get(0)).start();
//        new Thread(AIGameUtils.getBoard().getPlayers().get(1)).start();
    }

    public void selectEasyMode(ActionEvent event) {
        try {
            AIGameUtils.setDifficulty("easy");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectMediumMode(ActionEvent event) {
        try {
            AIGameUtils.setDifficulty("medium");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectHardMode(ActionEvent event) {
        try {
            AIGameUtils.setDifficulty("hard");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectNumberOfRowsAndColumns(ActionEvent event) {
        try {
            AIGameUtils.setNumberOfRows(rowSpinner.getValue());
            goToBoardAIScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueForRows = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 8, 3);
        rowSpinner.setValueFactory(valueForRows);
    }

    public void goToChooseBoardSizeScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/boardSizeScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToIntroScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/introScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToBoardAIScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/boardAIScene.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
