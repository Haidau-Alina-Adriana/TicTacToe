import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //java -jar C:\Users\Asus\Documents\GitHub\TicTacToe\out\artifacts\TicTacToe_jar\TicTacToe.jar
        try {
            Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/introScene.fxml"));
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
