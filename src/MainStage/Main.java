package MainStage;

import com.app.Config;
import com.app.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    Stage window;

    Pane mainPane, userPane;
    Scene mainScene, userScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.initialize();
        DBConnection.connect(Config.getConfig("db_file"));

        window = primaryStage;

        // Main frame
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("MainFrame.fxml"));
        mainPane = loader.load();
        Controller controller = loader.getController();
        mainScene = new Scene(mainPane);
        controller.setMain(this);

        // User frame
        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("UserScene.fxml"));
        userPane = loader.load();
        UserSceneController userSceneController = loader.getController();
        userScene = new Scene(userPane);
        userSceneController.setMain(this);

        // References
        controller.setUserSceneReference(userScene);
        controller.setUserSceneControllerReference(userSceneController);
        userSceneController.setMainScene(mainScene);

        primaryStage.setTitle(Config.getConfig("title"));
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public void setScene(Scene scene) {
        window.setScene(scene);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
