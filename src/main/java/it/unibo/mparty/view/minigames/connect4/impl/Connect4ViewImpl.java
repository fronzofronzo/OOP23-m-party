package it.unibo.mparty.view.minigames.connect4.impl;

import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Connect4ViewImpl extends AbstractSceneView implements Connect4View {

    @FXML
    private Label displayLabel;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane buttonGrid;

    @Override
    public void showResult(Pair<String, Integer> result) {
        this.updateDisplayLabel(result.getFirst() + " ha vinto " + result.getSecond() + "monete");
        this.activateExitButton();
    }

    @Override
    public void updateDisplayLabel(String msg) {
        displayLabel.setText(msg);;
    }

    private void activateExitButton () {

    }

    @Override
    public void addCircle(int row, boolean color) {
        
    }

    @Override
    public int getColumn(ActionEvent e) {
        Button but = (Button) (e.getSource());
        return GridPane.getColumnIndex(but);
    }

}
