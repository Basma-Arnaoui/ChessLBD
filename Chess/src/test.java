import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file and create the scene
        Parent root = FXMLLoader.load(getClass().getResource("landingPage.fxml"));
        Scene scene = new Scene(root);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
