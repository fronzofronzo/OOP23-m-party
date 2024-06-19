package it.unibo.mparty.model.minigames.nanogram.impl;

import it.unibo.mparty.model.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NanogramViewImpl implements NanogramView {

    @FXML
    private HBox buttonBox;

    @FXML
    private GridPane columnHints;

    @FXML
    private RadioButton crossButton;

    @FXML
    private RadioButton filledButton;

    @FXML
    private GridPane gameGrid;

    @FXML
    private HBox livesBox;

    @FXML
    private Label livesLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Label numberLivesLabel;

    @FXML
    private BorderPane pane;

    @FXML
    private GridPane rowHints;

    @Override
    public Pair<Integer, Integer> userClicked() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userClicked'");
    }

    @Override
    public void displayStatusMessage(String message, String messageType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayStatusMessage'");
    }

}