package it.unibo.mparty.view.endGame.impl;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.endGame.api.EndGameView;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

import java.util.Map;

/**
 * Implementation of the {@link EndGameView} interface.
 */
public class EndGameViewImpl extends AbstractSceneView implements EndGameView {

    private static final int HEADER_FONT_SIZE = 20;
    private static final int TEXT_FONT_SIZE = 18;

    @FXML
    private TableColumn<Map.Entry<String, Pair<Integer, Integer>>, Integer> coinColumn;

    @FXML
    private TableColumn<Map.Entry<String, Pair<Integer, Integer>>, String> playerColumn;

    @FXML
    private TableColumn<Map.Entry<String, Pair<Integer, Integer>>, Integer> rankColumn;

    @FXML
    private TableColumn<Map.Entry<String, Pair<Integer, Integer>>, Integer> starColumn;

    @FXML
    private TableView<Map.Entry<String, Pair<Integer, Integer>>> tableView;

    @FXML
    private void initialize() {
        setHeaderFont(this.playerColumn);
        setHeaderFont(this.starColumn);
        setHeaderFont(this.coinColumn);
        setHeaderFont(this.rankColumn);

        this.playerColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getKey()));
        this.starColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getValue().getFirst()).asObject());
        this.coinColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getValue().getSecond()).asObject());

        this.rankColumn.setCellValueFactory(cellData -> {
            ObservableList<Map.Entry<String, Pair<Integer, Integer>>> items = this.tableView.getItems();
            return new SimpleIntegerProperty(items.indexOf(cellData.getValue()) + 1).asObject();
        });

        centerAlignText(this.playerColumn);
        centerAlignText(this.starColumn);
        centerAlignText(this.coinColumn);
        centerAlignText(this.rankColumn);

        this.tableView.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(final Map.Entry<String, Pair<Integer, Integer>> item, final boolean empty) {
                super.updateItem(item, empty);
                if (getIndex() == 0 && item != null) {
                    setStyle("-fx-font-weight: bold; -fx-background-color: yellow;");
                } else {
                    setStyle("-fx-background-color: transparent;");
                }
            }
        });
    }

    @FXML
    private void exitClicked() {
        Platform.exit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResults(final Map<String, Pair<Integer, Integer>> result) {
        this.tableView.setItems(FXCollections.observableArrayList(result.entrySet()));
    }

    private <T> void centerAlignText(final TableColumn<Map.Entry<String, Pair<Integer, Integer>>, T> column) {
        column.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(final T item, final boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item.toString());
                    setFont(Font.font("System", TEXT_FONT_SIZE));
                    setStyle("-fx-alignment: CENTER;");
                } else {
                    setText(null);
                }
            }
        });
    }

    private void setHeaderFont(final TableColumn<?, ?> column) {
        column.setStyle("-fx-font-size: " + HEADER_FONT_SIZE + "px; -fx-font-weight: bold; -fx-alignment: CENTER;");
    }
}
