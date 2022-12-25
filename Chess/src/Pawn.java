
import javafx.scene.PointLight;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Pawn extends Piece {

    int direction; // 1 if "black", -1 if "white"
    int firstTime;
    int side;

    public Pawn(String color, Position position) {
        super(color, "pawn", position);
        if (color.equals("black"))
            direction = 1; // To move down the chessboard (as "black" are above)
        else
            direction = -1; // To move up the chessboard (as "white" are below)

        this.position.setImage("pawn", color);
        this.firstTime = 0; // Was never moved before
        this.side = 0;


    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        System.out.println("Position: X: " + x + " Y: " + y);
        Position[][] positions = position.getBoard().getPositions();

        // Check if the Pawn can attack a Piece at the up-right (or down-right) position depending on the direction
        if ((x + direction) < 8 && (y - 1) >= 0 && (x+direction)>=0) {
            if (positions[x + direction][y - 1].getIsOccupied()) {
                // If this Piece is an enemy
                if (!positions[x + direction][y - 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x+direction][y-1]); // Add the enemy Piece position to possible moves
                }
            }
        }

        // Check if the Pawn can attack a Piece at the up-left (or down-left) position depending on the direction
        if ((x + direction) < 8 && (y + 1) >= 0 && (y+1)<8 && (x+direction)>=0) {
            if (positions[x + direction][y + 1].getIsOccupied()) {
                if (!positions[x + direction][y + 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x + direction][y + 1]); // Add the enemy Piece position to possible moves
                }
            }
        }

        // Check if the Pawn can move forward
        if (x + direction < 8 && (x+direction)>=0) {
            if (!positions[x + direction][y].getIsOccupied()) { // If forward square is empty
                possibleMoves.add(positions[x + direction][y]); // Append the forward square to possible moves
            }
        }

        // If Pawn moves for the first time
        if ((this.firstTime==0)&&(possibleMoves.size()!=0)){
                if((this.color.equals("white")) && (!positions[x+direction-1][y].getIsOccupied())) // If it is a white Pawn
                possibleMoves.add(positions[x + direction-1][y]); //  Add the next-to-forward position to possible moves
                else if((this.color.equals("black")) && (!positions[x+direction+1][y].getIsOccupied())) // If it is a black Pawn
                 possibleMoves.add(positions[x+direction+1][y]); //  Add the next-to-forward position to possible moves
        }

        this.canbeenpassed(possibleMoves);

        System.out.println("Possible Moves :");
        for (Position c : possibleMoves) {
            System.out.print(c.getX() + "" + c.getY());
        } // Displays possible moves of the Pawn

        return possibleMoves;
    }

    public void changeFirstTime(){
        this.firstTime = 1;
    }
    public int getSide(){
        return this.side;
    }
    public ArrayList<Position> canbeenpassed(ArrayList<Position> possibleMoves){ // Implements "en passant" rule
        // returns an ArrayList "passList" containing the Pieces a Pawn can attack with the "en passant" rule
        ArrayList<Position> passList = new ArrayList<Position>();
        if (this.color.equals("black")) { // Conditions for a Black Pawn
            if ((this.position.getX() == 4) &&(this.position.getY()+1<8)&& (position.getBoard().positions[4][this.position.getY()+1].getIsOccupied())&&(position.getBoard().positions[4][this.position.getY()+1].getOccupyingPiece().getColor().equals("white"))){
                possibleMoves.add(position.getBoard().positions[5][this.position.getY()+1]); // Add the enemy Piece to possible moves
                passList.add(position.getBoard().positions[5][this.position.getY()+1]); // Append the enemy Piece to PassList

            }
            if ((this.position.getX() == 4) &&(this.position.getY()-1>=0) &&(position.getBoard().positions[4][this.position.getY()-1].getIsOccupied())&&(position.getBoard().positions[4][this.position.getY()-1].getOccupyingPiece().getColor().equals("white"))){
                possibleMoves.add(position.getBoard().positions[5][this.position.getY()-1]); // Add the enemy Piece to possible moves
                passList.add(position.getBoard().positions[5][this.position.getY()-1]); // Append the enemy Piece to PassList

            }

        }
        if (this.color == "white") { // Conditions for a White Pawn
            if ((this.position.getX() == 3) &&(this.position.getY()+1<8)&& (position.getBoard().positions[3][this.position.getY()+1].getIsOccupied())&&(position.getBoard().positions[3][this.position.getY()+1].getOccupyingPiece().getColor().equals("black"))){
                possibleMoves.add(position.getBoard().positions[2][this.position.getY()+1]); // Add the enemy Piece to possible moves
                passList.add(position.getBoard().positions[2][this.position.getY()+1]); // Append the enemy Piece to PassList
            }
            if ((this.position.getX() == 3) &&(this.position.getY()-1>=0) &&(position.getBoard().positions[3][this.position.getY()-1].getIsOccupied())&&(position.getBoard().positions[3][this.position.getY()-1].getOccupyingPiece().getColor().equals("black"))){
                possibleMoves.add(position.getBoard().positions[2][this.position.getY()-1]); // Add the enemy Piece to possible moves
                passList.add(position.getBoard().positions[2][this.position.getY()-1]); // Append the enemy Piece to PassList
            }

        }
    return passList;
    }

    public boolean canbepromoted(){ // returns true if the Pawn can be promoted, false otherwise
        if( (this.color=="black") &&(this.position.getX()==7)){ // If the Pawn is black and is at the bottom row
            return true;
        }
        else if ((this.color=="white")&&(this.position.getX()==0)){ // If the Pawn is white and is at the top row
            return true;
        }
        return false;
    }
}

