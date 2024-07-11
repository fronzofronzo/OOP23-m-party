package it.unibo.mparty.view.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.controller.minigames.domino.impl.DominoControllerImpl;
import it.unibo.mparty.model.minigames.domino.tile.api.Tile;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.domino.DominoMessage;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Implementation of the {@link DominoView} interface.
 */
public class DominoViewImpl extends AbstractSceneView implements DominoView {

    private static final String TUTORIAL_PATH = "text/dominoTutorial.txt";
    private static final int PREF_SIZE = 50;
    private static final int SPACING = 10;

    @FXML
    private Label messageLabel;

    @FXML
    private Label deckSize;

    @FXML
    private Button drawButton;

    @FXML
    private Button playButton;

    @FXML
    private VBox buttonBox;

    @FXML
    private Label player1Label;

    @FXML
    private HBox player1Tiles;

    @FXML
    private Label player2Label;

    @FXML
    private HBox player2Tiles;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox tilesContainer;

    @FXML
    private Button tutorialButton;

    @FXML
    private Label tutorialLabel;

    private DominoController controller;
    private Integer selectedSideA;
    private Integer selectedSideB;

    @FXML
    public void initialize() {
        this.controller = new DominoControllerImpl(this);
        this.tilesContainer = new VBox();
        this.tilesContainer.setSpacing(SPACING);
        this.tilesContainer.setAlignment(Pos.CENTER);
        this.scrollPane.setContent(this.tilesContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);
        this.messageLabel.setText("");

        final HBox boardHBox = new HBox();
        boardHBox.setAlignment(Pos.CENTER);
        boardHBox.setSpacing(SPACING);
        this.tilesContainer.getChildren().add(boardHBox);

        this.playerCantDraw();
        this.updateTutorialLabel();
    }

    @FXML
    public void drawButtonClicked() {
        this.messageLabel.setText("");
        this.controller.drawTile();
    }

    @FXML
    public void playButtonClicked() {
        if (this.selectedSideA != null && this.selectedSideB != null) {
            this.controller.playTile(this.selectedSideA, this.selectedSideB);
            this.selectedSideA = null;
            this.selectedSideB = null;
        } else if (this.selectedSideA == null && this.selectedSideB == null) {
            this.messageLabel.setText(DominoMessage.SELECT_TILE.toString());
        }
    }

    @FXML
    public void tutorialClicked() {
        if (this.tutorialLabel.isVisible()) {
            this.tutorialButton.setText("Tutorial");
            this.tutorialLabel.setVisible(false);
        } else {
            this.tutorialButton.setText("Chiudi\nTutorial");
            this.tutorialLabel.setVisible(true);
        }
    }

    private void updateTutorialLabel() {
        final InputStream input = getClass().getClassLoader().getResourceAsStream(TUTORIAL_PATH);
        if (input != null) {
            try {
                this.tutorialLabel.setText(new String(input.readAllBytes(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                final Logger log = Logger.getLogger(DominoViewImpl.class.getName());
                log.fine(e.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerTiles(final boolean isPlayer1, final Set<Tile> playerTiles) {
        final HBox playerTilesBox = isPlayer1 ? this.player1Tiles : this.player2Tiles;
        playerTilesBox.getChildren().clear();
        for (final Tile tile : playerTiles) {
            final VBox tileBox = new VBox();
            tileBox.setAlignment(Pos.CENTER);
            VBox.setVgrow(tileBox, Priority.ALWAYS);
            VBox.setMargin(tileBox, new Insets(0, PREF_SIZE, 0, PREF_SIZE));
            this.generateTile(tileBox, tile.getSideA().getValue(), tile.getSideB().getValue());
            tileBox.setStyle("-fx-border-color: #d3d3d3; -fx-border-width: 1px; -fx-border-style: solid;");
            playerTilesBox.getChildren().add(tileBox);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final boolean isPlayer1, final String playerName) {
        final Label targetLabel = isPlayer1 ? this.player1Label : this.player2Label;
        targetLabel.setText(" " + playerName + " ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTurn(final boolean isPlayer1Turn) {
        if (isPlayer1Turn) {
            this.highlightPlayerTurn(this.player1Label, this.player2Label);
            this.clearTileValues(this.player2Tiles);
        } else {
            this.highlightPlayerTurn(this.player2Label, this.player1Label);
            this.clearTileValues(this.player1Tiles);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerCanDraw() {
        this.drawButton.setDisable(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playerCantDraw() {
        this.drawButton.setDisable(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMessage(final DominoMessage message) {
        this.messageLabel.setText(message.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRemainingTileSize(final int size) {
        this.deckSize.setText(DominoMessage.REMAINING_TILE + String.valueOf(size));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> winner) {
        this.messageLabel.setText(DominoMessage.WIN.getFormattedMessage(winner.getFirst(), winner.getSecond()));

        this.drawButton.setDisable(true);
        this.playButton.setDisable(true);

        this.disableTiles(this.player1Tiles);
        this.disableTiles(this.player2Tiles);

        this.tilesContainer.getChildren().forEach(node -> {
            if (node instanceof HBox) {
                ((HBox) node).getChildren().forEach(tileNode -> tileNode.setDisable(true));
            }
        });

        final Button returnButton = new Button("Torna al \ngioco principale");
        returnButton.setStyle("-fx-font-size: 13pt; -fx-text-alignment: center;");
        returnButton.setOnAction(e -> {
            try {
                this.getMainView().setBoardScene();
            } catch (IOException ex) {
                final Logger log = Logger.getLogger(DominoViewImpl.class.getName());
                log.fine(ex.getMessage());
            }
        });

        this.buttonBox.getChildren().add(returnButton);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final List<Pair<Integer, Integer>> boardTiles) {
        this.tilesContainer.getChildren().clear();

        final HBox rowBox = new HBox();
        rowBox.setAlignment(Pos.CENTER);
        rowBox.setSpacing(SPACING);

        for (final Pair<Integer, Integer> tile : boardTiles) {
            final HBox tileBox = new HBox();
            this.generateTile(tileBox, tile.getFirst(), tile.getSecond());
            tileBox.setAlignment(Pos.CENTER);
            tileBox.setDisable(true);
            VBox.setMargin(tileBox, new Insets(0, PREF_SIZE, 0, PREF_SIZE));

            rowBox.getChildren().add(tileBox);
        }
        this.tilesContainer.getChildren().add(rowBox);
    }

    private void highlightPlayerTurn(final Label currentPlayerLabel, final Label otherPlayerLabel) {
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        otherPlayerLabel.setBackground(Background.EMPTY);
    }

    private void disableTiles(final HBox playerTilesBox) {
        playerTilesBox.getChildren().forEach(tileNode -> {
            if (tileNode instanceof VBox) {
                ((VBox) tileNode).getChildren().forEach(sideNode -> sideNode.setDisable(true));
            }
        });
    }

    private void clearTileValues(final HBox playerTiles) {
        playerTiles.getChildren().stream()
                .filter(node -> node instanceof VBox)
                .map(node -> (VBox) node)
                .flatMap(vBox -> vBox.getChildren().stream())
                .filter(child -> child instanceof Button)
                .map(button -> (Button) button)
                .forEach(button -> {
                    button.setText("");
                    button.setDisable(true);
                });
    }

    private void generateTile(final Pane box, final int valueA, final int valueB) {
        box.setPrefSize(PREF_SIZE * 2, PREF_SIZE);

        final Button sideA = this.generateSide(valueA);
        final Button sideB = this.generateSide(valueB);

        box.getChildren().add(sideA);
        box.getChildren().add(sideB);

        box.setOnMouseClicked(event -> {
            final VBox clicked = (VBox) event.getSource();
            this.messageLabel.setText("");
            this.selectedSideA = Integer.parseInt(((Button) clicked.getChildren().get(0)).getText());
            this.selectedSideB = Integer.parseInt(((Button) clicked.getChildren().get(1)).getText());
        });
    }

    private Button generateSide(final int value) {
        final Button button = new Button(String.valueOf(value));
        button.setPrefSize(PREF_SIZE, PREF_SIZE);
        return button;
    }
}
