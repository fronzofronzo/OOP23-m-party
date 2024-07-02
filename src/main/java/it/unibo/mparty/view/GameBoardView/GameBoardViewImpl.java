package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.List;
import java.util.Objects;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    //private final static int N_VBOX = 4;

    private GridPane board;
    @FXML 
    private BorderPane borderPane;
    @FXML 
    private VBox sectionP1;
    @FXML 
    private Label nameP1;
    @FXML 
    private Label coinsP1;
    @FXML 
    private Label starsP1;
    @FXML 
    private VBox sectionP2;
    @FXML 
    private Label nameP2;
    @FXML 
    private Label coinsP2;
    @FXML 
    private Label starsP2;
    @FXML 
    private VBox sectionP3;
    @FXML 
    private Label nameP3;
    @FXML 
    private Label coinsP3;
    @FXML 
    private Label starsP3;
    @FXML 
    private VBox sectionP4;
    @FXML 
    private Label nameP4;
    @FXML 
    private Label coinsP4;
    @FXML 
    private Label starsP4;
    @FXML
    private Pane paneCommand;
    @FXML 
    private Button useItem1;
    @FXML 
    private Button useItem2;
    @FXML 
    private Button useItem3;
    @FXML
    private Button buttonUP;
    @FXML
    private Button buttonLEFT;
    @FXML
    private Button buttonDOWN;
    @FXML
    private Button buttonRIGHT;
    @FXML
    private Button buttonRollDice;
    @FXML
    private Button buttonMove;
    @FXML
    private Button buttonEnter;

    //private List<Label> labelPlayersNames = List.of(nameP1, nameP2, nameP3, nameP4);     
    //private List<Label> labelPlayersCoins = List.of(coinsP1, coinsP2, coinsP3, coinsP4); 
    //private List<Label> labelPlayersStars = List.of(starsP1, starsP2, starsP3, starsP4);
    //private List<Button> buttonsItem = List.of(useItem1, useItem2, useItem3);

    @Override
    public void updatePlayer(String nickname, int coins, int money, List<String> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlayer'");
    }

    @Override
    public void updateCommands(List<String> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCommands'");
    }

    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> map, List<String> nicknames) {
        this.board = new GridPane();
        //System.out.println(map);
        this.populateGridPane(dimension, map);
        this.board.prefWidthProperty().bind(this.borderPane.widthProperty());
        this.board.prefHeightProperty().bind(this.borderPane.heightProperty());
        this.borderPane.setCenter(this.board);
    }

    private void populateGridPane(Pair<Integer,Integer> dimension, Map<Position, SlotType> map) {
        int W = 20;
        int H = 20;
        for (int i = 0; i < dimension.getFirst(); i++){      
                ColumnConstraints c = new ColumnConstraints();
                c.setMinWidth(W);
                c.setPrefWidth(W);
                c.setMaxWidth(W);
                this.board.getColumnConstraints().add(c);
        }for (int i = 0; i < dimension.getSecond(); i++){
                RowConstraints r = new RowConstraints();
                r.setMinHeight(H);
                r.setPrefHeight(H);
                r.setMaxHeight(H);     
                this.board.getRowConstraints().add(r);
        }
        for (int i = 0; i < dimension.getFirst(); i++) {
            for (int j = 0; j < dimension.getSecond(); j++) {
                Label tmp = new Label(Objects.isNull(map.get(new Position(i, j))) ? "void" : map.get(new Position(i, j)).toString());
                this.board.add(tmp, i, j);
            }
        }
    }    
}
