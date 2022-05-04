package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//public class Main {
//    public static void main(String[] args) {
//        //java -jar C:\Users\Asus\Documents\GitHub\TicTacToe\out\artifacts\TicTacToe_jar\TicTacToe.jar
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setVisible(true);
//    }
//}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/introScene.fxml"));
            primaryStage.setTitle("TicTacToe");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
