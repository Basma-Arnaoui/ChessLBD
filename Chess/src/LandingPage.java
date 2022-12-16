import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LandingPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Application");

        /* StackPane root = new StackPane();
        Scene scene = new Scene(root, 650, 650);
        root.setStyle("-fx-background-image: url('images/chessboard.jpg'); -fx-background-repeat: no-repeat; -fx-background-size: 500 500; -fx-background-position: center center;");
        primaryStage.setScene(scene);
        //primaryStage.show(); */

        // Create a label for the title
        Label titleLabel = new Label("Welcome to the Chess Application");
        titleLabel.setStyle("-fx-font-size: 42px; -fx-font-weight: 800;");

        // Create an image view for the chessboard image
        Image chessboardImage = new Image("images/chessboard.jpg");
        ImageView chessboardImageView = new ImageView(chessboardImage);
        chessboardImageView.setFitWidth(500);
        chessboardImageView.setPreserveRatio(true);

        // Create a button for starting a new game
        Button newGameButton = new Button("Start New Game");
        newGameButton.setStyle("-fx-font-size: 24px; -fx-padding: 10px 25px; -fx-margin-top: 12px; -fx-font-weight: bold;");
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StackPane pane = new StackPane();
                ChessBoard board = new ChessBoard();
                pane.getChildren().add(board);
                pane.setPrefSize(600,600);
                pane.setAlignment(board, Pos.CENTER);
                primaryStage.setScene(new Scene(pane));
                primaryStage.show();
                //primaryStage.setScene(new Scene(pane));
            }
        });

        // Create a horizontal box for the buttons
        HBox buttonBox = new HBox(50, newGameButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a vertical box for the title and buttons
        VBox mainBox = new VBox(10, titleLabel, chessboardImageView, buttonBox);
        mainBox.setPadding(new Insets(10));
        mainBox.setAlignment(Pos.CENTER);

        // Set the vertical box as the center of the border pane
        BorderPane root = new BorderPane();
        root.setCenter(mainBox);

        // Set the border pane as the root node of the scene and show the stage
        primaryStage.setScene(new Scene(root, 800, 550));
        primaryStage.show();
    }
}

