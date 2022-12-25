
import javafx.scene.image.ImageView;

public class Image {

    public ImageView getImage(String name, String color){

        ImageView img = new ImageView("Images/"+ color+name+ ".png");
        // Set the Chess Pieces images' height and width
        img.setFitHeight(75);
        img.setFitWidth(75);

        return img;
    }
}
