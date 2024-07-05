package it.unibo.mparty.view.endGame.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.endGame.api.EndGameView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EndGameViewImpl extends AbstractSceneView implements EndGameView {

    @FXML
    private TableColumn<Player, Integer> coinColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableColumn<Player, String> playerColumn;

    @FXML
    private TableColumn<?, ?> rankColumn;

    @FXML
    private TableColumn<Player, Integer> starColumn;

    @Override
    public void setPlayerColumn(List<String> playerNames){
        this.playerColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    public void setCoinColumn(List<Integer> playerCoins){

    }

    @Override
    public void setStarColumn(List<Integer> playerStars){

    }

    @FXML
    private void exitClicked() {
        Platform.exit();
    }
}
