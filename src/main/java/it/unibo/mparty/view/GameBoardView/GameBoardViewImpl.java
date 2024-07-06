package it.unibo.mparty.view.GameBoardView;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.utilities.SlotType;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView{

    private static final String SLOT_STYLE = "-fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;";
    private static final String TEXT_COINS = "MONETE: ";
    private static final String TEXT_STARS = "STELLE: ";
    private static final String TEXT_ITEMS = "OGGETTI: ";
    private static final String TEXT_VOID_ITEM = "NESSUN OGGETTO";
    private static final String TEXT_DICE = "RISULTATO: ";

    private static final Map<SlotType,Color> SLOT_COLOR = Map.of(SlotType.ACTIVE_STAR, Color.GOLD,
                                                                 SlotType.BONUS, Color.LIGHTGREEN,
                                                                 SlotType.MALUS, Color.LIGHTCORAL,
                                                                 SlotType.MULTIPLAYER, Color.LIGHTGRAY,
                                                                 SlotType.NOT_ACTIVE_STAR, Color.WHEAT,
                                                                 SlotType.PATH, Color.WHEAT,
                                                                 SlotType.SHOP, Color.SKYBLUE,
                                                                 SlotType.SINGLEPLAYER, Color.LIGHTGRAY,
                                                                 SlotType.VOID, Color.BLACK);
    private static final Map<SlotType,String> TEXT_TOOL_TIP = Map.of(SlotType.ACTIVE_STAR, "SLOT STELLA",
                                                                 SlotType.BONUS, "SLOT BONUS",
                                                                 SlotType.MALUS, "SLOT MALUS",
                                                                 SlotType.MULTIPLAYER, "SLOT GIOCO",
                                                                 SlotType.NOT_ACTIVE_STAR, "SENTIERO",
                                                                 SlotType.PATH, "SENTIERO",
                                                                 SlotType.SHOP, "NEGOZIO",
                                                                 SlotType.SINGLEPLAYER, "GIOCO",
                                                                 SlotType.VOID, "");
    private static final Map<Integer,Color> PLAYER_COLOR = Map.of(0, Color.ORANGE,
                                                                  1, Color.PURPLE,
                                                                  2, Color.BLUE,
                                                                  3, Color.WHITE);
    private static final Map<Color,String> COLOR_TO_TEXT = Map.of(Color.ORANGE, "Arancione",
                                                                  Color.PURPLE, "Viola",
                                                                  Color.BLUE, "Blu",
                                                                  Color.WHITE, "Bianco");
    @FXML
    private GridPane board;
    @FXML 
    private Label nameP1;
    @FXML 
    private Label coinsP1;
    @FXML 
    private Label starsP1;
    @FXML
    private Label itemP1;
    @FXML
    private Label colorP1;
    @FXML 
    private Label nameP2;
    @FXML 
    private Label coinsP2;
    @FXML 
    private Label starsP2;
    @FXML
    private Label itemP2;
    @FXML
    private Label colorP2;
    @FXML 
    private Label nameP3;
    @FXML 
    private Label coinsP3;
    @FXML 
    private Label starsP3;
    @FXML
    private Label itemP3;
    @FXML
    private Label colorP3;
    @FXML 
    private Label nameP4;
    @FXML 
    private Label coinsP4;
    @FXML 
    private Label starsP4;
    @FXML
    private Label itemP4;
    @FXML
    private Label colorP4;
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
    private Label resultDice;
    @FXML
    private Label labelMessage;

    private static final int RADIUS = 8;
    private Circle player1 = new Circle(RADIUS);
    private Circle player2 = new Circle(RADIUS);
    private Circle player3 = new Circle(RADIUS);
    private Circle player4 = new Circle(RADIUS);

    private List<Label> labelPlayersNames = new ArrayList<>();     
    private List<Label> labelPlayersCoins = new ArrayList<>(); 
    private List<Label> labelPlayersStars = new ArrayList<>();
    private List<Label> labelPlayersItems = new ArrayList<>();
    private List<Label> labelPlayersColor = new ArrayList<>();
    private List<Button> buttonsItem = new ArrayList<>();
    private List<Button> buttonsDirection = new ArrayList<>();
    private List<Circle> players = new ArrayList<>();
    private Map<Position,FlowPane> mapSlots = new HashMap<>();

    @Override
    public void updatePlayer(String palyer, int coins, int stars, List<String> items, Position position) {
        for (int i = 0; i < this.labelPlayersNames.size(); i++) {
            if (this.labelPlayersNames.get(i).getText().equals(palyer)) {
                this.labelPlayersCoins.get(i).setText(TEXT_COINS + String.valueOf(coins));
                this.labelPlayersStars.get(i).setText(TEXT_STARS + String.valueOf(stars));
                this.labelPlayersItems.get(i).setText(TEXT_ITEMS + this.printItems(items));
                for (Map.Entry<Position,FlowPane> entry : this.mapSlots.entrySet()) {
                    entry.getValue().getChildren().remove(this.players.get(i));
                }
                //this.board.add(this.players.get(i), position.getX(), position.getY());
                this.mapSlots.get(position).getChildren().add(this.players.get(i));
            }
        }
    }

    @Override
    public void setUpBoard(Pair<Integer,Integer> dimension, Map<Position, SlotType> map, List<String> usernames) {
        this.populateGridPane(dimension, map);
        this.createData();
        for (int i = 0; i < usernames.size(); i++) {
            this.labelPlayersNames.get(i).setText(usernames.get(i));
            this.players.get(i).setFill(PLAYER_COLOR.get(i));
            this.labelPlayersColor.get(i).setText("(" + COLOR_TO_TEXT.get(PLAYER_COLOR.get(i)) + ")");
        }
    }
        
    private void createData() {
        this.labelPlayersNames.addAll(List.of(this.nameP1, this.nameP2, this.nameP3, this.nameP4));
        this.labelPlayersCoins.addAll(List.of(this.coinsP1, this.coinsP2, this.coinsP3, this.coinsP4));
        this.labelPlayersStars.addAll(List.of(this.starsP1, this.starsP2, this.starsP3, this.starsP4));
        this.labelPlayersItems.addAll(List.of(this.itemP1, this.itemP2, this.itemP3, this.itemP4));
        this.labelPlayersColor.addAll(List.of(this.colorP1, this.colorP2, this.colorP3, this.colorP4));
        this.buttonsItem.addAll(List.of(this.useItem1, this.useItem2, this.useItem3));
        this.buttonsDirection.addAll(List.of(this.buttonUP, this.buttonDOWN, this.buttonLEFT, this.buttonRIGHT));
        this.players.addAll(List.of(this.player1, this.player2, this.player3, this.player4));
        this.players.forEach(c -> c.setStroke(Color.BLACK));
        this.players.forEach(c -> c.setStrokeWidth(2));
        //this.setUpPlayers();
    }

    /* 
    private void setUpPlayers() {
        var ff = this.board.getCellBounds(RADIUS, RADIUS);
        var g = ff.getWidth();
        var h = ff.getHeight();
        double cellWidth = this.board.getWidth() / this.board.getColumnCount();
        double cellHeight = this.board.getHeight() / this.board.getRowCount();
        //double padding = 3.0;
        double offsetX = cellWidth;
        double offsetY = cellHeight - (RADIUS / 2);
        this.player1.setTranslateY(-offsetY); // Sposta il primo cerchio in alto
        this.player2.setTranslateX(offsetX); // Sposta il secondo cerchio a destra
        this.player2.setTranslateY(-offsetY); // Sposta il secondo cerchio in alto
        this.player3.setTranslateY(offsetY); // Sposta il terzo cerchio in basso
        this.player4.setTranslateX(offsetX); // Sposta il quarto cerchio a destra
        this.player4.setTranslateY(offsetY); // Sposta il quarto cerchio in basso
    }*/

    private void populateGridPane(Pair<Integer,Integer> dimension, Map<Position, SlotType> map) {
        for (int i = 0; i < dimension.getFirst(); i++) {
            for (int j = 0; j < dimension.getSecond(); j++) {
                Position pos = new Position(i, j);
                SlotType slotType = Objects.isNull(map.get(pos)) ? 
                                                   SlotType.VOID :
                                                   map.get(pos);
                FlowPane tmp = new FlowPane();
                BackgroundFill backgroundfill = new BackgroundFill(SLOT_COLOR.get(slotType), 
                                                                   CornerRadii.EMPTY, 
                                                                   null);
                Background background = new Background(backgroundfill);
                tmp.setBackground(background);
                if (!slotType.equals(SlotType.VOID)) {
                    tmp.setStyle(SLOT_STYLE);
                    Tooltip tt = new Tooltip(TEXT_TOOL_TIP.get(slotType));
                    Tooltip.install(tmp, tt);
                    this.mapSlots.put(pos, tmp);
                }
                this.board.add(tmp, i, j);
            }
        }
    }

    @Override
    public void updateCommands(List<String> items, String message) {
        for (int i = 0; i < this.buttonsItem.size(); i++) {
            this.buttonsItem.get(i).setText(i < items.size() ?
                                            items.get(i) :
                                            TEXT_VOID_ITEM);
        }
        this.labelMessage.setText(message);
    }
    
    @FXML
    private void rollDice(){
        this.getMainController().rollDice();
    }

    @Override
    public void showResultDice(int result) {
        this.resultDice.setText(TEXT_DICE + String.valueOf(result));
    }

    @FXML
    private void movePlayer(ActionEvent e){
        final Button bt = (Button)e.getSource();
        Optional<Direction> dir = Optional.empty();
        if (bt.equals(buttonMove)) {
            
        } else if (bt.equals(buttonDOWN)) {
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
    private void action() throws IOException{
        this.getMainController().action();
    }

    @FXML
    private void useItem(ActionEvent e){
        Button bt = (Button)e.getSource();
        String text = bt.getText();
        if (!text.equals(TEXT_VOID_ITEM)) {
            //this.getMainController().useItem(text);
        }
    }

    private String printItems(List<String> items) {
        String output = "";
        if (!items.isEmpty()) {
            for (String i : items) {
                output = output.concat("\n- " + i.toString());
            }
        }
        return output;
    }
}