package it.unibo.mparty.view.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.controller.minigames.domino.impl.DominoControllerImpl;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.domino.DominoMessage;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Set;

public class DominoViewImpl extends AbstractSceneView implements DominoView {

    private static final int PREF_SIZE = 50;

    @FXML
    private Label messageLabel;

    @FXML
    private Label player1Label;

    @FXML
    private HBox player1Tiles;

    @FXML
    private Label player2Label;

    @FXML
    private HBox player2Tiles;

    @FXML
    private GridPane tilesGrid;

    private DominoController controller;
    private Integer selectedSideA;
    private Integer selectedSideB;

    @FXML
    private void initialize() {
        //todo: da cancellare
        this.initPlayers(new PlayerImplementation("Player1", "Luigi"),
                new PlayerImplementation("Player2", "Mario"));
    }

    //todo: da mettere nell'interfaccia e deve essere chiamato da fuori (gioco principale)
    public void initPlayers(final Player player1, final Player player2) {
        this.controller = new DominoControllerImpl(this, player1, player2);
        this.controller.setUp();
    }

    @FXML
    private void drawButtonClicked() {
        this.controller.drawTile();
    }

    @FXML
    private void playButtonClicked() {
        if (this.selectedSideA != null && this.selectedSideB != null) {
            this.controller.playTile(this.selectedSideA, this.selectedSideB);
            this.selectedSideA = null;
            this.selectedSideB = null;
        }
    }

    @Override
    public void setPlayerTiles(final boolean isPlayer1, final Set<Tile> playerTiles) {
        if (isPlayer1) {
            this.player1Tiles.getChildren().clear();
            for (Tile tile : playerTiles) {
                this.player1Tiles.getChildren().add(this.generateTile(tile.getSideA().getValue(),
                        tile.getSideB().getValue()));
            }
        } else {
            this.player2Tiles.getChildren().clear();
            for (Tile tile : playerTiles) {
                this.player2Tiles.getChildren().add(this.generateTile(tile.getSideA().getValue(),
                        tile.getSideB().getValue()));
            }
        }
    }

    @Override
    public void setPlayerName(final boolean isPlayer1, final String playerName) {
        if (isPlayer1) {
            this.player1Label.setText(playerName);
        } else {
            this.player2Label.setText(playerName);
        }
    }

    @Override
    public void setTurn(final boolean isPlayer1Turn, final String playerName) {
        this.messageLabel.setText(DominoMessage.TURN + playerName);
        if (isPlayer1Turn) {
            clearTileValues(this.player2Tiles);
        } else {
            clearTileValues(this.player1Tiles);
        }
    }

    @Override
    public void setBoard(LinkedList<Tile> boardTiles) {
        Tile tile = boardTiles.getFirst();
        this.tilesGrid.getChildren().add(this.generateTile(tile.getSideA().getValue(), tile.getSideB().getValue()));
    }

    @Override
    public void setErrorMessage() {
        this.messageLabel.setText(DominoMessage.MOVE_NOT_VALID.toString());
    }

    private void clearTileValues(final HBox playerTiles) {
        playerTiles.getChildren().stream().filter(node -> node instanceof VBox)
                .map(node -> (VBox) node)
                .flatMap(vBox -> vBox.getChildren()
                        .stream()).filter(child -> child instanceof Button)
                .map(button -> (Button) button)
                .forEach(button -> button.setText(""));
    }

    private VBox generateTile(final int valueA, final int valueB) {
        VBox box = new VBox();
        box.setPrefSize(PREF_SIZE * 2, PREF_SIZE);
        box.setAlignment(Pos.CENTER);
        VBox.setVgrow(box, Priority.ALWAYS);
        VBox.setMargin(box, new Insets(0, PREF_SIZE, 0, PREF_SIZE));

        Button sideA = generateSide(valueA);
        Button sideB = generateSide(valueB);

        box.getChildren().add(sideA);
        box.getChildren().add(sideB);

        box.setOnMouseClicked(event -> {
            this.selectedSideA = Integer.parseInt(sideA.getText());
            this.selectedSideB = Integer.parseInt(sideB.getText());
        });
        return box;
    }

    private Button generateSide(final int value) {
        Button button = new Button(String.valueOf(value));
        button.setPrefSize(PREF_SIZE, PREF_SIZE);
        return button;
    }
}
