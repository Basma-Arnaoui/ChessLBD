import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import javafx.scene.control.Labeled;
import java.util.Stack;

public class LandingPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Application");

        // Create a label for the title
        /* Label titleLabel = new Label("Welcome to the Chess Application");
        titleLabel.setStyle("-fx-color: #ffffff; -fx-font-size: 42px; -fx-font-weight: 800; -fx-position: absolute; -fx-top: 40; -fx-left: 25;"); */
        // Create the Text
        Text text = new Text("Welcome to Chess Game");
        text.setStyle("-fx-font-size: 50px; -fx-font-weight: 700;");
        text.setFill(Color.WHITE);
        text.setTranslateY(-300);
        // Create an image view for the chessboard image
        Image chessboardImage = new Image("images/chessboard.jpg");
        ImageView background = new ImageView(chessboardImage);
        background.setFitWidth(1000);
        background.setFitHeight(1000);
        // Set the Contrast of the image
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setContrast(0.15);
        Effect imageEffect = colorAdjust;
        background.setEffect(imageEffect);


        // Create a button for starting a new game
        Button newGameButton = new Button("Start New Game");
        newGameButton.setStyle("-fx-font-size: 30px; -fx-padding: 15px 50px; -fx-font-weight: bold; -fx-background-color: white; -fx-border-radius: 12px");
        newGameButton.setTranslateY(-175);
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    new TestDesign().start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Create buttons for choosing the theme
        RadioButton ClassicThemeCheckBox =  new RadioButton("Classic");
        ClassicThemeCheckBox.setTextFill(Color.LIGHTBLUE);
        ClassicThemeCheckBox.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        ClassicThemeCheckBox.setTranslateY(-20);
        ClassicThemeCheckBox.setSelected(true);
        RadioButton DarkThemeCheckBox = new RadioButton("Dark");
        DarkThemeCheckBox.setTextFill(Color.LIGHTBLUE);
        DarkThemeCheckBox.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        DarkThemeCheckBox.setTranslateY(-20);
        RadioButton SpecialThemeCheckBox = new RadioButton("Special");
        SpecialThemeCheckBox.setTextFill(Color.LIGHTBLUE);
        SpecialThemeCheckBox.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        SpecialThemeCheckBox.setTranslateY(-20);

        // Create an array of theme buttons
        RadioButton[] themeButtons = new RadioButton[3];
        themeButtons[0] = ClassicThemeCheckBox;
        themeButtons[1] = DarkThemeCheckBox;
        themeButtons[2] = SpecialThemeCheckBox;

        // Add click event to all themes
        for (RadioButton themeButton: themeButtons) {
            themeButton.setOnAction(event -> {
                // Deselect all other radio buttons
                for (RadioButton btn: themeButtons) {
                    if (btn != themeButton) {
                        btn.setSelected(false);
                    }
                }
                // Select the current radio button
                themeButton.setSelected(true);
            });
        }


        // Create a Horizontal Box to hold the buttons
        HBox themesBox = new HBox(ClassicThemeCheckBox, DarkThemeCheckBox, SpecialThemeCheckBox);
        themesBox.setAlignment(Pos.BOTTOM_CENTER);
        themesBox.setSpacing(75);

        // Create a vertical Box to hold the buttons
        VBox buttonBox = new VBox(newGameButton);
        buttonBox.setSpacing(30);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setTranslateY(-200);
        // Create a stack Pane to hold the background image and the buttons
        StackPane root = new StackPane(background, text, buttonBox, themesBox, newGameButton);

        // Set the size of the window and show it
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

