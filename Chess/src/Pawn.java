
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Pawn extends Piece {

    int direction;
    boolean firstTime;

    public Pawn(String color, Position position) {
        super(color, "pawn", position);

        if (color.equals("black"))
            direction = 1;
        else
            direction = -1;


        this.position.setImage("pawn", color);

    }

    @Override
    public ArrayList<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        System.out.println("Position: X:" + x + " Y: " + y);
        Position[][] positions = position.getBoard().getPositions();

        if ((x + direction) < 8 && (y - 1) >= 0 ) {
            if (positions[x + direction][y - 1].getIsOccupied()) {
                if (!positions[x + direction][y - 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x+direction][y-1]);
                    System.out.println("1");
                }
            }
        }

        if ((x + direction) < 8 && (y + 1) >= 0 && (y+1)<8) {
            if (positions[x + direction][y + 1].getIsOccupied()) {
                if (!positions[x + direction][y + 1].getOccupyingPiece().color.equals(color)) {
                    possibleMoves.add(positions[x + direction][y + 1]);
                    System.out.println("2");
                }
            }
        }

        if (x + direction < 8) {
            if (!positions[x + direction][y].getIsOccupied()) {
                possibleMoves.add(positions[x + direction][y]);
                System.out.println("3");
            }
        }

        System.out.println("Possible Moves :");
        for (Position c : possibleMoves) {
            System.out.print(c.getX() + "" + c.getY());
        }

        return possibleMoves;
    }
}

