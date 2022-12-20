
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;


public class TestDesign extends Application {

    private long startTime;
    private Label timerLabel;

    protected static long elapsedTime;
    public static void main(String[] args) {
        //launch(args);
        Application.launch();
    }

    public static String convertSecondsToMinutesSeconds(long seconds) {
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return minutes + ":" + (remainingSeconds < 10 ? "0" : "") + remainingSeconds;
    }


    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        ChessBoard board = new ChessBoard();
        pane.getChildren().add(board);
        pane.setPrefSize(800,700);
        pane.setAlignment(board, Pos.CENTER);


        timerLabel = new Label();
        pane.getChildren().add(timerLabel);

            // Set up the timer
        startTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    elapsedTime = (now - startTime) / 1000000000;
                    timerLabel.setText("Time elapsed: " + convertSecondsToMinutesSeconds(elapsedTime));
                }
            };
            timer.start();
            pane.setAlignment(timerLabel, Pos.BOTTOM_LEFT);
            timerLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-background-color: black; -fx-padding: 10px;");


        stage.setScene(new Scene(pane));
        stage.show();
    }
}