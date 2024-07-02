package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class MemorySweepViewImpl implements MemorySweepView {

    private MemorySweepController observer;

    @FXML
    private GridPane memorySweepGrid;

    @Override
    public void setUp(Set<Position> randoms) {
        for(var child : memorySweepGrid.getChildren()) {
            if(child instanceof Button) {
                ((Button) child).setText(" ");
            }
        }
        for(var child : this.memorySweepGrid.getChildren()){
            var position = this.buttonPos((Button) child);
            if(randoms.contains(position)){
                ((Button) child).setText("*");
            }
        }
    }

    @Override
    public void hit(MemorySweep.HitType type) {

    }

    @Override
    public void buttonClicked(ActionEvent e) {

    }

    @Override
    public void setObserver(MemorySweepController controller) {
        this.observer = controller;

    }

    private Position buttonPos(Button button) {
        var x = GridPane.getRowIndex(button);
        var y = GridPane.getColumnIndex(button);
        return new Position(x,y);
    }
}