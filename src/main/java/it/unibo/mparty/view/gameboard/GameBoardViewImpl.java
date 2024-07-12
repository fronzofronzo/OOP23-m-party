package it.unibo.mparty.view.gameboard;

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

/**
 * This class extends {@link AbstractSceneView} and implements
 * {@link GameBoardView}.
 */
public class GameBoardViewImpl extends AbstractSceneView implements GameBoardView {

    private static final String SLOT_STYLE = "-fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;";
    private static final String TEXT_COINS = "MONETE: ";
    private static final String TEXT_STARS = "STELLE: ";
    private static final String TEXT_ITEMS = "OGGETTI: ";
    private static final String TEXT_VOID_ITEM = "NESSUN OGGETTO";
    private static final String TEXT_DICE_RESULT = "RISULTATO: ";
    private static final String TEXT_TURN = "TURNO: ";
    private static final int RADIUS = 8;
    private static final int PAWN_STROKE = 2;
    private static final Map<SlotType, Color> SLOT_COLOR = Map.of(SlotType.ACTIVE_STAR, Color.GOLD,
            SlotType.BONUS, Color.LIGHTGREEN,
            SlotType.MALUS, Color.LIGHTCORAL,
            SlotType.MULTIPLAYER, Color.LIGHTGRAY,
            SlotType.NOT_ACTIVE_STAR, Color.WHEAT,
            SlotType.PATH, Color.WHEAT,
            SlotType.SHOP, Color.SKYBLUE,
            SlotType.SINGLEPLAYER, Color.LIGHTGRAY,
            SlotType.VOID, Color.BLACK);
    private static final Map<Integer, Color> PLAYER_COLOR = Map.of(0, Color.ORANGE,
            1, Color.PURPLE,
            2, Color.BLUE,
            3, Color.WHITE);
    private static final Map<Color, String> COLOR_TO_TEXT = Map.of(Color.ORANGE, "Arancione",
            Color.PURPLE, "Viola",
            Color.BLUE, "Blu",
            Color.WHITE, "Bianco");
    private final Circle player1;
    private final Circle player2;
    private final Circle player3;
    private final Circle player4;
    private final List<Label> labelPlayersNames;
    private final List<Label> labelPlayersCoins;
    private final List<Label> labelPlayersStars;
    private final List<Label> labelPlayersItems;
    private final List<Label> labelPlayersColor;
    private final List<Button> buttonsItem;
    private final List<Button> buttonsDirection;
    private final List<Circle> pawns;
    private final Map<Position, FlowPane> board;
    @FXML
    private GridPane gridPaneBoard;
    @FXML
    private Label nameP1;
    @FXML
    private Label colorP1;
    @FXML
    private Label coinsP1;
    @FXML
    private Label starsP1;
    @FXML
    private Label itemP1;
    @FXML
    private Label nameP2;
    @FXML
    private Label colorP2;
    @FXML
    private Label coinsP2;
    @FXML
    private Label starsP2;
    @FXML
    private Label itemP2;
    @FXML
    private Label nameP3;
    @FXML
    private Label colorP3;
    @FXML
    private Label coinsP3;
    @FXML
    private Label starsP3;
    @FXML
    private Label itemP3;
    @FXML
    private Label nameP4;
    @FXML
    private Label colorP4;
    @FXML
    private Label coinsP4;
    @FXML
    private Label starsP4;
    @FXML
    private Label itemP4;
    @FXML
    private Button useItem1;
    @FXML
    private Button useItem2;
    @FXML
    private Button useItem3;
    @FXML
    private Button buttonUP;
    @FXML
    private Button buttonRIGHT;
    @FXML
    private Button buttonDOWN;
    @FXML
    private Button buttonLEFT;
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
    @FXML
    private Label labelTurn;

    /**
     * This is the contructor of {@link GameBoardViewImpl}.
     */
    public GameBoardViewImpl() {
        this.player1 = new Circle(RADIUS);
        this.player2 = new Circle(RADIUS);
        this.player3 = new Circle(RADIUS);
        this.player4 = new Circle(RADIUS);
        this.labelPlayersNames = new ArrayList<>();
        this.labelPlayersCoins = new ArrayList<>();
        this.labelPlayersStars = new ArrayList<>();
        this.labelPlayersItems = new ArrayList<>();
        this.labelPlayersColor = new ArrayList<>();
        this.buttonsItem = new ArrayList<>();
        this.buttonsDirection = new ArrayList<>();
        this.pawns = new ArrayList<>();
        this.board = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpBoard(final Pair<Integer, Integer> dimension,
            final Map<Position, SlotType> map,
            final List<String> usernames) {
        this.createData();
        this.populateBoard(dimension, map);
        this.setUpPlayers(usernames);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayer(final String palyer,
            final int coins,
            final int stars,
            final List<String> items,
            final Position position) {
        for (int i = 0; i < this.labelPlayersNames.size(); i++) {
            if (this.labelPlayersNames.get(i).getText().equals(palyer)) {
                this.labelPlayersCoins.get(i).setText(TEXT_COINS + coins);
                this.labelPlayersStars.get(i).setText(TEXT_STARS + stars);
                this.labelPlayersItems.get(i).setText(TEXT_ITEMS + this.printItems(items));
                for (final Map.Entry<Position, FlowPane> entry : this.board.entrySet()) {
                    entry.getValue().getChildren().remove(this.pawns.get(i));
                }
                this.board.get(position).getChildren().add(this.pawns.get(i));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBoard(final Map<Position, SlotType> boardUpdates) {
        if (!boardUpdates.isEmpty()) {
            for (final Map.Entry<Position, SlotType> entry : boardUpdates.entrySet()) {
                final FlowPane flowPane = this.board.get(entry.getKey());
                getUpFlowPane(flowPane, entry.getValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateCommands(final List<String> items,
            final String message,
            final Pair<String, String> turn) {
        for (int i = 0; i < this.buttonsItem.size(); i++) {
            this.buttonsItem.get(i).setText(i < items.size()
                    ? items.get(i)
                    : TEXT_VOID_ITEM);
        }
        this.labelMessage.setText(message);
        this.labelTurn.setText(TEXT_TURN + turn.getFirst() + "/" + turn.getSecond());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResultDice(final int result) {
        this.resultDice.setText(TEXT_DICE_RESULT + result);
    }

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void rollDice() {
        this.getMainController().rollDice();
    }

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void movePlayer(final ActionEvent e) {
        final Button bt = (Button) e.getSource();
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
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void action() throws IOException {
        this.getMainController().action();
    }

    @FXML
    @SuppressWarnings("PMD.UnusedPrivateMethod")
    private void useItem(final ActionEvent e) {
        final Button bt = (Button) e.getSource();
        final String text = bt.getText();
        if (!TEXT_VOID_ITEM.equals(text)) {
            this.getMainController().useItem(text);
        }
    }

    private String printItems(final List<String> items) {
        String output = "";
        if (!items.isEmpty()) {
            for (final String i : items) {
                output = output.concat("\n- " + i);
            }
        }
        return output;
    }

    private void createData() {
        this.labelPlayersNames.addAll(List.of(this.nameP1, this.nameP2, this.nameP3, this.nameP4));
        this.labelPlayersCoins.addAll(List.of(this.coinsP1, this.coinsP2, this.coinsP3, this.coinsP4));
        this.labelPlayersStars.addAll(List.of(this.starsP1, this.starsP2, this.starsP3, this.starsP4));
        this.labelPlayersItems.addAll(List.of(this.itemP1, this.itemP2, this.itemP3, this.itemP4));
        this.labelPlayersColor.addAll(List.of(this.colorP1, this.colorP2, this.colorP3, this.colorP4));
        this.buttonsItem.addAll(List.of(this.useItem1, this.useItem2, this.useItem3));
        this.buttonsDirection.addAll(List.of(this.buttonUP, this.buttonDOWN, this.buttonLEFT, this.buttonRIGHT));
        this.pawns.addAll(List.of(this.player1, this.player2, this.player3, this.player4));
        this.pawns.forEach(c -> c.setStroke(Color.BLACK));
        this.pawns.forEach(c -> c.setStrokeWidth(PAWN_STROKE));
    }

    private void populateBoard(final Pair<Integer, Integer> dimension,
            final Map<Position, SlotType> map) {
        for (int i = 0; i < dimension.getFirst(); i++) {
            for (int j = 0; j < dimension.getSecond(); j++) {
                final Position pos = new Position(i, j);
                final SlotType slotType = Objects.isNull(map.get(pos))
                        ? SlotType.VOID
                        : map.get(pos);
                FlowPane tmp = new FlowPane();
                tmp = getUpFlowPane(tmp, slotType);
                this.board.put(pos, tmp);
                this.gridPaneBoard.add(tmp, i, j);
            }
        }
    }

    private void setUpPlayers(final List<String> usernames) {
        for (int i = 0; i < usernames.size(); i++) {
            this.labelPlayersNames.get(i).setText(usernames.get(i));
            this.pawns.get(i).setFill(PLAYER_COLOR.get(i));
            this.labelPlayersColor.get(i).setText("(" + COLOR_TO_TEXT.get(PLAYER_COLOR.get(i)) + ")");
        }
    }

    private FlowPane getUpFlowPane(final FlowPane tmp, final SlotType slotType) {
        final BackgroundFill backgroundfill = new BackgroundFill(SLOT_COLOR.get(slotType),
                CornerRadii.EMPTY,
                null);
        final Background background = new Background(backgroundfill);
        tmp.setBackground(background);
        if (!slotType.equals(SlotType.VOID)) {
            tmp.setStyle(SLOT_STYLE);
            final Tooltip toolTip = new Tooltip(slotType.toString());
            Tooltip.install(tmp, toolTip);
        }
        return tmp;
    }
}
