
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        pane.setPrefSize(810,750);
        pane.setAlignment(board, Pos.CENTER);


        timerLabel = new Label();
        pane.getChildren().add(timerLabel);

            // Set up the timer
        startTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    elapsedTime = (now - startTime) / 1000000000;
                    if (elapsedTime==LandingPage.duration*60)
                    {
                        ChessBoard.winner = "";
                        new Result().start(new Stage());

                    }
                    else{
                        timerLabel.setText("Time elapsed: " + convertSecondsToMinutesSeconds(elapsedTime));
                    }

                }
            };
            timer.start();
            pane.setAlignment(timerLabel, Pos.BOTTOM_LEFT);
            timerLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-background-color: black; -fx-padding: 10px;");

        // new Label of each game player

        Label whitePlayer = new Label();
        Label blackPlayer = new Label();
        pane.getChildren().add(whitePlayer);
        pane.getChildren().add(blackPlayer);

        blackPlayer.setStyle("-fx-font-size: 24pt;-fx-color:White; -fx-font-weight: bold; -fx-background-color: Black; -fx-padding: 12px;");
        whitePlayer.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-background-color: White; -fx-padding: 12px;");



        pane.setAlignment(blackPlayer,Pos.TOP_RIGHT);
        whitePlayer.setTranslateY(190);
        whitePlayer.setTranslateX(310);


        whitePlayer.setText("White Player");
        blackPlayer.setText("Black Player");

        // Exit
        Button exit = new Button("Exit");
        exit.setStyle("-fx-text-fill: black; -fx-background-color: grey; -fx-font-size: 25pt; -fx-font-weight: 800; -fx-border-style: solid; -fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 8px");
        pane.setAlignment(exit, Pos.BOTTOM_RIGHT);

        // Exit the game when the ExitButton is clicked
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new LandingPage().start(new Stage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pane.getChildren().add(exit);

        stage.setScene(new Scene(pane));
        stage.show();
    }
}