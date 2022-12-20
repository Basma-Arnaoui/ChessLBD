import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Effect;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;


import java.awt.*;
import java.util.Locale;

public class Result extends Application {
    public ChessBoard board;
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage, String color) {
        // Mohieddine Code

        //
        Label titleLabel = new Label("The " + ChessBoard.winner + " won the game !");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: 700;");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setTranslateY(-150);

        Label timeElapsedLabel = new Label("Time elapsed: " + TestDesign.convertSecondsToMinutesSeconds(TestDesign.elapsedTime));
        timeElapsedLabel.setStyle("-fx-padding: 15px 25px; -fx-background-color: white; -fx-font-weight: 800; -fx-font-size: 34px;");
        // Setting the background image
        Image endGameImage = new Image("images/endgame.jpg");
        ImageView background = new ImageView(endGameImage);
        background.setFitWidth(1000);
        background.setFitHeight(1000);

        // Set the Contrast of the image
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0);
        // Effect imageEffect = colorAdjust;
        background.setEffect(colorAdjust);

        // Restarting a Game button
        Button restartGameButton = new Button("Restart Game");
        // restartGameButton.getScene().getStylesheets().add("css/restart-btn.css");
        restartGameButton.setStyle("-fx-style-sheet: url('css/restart-btn.css')");

        // Create a Horizontal Box for elapsed time and restart game
        HBox box = new HBox(timeElapsedLabel, restartGameButton);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(120);
        box.setTranslateY(100);

        StackPane root = new StackPane(background, titleLabel, box);
        Scene scene =  new Scene(root, 1000, 1000);
        // scene.getStylesheets().add("css/restart-btn.css");

        // Show the final window
        stage.setScene(scene);
        stage.show();

        // Basma code
        /*
        // Create a label to display the result of the chess game
        Label resultLabel = new Label();
        resultLabel.setText(color.toUpperCase(Locale.ROOT)+" WON THE GAME!");
        resultLabel.setStyle("-fx-font-size : 20px; -fx-color : rgb(256,0,0); -fx- text-align : center; -fx-background-image : url(result.jpeg);");

        StackPane root = new StackPane();
        root.setId("pane");
        Scene scene = new Scene(root, 800, 1000);
        scene.getStylesheets().addAll(this.getClass().getResource("result.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


        // Create a vertical box layout to hold the result label
        VBox root = new VBox();
        root.getChildren().add(resultLabel);

        // Set the scene and show the stage
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
        */
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}