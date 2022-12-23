
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
        pane.setPrefSize(840,750);
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
        pane.setStyle("-fx-background-color : Bisque;");
        whitePlayer.setTranslateY(190);
        whitePlayer.setTranslateX(310);

        blackPlayer.setText("Black Player");
        whitePlayer.setText("White Player");


        Label a = new Label();
        pane.getChildren().add(a);
        a.setText("a");
        a.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        a.setTranslateX(-375);
        a.setTranslateY(250);

        Label b = new Label();
        pane.getChildren().add(b);
        b.setText("b");
        b.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        b.setTranslateX(-300);
        b.setTranslateY(250);

        Label c = new Label();
        pane.getChildren().add(c);
        c.setText("c");
        c.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        c.setTranslateX(-225);
        c.setTranslateY(250);

        Label d = new Label();
        pane.getChildren().add(d);
        d.setText("d");
        d.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        d.setTranslateX(-150);
        d.setTranslateY(250);

        Label e = new Label();
        pane.getChildren().add(e);
        e.setText("e");
        e.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        e.setTranslateX(-75);
        e.setTranslateY(250);

        Label f = new Label();
        pane.getChildren().add(f);
        f.setText("f");
        f.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        f.setTranslateX(0);
        f.setTranslateY(250);

        Label g = new Label();
        pane.getChildren().add(g);
        g.setText("g");
        g.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        g.setTranslateX(75);
        g.setTranslateY(250);

        Label h = new Label();
        pane.getChildren().add(h);
        h.setText("h");
        h.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        h.setTranslateX(150);
        h.setTranslateY(250);

        Label aa = new Label();
        pane.getChildren().add(aa);
        aa.setText("1");
        aa.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        aa.setTranslateX(205);
        aa.setTranslateY(-320);

        Label bb = new Label();
        pane.getChildren().add(bb);
        bb.setText("2");
        bb.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        bb.setTranslateX(205);
        bb.setTranslateY(-245);

        Label cc = new Label();
        pane.getChildren().add(cc);
        cc.setText("3");
        cc.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        cc.setTranslateX(205);
        cc.setTranslateY(-170);

        Label dd = new Label();
        pane.getChildren().add(dd);
        dd.setText("4");
        dd.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        dd.setTranslateX(205);
        dd.setTranslateY(-95);

        Label ee = new Label();
        pane.getChildren().add(ee);
        ee.setText("5");
        ee.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        ee.setTranslateX(205);
        ee.setTranslateY(-20);

        Label ff = new Label();
        pane.getChildren().add(ff);
        ff.setText("6");
        ff.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        ff.setTranslateX(205);
        ff.setTranslateY(55);

        Label gg = new Label();
        pane.getChildren().add(gg);
        gg.setText("7");
        gg.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        gg.setTranslateX(205);
        gg.setTranslateY(130);

        Label hh = new Label();
        pane.getChildren().add(hh);
        hh.setText("8");
        hh.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        hh.setTranslateX(205);
        hh.setTranslateY(205);




        pane.setAlignment(blackPlayer,Pos.TOP_RIGHT);
        whitePlayer.setTranslateY(210);
        whitePlayer.setTranslateX(320);


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