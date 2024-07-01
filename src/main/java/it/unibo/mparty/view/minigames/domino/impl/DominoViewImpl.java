package it.unibo.mparty.view.minigames.domino.impl;

import it.unibo.mparty.controller.minigames.domino.api.DominoController;
import it.unibo.mparty.controller.minigames.domino.impl.DominoControllerImpl;
import it.unibo.mparty.model.minigames.domino.api.Tile;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.impl.PlayerImplementation;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.domino.api.DominoView;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Set;

public class DominoViewImpl extends AbstractSceneView implements DominoView {

    private static final int PREF_SIZE = 50;

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

    private DominoController controller;

    @FXML
    private void initialize() {
        //todo: da cancellare
        this.initPlayers(new PlayerImplementation("Player1", "Luigi"), new PlayerImplementation("Player2", "Mario"));

        this.messageLabel.setText("");
    }

    //todo: da mettere nell'interfaccia e deve essere chiamato da fuori (gioco principale)
    public void initPlayers(Player player1, Player player2) {
        this.controller = new DominoControllerImpl(this, player1, player2);
        this.controller.setUp();
    }

    @FXML
    private void drawButtonClicked() {

    }

    @FXML
    private void playButtonClicked() {

    }

    @Override
    public void setPlayerTiles(boolean isPlayer1, Set<Tile> playerTiles) {
        if (isPlayer1) {
            this.player1Tiles.getChildren().clear();
            for (Tile tile : playerTiles) {
                this.player1Tiles.getChildren().add(generateDomino(tile.getSideA().getValue(),
                        tile.getSideB().getValue()));
            }
        } else {
            this.player2Tiles.getChildren().clear();
            for (Tile tile : playerTiles) {
                this.player2Tiles.getChildren().add(generateDomino(tile.getSideA().getValue(),
                        tile.getSideB().getValue()));
            }
        }
    }

    @Override
    public void setPlayerName(boolean isPlayer1, String playerName){
        if (isPlayer1){
            this.player1Label.setText(playerName);
        } else {
            this.player2Label.setText(playerName);
        }
    }

    private VBox generateDomino(int valueA, int valueB) {
        VBox box = new VBox();
        box.setPrefSize(PREF_SIZE * 2, PREF_SIZE);
        VBox.setVgrow(box, Priority.ALWAYS);
        box.setAlignment(Pos.CENTER);
        VBox.setMargin(box, new Insets(0, PREF_SIZE, 0, PREF_SIZE));
        Button sideA = generateSide(valueA);
        Button sideB = generateSide(valueB);

        box.getChildren().add(sideA);
        box.getChildren().add(sideB);
        return box;
    }

    private Button generateSide(int value) {
        Button button = new Button(String.valueOf(value));
        button.setPrefSize(PREF_SIZE, PREF_SIZE);
        //button.setMaxSize();
        return button;
    }
}
