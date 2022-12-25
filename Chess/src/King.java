

import java.util.ArrayList;
public class King extends Piece{

    public King(String color, Position position) {
        super(color, "king", position);
        this.position.setImage("king",color);
        this.firstTime = 0;
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int row = position.getX();
        int column = position.getY();

        Position[][] positions = position.getBoard().getPositions();
        //Down Possible Move
        if(row+1<8) { // If King is not on the last row
            if (!positions[row+1][column].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row+1][column]); // Add to possible moves
            else {
                if (!positions[row + 1][column].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row+1][column]); // Add to possible moves
            }
        }

        //Up Possible Move
        if(row-1>=0) { // If King is not on the first row
            if (!positions[row - 1][column].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row - 1][column]);
            else {
                if (!positions[row - 1][column].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row - 1][column]); // Add to possible moves
            }
        }

        //Right Possible Move
        if(column+1<8) { // If King is not on the most right column
            if (!positions[row][column+1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row][column+1]); // Add to possible moves
            else {
                if (!positions[row][column+1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row][column+1]); // Add to possible moves
            }
        }

        //Left Possible Move
        if(column-1>=0) { // If the King is not on the most left column
            if (!positions[row][column-1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row][column-1]); // Add to possible moves
            else {
                if (!positions[row][column-1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row][column-1]); // Add to possible moves
            }
        }

        //Right Down Possible Move
        if(row+1<8 && column+1<8) { // If King is neither on the most right column, nor the last row
            if (!positions[row + 1][column + 1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row + 1][column + 1]); // Add to possible moves
            else {
                if (!positions[row + 1][column + 1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row + 1][column + 1]); // Add to possible moves
            }
        }

        //Left Up Possible Move
        if(row-1>=0 && column-1>=0) { // If King is neither on the most left column, nor the first row
            if (!positions[row - 1][column - 1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row - 1][column - 1]); // Add to possible moves
            else {
                if (!positions[row - 1][column - 1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row - 1][column - 1]); // Add to possible moves
            }
        }

        //Left Down Possible Move
        if(row+1<8 && column-1>=0) { // If King is neither on the most left column, nor the last row
            if (!positions[row+1][column-1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row+1][column-1]); // Add to possible moves
            else {
                if (!positions[row + 1][column-1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row+1][column-1]); // Add to possible moves
            }
        }

        //Right Up Possible Move
        if(row-1>=0 && column+1<8) { // If the King is neither on the most right column, nor the first row
            if (!positions[row-1][column+1].getIsOccupied()) // If Position is not empty
                possibleMoves.add(positions[row-1][column+1]); // Add to possible moves
            else {
                if (!positions[row-1][column+1].getOccupyingPiece().color.equals(this.color)) // If it is an enemy Piece
                    possibleMoves.add(positions[row-1][column+1]); // Add to possible moves
            }
        }


        // Display Possible Moves
        System.out.println("Possible Moves :");
        for(Position c:possibleMoves){
            System.out.print(c.getX()+ ""+ c.getY());
        }

        return possibleMoves;
    }
    public void changeFirstTime(){
        this.firstTime = 1;
    }


}
