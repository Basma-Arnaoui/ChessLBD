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
import javax.swing.SingleSelectionModel;

public class LandingPage extends Application {
    static String Theme;
    static int duration;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Application");

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
        colorAdjust.setContrast(0.1);
        // Effect imageEffect = colorAdjust;
        background.setEffect(colorAdjust);


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
        // Set the first theme button as selected by default
        LandingPage.setTheme(themeButtons[0].getText());


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
                LandingPage.setTheme(themeButton.getText());
            });
        }


        // Create a time button
        MenuButton menuButton = new MenuButton("Duration");
        MenuItem Item1 = new MenuItem("1");
        MenuItem Item2 = new MenuItem("2");
        MenuItem Item3 = new MenuItem("15");
        MenuItem Item4 = new MenuItem("20");
        MenuItem Item5 = new MenuItem("25");
        MenuItem Item6 = new MenuItem("30");
        menuButton.getItems().addAll(Item1, Item2,Item3, Item4,Item5,Item6);
        menuButton.setStyle("-fx-background-color: lightgray; -fx-font-size: 18px;");

        final String[] selectedOption = {"1"};
        // create action event
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                selectedOption[0] = ((MenuItem)e.getSource()).getText() ;
                menuButton.setText(selectedOption[0]);
                LandingPage.setDuration(Integer.parseInt(selectedOption[0]));
                //Label lab = new Label("The time for each player will be "+selectedOption[0]+" min.");
            }
        };

        // add action events to the menu items
        Item1.setOnAction(event1);
        Item2.setOnAction(event1);
        Item3.setOnAction(event1);
        Item4.setOnAction(event1);
        Item5.setOnAction(event1);
        Item6.setOnAction(event1);





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
        StackPane root = new StackPane(background, text, buttonBox, themesBox, newGameButton,menuButton);

        // Set the size of the window and show it
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void setTheme(String Theme) {
        LandingPage.Theme = Theme;
    }


    public static void setDuration(int dura){
        LandingPage.duration = dura;
    }
    public String getTheme() {
        return Theme;
    }
}
