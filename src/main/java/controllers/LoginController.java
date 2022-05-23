package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import network.Server;
import org.apache.commons.validator.routines.EmailValidator;
import utils.ConnectToAppUtils;

public class LoginController {
    @FXML
    private Label messageLabel;
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;

    public void checkCredentials(ActionEvent event) {
        try {
            if (inputEmail.getText().trim().isEmpty()) {
                messageLabel.setText("Email cannot be empty!");
            } else if (inputPassword.getText().trim().isEmpty()) {
                messageLabel.setText("Password cannot be empty!");
            } else if (!EmailValidator.getInstance().isValid(inputEmail.getText())) {
                messageLabel.setText("Not a valid email!");
            } else {
                String returnedMessage = ConnectToAppUtils.loginIntoAccount(
                        inputEmail.getText(), inputPassword.getText());
                if (returnedMessage.equals("Succes!")) {
                    goToHomeScene(event);
                } else {
                    messageLabel.setText(returnedMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToHomeScene(ActionEvent event) {
        try {
//            Server startServer = new Server();
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/homeScene.fxml"));
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
}
