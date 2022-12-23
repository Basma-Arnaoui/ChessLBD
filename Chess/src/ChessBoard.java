
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
    protected int turns;
    protected boolean firstClick;
    protected static String winner = null;

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
        this.turns = 0; // 0 for white and 1 for black
        this.clicks = new ArrayList<Position>();

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

    private void showAlertWithHeaderText(String color) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(color.toUpperCase(Locale.ROOT)+ "IS IN CHECK! MOVE YOUR KING");

        alert.showAndWait();
    }
    private String promoAlert(String color){

        /*final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Queen");
        ImageView im1 = new Image().getImage("queen",color);
        rb1.setGraphic(new ImageView(im1.getImage()));
        rb1.setToggleGroup(group);
        rb1.setSelected(true);


        RadioButton rb2 = new RadioButton("Bishop");
        rb2.setToggleGroup(group);
        ImageView im2 = new Image().getImage("bishop",color);
        rb2.setGraphic(new ImageView(im2.getImage()));

        RadioButton rb3 = new RadioButton("Rook");
        rb3.setToggleGroup(group);
        ImageView im3 = new Image().getImage("rook",color);
        rb3.setGraphic(new ImageView(im3.getImage()));

        RadioButton rb4 = new RadioButton("Knight");
        rb4.setToggleGroup(group);
        ImageView im4 = new Image().getImage("knight",color);
        rb4.setGraphic(new ImageView(im4.getImage()));
        final String[] selected = {new String()};
        Button button= new Button("Choose");
        button.setOnAction(e ->
                {
                    if (rb1.isSelected())
                        selected[0] = "queen";
                    else if(rb2.isSelected())
                        selected[0] = "bishop";
                    else if(rb3.isSelected())
                        selected[0] = "rook";
                    else selected[0] = "knight";
                }
        );*/
        final String[] selected = {new String()};
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog with Custom Actions");
        alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeQ = new ButtonType("Queen");
        ButtonType buttonTypeB = new ButtonType("Bishop");
        ButtonType buttonTypeR = new ButtonType("Rook");
        ButtonType buttonTypeK = new ButtonType("Knight");


        alert.getButtonTypes().setAll(buttonTypeQ, buttonTypeB, buttonTypeR, buttonTypeK);

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
          for(Position[] lpos : positions){
            for (Position pos : lpos){
                if ((pos.isOccupied == true)&&(!pos.getOccupyingPiece().getColor().equals(color))) {
                    for (Position i : pos.getOccupyingPiece().possibleMoves()){
                        if ((i.getIsOccupied()==true)&&(i.getOccupyingPiece().getName().equals("king")) ){
                            showAlertWithHeaderText(color);
                            i.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
                        }
                    }
                }
            }}
    }

    public void deselectAll(Position cell){
        this.firstClick = false;
        cell.possiblePositions();
        ArrayList<Position> srcPM = cell.getOccupyingPiece().possibleMoves();
        for (Position c:srcPM){
            this.deselectPosition(c);
        }

    }
    void deselectPosition(Position cell){
        cell.setColor();

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
            c.setColor();
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

        positions[7][4].addPiece(new King("white",positions[7][4]));
        positions[7][3].addPiece(new Queen("white",positions[7][3]));

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




    public boolean ltCastle(){
            if ((positions[0][1].getOccupyingPiece()==null)&&(positions[0][2].getOccupyingPiece()==null)&&(positions[0][3].getOccupyingPiece()==null)) return true;
            return false;
        }

    public boolean rtCastle(){

        if ((positions[0][6].getOccupyingPiece()==null)&&(positions[0][5].getOccupyingPiece()==null)) return true;
        return false;
    }
    public boolean lbCastle(){
        if ((positions[7][1].getOccupyingPiece()==null)&&(positions[7][2].getOccupyingPiece()==null)&&(positions[7][3].getOccupyingPiece()==null)) return true;
        return false;
    }
    public boolean rbCastle(){
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

            if ((clickedPosition.getIsOccupied()) && (((clickedPosition.getOccupyingPiece().getColor()=="black")&&(ChessBoard.this.turns%2==1)) || ((clickedPosition.getOccupyingPiece().getColor()=="white") && (ChessBoard.this.turns%2==0)))){
                if (!ChessBoard.this.firstClick) {

                    ChessBoard.this.selectPosition(clickedPosition);
                    ChessBoard.this.clicks.add(clickedPosition);
                    if(clickedPosition.getOccupyingPiece().canbepromoted()){

                    }

                }

                    else{

                        Position lastclick = ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1);



                        if ((lastclick.getOccupyingPiece().getName().equals("king"))&&(clickedPosition.getOccupyingPiece().getName().equals("rook"))){
                            if((lastclick.getOccupyingPiece().getFirstTime()==0)&&(clickedPosition.getOccupyingPiece().getFirstTime()==0)){
                                if ((clickedPosition.getX() == 0) && (clickedPosition.getY() == 7)) {
                                    if (rtCastle()) {
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
                                if (ltCastle()){
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
                                if(lbCastle()){
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
                                if (rbCastle()) {
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




                    if ((clickedPosition.getY()==ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getY()) & (clickedPosition.getX()==ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getX())){
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
                                if((clickedPosition.getIsOccupied()==true)&& (clickedPosition.getOccupyingPiece().getName().equals("king"))){ new Result().start(new Stage());
                                    new java.util.Timer().schedule(
                                            new java.util.TimerTask() {
                                                @Override
                                                public void run() {
                                                    Platform.exit();
                                                }
                                            },
                                            10000
                                    );
                                }                                ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1).getOccupyingPiece().changeFirstTime();
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
                Position lastclick = ChessBoard.this.clicks.get(ChessBoard.this.clicks.size()-1);
                boolean didpass = false;
                if (lastclick.getOccupyingPiece().getName().equals("pawn")) {
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
                        if((clickedPosition.getIsOccupied()==true)&& (clickedPosition.getOccupyingPiece().getName().equals("king"))){
                            if (ChessBoard.this.turns % 2 == 0) {
                                ChessBoard.winner = "White";
                            }
                            else {
                                ChessBoard.winner = "Black";
                            }
                            new Result().start(new Stage());
                            new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                        @Override
                                        public void run() {
                                            Platform.exit();
                                        }
                                    },
                                    5000
                            );
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

            if ((clickedPosition.getIsOccupied())&&(clickedPosition.getOccupyingPiece().getName().equals("pawn"))){
                System.out.println("HA LCONDITION LWLA");
                if (clickedPosition.getOccupyingPiece().canbepromoted()){
                    System.out.println("HA LCONDITION TANIA");

                    ChessBoard.this.promoAlert(clickedPosition.getOccupyingPiece().getColor());
                    clickedPosition.getOccupyingPiece().setPosition(null);
                    clickedPosition.getOccupyingPiece().setAlive(false);
                    clickedPosition.getOccupyingPiece().delete();
                    String to = ChessBoard.this.promoAlert(clickedPosition.getOccupyingPiece().getColor());
                    if (to.equals("queen"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Queen(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    else if (to.equals("bishop"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Bishop(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    else if (to.equals("rook"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Rook(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));
                    else if (to.equals("knight"))   positions[clickedPosition.getX()][clickedPosition.getY()].addPiece(new Knight(clickedPosition.getOccupyingPiece().getColor(),positions[clickedPosition.getX()][clickedPosition.getY()]));

                }
            }
        }
    }
}
