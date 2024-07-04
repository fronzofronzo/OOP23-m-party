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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.util.LinkedList;
import java.util.Set;

public class DominoViewImpl extends AbstractSceneView implements DominoView {

    private static final int PREF_SIZE = 50;

    private static final int SPACING = 10;

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
    private ScrollPane scrollPane;

    //private GridPane tilesGrid;

    private DominoController controller;
    private Integer selectedSideA;
    private Integer selectedSideB;
    private VBox tilesContainer;

    @FXML
    private void initialize() {
        this.tilesContainer = new VBox();
        this.tilesContainer.setSpacing(SPACING);
        this.scrollPane.setContent(tilesContainer);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setFitToHeight(true);
        this.messageLabel.setText("");
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
        this.messageLabel.setText("");
        this.controller.drawTile(); //ogni volta che aggiunge una carta devo ricontrollare se ha mosse
    }

    @FXML
    private void playButtonClicked() {
        if (this.selectedSideA != null && this.selectedSideB != null) {
            this.controller.playTile(this.selectedSideA, this.selectedSideB);
            this.selectedSideA = null;
            this.selectedSideB = null;
        } else if (this.selectedSideA == null && this.selectedSideB == null) {
            this.messageLabel.setText(DominoMessage.SELECT_TILE.toString());
        }
    }

    @Override
    public void setPlayerTiles(final boolean isPlayer1, final Set<Tile> playerTiles) {
        HBox playerTilesBox = isPlayer1 ? this.player1Tiles : this.player2Tiles;
        playerTilesBox.getChildren().clear();
        for (Tile tile : playerTiles) {
            VBox tileBox = new VBox();
            tileBox.setAlignment(Pos.CENTER);
            VBox.setVgrow(tileBox, Priority.ALWAYS);
            VBox.setMargin(tileBox, new Insets(0, PREF_SIZE, 0, PREF_SIZE));
            this.generateTile(tileBox, tile.getSideA().getValue(), tile.getSideB().getValue());
            playerTilesBox.getChildren().add(tileBox);
        }
    }

    @Override
    public void setPlayerName(final boolean isPlayer1, final String playerName) {
        Label targetLabel = isPlayer1 ? this.player1Label : this.player2Label;
        targetLabel.setText(" " + playerName + " ");
    }

    @Override
    public void setTurn(final boolean isPlayer1Turn) {
        if (isPlayer1Turn) {
            this.highlightPlayerTurn(this.player1Label, this.player2Label);
            //clearTileValues(this.player2Tiles);
        } else {
            this.highlightPlayerTurn(this.player2Label, this.player1Label);
            //clearTileValues(this.player1Tiles);
        }
    }

    private void highlightPlayerTurn(Label currentPlayerLabel, Label otherPlayerLabel) {
        currentPlayerLabel.setBackground(new Background(new BackgroundFill
                (Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        otherPlayerLabel.setBackground(Background.EMPTY);
    }

    @Override
    public void playerCanDraw() {
        this.drawButton.setDisable(false);
    }

    @Override
    public void playerCantDraw() {
        this.drawButton.setDisable(true);
    }

    @Override
    public void setBoard(LinkedList<Tile> boardTiles) {
        tilesContainer.getChildren().clear();

        HBox rowBox = new HBox();
        rowBox.setAlignment(Pos.CENTER);
        rowBox.setSpacing(SPACING);

        for (Tile tile : boardTiles) {
            HBox tileBox = new HBox();
            generateTile(tileBox, tile.getSideA().getValue(), tile.getSideB().getValue());
            tileBox.setAlignment(Pos.CENTER);
            tileBox.setDisable(true);
            VBox.setMargin(tileBox, new Insets(0, PREF_SIZE, 0, PREF_SIZE));

            rowBox.getChildren().add(tileBox);
        }
        tilesContainer.getChildren().add(rowBox);
    }

    @Override
    public void setMessage(DominoMessage message) {
        this.messageLabel.setText(message.toString());
    }

    @Override
    public void gameEnd(String winner) {
        this.messageLabel.setText(winner + DominoMessage.WIN);

        this.drawButton.setDisable(true);

        disableTiles(this.player1Tiles);
        disableTiles(this.player2Tiles);

        tilesContainer.getChildren().forEach(node -> {
            if (node instanceof HBox) {
                ((HBox) node).getChildren().forEach(tileNode -> tileNode.setDisable(true));
            }
        });
    }

    private void disableTiles(HBox playerTilesBox) {
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

        Button sideA = generateSide(valueA);
        Button sideB = generateSide(valueB);

        box.getChildren().add(sideA);
        box.getChildren().add(sideB);

        box.setOnMouseClicked(event -> {
            VBox clicked = (VBox) event.getSource();
            this.messageLabel.setText("");
            this.selectedSideA = Integer.parseInt(((Button) clicked.getChildren().get(0)).getText());
            this.selectedSideB = Integer.parseInt(((Button) clicked.getChildren().get(1)).getText());
        });
    }

    private Button generateSide(final int value) {
        Button button = new Button(String.valueOf(value));
        button.setPrefSize(PREF_SIZE, PREF_SIZE);
        return button;
    }
}
