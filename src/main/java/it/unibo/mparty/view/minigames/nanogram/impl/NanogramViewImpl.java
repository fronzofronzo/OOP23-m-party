package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.GameView;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Implementation of the {@link NanogramView} interface representing the view for a Nanogram game.
 * This class handles the UI components and interactions for the Nanogram game.
 */
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

    private Pair<Integer, Integer> lastClickedCell;

    private CellState selectedState = CellState.FILLED;

    @FXML
    private void initialize() {
        // Create a ToggleGroup and add the RadioButtons to it
        ToggleGroup toggleGroup = new ToggleGroup();
        filledButton.setToggleGroup(toggleGroup);
        crossButton.setToggleGroup(toggleGroup);

        // Set filledButton as selected by default
        filledButton.setSelected(true);

        filledButton.setOnAction(event -> selectedState = CellState.FILLED);
        crossButton.setOnAction(event -> selectedState = CellState.CROSSED);

        for (int row = 0; row < gameGrid.getRowCount(); row++) {
            for (int col = 0; col < gameGrid.getColumnCount(); col++) {
                final int finalRow = row;
                final int finalCol = col;
                Pane cell = new Pane();
                cell.getStyleClass().add("cell");
                cell.setOnMouseClicked(event -> handleCellClick(event, finalRow, finalCol));
                gameGrid.add(cell, finalCol, finalRow);
            }
        }
    }

    private void handleCellClick(MouseEvent event, int row, int col) {
        Pane cell = (Pane) getNodeByRowColumnIndex(row, col, gameGrid);
        if (cell != null && !cell.getStyleClass().contains("filled") && !cell.getStyleClass().contains("crossed")) {
            lastClickedCell = new Pair<>(row, col);
            updateCell(row, col, selectedState);
            System.out.println("Cell clicked: (" + row + ", " + col + "), State: " + selectedState);
        } else {
            System.out.println("Cell (" + row + ", " + col + ") has already been selected.");
        }
    }

    @Override
    public Pair<Integer, Integer> userClicked() {
        return lastClickedCell;
    }

    @Override
    public void updateCell(int row, int col, CellState cellState) {
        Pane cell = (Pane) getNodeByRowColumnIndex(row, col, gameGrid);
        if (cell != null) {
            switch (cellState) {
                case FILLED:
                    cell.getStyleClass().removeAll("crossed");
                    cell.getStyleClass().add("filled");
                    break;
                case EMPTY:
                    cell.getStyleClass().removeAll("filled", "crossed");
                    break;
                case CROSSED:
                    cell.getStyleClass().removeAll("filled");
                    cell.getStyleClass().add("crossed");
                    break;
                default:
                    throw new IllegalArgumentException("Unknown CellState: " + cellState);
            }
        }
    }

    private Pane getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column) {
                return (Pane) node;
            }
        }
        return null;
    }

    @Override
    public void updateLives(int actualLives) {
        numberLivesLabel.setText(String.valueOf(actualLives));
    }

    @Override
    public void displayStatusMessage(StatusMessage message) {
        messageLabel.setText(message.toString());
    }

    @Override
    public void displayBoard(Board board) {
        // Implementa la logica per visualizzare il board
    }

    @Override
    public GameView getMainView() {
        return null;
    }

    @Override
    public void init(GameView view, GameController controller) {
        // Implementa l'inizializzazione della view con il controller
    }
}
