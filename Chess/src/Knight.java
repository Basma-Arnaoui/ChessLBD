
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(String color,Position position) {
        super(color, "knight", position);
        this.position.setImage("knight",color);
    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int row = position.getX();
        int col = position.getY();

        Position[][] positions = position.getBoard().getPositions();

        if(row-2>=0 && col-1>=0){
            if(!positions[row-2][col-1].getIsOccupied())
                possibleMoves.add(positions[row-2][col-1]);
            else
            if(!positions[row-2][col-1].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row-2][col-1]);
        }

        if(row-2>=0 && col+1<=7){
            if(!positions[row-2][col+1].getIsOccupied())
                possibleMoves.add(positions[row-2][col+1]);
            else
            if(!positions[row-2][col+1].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row-2][col+1]);
        }

        if(row+2<=7 && col-1>=0){
            if(!positions[row+2][col-1].getIsOccupied())
                possibleMoves.add(positions[row+2][col-1]);
            else {
                if (!positions[row + 2][col - 1].getOccupyingPiece().getColor().equals(color))
                    possibleMoves.add(positions[row + 2][col - 1]);
            }
        }

        if(row+2<=7 && col+1<=7){
            if(!positions[row+2][col+1].getIsOccupied())
                possibleMoves.add(positions[row+2][col+1]);
            else
            if(!positions[row+2][col+1].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row+2][col+1]);
        }

        if(row-1>=0 && col-2>=0){
            if(!positions[row-1][col-2].getIsOccupied())
                possibleMoves.add(positions[row-1][col-2]);
            else
            if(!positions[row-1][col-2].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row-1][col-2]);
        }

        if(row-1>=0 && col+2<=7){
            if(!positions[row-1][col+2].getIsOccupied())
                possibleMoves.add(positions[row-1][col+2]);
            else
            if(!positions[row-1][col+2].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row-1][col+2]);
        }

        if(row+1<=7 && col-2>=0){
            if(!positions[row+1][col-2].getIsOccupied())
                possibleMoves.add(positions[row+1][col-2]);
            else
            if(!positions[row+1][col-2].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row+1][col-2]);
        }

        if(row+1<=7 && col+2<=7){
            if(!positions[row+1][col+2].getIsOccupied())
                possibleMoves.add(positions[row+1][col+2]);
            else
            if(!positions[row+1][col+2].getOccupyingPiece().getColor().equals(color))
                possibleMoves.add(positions[row+1][col+2]);
        }

        System.out.println("Possible Moves :");
        for(Position c:possibleMoves){
            System.out.print(c.getX()+""+c.getY());
        }

        return possibleMoves;
    }
}

