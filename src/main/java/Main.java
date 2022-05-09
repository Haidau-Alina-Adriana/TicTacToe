import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double BUTTON_PADDING = 0;

    @Override
    public void start(Stage primaryStage) {

//
//        int n = 3, m = 3;
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        primaryStage.setMinWidth(240);
//        primaryStage.setMinHeight(240);
//
//        grid.setPadding(new Insets(BUTTON_PADDING));
//
//        for (int r = 0; r < n; r++) {
//            for (int c = 0; c < m; c++) {
//                Button button = new Button();
//                button.setMinWidth(30);
//                button.setMinHeight(30);
//                grid.add(button, c, r);
//            }
//        }
//        ScrollPane scrollPane = new ScrollPane(grid);
//
//
//        primaryStage.setScene(new Scene(scrollPane));
//        primaryStage.show();


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
