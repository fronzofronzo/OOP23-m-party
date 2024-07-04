package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.Circle;

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
    private Label itemP1;
    @FXML 
    private VBox sectionP2;
    @FXML 
    private Label nameP2;
    @FXML 
    private Label coinsP2;
    @FXML 
    private Label starsP2;
    @FXML
    private Label itemP2;
    @FXML 
    private VBox sectionP3;
    @FXML 
    private Label nameP3;
    @FXML 
    private Label coinsP3;
    @FXML 
    private Label starsP3;
    @FXML
    private Label itemP3;
    @FXML 
    private VBox sectionP4;
    @FXML 
    private Label nameP4;
    @FXML 
    private Label coinsP4;
    @FXML 
    private Label starsP4;
    @FXML
    private Label itemP4;
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
    @FXML
    private Label resultDice;

    private Circle player1 = new Circle(10, Color.ORANGE);
    private Circle player2 = new Circle(10, Color.PURPLE);
    private Circle player3 = new Circle(10, Color.BLUE);
    private Circle player4 = new Circle(10, Color.PINK);

    private List<Label> labelPlayersNames = new ArrayList<>();     
    private List<Label> labelPlayersCoins = new ArrayList<>(); 
    private List<Label> labelPlayersStars = new ArrayList<>();
    private List<Label> labelPlayersItems = new ArrayList<>();
    private List<Button> buttonsItem = new ArrayList<>();
    private List<Button> buttonsDirection = new ArrayList<>();
    private List<Circle> players = new ArrayList<>();

    @Override
    public void updatePlayerStats(String palyer, int coins, int stars, List<String> items) {
        for (int i = 0; i <= this.labelPlayersNames.size(); i++) {
            if (this.labelPlayersNames.get(i).getText().equals(palyer)) {
                this.labelPlayersCoins.get(i).setText(String.valueOf(coins));
                this.labelPlayersStars.get(i).setText(String.valueOf(stars));
                this.labelPlayersItems.get(i).setText(items.toString());
            }
        }
    }

    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> map, List<String> nicknames, Position stratingPosition) {
        this.populateGridPane(dimension, map);
        this.setSize();
        this.createData();
        for(int i=0; i< nicknames.size(); i++){
            this.labelPlayersNames.get(i).setText(nicknames.get(i));
            this.labelPlayersStars.get(i).setText(String.valueOf(0));
            this.labelPlayersCoins.get(i).setText(String.valueOf(0));
        }
        for (int i = 0; i < nicknames.size(); i++) {
            this.board.add(this.players.get(i), stratingPosition.getX(), stratingPosition.getY());
        }
    }
        
    private void createData() {
        this.labelPlayersNames.addAll(List.of(this.nameP1, this.nameP2, this.nameP3, this.nameP4));
        this.labelPlayersCoins.addAll(List.of(this.coinsP1, this.coinsP2, this.coinsP3, this.coinsP4));
        this.labelPlayersStars.addAll(List.of(this.starsP1, this.starsP2, this.starsP3, this.starsP4));
        this.labelPlayersItems.addAll(List.of(this.itemP1, this.itemP2, this.itemP3, this.itemP4));
        this.buttonsItem.addAll(List.of(this.useItem1, this.useItem2, this.useItem3));
        this.buttonsDirection.addAll(List.of(this.buttonUP, this.buttonDOWN, this.buttonLEFT, this.buttonRIGHT));
        this.players.addAll(List.of(this.player1, this.player2, this.player3, this.player4));
        this.setUpPlayers();
    }

    private void setUpPlayers() {
        //double cellWidth = this.board.getWidth() / this.board.getColumnCount();
        //double cellHeight = this.board.getHeight() / this.board.getRowCount();

        //double offsetX = (cellWidth / 2) - this.player1.getRadius();
        //double offsetY = (cellHeight / 2) - this.player1.getRadius();
        int d = 10;
        this.player1.setTranslateY(-d); // Sposta il primo cerchio in alto
        this.player2.setTranslateX(d); // Sposta il secondo cerchio a destra
        this.player2.setTranslateY(d); // Sposta il secondo cerchio in alto
        this.player3.setTranslateY(d); // Sposta il terzo cerchio in basso
        this.player4.setTranslateX(d); // Sposta il quarto cerchio a destra
        this.player4.setTranslateY(d); // Sposta il quarto cerchio in basso
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
        for (int i = 0; i < dimension.getX(); i++) {
            for (int j = 0; j < dimension.getY(); j++) {
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

    @Override
    public void updateCommands(List<String> items, Set<Direction> directions) {
        for (int i = 0; i < this.buttonsItem.size(); i++) {
            if (i < items.size()) {
                this.buttonsItem.get(i).setText(items.get(i));
                this.buttonsItem.get(i).setDisable(false);
            } else {
                this.buttonsItem.get(i).setText("");
                this.buttonsItem.get(i).setDisable(true);
            }
        }
        this.buttonsDirection.stream().forEach(b -> b.setDisable(true));
        for (Direction d : directions) {
            switch (d) {
                case UP: this.buttonUP.setDisable(true);
                case DOWN: this.buttonDOWN.setDisable(true);
                case LEFT: this.buttonLEFT.setDisable(true);
                case RIGHT: this.buttonRIGHT.setDisable(true);
                default: break;
            }
        }
    }
    
    @FXML
    private void rollDice(){
        this.getMainController().rollDice();
    }

    @Override
    public void showResultDice(int result) {
        this.resultDice.setText(String.valueOf(result));
    }

    @FXML
    private void movePlayer(ActionEvent e){
        final Button bt = (Button)e.getSource();
        Optional<Direction> dir = Optional.empty();
        if (bt.equals(buttonDOWN)) {
            dir = Optional.of(Direction.DOWN);
        } else if (bt.equals(buttonUP)) {
            dir = Optional.of(Direction.UP);
        } else if (bt.equals(buttonLEFT)) {
            dir = Optional.of(Direction.LEFT);
        } else if (bt.equals(buttonRIGHT)) {
            dir = Optional.of(Direction.RIGHT);
        }
        this.getMainController().movePlayer(dir);
    }

    @FXML
    private void activateSlot(){
        this.getMainController().activateSlot();
    }

    @Override
    public void updatePlayerPos(Pair<String,Position> playerInfo) {
        for (int i = 0; i < this.labelPlayersNames.size(); i++) {
            if (this.labelPlayersNames.get(i).getText().equals(playerInfo.getX())) {
                this.board.getChildren().remove(this.players.get(i));
                this.board.add(this.players.get(i), playerInfo.getY().getX(), playerInfo.getY().getY());
            }
        }
    }    
}