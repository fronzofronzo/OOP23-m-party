package it.unibo.mparty.view.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.controller.minigames.domino.impl.DominoControllerImpl;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Set;

public class DominoViewImpl extends AbstractSceneView implements DominoView {

    @FXML
    private Button drawButton;

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
    private Button putButton;

    private final DominoController controller;

    public DominoViewImpl() {
        this.controller = new DominoControllerImpl(this);
    }

    @FXML
    private void drawButtonClicked() {

    }

    @FXML
    private void playButtonClicked() {

    }

    @Override
    public void setPlayerTiles(boolean isPlayer1, Set<Tile> playerTiles){
        //todo aggiungi p1 e p2 i loro tiles
        if (isPlayer1){
            this.player1Tiles.getChildren().clear();
            for (Tile tile : playerTiles){
                this.player1Tiles.getChildren().add(this.createTileView(tile));
            }
        } else{
            this.player2Tiles.getChildren().clear();
            for (Tile tile : playerTiles){
                this.player2Tiles.getChildren().add(this.createTileView(tile));
            }
        }
    }

    private Text createTileView(Tile tile) {
        return new Text("[" + tile.getSideA() + "|" + tile.getSideB() + "]");
    }
}
