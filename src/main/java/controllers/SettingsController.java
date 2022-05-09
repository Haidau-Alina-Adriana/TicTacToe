package controllers;

import com.company.user.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.SessionUtils;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SettingsController {
    @FXML
    private AnchorPane changeUsername;
    @FXML
    private TextField usernameInfo;
    @FXML
    private Label messageUsername;

    @FXML
    private AnchorPane changeEmail;
    @FXML
    private TextField emailInfo;
    @FXML
    private Label messageEmail;

    public void changeUsername() {
        changeUsername.setVisible(true);
    }

    public void saveUsernameChanges() {
        if (usernameInfo.getText().equals("")) {
            messageUsername.setText("Input cannot be empty!");
        } else {
            String inputUsername = usernameInfo.getText();
            UserRepository userRepo = new UserRepository();
            if (userRepo.findByUsername(inputUsername) != 0) {
                messageUsername.setText("Username already exists!");
            } else {
                userRepo.updateUsername(SessionUtils.getUser(), inputUsername);
                usernameInfo.setText("");
                changeUsername.setVisible(false);
            }
        }
    }

    public void cancelUsernameChanges() {
        changeUsername.setVisible(false);
    }

    public void changeEmail() {
        changeEmail.setVisible(true);
    }


    public void saveEmailChanges() {
        if (emailInfo.getText().equals("")) {
            messageEmail.setText("Input cannot be empty!");
        } else {
            String inputEmail = emailInfo.getText();
            UserRepository userRepo = new UserRepository();
            if (!userRepo.findByEmail(inputEmail).isEmpty()) {
                messageUsername.setText("Email already exists!");
            } else {
                userRepo.updateEmail(SessionUtils.getUser(), inputEmail);
                emailInfo.setText("");
                changeEmail.setVisible(false);
            }
        }
    }

    public void cancelEmailChanges() {
        changeEmail.setVisible(false);
    }

    public void deleteAccount(ActionEvent event) {
        UserRepository userRepo = new UserRepository();
        userRepo.deleteUser(SessionUtils.getUser());
        SessionUtils.logout();
        goToIntroScene(event);
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

    public void goToHomeScene(ActionEvent event) {
        try {
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
