package sample;

import com.app.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.initialize();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle(Config.getConfig("title"));
        primaryStage.setScene(new Scene(root, Integer.parseInt(Config.getConfig("width")), Integer.parseInt(Config.getConfig("height"))));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
