
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Pawn extends Piece {

    int direction;
    int firstTime;
    int side;

    public Pawn(String color, Position position) {
        super(color, "pawn", position);
        if (color.equals("black"))
            direction = 1;
        else
            direction = -1;


        this.position.setImage("pawn", color);
        this.firstTime = 0;
        this.side = 0;


    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        System.out.println("Position: X:" + x + " Y: " + y);
        Position[][] positions = position.getBoard().getPositions();



        if ((x + direction) < 8 && (y - 1) >= 0 && (x+direction)>=0) {
            if (positions[x + direction][y - 1].getIsOccupied()) {
                if (!positions[x + direction][y - 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x+direction][y-1]);
                    System.out.println("1");
                }
            }
        }

        if ((x + direction) < 8 && (y + 1) >= 0 && (y+1)<8 && (x+direction)>=0) {
            if (positions[x + direction][y + 1].getIsOccupied()) {
                if (!positions[x + direction][y + 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x + direction][y + 1]);
                    System.out.println("2");
                }
            }
        }

        if (x + direction < 8 && (x+direction)>=0) {
            if (!positions[x + direction][y].getIsOccupied()) {
                possibleMoves.add(positions[x + direction][y]);
                System.out.println("3");
            }
        }

        if ((this.firstTime==0)&&(possibleMoves.size()!=0)){
                if((this.color.equals("white")) && (!positions[x+direction-1][y].getIsOccupied()))
                possibleMoves.add(positions[x + direction-1][y]);
                else if((this.color.equals("black")) && (!positions[x+direction+1][y].getIsOccupied()))
                 possibleMoves.add(positions[x+direction+1][y]);
        }

        this.canbeenpassed(possibleMoves);

        System.out.println("Possible Moves :");
        for (Position c : possibleMoves) {
            System.out.print(c.getX() + "" + c.getY());
        }

        return possibleMoves;
    }

    public void changeFirstTime(){
        this.firstTime = 1;
    }
    public int getSide(){
        return this.side;
    }
    public Position canbeenpassed(ArrayList<Position> possibleMoves){

        if (this.color.equals("black")) {
            if ((this.position.getX() == 4) &&(this.position.getY()+1<8)&& (position.getBoard().positions[4][this.position.getY()+1].getIsOccupied())&&(position.getBoard().positions[4][this.position.getY()+1].getOccupyingPiece().getColor().equals("white"))){
                possibleMoves.add(position.getBoard().positions[5][this.position.getY()+1]);
                return position.getBoard().positions[5][this.position.getY()+1];

            }
            if ((this.position.getX() == 4) &&(this.position.getY()-1>=0) &&(position.getBoard().positions[4][this.position.getY()-1].getIsOccupied())&&(position.getBoard().positions[4][this.position.getY()-1].getOccupyingPiece().getColor().equals("white"))){
                possibleMoves.add(position.getBoard().positions[5][this.position.getY()-1]);
                return position.getBoard().positions[5][this.position.getY()-1];

            }

        }
        if (this.color == "white") {
            if ((this.position.getX() == 3) &&(this.position.getY()+1<8)&& (position.getBoard().positions[3][this.position.getY()+1].getIsOccupied())&&(position.getBoard().positions[3][this.position.getY()+1].getOccupyingPiece().getColor().equals("black"))){
                possibleMoves.add(position.getBoard().positions[2][this.position.getY()+1]);
                return position.getBoard().positions[2][this.position.getY()+1];

            }
            if ((this.position.getX() == 3) &&(this.position.getY()-1>=0) &&(position.getBoard().positions[3][this.position.getY()-1].getIsOccupied())&&(position.getBoard().positions[3][this.position.getY()-1].getOccupyingPiece().getColor().equals("black"))){
                possibleMoves.add(position.getBoard().positions[2][this.position.getY()-1]);
                return position.getBoard().positions[2][this.position.getY()-1];
            }

        }
    return null;
    }


}

