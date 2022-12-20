
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.util.Locale;

public class Result extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage, String color) {
        // Create a label to display the result of the chess game
        Label resultLabel = new Label();
        resultLabel.setText(color.toUpperCase(Locale.ROOT)+" WON THE GAME!");
        resultLabel.setStyle("-fx-font-size : 20px; -fx-color : rgb(256,0,0); -fx- text-align : center; -fx-background-image : url(result.jpeg);");
        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().addAll(this.getClass().getResource("result.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        /*// Create a vertical box layout to hold the result label
        VBox root = new VBox();
        root.getChildren().add(resultLabel);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();*/
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}