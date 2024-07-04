package it.unibo.mparty.view.endGame.impl;

import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.endGame.api.EndGameView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

public class EndGameViewImpl extends AbstractSceneView implements EndGameView {
    @FXML
    private TableColumn<?, ?> coinColumn;

    @FXML
    private Button menuButton;

    @FXML
    private TableColumn<?, ?> playerColumn;

    @FXML
    private TableColumn<?, ?> rankColumn;

    @FXML
    private TableColumn<?, ?> starColumn;

    @FXML
    private void menuClicked(MouseEvent event) {

    }
}
