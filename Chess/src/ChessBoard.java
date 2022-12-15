
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Region.*;

import java.util.ArrayList;
import java.util.Iterator;

public class ChessBoard extends GridPane{

    protected Position[][] positions;
    protected ArrayList<Position> srcPossibleMoves;
    protected Position src;
    protected boolean firstClick;

    public ChessBoard() {

        for (int row = 0; row < 8; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(75);
            rc.setMaxHeight(75);
            this.getRowConstraints().add(rc);
        }

        for (int col = 0; col < 8; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(75);
            cc.setMaxWidth(75);
            this.getColumnConstraints().add(cc);
        }

        this.positions = new Position[8][8];

        //Button handler to handle click operations
        ButtonHandler onClick = new ButtonHandler();

        //Adding positions to gridpane
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                positions[row][column] = new Position(row, column,this);
                positions[row][column].setOnMouseClicked(onClick);
                positions[row][column].setMinSize(75,75);
                this.add(positions[row][column], column, row);
                System.out.print(row+""+column+" ");
            }
            System.out.println(" ");
        }
        this.setGridLinesVisible(true);
        //adding Pieces
        addPieces();

        this.firstClick = false;

    }


    void selectPosition(Position cell){
        this.firstClick = true;
        src = cell;
        cell.possiblePositions();
        srcPossibleMoves = cell.getOccupyingPiece().possibleMoves();
        for (Position c:srcPossibleMoves){
            if(c.getIsOccupied())
                c.attackPositions();
            else
                c.possiblePositions();
        }
    }

    void makeMove(Position src,Position dest){

        if(dest.getIsOccupied())
            dest.deletePiece();
        Piece p = src.occupyingPiece;
        dest.addPiece(p);
        dest.setGraphic(p.getImage());

        src.setGraphic(null);
        src.occupyingPiece = null;
        src.isOccupied = false;
        p.setPosition(dest);
    }

    void showAttackPositions(ArrayList<Position> attackPositions){
        for (Position c: attackPositions) {
            c.attackPositions();
        }
    }

    void showPossiblePositions(ArrayList<Position> possiblePositions){
        for (Position c: srcPossibleMoves) {
            c.possiblePositions();
        }
    }

    void resetColor(ArrayList<Position> resetPositions){
        for(Position c:resetPositions){
            c.changeColor();
        }


    }

    public void addPieces(){

        positions[0][0].addPiece(new Rook("black",positions[0][0]));
        positions[0][7].addPiece(new Rook("black",positions[0][7]));

        positions[0][1].addPiece(new Knight("black",positions[0][1]));
        positions[0][6].addPiece(new Knight("black",positions[0][6]));

        positions[0][2].addPiece(new Bishop("black",positions[0][2]));
        positions[0][5].addPiece(new Bishop("black",positions[0][5]));

        positions[0][3].addPiece(new Queen("black",positions[0][3]));
        positions[0][4].addPiece(new King("black",positions[0][4]));

        //White
        positions[7][0].addPiece(new Rook("white",positions[7][0]));
        positions[7][7].addPiece(new Rook("white",positions[7][7]));

        positions[7][1].addPiece(new Knight("white",positions[7][1]));
        positions[7][6].addPiece(new Knight("white",positions[7][6]));

        positions[7][2].addPiece(new Bishop("white",positions[7][2]));
        positions[7][5].addPiece(new Bishop("white",positions[7][5]));

        positions[7][3].addPiece(new King("white",positions[7][3]));
        positions[7][4].addPiece(new Queen("white",positions[7][4]));

        for (int col=0; col<8; col++){
            positions[1][col].addPiece(new Pawn("black",positions[1][col]));
        }

        for (int col=0; col<8; col++){
            positions[6][col].addPiece(new Pawn("white",positions[6][col]));
        }
    }

    public void addRowColumnContraints(GridPane gPane){

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(50);
        for(int i=0;i<8;i++){
            gPane.getColumnConstraints().add(columnConstraints);
        }

        RowConstraints rowConstraints = new RowConstraints();

        rowConstraints.setPrefHeight(50);
        for(int i=0;i<8;i++){
            gPane.getRowConstraints().add(rowConstraints);
        }
    }

    public Position[][] getPositions() {
        return this.positions;
    }

    public GridPane board() {
        return this;
    }
    public class ButtonHandler implements EventHandler<MouseEvent> {
        public ButtonHandler() {
        }

        public void handle(MouseEvent mouseEvent) {
            Position clickedPosition = (Position)mouseEvent.getSource();
            System.out.println("Position Row: " + clickedPosition.getX() + "Col " + clickedPosition.getY());
            Iterator var3;
            Position c;
            if (clickedPosition.getIsOccupied()) {
                if (!ChessBoard.this.firstClick) {
                    ChessBoard.this.selectPosition(clickedPosition);
                } else {
                    var3 = ChessBoard.this.srcPossibleMoves.iterator();

                    while(var3.hasNext()) {
                        c = (Position)var3.next();
                        if (c.equals(clickedPosition)) {
                            ChessBoard.this.makeMove(ChessBoard.this.src, clickedPosition);
                            ChessBoard.this.src.changeColor();
                            ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                            ChessBoard.this.firstClick = false;
                            ChessBoard.this.src = null;
                            ChessBoard.this.srcPossibleMoves = null;
                            break;
                        }
                    }
                }
            } else if (ChessBoard.this.firstClick) {
                var3 = ChessBoard.this.srcPossibleMoves.iterator();

                while(var3.hasNext()) {
                    c = (Position)var3.next();
                    if (c.equals(clickedPosition)) {
                        ChessBoard.this.makeMove(ChessBoard.this.src, clickedPosition);
                        ChessBoard.this.src.changeColor();
                        ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                        ChessBoard.this.firstClick = false;
                        ChessBoard.this.src = null;
                        ChessBoard.this.srcPossibleMoves = null;
                        break;
                    }
                }
            }

        }
    }
}
