package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    private static final Map<SlotType,Color> SLOT_COLOR = Map.of(SlotType.ACTIVE_STAR, Color.GOLD, 
                                                                 SlotType.BONUS, Color.LIGHTGREEN, 
                                                                 SlotType.MALUS, Color.LIGHTCORAL, 
                                                                 SlotType.MULTIPLAYER, Color.LIGHTGRAY, 
                                                                 SlotType.NOT_ACTIVE_STAR, Color.WHEAT, 
                                                                 SlotType.PATH, Color.WHEAT, 
                                                                 SlotType.SHOP, Color.SKYBLUE, 
                                                                 SlotType.SINGLEPLAYER, Color.LIGHTGRAY, 
                                                                 SlotType.VOID, Color.BLACK);
    @FXML
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
    @FXML
    private SplitPane leftSplitPane;
    @FXML
    private SplitPane rightSplitPane;

    //private List<Label> labelPlayersNames;     
    private List<Label> labelPlayersCoins = new ArrayList<>(); 
    //private List<Label> labelPlayersStars;
    //private List<Button> buttonsItem;

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
        this.populateGridPane(dimension, map);
        this.setSize();
        this.createData();
        }
        
    private void createData() {
        this.labelPlayersCoins.addAll(List.of(this.coinsP1, this.coinsP2, this.coinsP3, this.coinsP4));
    }

    private void setSize() {
        this.borderPane.setMinSize(1000, 600);
        this.leftSplitPane.setMinSize(150, 400);
        this.rightSplitPane.setMinSize(150, 400);
        this.board.setMinSize(700, 400);
        this.paneCommand.setMinSize(1000, 100);
        this.paneCommand.prefHeight(100);
        this.paneCommand.maxHeight(100);
    }

    private void populateGridPane(Pair<Integer,Integer> dimension, Map<Position, SlotType> map) {
        for (int i = 0; i < dimension.getFirst(); i++) {
            for (int j = 0; j < dimension.getSecond(); j++) {
                Pane tmp = new Pane();
                BackgroundFill backgroundfill = new BackgroundFill(getColor(map.get(new Position(i, j))),CornerRadii.EMPTY, null);
                Background background = new Background(backgroundfill);
                tmp.setBackground(background);
                this.board.add(tmp, i, j);
            }
        }
    }

    private Color getColor(SlotType slotType) {
        if (Objects.isNull(slotType)) {
            return SLOT_COLOR.get(SlotType.VOID);
        } else {
            return SLOT_COLOR.get(slotType);
        }
    }    
}
