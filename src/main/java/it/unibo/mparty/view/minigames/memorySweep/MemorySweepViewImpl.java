package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MemorySweepViewImpl implements MemorySweepView {

    private MemorySweepController observer;

    @FXML
    private GridPane memorySweepGrid;

    @Override
    public void setUp() {

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
}