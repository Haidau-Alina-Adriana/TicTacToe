package controllers;

import AIGame.Board;
import com.company.user.Player;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import network.Client;
import network.GameForPlayerVsPlayer;
import network.Server;
import utils.AIGameUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayController implements Initializable{

        @FXML
        private Label message;
        @FXML
        private GridPane gameGrid;
        @FXML
        private Button startBtn;

        public static Label accessMessageLabel;
        private static final int SIZE = 8;

        public void drawGrid(Event event) {
            AIGameUtils.setNumberOfRows(SIZE);
            startBtn.setDisable(true);
            Board board = new Board();

            message.setText("Your turn!");
            gameGrid.setAlignment(Pos.CENTER);
            gameGrid.setPadding(new Insets(10, 10, 10, 10));

            for (int r = 0; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    Button button = new Button();
                    button.setId(String.valueOf(r * SIZE + c));
                    button.setMinWidth(30);
                    button.setMinHeight(30);
                    button.setOnAction(event1 -> {
                        new GameForPlayerVsPlayer().userMove(button);
                    });
                    gameGrid.add(button, c, r);
                    board.addPiece(button);
                }
            }
            board.addPlayer(new Player(0));
            board.addPlayer(new Player(1));
            AIGameUtils.setBoard(board);

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            accessMessageLabel = message;
            new Client();
        }

        public void goBack(ActionEvent event) {
            try {
                AIGameUtils.initializeGrid();
                AIGameUtils.setEndGame(false);
                Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/homeScene.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
