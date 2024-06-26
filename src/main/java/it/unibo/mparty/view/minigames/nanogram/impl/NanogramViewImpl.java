package it.unibo.mparty.view.minigames.nanogram.impl;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.nanogram.api.NanogramView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private int lastClickedRow = -1;
    private int lastClickedColumn = -1;

    private void initializedGameGrid() {
        for (int i = 0; i < gameGrid.getRowCount(); i++) {
            for (int j = 0; j < gameGrid.getColumnCount(); j++) {
                Button cell = new Button();
                cell.setStyle("-fx-border-color: black; -fx-background-color: white;");
                int finalRow = i;
                int finalCol = j;
                cell.setOnAction(event -> handleCellClick(cell, finalRow, finalCol));
                gameGrid.add(cell, i, j);
            }
        }
    }

    private void handleCellClick(Button cell, int row, int column) {
        if ("white".equals(cell.getStyle().split(" ")[1])) {
            cell.setStyle("-fx-border-color: black; -fx-background-color: black;");
        } else {
            cell.setStyle("-fx-border-color: black; -fx-background-color: white;");
        }

        lastClickedRow = row;
        lastClickedColumn = column;
    }

    @Override
    public Pair<Integer, Integer> userClicked() {
        if (lastClickedColumn == -1 || lastClickedRow == -1) {
            return null;
        }

        return new Pair<Integer, Integer>(lastClickedRow, lastClickedColumn);
    }

    @Override
    public void displayStatusMessage(String message, String messageType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayStatusMessage'");
    }

}