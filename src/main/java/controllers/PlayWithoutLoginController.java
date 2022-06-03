package controllers;

import AIGame.GameForPlayerVsBot;
import com.company.user.Player;
import utils.GameUtils;
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

    public static Label accessMessageLabel;

    public void drawGrid(Event event) {
        startBtn.setDisable(true);
        Board board = new Board();

        message.setText("Your turn!");
        int n = GameUtils.getNumberOfRows();
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10, 10, 10, 10));

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Button button = new Button();
                button.setId(String.valueOf(r * n + c));
                button.setMinWidth(30);
                button.setMinHeight(30);
                button.setOnAction(event1 -> new GameForPlayerVsBot().userMove(button));
                gameGrid.add(button, c, r);
                board.addPiece(button);
            }
        }
        board.addPlayer(new Player(0));
        board.addPlayer(new Player(1));
        GameUtils.setBoard(board);
    }

    public void selectEasyMode(ActionEvent event) {
        try {
            GameUtils.setDifficulty("easy");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectMediumMode(ActionEvent event) {
        try {
            GameUtils.setDifficulty("medium");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectHardMode(ActionEvent event) {
        try {
            GameUtils.setDifficulty("hard");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectNumberOfRowsAndColumns(ActionEvent event) {
        try {
            GameUtils.setNumberOfRows(rowSpinner.getValue());
            goToBoardAIScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accessMessageLabel = message;
        SpinnerValueFactory<Integer> valueForRows = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 6, 3);
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

    public void restartGame(ActionEvent event) {
        GameUtils.initializeGrid();
        GameUtils.setEndGame(false);
        GameUtils.turn = 0;
        gameGrid.getChildren().clear();
        drawGrid(event);
    }

    public void goToIntroScene(ActionEvent event) {
        try {
            GameUtils.initializeGrid();
            GameUtils.setEndGame(false);
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
