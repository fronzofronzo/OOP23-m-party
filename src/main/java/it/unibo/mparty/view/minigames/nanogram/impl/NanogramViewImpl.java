package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.controller.minigames.nanogram.impl.NanogramControllerImpl;
import it.unibo.mparty.model.minigames.nanogram.board.api.Board;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.nanogram.util.Cross;
import it.unibo.mparty.view.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.List;
import java.util.stream.IntStream;

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

    private boolean selectedState = true;

    @Override
    public void init(final int size) {
        this.controller.startGame();

        ToggleGroup toggleGroup = new ToggleGroup();
        this.filledButton.setToggleGroup(toggleGroup);
        this.crossButton.setToggleGroup(toggleGroup);

        this.filledButton.setSelected(true);

        this.filledButton.setOnAction(event -> this.selectedState = true);
        this.crossButton.setOnAction(event -> this.selectedState = false);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final int finalRow = row;
                final int finalCol = col;
                final Pane cell = new Pane();
                cell.setOnMouseClicked(event -> handleCellClick(finalRow, finalCol));
                this.gameGrid.add(cell, finalCol, finalRow);
            }
        }

        System.out.println("column: "+gameGrid.getColumnCount());
    }

    private void handleCellClick(final int row, final int col) {
        final Pane cell = getNodeByRowColumnIndex(row, col, this.gameGrid);
        if (cell != null) {
            this.controller.updateModel(row, col, this.selectedState);
        }
    }

    private void setHints(final GridPane grid, final List<List<Integer>> hintsList, final boolean isRowHints) {
        grid.getChildren().clear();
        final int numLines = hintsList.size();

        for (int line = 0; line < numLines; line++) {
            final List<Integer> hints = hintsList.get(line);
            final int numHints = hints.size();

            for (int i = 0; i < numHints; i++) {
                final Label hintLabel = new Label(String.valueOf(hints.get(i)));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRowHints(final List<List<Integer>> rowHints) {
        this.setHints(this.rowHints, rowHints, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumnHints(final List<List<Integer>> columnHints) {
        this.setHints(this.columnHints, columnHints, false);
    }

    @Override
    public void setCorrectCell(int row, int col, boolean state){
        draw(row,col,state,Color.BLACK);
    }

    @Override
    public void setErrorCell(int row, int col, boolean state){
        draw(row, col, !state, Color.RED);
        displayStatusMessage(StatusMessage.ERROR);
    }

    private void draw(final int row, final int col, final boolean state, final Color color) {
        final Pane cell = getNodeByRowColumnIndex(row, col, this.gameGrid);
        if (cell != null) {
            cell.getChildren().clear();
            if (state) {
                BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
                cell.setBackground(new Background(backgroundFill));
            } else {
//                double width = cell.getWidth();
//                double height = cell.getHeight();
//                Line line1 = new Line(0, 0, width, height);
//                Line line2 = new Line(0, height, width, 0);
//                line1.setStroke(color);
//                line2.setStroke(color);
//                line1.setStrokeWidth(2);
//                line2.setStrokeWidth(2);
//                cell.getChildren().addAll(line1, line2);
                cell.getChildren().add(new Cross(color));
            }
        }

    }

    private Pane getNodeByRowColumnIndex(final int row, final int column, final GridPane gridPane) {
        return gridPane.getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row
                        && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column)
                .map(node -> (Pane) node)
                .findFirst()
                .orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLives(final int actualLives) {
        final String LIVES = "Vite: ";
        this.livesLabel.setText(LIVES + actualLives);
    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public void clearMessageLabel() {
//        this.messageLabel.setText(" ");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayStatusMessage(final StatusMessage message) {
        this.messageLabel.setText(message.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableAllCells() {
        this.gameGrid.getChildren().stream()
                .filter(node -> node instanceof Pane)
                .forEach(node -> node.setDisable(true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillRemainingCellsWithCrosses() {
        IntStream.range(0, this.gameGrid.getRowCount())
                .forEach(row -> IntStream.range(0, this.gameGrid.getColumnCount())
                        .forEach(col -> {
                            final Pane cell = getNodeByRowColumnIndex(row, col, this.gameGrid);
                            if (cell != null) {
                                setCorrectCell(row, col, false);
                            }
                        }));
    }
}
