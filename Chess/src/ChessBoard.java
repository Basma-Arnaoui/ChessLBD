
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.Region.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import static javafx.scene.paint.Color.*;

public class ChessBoard extends GridPane{

    protected Position[][] positions;
    protected ArrayList<Position> clicks;
    protected ArrayList<Position> srcPossibleMoves;
    protected Position src;
    protected static int turns;
    protected boolean firstClick;
    protected static String winner = null;

    public ChessBoard() {

        // Set the row min-max heights of the chessboard cells
        for (int row = 0; row < 8; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(75);
            rc.setMaxHeight(75);
            this.getRowConstraints().add(rc);
        }

        // Set the row min-max widths of the chessboard cells
        for (int col = 0; col < 8; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(75);
            cc.setMaxWidth(75);
            this.getColumnConstraints().add(cc);
        }

        // Create the full 64 cells chessboard
        this.positions = new Position[8][8];
        this.turns = 0; // 0 for white and 1 for black
        this.clicks = new ArrayList<Position>();

        // Button handler to handle click operations
        ButtonHandler onClick = new ButtonHandler();

        // Adding positions to grid Pane
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                positions[row][column] = new Position(row, column,this);
                positions[row][column].setOnMouseClicked(onClick);
                positions[row][column].setMinSize(75,75);
                this.add(positions[row][column], column, row);
                System.out.print(row + "" + column + " ");
            }
            System.out.println(" ");
        }
        this.setGridLinesVisible(true);
        //adding Pieces
        addPieces();
        this.firstClick = false;
    }


    // Selecting a Position in the Chessboard
    void selectPosition(Position cell){
        this.firstClick = true;
        src = cell;
        cell.possiblePositions();
        srcPossibleMoves = cell.getOccupyingPiece().possibleMoves(); // Select all possible Positions the Piece can reach
        for (Position c:srcPossibleMoves){
            if(c.getIsOccupied()) // if a Position is occupied
                c.attackPositions(); // Label as an attack Position
            else // Position is empty
                c.possiblePositions(); // Label as just a possible Position
        }
    }

    // Create a Window when the King is "In check"
    private void showAlertWithHeaderText(String color) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(color.toUpperCase(Locale.ROOT)+ " IS IN CHECK! MOVE YOUR KING");

        alert.showAndWait();
    }

    // Create a Window when a Pawn gets promoted
    private String promoAlert(String color){
        final String[] selected = {new String()};
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("PAWN PROMOTION");
        alert.setContentText("Choose your option:");

        // Buttons for possible Promotions
        ButtonType buttonTypeQ = new ButtonType("Queen");
        ButtonType buttonTypeB = new ButtonType("Bishop");
        ButtonType buttonTypeR = new ButtonType("Rook");
        ButtonType buttonTypeK = new ButtonType("Knight");
        alert.getButtonTypes().setAll(buttonTypeQ, buttonTypeB, buttonTypeR, buttonTypeK);

        // 1-element ArrayList of Promotions Buttons
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeQ){
            selected[0] = "queen";
        } else if (result.get() == buttonTypeB) {
            selected[0] = "bishop";
        } else if (result.get() == buttonTypeR) {
            selected[0] = "rook";
        } else if(result.get() == buttonTypeK){
            selected[0] = "knight";
        }
        else selected[0] = "queen";
        return selected[0];

    }

    public void isCheck(String color){
          for(Position[] lpos : positions) { // Iterate Through Rows
              for (Position pos : lpos) { // Iterate through Position in Row
                  if ((pos.isOccupied == true) && (!pos.getOccupyingPiece().getColor().equals(color))) { // If the Position is occupied by a Piece of the other "color"
                      for (Position i : pos.getOccupyingPiece().possibleMoves()) { // Iterate through its possible moves
                          if ((i.getIsOccupied() == true) && (i.getOccupyingPiece().getName().equals("king"))) { // The Piece can attack the King
                              showAlertWithHeaderText(color); // Display Alert Window
                              i.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                          }
                      }
                  }
              }
          }
    }

    // Deselect all possible positions
    public void deselectAll(Position cell){
        this.firstClick = false;
        cell.possiblePositions();
        ArrayList<Position> srcPM = cell.getOccupyingPiece().possibleMoves();
        for (Position c:srcPM){
            this.deselectPosition(c);
        }
    }

    //
    void deselectPosition(Position cell){
        cell.setColor();
    }
    // Move the Piece from "src" Position to "dest" Position
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

    // Reset the background color of an ArrayList of Positions
    void resetColor(ArrayList<Position> resetPositions){
        for(Position c:resetPositions){
            c.setColor();
        }

    }

    // Set the Pieces in the Chessboard initially
    public void addPieces(){
        // Black Pieces
        positions[0][0].addPiece(new Rook("black",positions[0][0]));
        positions[0][7].addPiece(new Rook("black",positions[0][7]));

        positions[0][1].addPiece(new Knight("black",positions[0][1]));
        positions[0][6].addPiece(new Knight("black",positions[0][6]));

        positions[0][2].addPiece(new Bishop("black",positions[0][2]));
        positions[0][5].addPiece(new Bishop("black",positions[0][5]));

        positions[0][3].addPiece(new Queen("black",positions[0][3]));
        positions[0][4].addPiece(new King("black",positions[0][4]));

        // Pawns
        for (int col=0; col<8; col++){
            positions[1][col].addPiece(new Pawn("black",positions[1][col]));
        }
        //White Pieces
        positions[7][0].addPiece(new Rook("white",positions[7][0]));
        positions[7][7].addPiece(new Rook("white",positions[7][7]));

        positions[7][1].addPiece(new Knight("white",positions[7][1]));
        positions[7][6].addPiece(new Knight("white",positions[7][6]));

        positions[7][2].addPiece(new Bishop("white",positions[7][2]));
        positions[7][5].addPiece(new Bishop("white",positions[7][5]));

        positions[7][4].addPiece(new King("white",positions[7][4]));
        positions[7][3].addPiece(new Queen("white",positions[7][3]));


        for (int col=0; col<8; col++){
            positions[6][col].addPiece(new Pawn("white",positions[6][col]));
        }
    }

    // Return all the Positions in the Chessboard
    public Position[][] getPositions() {
        return this.positions;
    }

    // Castling Move
    public boolean ltCastle(){ // Left Top Castle
            // If all positions between the King and the Top Left Rook are empty
            if ((positions[0][1].getOccupyingPiece()==null)&&(positions[0][2].getOccupyingPiece()==null)&&(positions[0][3].getOccupyingPiece()==null)) return true;
            return false;
        }

    public boolean rtCastle(){ // Right Top Castle
        // If all positions between the King and the Top Right Rook are empty
        if ((positions[0][6].getOccupyingPiece()==null)&&(positions[0][5].getOccupyingPiece()==null)) return true;
        return false;
    }
    public boolean lbCastle(){ // Left Bottom Castle
        // If all positions between the King and the Left Bottom Rook are empty
        if ((positions[7][1].getOccupyingPiece()==null)&&(positions[7][2].getOccupyingPiece()==null)&&(positions[7][3].getOccupyingPiece()==null)) return true;
        return false;
    }
    public boolean rbCastle(){ // Right Bottom Castle
        // If all positions between the King and the Right Bottom Rook are empty
        if ((positions[7][6].getOccupyingPiece()==null)&&(positions[7][5].getOccupyingPiece()==null)) return true;
        return false;
    }


    public class ButtonHandler implements EventHandler<MouseEvent> {
        public ButtonHandler() {
        }


        public void handle(MouseEvent mouseEvent) {

            Position clickedPosition = (Position)mouseEvent.getSource();
            System.out.println("Position Row: " + clickedPosition.getX() + "Col " + clickedPosition.getY());
            Iterator var3;
            Position c;
            int castleI = 0;
            // If the Clicked Positions is a Black (or White) Piece , and it is the Black's (or White's) turn
            if ((clickedPosition.getIsOccupied()) && (((clickedPosition.getOccupyingPiece().getColor()=="black")&&(ChessBoard.this.turns%2==1)) || ((clickedPosition.getOccupyingPiece().getColor()=="white") && (ChessBoard.this.turns%2==0)))){
                if (!ChessBoard.this.firstClick) { // If this is its first Click on the Piece
                    ChessBoard.this.selectPosition(clickedPosition);
                    ChessBoard.this.clicks.add(clickedPosition);
                    if(clickedPosition.getOccupyingPiece().canbepromoted()){
                    }
                }

                    else{
                        Position lastclick = ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1);
                        // If the Last Click was on a King and the current click is on Rook
                        if ((lastclick.getOccupyingPiece().getName().equals("king"))&&(clickedPosition.getOccupyingPiece().getName().equals("rook"))){
                            // If King and Rook were never moved before
                            if((lastclick.getOccupyingPiece().getFirstTime()==0)&&(clickedPosition.getOccupyingPiece().getFirstTime()==0)){
                                // Clicked Position is Right Top Corner
                                if ((clickedPosition.getX() == 0) && (clickedPosition.getY() == 7)) {
                                    if (rtCastle()) { // Performs a Right Top Castling
                                        ChessBoard.this.turns = ChessBoard.this.turns+1;
                                        clickedPosition.getOccupyingPiece().changeFirstTime();
                                        lastclick.getOccupyingPiece().changeFirstTime();
                                        ChessBoard.this.makeMove(lastclick, positions[0][6]);
                                        ChessBoard.this.makeMove(clickedPosition, positions[0][5]);
                                        castleI=1;
                                        if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                                        else ChessBoard.this.isCheck("black");
                                        ChessBoard.this.src.changeColor();
                                        ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                                        ChessBoard.this.firstClick = false;
                                        ChessBoard.this.src = null;
                                        ChessBoard.this.srcPossibleMoves = null;


                                    }
                                }
                                if (ltCastle()){ // Performs a Left Top Castling
                                if ((clickedPosition.getX() == 0) && (clickedPosition.getY() == 0)) {
                                    ChessBoard.this.turns = ChessBoard.this.turns+1;
                                    clickedPosition.getOccupyingPiece().changeFirstTime();
                                    lastclick.getOccupyingPiece().changeFirstTime();
                                    ChessBoard.this.makeMove(lastclick, positions[0][2]);
                                    ChessBoard.this.makeMove(clickedPosition, positions[0][3]);
                                    if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                                    else ChessBoard.this.isCheck("black");
                                    castleI=1;
                                    ChessBoard.this.src.changeColor();
                                    ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                                    ChessBoard.this.firstClick = false;
                                    ChessBoard.this.src = null;
                                    ChessBoard.this.srcPossibleMoves = null;

                                }
                                }
                                if(lbCastle()){ // Performs a Left Bottom Castling
                                if ((clickedPosition.getX() == 7) && (clickedPosition.getY() == 0)) {                                        ChessBoard.this.deselectAll(positions[0][0]);
                                    ChessBoard.this.turns = ChessBoard.this.turns+1;
                                    clickedPosition.getOccupyingPiece().changeFirstTime();
                                    lastclick.getOccupyingPiece().changeFirstTime();
                                    ChessBoard.this.makeMove(lastclick, positions[7][2]);
                                    ChessBoard.this.makeMove(clickedPosition, positions[7][3]);
                                    if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                                    else ChessBoard.this.isCheck("black");
                                    castleI=1;
                                    ChessBoard.this.src.changeColor();
                                    ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                                    ChessBoard.this.firstClick = false;
                                    ChessBoard.this.src = null;
                                    ChessBoard.this.srcPossibleMoves = null;
                                }
                                }
                                if (rbCastle()) { // Performs a Right Bottom Castling
                                    if ((clickedPosition.getX() == 7) && (clickedPosition.getY() == 7)) {
                                        ChessBoard.this.turns = ChessBoard.this.turns+1;
                                        clickedPosition.getOccupyingPiece().changeFirstTime();
                                        lastclick.getOccupyingPiece().changeFirstTime();
                                        ChessBoard.this.makeMove(lastclick, positions[7][6]);
                                        ChessBoard.this.makeMove(clickedPosition, positions[7][5]);
                                        if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                                        else ChessBoard.this.isCheck("black");
                                        castleI=1;
                                        ChessBoard.this.src.changeColor();
                                        ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                                        ChessBoard.this.firstClick = false;
                                        ChessBoard.this.src = null;
                                        ChessBoard.this.srcPossibleMoves = null;
                                    }
                                }
                            }
                        }
                    // If the Clicked Position is the same as the Last Clicked Position
                    if ((clickedPosition.getY()==ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getY()) & (clickedPosition.getX()==ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getX())){
                        // Deselect the Position
                        ChessBoard.this.firstClick = false;
                        ChessBoard.this.deselectPosition(clickedPosition);
                        var3 = ChessBoard.this.srcPossibleMoves.iterator();
                        while (var3.hasNext()) {
                            c = (Position) var3.next();
                            ChessBoard.this.deselectPosition(c);
                        }
                    }
                    else if (castleI==0){
                        var3 = ChessBoard.this.srcPossibleMoves.iterator();
                        while (var3.hasNext()) {
                            c = (Position) var3.next();
                            if (c.equals(clickedPosition)) {
                                // If the attacked position contains a King
                                if((clickedPosition.getIsOccupied()==true)&& (clickedPosition.getOccupyingPiece().getName().equals("king"))){
                                    // The game end, the Result Page is Displayed
                                    new Result().start(new Stage());
                                }
                                // If the clicked position was not a King
                                ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getOccupyingPiece().changeFirstTime();
                                // Make the move from "src" to the clicked Position
                                ChessBoard.this.makeMove(ChessBoard.this.src, clickedPosition);
                                ChessBoard.this.src.changeColor();
                                if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                                else ChessBoard.this.isCheck("black");
                                ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                                ChessBoard.this.firstClick = false;
                                ChessBoard.this.src = null;
                                ChessBoard.this.srcPossibleMoves = null;
                                break;
                            }

                        }
                    }
                }
            } else if (ChessBoard.this.firstClick) {
                var3 = ChessBoard.this.srcPossibleMoves.iterator();
                Position lastclick = ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1); // Last click before the current click
                boolean didpass = false;
                if (lastclick.getOccupyingPiece().getName().equals("pawn")) { // If last click was on a Pawn
                    // If last clicked Pawn can apply "en passant" rule
                    if ((lastclick.getOccupyingPiece().canbeenpassed(lastclick.getOccupyingPiece().possibleMoves()) != null)) {
                        for (Position pos : lastclick.getOccupyingPiece().canbeenpassed(lastclick.getOccupyingPiece().possibleMoves())) {
                            if ((pos.getX() == clickedPosition.getX()) && (pos.getY() == clickedPosition.getY())) {

                                ChessBoard.this.positions[lastclick.getX()][clickedPosition.getY()].setGraphic(null);
                                ChessBoard.this.positions[lastclick.getX()][clickedPosition.getY()].occupyingPiece = null;
                                ChessBoard.this.positions[lastclick.getX()][clickedPosition.getY()].isOccupied = false;
                                ChessBoard.this.clicks.get(ChessBoard.this.clicks.size() - 1).getOccupyingPiece().changeFirstTime();
                                ChessBoard.this.makeMove(lastclick, clickedPosition);
                                didpass = true;
                            }

                        }
                    }
                }

                while(var3.hasNext()&& castleI==0) {
                    c = (Position)var3.next();
                    if (c.equals(clickedPosition)) {
                        // If a Piece in turn attacks a King
                        if((clickedPosition.getIsOccupied()==true)&& (clickedPosition.getOccupyingPiece().getName().equals("king"))){
                            // If the Piece is white
                            if (ChessBoard.this.turns % 2 == 0) {
                                // The White won the game
                                ChessBoard.winner = "White";
                            }
                            else {
                                // The Black won the game
                                ChessBoard.winner = "Black";
                            }
                            // Display the Result page
                            new Result().start(new Stage());

                            }
                        
                        if (didpass==false){
                        ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getOccupyingPiece().changeFirstTime();
                        ChessBoard.this.makeMove(ChessBoard.this.src, clickedPosition);}
                        if (ChessBoard.this.turns%2==1) ChessBoard.this.isCheck("white");
                        else ChessBoard.this.isCheck("black");
                        ChessBoard.this.turns = ChessBoard.this.turns+1;
                        ChessBoard.this.src.changeColor();
                        ChessBoard.this.resetColor(ChessBoard.this.srcPossibleMoves);
                        ChessBoard.this.firstClick = false;
                        ChessBoard.this.src = null;
                        ChessBoard.this.srcPossibleMoves = null;
                        break;
                    }
                }
            }

            // If the click is on a Pawn
            if ((clickedPosition.getIsOccupied())&&(clickedPosition.getOccupyingPiece().getName().equals("pawn"))){
                // If it can be promoted
                if (clickedPosition.getOccupyingPiece().canbepromoted()){
                    // Show promotion Window
                    ChessBoard.this.promoAlert(clickedPosition.getOccupyingPiece().getColor());
                    clickedPosition.getOccupyingPiece().setPosition(null);
                    clickedPosition.getOccupyingPiece().setAlive(false);
                    clickedPosition.getOccupyingPiece().delete(); // Delete the Pawn Piece
                    String to = ChessBoard.this.promoAlert(clickedPosition.getOccupyingPiece().getColor());
                    // If Player chooses Queen
                    if (to.equals("queen"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Queen(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    // Bishop
                    else if (to.equals("bishop"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Bishop(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    // Rook
                    else if (to.equals("rook"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Rook(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    // Knight
                    else if (to.equals("knight"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Knight(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                }
            }
        }
    }
}
