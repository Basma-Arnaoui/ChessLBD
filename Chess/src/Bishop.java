
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Bishop  extends Piece{

    public Bishop(String color, Position position){
        super(color,"bishop",position);
        this.position.setImage("bishop",color);
        this.firstTime = 0;

    }
    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        int row = position.getX(); //row
        int column = position.getY(); //column

        Position[][] positions = position.getBoard().getPositions();

        //Right Up Possible Moves
        for(int r=row-1, c=column+1; r>=0 && c<=7 ; r--, c++){
            if(!positions[r][c].getIsOccupied()){
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().getColor().equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        //Right Down Possible Moves
        for(int r = row+1, c = column+1; r <= 7 && c <= 7 ; r++, c++){
            if(!positions[r][c].getIsOccupied()){
                possibleMoves.add(positions[r][c]);
            }
            else {
                if(!positions[r][c].getOccupyingPiece().getColor().equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // There is a Piece of the same color
                    break;
            }
        }

        //Left Up Possible Moves
        for(int r=row-1, c=column-1; r>=0 && c>=0 ; r--, c--){
            if(!positions[r][c].getIsOccupied()){
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().getColor().equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        //Left Down Possible Moves
        for(int r=row+1, c=column-1; r<=7 && c>=0 ; r++, c--){
            if(!positions[r][c].getIsOccupied() ){
                possibleMoves.add(positions[r][c]);
            }
            else{
                if(!positions[r][c].getOccupyingPiece().getColor().equals(this.color)) { // If there is no Piece of the same color
                    possibleMoves.add(positions[r][c]);
                    break;
                }
                else // If there is a Piece of the same color
                    break;
            }
        }

        // Display Possible Moves of the Bishop
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

