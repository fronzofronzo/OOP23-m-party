package it.unibo.mparty.view.minigames.connect4.impl;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Connect4ViewImpl extends AbstractSceneView implements Connect4View {

    @FXML
    private Label displayLabel;

    @FXML
    private Button exitButton;

    @Override
    public void showResult(Pair<String, Integer> result) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showResult'");
    }

    @Override
    public void addCircle(int row) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCircle'");
    }

    @Override
    public void updateDisplayLabel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDisplayLabel'");
    }

    private void activateExitButton () {
        
    }

}
