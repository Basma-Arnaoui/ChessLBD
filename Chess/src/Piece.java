
import javafx.scene.image.ImageView;

import java.util.ArrayList;
public abstract class Piece {
    String color;
    int side;
    String name;
    boolean isAlive;
    Position position;
    int firstTime;
    ImageView image;
    Piece(String color, String name, Position pos){
        this.color = color;
        this.name = name;
        this.position = pos;
        this.isAlive = true;
        this.firstTime = 0;
        this.side = 0;
    }
    public abstract ArrayList<Position> possibleMoves();
     public void delete(){
         this.isAlive = false;
         this.position = null;
     }
     public int getSide(){
         return this.side;
     }
     public int getFirstTime(){
         return this.firstTime;
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

    public void changeFirstTime(){
        this.firstTime = 1;
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
    public ArrayList<Position> canbeenpassed(ArrayList<Position> a){return null;}
}

