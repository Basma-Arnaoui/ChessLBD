import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region.*;
import javafx.scene.layout.StackPane;


import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

import static javafx.scene.paint.Color.*;
public class Position extends Button{
    ChessBoard board;
    int x,y;
    boolean isOccupied;
    Piece occupyingPiece;
    String theme;

    public Position(int x, int y, ChessBoard board){
        this.x = x;
        this.y = y;
        occupyingPiece = null;
        isOccupied = false;
        this.board = board;
        this.theme = LandingPage.Theme;
        this.setColor();
    }
    public void setColor()
    {
        Color[] Classic = {BROWN,WHITESMOKE};
        Color[] Special = {BROWN,YELLOW};
        Color[] Dark = {DARKBLUE,WHITE};
        if (this.theme.equals("Classic"))
        {
            if ((x+y)%2 == 0){
                this.setBackground(new Background(new BackgroundFill(Classic[0], null,null)));;
            }
            else {
                this.setBackground(new Background(new BackgroundFill(Classic[1],null,null)));;
            }
        }
        if (this.theme.equals("Special"))
        {
            if ((this.x+this.y)%2 == 0){
                this.setBackground(new Background(new BackgroundFill(Special[0], null,null)));;
            }
            else {
                this.setBackground(new Background(new BackgroundFill(Special[1],null,null)));;
            }
        }
        if (this.theme.equals("Dark"))
        {
            if ((this.x+this.y)%2 == 0){
                this.setBackground(new Background(new BackgroundFill(Dark[0], null,null)));;
            }
            else {
                this.setBackground(new Background(new BackgroundFill(Dark[1],null,null)));;
            }
        }
    }
    public Piece getOccupyingPiece(){
        return this.occupyingPiece;
    }
    public void addPiece(Piece p){
        this.occupyingPiece = p;
        this.isOccupied = true;
    }
    public void deletePiece(){
        this.getOccupyingPiece().setPosition(null);
        this.getOccupyingPiece().setAlive(false);
        this.occupyingPiece = null;
        this.isOccupied = false;
    }

    public void changeSelectedCellColor(){
        this.setBackground(new Background(new BackgroundFill(GREEN,null,null)));
    }

    public void changeColor(){
        this.setColor();
    }
    public void possiblePositions(){
        this.setBackground(new Background(new BackgroundFill(LIGHTBLUE, null, null)));
    }
    public void attackPositions(){
        this.setBackground(new Background(new BackgroundFill(RED,null,null)));
    }
    public boolean getIsOccupied(){
        return this.isOccupied;
    }
    public void setIsOccupied(boolean oc){
        this.isOccupied = oc;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y=y;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public void setImage(String n, String col){
        this.setGraphic(new Image().getImage(n, col));
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
