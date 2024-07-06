package it.unibo.mparty.view.endGame.impl;

import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.endGame.api.EndGameView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Collections;
import java.util.List;

public class EndGameViewImpl extends AbstractSceneView implements EndGameView {

    @FXML
    private TableView<Player> tableView;

    @Override
    public void showResults(List<Player> players) {
        tableView.setItems(FXCollections.observableArrayList(players));
    }

    @FXML
    private void exitClicked() {
        Platform.exit();
    }
}
