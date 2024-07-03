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
                                                                 SlotType.BONUS, Color.GREEN, 
                                                                 SlotType.MALUS, Color.RED, 
                                                                 SlotType.MULTIPLAYER, Color.LIGHTGREEN, 
                                                                 SlotType.NOT_ACTIVE_STAR, Color.LIGHTGREEN, 
                                                                 SlotType.PATH, Color.LIGHTGREEN, 
                                                                 SlotType.SHOP, Color.BROWN, 
                                                                 SlotType.SINGLEPLAYER, Color.LIGHTGREEN, 
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
        this.populateGridPane(dimension, map);
        this.setSize();
        }
        
    private void setSize() {
        this.borderPane.setMinSize(1000, 600);
        this.leftSplitPane.setMinSize(150, 400);
        this.rightSplitPane.setMinSize(150, 400);
        this.board.setMinSize(700, 400);
        this.paneCommand.setMinSize(1000, 200);
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
            return Color.BLACK;
        } else {
            return SLOT_COLOR.get(slotType);
        }
    }    
}
