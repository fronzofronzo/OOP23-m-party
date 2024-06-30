package it.unibo.mparty.view.minigames.domino.impl;

import it.unibo.mparty.view.minigames.domino.api.DominoView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DominoViewImpl implements DominoView {

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

    @FXML
    private void drawButtonClicked() {

    }

    @FXML
    private void playButtonClicked() {

    }
}
