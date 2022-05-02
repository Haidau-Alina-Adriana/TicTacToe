package sample;

import com.company.MainFrame;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        //java -jar C:\Users\Asus\Documents\GitHub\TicTacToe\out\artifacts\TicTacToe_jar\TicTacToe.jar
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}

//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
////        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
////        primaryStage.setTitle("Hello World");
////        primaryStage.setScene(new Scene(root));
////        primaryStage.show();
//
//        //java -jar C:\Users\Asus\Documents\GitHub\Proiect-TicTacToe\out\artifacts\TicTacToe_jar\TicTacToe.jar
//        MainFrame mainFrame = new MainFrame();
//        mainFrame.setVisible(true);
//    }
//
//
////    public static void main(String[] args) {
////        launch(args);
////    }
//}
