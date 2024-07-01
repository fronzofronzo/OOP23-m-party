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
import javafx.scene.layout.*;

import java.util.LinkedList;
import java.util.Set;

public class DominoViewImpl extends AbstractSceneView implements DominoView {

    private static final int PREF_SIZE = 50;

    @FXML
    private Label messageLabel;

    @FXML
    private Button drawButton;

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
    private int col = 0;
    private final int row = 0;

    @FXML
    private void initialize() {
        //todo: da cancellare
        this.initPlayers(new PlayerImplementation("Player1", "Luigi"),
                new PlayerImplementation("Player2", "Mario"));

        this.playerCantDraw();
    }

    //todo: da mettere nell'interfaccia e deve essere chiamato da fuori (gioco principale)
    public void initPlayers(final Player player1, final Player player2) {
        this.controller = new DominoControllerImpl(this, player1, player2);
        this.controller.setUp();
    }

    @FXML
    private void drawButtonClicked() {
        //todo: set drawButton enable
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
        HBox playertiles = isPlayer1 ? this.player1Tiles : this.player2Tiles;
        playertiles.getChildren().clear();
        for (Tile tile : playerTiles) {
            VBox tileBox = new VBox();
            this.generateTile(tileBox, tile.getSideA().getValue(), tile.getSideB().getValue());
            playertiles.getChildren().add(tileBox);
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
    public void playerCanDraw(){
        this.drawButton.setDisable(false);
    }

    @Override
    public void playerCantDraw() {
        this.drawButton.setDisable(true);
    }

    @Override
    public void setBoard(LinkedList<Tile> boardTiles) {
        System.out.println("boardTiles: " + boardTiles);
        this.tilesGrid.getChildren().clear();
        for (Tile tile : boardTiles) {
            HBox tileBox = new HBox();
            this.generateTile(tileBox, tile.getSideA().getValue(), tile.getSideB().getValue());
            tileBox.setAlignment(Pos.CENTER);
            VBox.setVgrow(tileBox, Priority.ALWAYS);
            VBox.setMargin(tileBox, new Insets(0, PREF_SIZE, 0, PREF_SIZE));
            tileBox.setDisable(true);
            this.tilesGrid.add(tileBox, this.col, this.row);
            this.col++;
        }
    }

    @Override
    public void setMessage(DominoMessage message) {
        this.messageLabel.setText(message.toString());
    }

    private void clearTileValues(final HBox playerTiles) {
        playerTiles.getChildren().stream().filter(node -> node instanceof VBox)
                .map(node -> (VBox) node)
                .flatMap(vBox -> vBox.getChildren()
                        .stream()).filter(child -> child instanceof Button)
                .map(button -> (Button) button)
                .forEach(button -> button.setText(""));
    }

    private void generateTile(final Pane box, final int valueA, final int valueB) {
        box.setPrefSize(PREF_SIZE * 2, PREF_SIZE);

        Button sideA = generateSide(valueA);
        Button sideB = generateSide(valueB);

        box.getChildren().add(sideA);
        box.getChildren().add(sideB);

        box.setOnMouseClicked(event -> {
            VBox clicked = (VBox) event.getSource();
            this.selectedSideA = Integer.parseInt(((Button)clicked.getChildren().get(0)).getText());
            this.selectedSideB = Integer.parseInt(((Button)clicked.getChildren().get(1)).getText());
            System.out.println("sideA: "+this.selectedSideA);
            System.out.println("sideB: "+this.selectedSideB);
        });
    }

    private Button generateSide(final int value) {
        Button button = new Button(String.valueOf(value));
        button.setPrefSize(PREF_SIZE, PREF_SIZE);
        return button;
    }
}
