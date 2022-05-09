package controllers;

import com.company.user.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jpa.entity.TictactoeUsersEntity;

import java.util.List;

public class StatisticsController {
    @FXML
    private ScrollPane statisticsScrollPane;
    @FXML
    private TextArea currentTop;
    @FXML
    private Button seeStatisticsButton;

    public void updateStatistics() {
        statisticsScrollPane.setVisible(true);
        seeStatisticsButton.setVisible(false);
        List<TictactoeUsersEntity> users = UserRepository.getStatistics();
        StringBuilder result = new StringBuilder();
        for (int i = 0, n = users.size(); i < n; i++) {
            result.append(i + 1);
            result.append(". ");
            result.append(users.get(i).getUsername());
            if (i != n - 1) {
                result.append("\n");
            }
        }
        currentTop.setText(result.toString());
        statisticsScrollPane.setContent(currentTop);
    }

    public void goToHomeScene(ActionEvent event) {
        try {
            statisticsScrollPane.setVisible(false);
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
