package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static boolean hasRun = false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/convert.fxml"));
        hasRun = true;
        primaryStage.setTitle("Valuta Kalkulator");
        primaryStage.setScene(new Scene(root, 800, 556));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static boolean getHasRun(){
        return hasRun;
    }
}



