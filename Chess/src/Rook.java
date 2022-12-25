
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Rook extends  Piece{

    public Rook(String color, Position position) {
        super(color, "rook", position);
        this.position.setImage("rook",color);
        this.firstTime = 0;
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        int row = position.getX(); //row
        int column = position.getY(); //column

        Position[][] positions = position.getBoard().getPositions();
        //Up Possible Moves
        for(int r=row-1, c=column; r>=0 ; r--){
            if(!positions[r][c].getIsOccupied() ){ // If it is empty
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().color.equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        //Down Possible Moves
        for(int r=row+1, c=column; r<=7 ; r++){
            if(!positions[r][c].getIsOccupied() ){ // If it is empty
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().color.equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        //Left Possible Moves
        for(int r=row, c=column-1; c>=0 ; c--){
            if(!positions[r][c].getIsOccupied() ){ // If it is empty
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().color.equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        //Right Possible Moves
        for(int r=row, c=column+1; c<=7; c++){
            if(!positions[r][c].getIsOccupied() ){ // If it is empty
                possibleMoves.add(positions[r][c]);
            }
            else {
                if(!positions[r][c].getOccupyingPiece().color.equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        // Display Possible Moves
        System.out.println("Possible Moves :");
        for(Position c:possibleMoves){
            System.out.print(c.getX()+""+c.getY());
        }
        return possibleMoves;
    }
    public void changeFirstTime(){
        this.firstTime = 1;
    }

}

