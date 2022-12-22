import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import java.lang.RuntimeException;


public class Result extends Application {
    public ChessBoard board;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Mohieddine Code

        // Setting the background image
        Image endGameImage = new Image("images/endgame.jpg");
        ImageView background = new ImageView(endGameImage);
        background.setFitWidth(1000);
        background.setFitHeight(1000);

        // Announce the winner of the game
        Label titleLabel = new Label("The " + ChessBoard.winner + " won the game !");
        titleLabel.setStyle("-fx-font-size: 27pt; -fx-font-weight: 700;");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setTranslateY(65);

        // Set the duration of the game
        Label timeElapsedLabel = new Label("Time Elapsed: " + TestDesign.convertSecondsToMinutesSeconds(TestDesign.elapsedTime));
        timeElapsedLabel.setStyle("-fx-text-fill: white; -fx-padding: 15px 25px; -fx-background-color: transparent; -fx-font-weight: 800; -fx-font-size: 30pt; -fx-border-radius: 10px;");
        timeElapsedLabel.setTranslateY(45);

        // Set the Contrast of the image
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0);
        // Effect imageEffect = colorAdjust;
        background.setEffect(colorAdjust);

        // Restarting a Game button
        Button restartGameButton = new Button("Restart Game");
        restartGameButton.setStyle("-fx-style-sheet: url('css/restart-btn.css')");
        restartGameButton.setTranslateY(-200);
        restartGameButton.setPadding(new Insets(10, 30, 10, 30));
        restartGameButton.setStyle("-fx-text-fill: black; -fx-background-color: grey; -fx-font-size: 32pt; -fx-font-weight: 800; -fx-border-style: solid; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 8px");
        restartGameButton.setOpacity(0.6);


        restartGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new TestDesign().start(new Stage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Create a Horizontal Box for elapsed time and restart game
        HBox box = new HBox(titleLabel, timeElapsedLabel);
        box.setAlignment(Pos.TOP_CENTER);
        box.setSpacing(40);
        box.setTranslateY(100);

        StackPane root = new StackPane(background, box, restartGameButton);
        Scene scene =  new Scene(root, 1000, 1000);

        // Show the final window
        stage.setScene(scene);
        stage.show();
    }


}