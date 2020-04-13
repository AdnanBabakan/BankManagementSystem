package MainStage;

import com.app.Config;
import com.app.DBConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Config.initialize();
        DBConnection.connect(Config.getConfig("db_file"));

        DBConnection.run("INSERT INTO accounts (FirstName, LastName, Password, Balance) VALUES ('Adnan', 'Babakan', '123456', '1000')");

        Parent root = FXMLLoader.load(getClass().getResource("MainFrame.fxml"));
        primaryStage.setTitle(Config.getConfig("title"));
        primaryStage.setScene(new Scene(root, Integer.parseInt(Config.getConfig("width")), Integer.parseInt(Config.getConfig("height"))));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
                primaryStage.setOpacity(.8);
            }
        });

        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setOpacity(1);
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() - xOffset);
                primaryStage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
