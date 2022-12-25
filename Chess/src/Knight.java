
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(String color,Position position) {
        super(color, "knight", position);
        this.position.setImage("knight",color);
        this.firstTime = 0;

    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int row = position.getX();
        int col = position.getY();

        Position[][] positions = position.getBoard().getPositions();

        // Check if possible to move two rows up and one column to the left
        if(row-2>=0 && col-1>=0){
            if(!positions[row-2][col-1].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row-2][col-1]);
            else
            if(!positions[row-2][col-1].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row-2][col-1]); // Add to possible moves
        }

        // Check if possible to move two rows up and one column to the right
        if(row-2>=0 && col+1<=7){
            if(!positions[row-2][col+1].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row-2][col+1]);
            else
            if(!positions[row-2][col+1].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row-2][col+1]); // Add to possible moves
        }

        // Check if possible to move two rows down and one column to the left
        if(row+2<=7 && col-1>=0){
            if(!positions[row+2][col-1].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row+2][col-1]);
            else {
                if (!positions[row + 2][col - 1].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                    possibleMoves.add(positions[row + 2][col - 1]); // Add to possible moves
            }
        }

        // Check if possible to move two rows down and one column to the right
        if(row+2<=7 && col+1<=7){
            if(!positions[row+2][col+1].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row+2][col+1]);
            else
            if(!positions[row+2][col+1].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row+2][col+1]); // Add to possible moves
        }

        // Check if possible to move one row up and two columns to the left
        if(row-1>=0 && col-2>=0){
            if(!positions[row-1][col-2].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row-1][col-2]);
            else
            if(!positions[row-1][col-2].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row-1][col-2]); // Add to possible moves
        }

        // Check if possible to move one row up and two columns to the right
        if(row-1>=0 && col+2<=7){
            if(!positions[row-1][col+2].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row-1][col+2]);
            else
            if(!positions[row-1][col+2].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row-1][col+2]); // Add to possible moves
        }

        // Check if possible to move one row down and two columns to the left
        if(row+1<=7 && col-2>=0){
            if(!positions[row+1][col-2].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row+1][col-2]);
            else
            if(!positions[row+1][col-2].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row+1][col-2]); // Add to possible moves
        }

        // Check if possible to move one row down and two columns to the right
        if(row+1<=7 && col+2<=7){
            if(!positions[row+1][col+2].getIsOccupied()) // If the Position is empty
                possibleMoves.add(positions[row+1][col+2]);
            else
            if(!positions[row+1][col+2].getOccupyingPiece().getColor().equals(color)) // If it has a Piece of enemy color
                possibleMoves.add(positions[row+1][col+2]); // Add to possible moves
        }

        // Display the Knights possible moves
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

