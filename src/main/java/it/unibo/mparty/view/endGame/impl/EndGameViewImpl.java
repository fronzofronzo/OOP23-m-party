package it.unibo.mparty.view.endGame.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.endGame.api.EndGameView;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EndGameViewImpl extends AbstractSceneView implements EndGameView {

    @FXML
    private TableColumn<Player, Integer> coinColumn;

    @FXML
    private TableColumn<Player, String> playerColumn;

    @FXML
    private TableColumn<Player, Integer> rankColumn;

    @FXML
    private TableColumn<Player, Integer> starColumn;

    @FXML
    private TableView<Player> tableView;

    @Override
    public void setPlayerColumn(List<String> playerNames){
        for (String playerName : playerNames) {
            this.playerColumn.setCellValueFactory(new PropertyValueFactory<>(playerName));
        }
        this.rankColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(cellData.getValue()) + 1));
    }

    @Override
    public void setCoinColumn(List<Integer> playerCoins){
        for (Integer coin : playerCoins) {
            this.coinColumn.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(coin)));
        }
    }

    @Override
    public void setStarColumn(List<Integer> playerStars){
        for (Integer star : playerStars) {
            this.starColumn.setCellValueFactory(new PropertyValueFactory<>(String.valueOf(star)));
        }
    }

    @FXML
    private void exitClicked() {
        Platform.exit();
    }
}
