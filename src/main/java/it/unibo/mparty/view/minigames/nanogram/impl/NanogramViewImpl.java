package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;
import it.unibo.mparty.view.SceneView;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Implementation of the {@link NanogramView} interface representing the view for a Nanogram game.
 * This class handles the UI components and interactions for the Nanogram game.
 */
public class NanogramViewImpl implements NanogramView, SceneView {

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
    public GameView getMainView() {
        return null;
    }

    @Override
    public void init(GameView view, GameController controller) {

    }

    @Override
    public Pair<Integer, Integer> userClicked() {
        return null;
    }

    @Override
    public void updateCell(int row, int col, CellState cellState) {

    }

    @Override
    public void updateLives(int actualLives) {

    }

    @Override
    public void displayStatusMessage(StatusMessage message) {

    }

    @Override
    public void displayBoard(Board board) {

    }
}
