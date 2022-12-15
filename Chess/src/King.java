

import java.util.ArrayList;
public class King extends Piece{

    public King(String color, Position position) {
        super(color, "king", position);
        this.position.setImage("king",color);
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int row = position.getX();
        int column = position.getY();

        Position[][] positions = position.getBoard().getPositions();
        //Down
        if(row+1<8) {
            if (!positions[row+1][column].getIsOccupied())
                possibleMoves.add(positions[row+1][column]);
            else {
                if (!positions[row + 1][column].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row+1][column]);
            }
        }

        //Up
        if(row-1>=0) {
            if (!positions[row - 1][column].getIsOccupied())
                possibleMoves.add(positions[row - 1][column]);
            else {
                if (!positions[row - 1][column].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row - 1][column]);
            }
        }

        //Right
        if(column+1<8) {
            if (!positions[row][column+1].getIsOccupied())
                possibleMoves.add(positions[row][column+1]);
            else {
                if (!positions[row][column+1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row][column+1]);
            }
        }

        //Left
        if(column-1>=0) {
            if (!positions[row][column-1].getIsOccupied())
                possibleMoves.add(positions[row][column-1]);
            else {
                if (!positions[row][column-1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row][column-1]);
            }
        }

        //Right Down
        if(row+1<8 && column+1<8) {
            if (!positions[row + 1][column + 1].getIsOccupied())
                possibleMoves.add(positions[row + 1][column + 1]);
            else {
                if (!positions[row + 1][column + 1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row + 1][column + 1]);
            }
        }

        //Left Up
        if(row-1>=0 && column-1>=0) {
            if (!positions[row - 1][column - 1].getIsOccupied())
                possibleMoves.add(positions[row - 1][column - 1]);
            else {
                if (!positions[row - 1][column - 1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row - 1][column - 1]);
            }
        }

        //Left Down
        if(row+1<8 && column-1>=0) {
            if (!positions[row+1][column-1].getIsOccupied())
                possibleMoves.add(positions[row+1][column-1]);
            else {
                if (!positions[row + 1][column-1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row+1][column-1]);
            }
        }

        //Right Up
        if(row-1>=0 && column+1<8) {
            if (!positions[row-1][column+1].getIsOccupied())
                possibleMoves.add(positions[row-1][column+1]);
            else {
                if (!positions[row-1][column+1].getOccupyingPiece().color.equals(this.color))
                    possibleMoves.add(positions[row-1][column+1]);
            }
        }

        System.out.println("Possible Moves :");
        for(Position c:possibleMoves){
            System.out.print(c.getX()+""+c.getY());
        }

        return possibleMoves;
    }

}
