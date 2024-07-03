package it.unibo.mparty.controller.minigames.connect4.impl;

import java.util.List;

import it.unibo.mparty.controller.minigames.MinigameController;
import it.unibo.mparty.controller.minigames.connect4.api.Connect4Controller;
import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.model.minigames.connect4.impl.Connect4ModelImpl;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;

public class Connect4ControllerImpl implements MinigameController, Connect4Controller {

    private Connect4View view;
    private final Connect4Model model;

    public Connect4ControllerImpl (Connect4View view) {
        this.view=view;
        this.model= new Connect4ModelImpl();
    }

    @Override
    public void endGame() {
        this.view.getMainController().saveMinigameResult(this.model.getResult());
    }

    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players);
        this.view.updateDisplayLabel("E' il turno di " + this.model.getPlayer1());
        this.view.activateExitButton(false);
    }

    @Override
    public void selectColumn(int column) {
        int row = this.model.getAvailableRow(column);
        if (this.model.addDisc(column)) {
            this.view.addCircle(column, row, this.model.getTurnPlayer().equals(this.model.getPlayer1()));
            if (this.model.isOver()) {
                this.endGame();
                this.view.showResult(this.model.getResult());
            }
            this.view.updateDisplayLabel("E' il turno di "+ this.model.getTurnPlayer());
        }
    }

}
