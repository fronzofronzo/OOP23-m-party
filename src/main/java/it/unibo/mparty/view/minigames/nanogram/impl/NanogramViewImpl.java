package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.controller.minigames.nanogram.impl.NanogramControllerImpl;
import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.view.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * Implementation of the {@link NanogramView} interface representing the view for a Nanogram game.
 * This class handles the UI components and interactions for the Nanogram game.
 */
public class NanogramViewImpl extends AbstractSceneView implements NanogramView {

    @FXML
    private GridPane columnHints;

    @FXML
    private RadioButton crossButton;

    @FXML
    private RadioButton filledButton;

    @FXML
    private GridPane gameGrid;

    @FXML
    private Label livesLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private GridPane rowHints;

    private final NanogramController controller = new NanogramControllerImpl(this);

    private Board solutionBoard;

    private CellState selectedState = CellState.FILLED;

    private boolean error = false;

    @FXML
    private void initialize() {
        this.controller.startGame();

        this.updateLives(3);

        ToggleGroup toggleGroup = new ToggleGroup();
        this.filledButton.setToggleGroup(toggleGroup);
        this.crossButton.setToggleGroup(toggleGroup);

        this.filledButton.setSelected(true);

        this.filledButton.setOnAction(event -> this.selectedState = CellState.FILLED);
        this.crossButton.setOnAction(event -> this.selectedState = CellState.CROSSED);

        initGrid();
    }

    private void initGrid() {
        for (int row = 0; row < this.gameGrid.getRowCount(); row++) {
            for (int col = 0; col < this.gameGrid.getColumnCount(); col++) {
                final int finalRow = row;
                final int finalCol = col;
                Pane cell = new Pane();
                cell.getStyleClass().add("cell");
                cell.setOnMouseClicked(event -> handleCellClick(finalRow, finalCol));
                this.gameGrid.add(cell, finalCol, finalRow);
            }
        }
    }

    private void setHints(GridPane grid, List<List<Integer>> hintsList, boolean isRowHints) {
        grid.getChildren().clear();
        int numLines = hintsList.size();

        for (int line = 0; line < numLines; line++) {
            List<Integer> hints = hintsList.get(line);
            int numHints = hints.size();

            for (int i = 0; i < numHints; i++) {
                Label hintLabel = new Label(String.valueOf(hints.get(i)));
                hintLabel.getStyleClass().add("hint-label");
                hintLabel.setStyle("-fx-font-size: 24pt;");
                hintLabel.setAlignment(Pos.CENTER);
                GridPane.setHalignment(hintLabel, javafx.geometry.HPos.CENTER);

                if (isRowHints) {
                    grid.add(hintLabel, grid.getColumnCount() - numHints + i, line);
                } else {
                    grid.add(hintLabel, line, grid.getRowCount() - numHints + i);
                }
            }
        }
    }

    @Override
    public void setRowHints(List<List<Integer>> rowHints) {
        setHints(this.rowHints, rowHints, true);
    }

    @Override
    public void setColumnHints(List<List<Integer>> columnHints) {
        setHints(this.columnHints, columnHints, false);
    }

    private void handleCellClick(int row, int col) {
        Pane cell = getNodeByRowColumnIndex(row, col, this.gameGrid);
        this.error = false;
        if (cell != null && !cell.getStyleClass().contains("filled") && !cell.getStyleClass().contains("crossed")) {
            CellState correctState = this.solutionBoard.getCellState(row, col);
            if (!this.selectedState.equals(correctState)) {
                this.error = true;
            }
            this.controller.updateModel(row, col, this.selectedState);
        }
    }

    @Override
    public void setSolutionBoard(Board board) {
        this.solutionBoard = board;
    }

    @Override
    public void updateCell(int row, int col, CellState cellState) {
        Pane cell = (Pane) getNodeByRowColumnIndex(row, col, this.gameGrid);
        if (cell != null) {
            cell.getChildren().clear();

            switch (cellState) {
                case FILLED:
                    cell.setStyle(this.error ? "-fx-background-color: red;" : "-fx-background-color: black;");
                    break;
                case CROSSED:
                    drawCross(cell, this.error ? Color.RED : Color.BLACK);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown CellState: " + cellState);
            }
            cell.getStyleClass().add(cellState.toString().toLowerCase());
        }
    }

    private void drawCross(Pane cell, Color color) {
        double width = cell.getWidth();
        double height = cell.getHeight();
        Line line1 = new Line(0, 0, width, height);
        Line line2 = new Line(0, height, width, 0);
        line1.setStroke(color);
        line2.setStroke(color);
        line1.setStrokeWidth(2);
        line2.setStrokeWidth(2);
        cell.getChildren().addAll(line1, line2);
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
        String LIVES = "Lives: ";
        this.livesLabel.setText(LIVES + actualLives);
    }

    @Override
    public void clearMessageLabel() {
        this.messageLabel.setText(" ");
    }

    @Override
    public void displayStatusMessage(StatusMessage message) {
        this.messageLabel.setText(message.toString());
    }

    @Override
    public void disableAllCells() {
        for (javafx.scene.Node node : this.gameGrid.getChildren()) {
            if (node instanceof Pane) {
                node.setDisable(true);
            }
        }
    }

    @Override
    public void fillRemainingCellsWithCrosses() {
        for (int row = 0; row < this.gameGrid.getRowCount(); row++) {
            for (int col = 0; col < this.gameGrid.getColumnCount(); col++) {
                Pane cell = getNodeByRowColumnIndex(row, col, this.gameGrid);
                if (cell != null && !cell.getStyleClass().contains("filled") && !cell.getStyleClass().contains("crossed")) {
                    updateCell(row, col, CellState.CROSSED);
                }
            }
        }
    }
}
