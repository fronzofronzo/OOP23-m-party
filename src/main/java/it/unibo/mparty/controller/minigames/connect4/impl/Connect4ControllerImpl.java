package it.unibo.mparty.controller.minigames.connect4.impl;

import java.util.List;

import it.unibo.mparty.controller.minigames.connect4.api.Connect4Controller;
import it.unibo.mparty.model.minigames.connect4.api.Connect4Model;
import it.unibo.mparty.model.minigames.connect4.impl.Connect4ModelImpl;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;

/**
 * This class implements {@link Connect4Controller} interface.
 */
public class Connect4ControllerImpl implements Connect4Controller {

    private Connect4View view;
    private final Connect4Model model;

    /**
     * Construct a new istance of {@link Connect4ControllerImpl}.
     * @param view the {@link Connect4View} of the minigame connect4
     */
    public Connect4ControllerImpl(final Connect4View view) {
        this.view = view;
        this.model = new Connect4ModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.view.getMainController().saveMinigameResult(this.model.getResult());
        this.view.getMainView().switchToBoard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final List<String> players) {
        this.model.setUpPlayers(players);
        this.view.updateDisplayLabel("E' il turno di " + this.model.getPlayer1());
        this.view.activateExitButton(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void check(final int column) {
        int row = this.model.getAvailableRow(column);
        if (this.model.addDisc(column)) {
            this.view.addCircle(column, row, this.model.getTurnPlayer().equals(this.model.getPlayer1()));
            if (this.model.isOver()) {
                this.view.showResult(this.model.getResult());
                return;
            }
            this.view.updateDisplayLabel("E' il turno di " + this.model.getTurnPlayer());
        }
    }

}
