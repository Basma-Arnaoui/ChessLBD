
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
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
                    timerLabel.setText("Time elapsed: " + convertSecondsToMinutesSeconds(elapsedTime));
                }
            };
            timer.start();
            pane.setAlignment(timerLabel, Pos.BOTTOM_LEFT);
            timerLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-background-color: rgb(20,106,141); -fx-padding: 10px;-fx-text-fill:rgb(223,233,185);");

        // new Label of each game player

        Label WhitePlayer = new Label();
        Label BlackPlayer = new Label();
        pane.getChildren().add(WhitePlayer);
        pane.getChildren().add(BlackPlayer);

        BlackPlayer.setStyle("-fx-font-size: 24pt;-fx-text-fill:White; -fx-font-weight: bold; -fx-background-color: Black; -fx-padding: 12px;");
        WhitePlayer.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold; -fx-background-color: White; -fx-padding: 12px;");

        pane.setAlignment(BlackPlayer,Pos.TOP_RIGHT);
        pane.setStyle("-fx-background-color : rgb(109,103,110);");
        WhitePlayer.setTranslateY(190);
        WhitePlayer.setTranslateX(310);

        BlackPlayer.setText("Black Player");
        WhitePlayer.setText("White Player");


        Label a = new Label();
        pane.getChildren().add(a);
        a.setText("a");
        a.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        a.setTranslateX(-375);
        a.setTranslateY(268);

        Label b = new Label();
        pane.getChildren().add(b);
        b.setText("b");
        b.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        b.setTranslateX(-300);
        b.setTranslateY(268);

        Label c = new Label();
        pane.getChildren().add(c);
        c.setText("c");
        c.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        c.setTranslateX(-225);
        c.setTranslateY(268);

        Label d = new Label();
        pane.getChildren().add(d);
        d.setText("d");
        d.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        d.setTranslateX(-150);
        d.setTranslateY(268);

        Label e = new Label();
        pane.getChildren().add(e);
        e.setText("e");
        e.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        e.setTranslateX(-75);
        e.setTranslateY(268);

        Label f = new Label();
        pane.getChildren().add(f);
        f.setText("f");
        f.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        f.setTranslateX(0);
        f.setTranslateY(268);

        Label g = new Label();
        pane.getChildren().add(g);
        g.setText("g");
        g.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        g.setTranslateX(75);
        g.setTranslateY(268);

        Label h = new Label();
        pane.getChildren().add(h);
        h.setText("h");
        h.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        h.setTranslateX(150);
        h.setTranslateY(268);

        Label aa = new Label();
        pane.getChildren().add(aa);
        aa.setText("1");
        aa.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        aa.setTranslateX(205);
        aa.setTranslateY(-310);

        Label bb = new Label();
        pane.getChildren().add(bb);
        bb.setText("2");
        bb.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        bb.setTranslateX(205);
        bb.setTranslateY(-235);

        Label cc = new Label();
        pane.getChildren().add(cc);
        cc.setText("3");
        cc.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        cc.setTranslateX(205);
        cc.setTranslateY(-160);

        Label dd = new Label();
        pane.getChildren().add(dd);
        dd.setText("4");
        dd.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        dd.setTranslateX(205);
        dd.setTranslateY(-85);

        Label ee = new Label();
        pane.getChildren().add(ee);
        ee.setText("5");
        ee.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        ee.setTranslateX(205);
        ee.setTranslateY(-10);

        Label ff = new Label();
        pane.getChildren().add(ff);
        ff.setText("6");
        ff.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        ff.setTranslateX(205);
        ff.setTranslateY(65);

        Label gg = new Label();
        pane.getChildren().add(gg);
        gg.setText("7");
        gg.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        gg.setTranslateX(205);
        gg.setTranslateY(140);

        Label hh = new Label();
        pane.getChildren().add(hh);
        hh.setText("8");
        hh.setStyle("-fx-font-size : 20pt; -fx-color : Beige;");
        hh.setTranslateX(205);
        hh.setTranslateY(215);

        TextField userTextField = new TextField();
        userTextField.setPrefWidth(600);
        userTextField.setStyle("-fx-background-color : Black;");
        stage.setScene(new Scene(userTextField));






        stage.setScene(new Scene(pane));
        stage.show();
    }
}