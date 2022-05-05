package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class IntroController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToIntroScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/introScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToLoginScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/loginScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToRegisterScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/registerScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToPlayWithNoAccountScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/difficultyScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToBoardAIScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/boardAIScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToChooseBoardSizeScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/boardSizeScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToChooseColorScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/colorScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToBoardScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/boardScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToHomeScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/homeScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToStatisticsScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/statisticsScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToSettingsScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("../fxmlFiles/settingsScene.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitApp() {
        Platform.exit();
    }

}