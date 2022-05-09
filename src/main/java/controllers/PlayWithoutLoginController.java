package controllers;

import com.company.GameWithOnePlayer;
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
    private ColorPicker currentColor;
    @FXML
    private GridPane gameGrid;
    @FXML
    private Spinner<Integer> rowSpinner = new Spinner<>();
    @FXML
    private Spinner<Integer> columnSpinner = new Spinner<>();


    public void drawGrid(Event event) {
        message.setText("Your turn!");
        int n = GameWithOnePlayer.getNumberOfRows(), m = GameWithOnePlayer.getNumberOfColumns();
        gameGrid.setAlignment(Pos.CENTER);
        gameGrid.setPadding(new Insets(10,10,10,10));

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                Button button = new Button();
                button.setMinWidth(30);
                button.setMinHeight(30);
                gameGrid.add(button, c, r);
            }
        }
    }

    public void selectEasyMode(ActionEvent event) {
        try {
            GameWithOnePlayer.setDifficulty("easy");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectMediumMode(ActionEvent event) {
        try {
            GameWithOnePlayer.setDifficulty("medium");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectHardMode(ActionEvent event) {
        try {
            GameWithOnePlayer.setDifficulty("hard");
            goToChooseBoardSizeScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectNumberOfRowsAndColumns(ActionEvent event) {
        try {
            GameWithOnePlayer.setNumberOfRows(rowSpinner.getValue());
            GameWithOnePlayer.setNumberOfColumns(columnSpinner.getValue());
            goToChooseColorScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueForRows = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 8, 3);
        SpinnerValueFactory<Integer> valueForColumns = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 8, 3);

        rowSpinner.setValueFactory(valueForRows);
        columnSpinner.setValueFactory(valueForColumns);
    }

    public void selectColor(ActionEvent event) {
        try {
            GameWithOnePlayer.setPlayerColor(currentColor.getValue());
            goToBoardAIScene(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void goToChooseColorScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/colorScene.fxml"));
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
