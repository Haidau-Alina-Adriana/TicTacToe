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
import org.apache.commons.validator.routines.EmailValidator;
import utils.ConnectToAppUtils;

public class RegisterController {
    @FXML
    private Label messageLabel;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;

    public void checkCredentials(ActionEvent event) {
        try {
            if (inputUsername.getText().trim().isEmpty()) {
                messageLabel.setText("Username cannot be empty!");
            } else if (inputEmail.getText().trim().isEmpty()) {
                messageLabel.setText("Email cannot be empty!");
            } else if (inputPassword.getText().trim().isEmpty()) {
                messageLabel.setText("Password cannot be empty!");
            } else if (!EmailValidator.getInstance().isValid(inputEmail.getText())) {
                messageLabel.setText("Not a valid email!");
            } else {
                String returnedMessage = ConnectToAppUtils.createAccount(inputUsername.getText(),
                        inputEmail.getText(), inputPassword.getText());
                if (returnedMessage.equals("Account created!")) {
                    goToLoginScene(event);
                } else {
                    messageLabel.setText(returnedMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToLoginScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/loginScene.fxml"));
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
