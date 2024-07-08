package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MemorySweepViewImpl extends AbstractSceneView implements MemorySweepView, Initializable {

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

    @Override
    public void showResult(Pair<String, Integer> result) {

    }

    @Override
    public void startMinigame(List<String> players) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}