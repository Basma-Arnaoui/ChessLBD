
import javafx.scene.image.ImageView;

import java.util.ArrayList;
public abstract class Piece {
    String color;
    String name;
    boolean isAlive;
    Position position;
    ImageView image;
    Piece(String color, String name, Position pos){
        this.color = color;
        this.name = name;
        this.position = pos;
        this.isAlive = true;

    }
    public abstract ArrayList<Position> possibleMoves();
     public void delete(){
         this.isAlive = false;
         this.position = null;
     }

    public ImageView getImage() {
        return new Image().getImage(name,color);
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
