package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.model.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;

/**
 * Implementation of the {@link NanogramController} interface.
 * This controller handles the interactions between the Nanogram model and view.
 */
public class NanogramControllerImpl implements NanogramController {

    private final NanogramViewImpl view;
    private final NanogramModelImpl model;

    /**
     * Constructs a new {@code NanogramControllerImpl} with the specified view.
     * Initializes the model for the Nanogram game.
     *
     * @param view the view for the Nanogram game.
     */
    public NanogramControllerImpl(NanogramViewImpl view) {
        this.view = view;
        this.model = new NanogramModelImpl();
    }

    @Override
    public void startGame() {
        this.model.initializeGame(Difficulty.SIMPLE);
        this.view.setRowHints(model.getRowHints());
        this.view.setColumnHints(model.getColumnHints());
        //this.updateView();
    }

    @Override
    public void getClick(Position click) {
        CellState currentState = model.getCellState(click.getX(), click.getY());
        CellState newState = currentState == CellState.EMPTY ? CellState.FILLED : CellState.EMPTY;
        model.updateCellState(click.getX(), click.getY(), newState);
        updateView();
    }

    @Override
    public void updateModel() {

    }

    @Override
    public void updateView() {
        for (int row = 0; row < model.getBoard().getGridSize(); row++) {
            for (int col = 0; col < model.getBoard().getGridSize(); col++) {
                view.updateCell(row, col, model.getCellState(row, col));
            }
        }
        view.updateLives(model.getLives());
        if (!model.isGameOver()) {
            view.displayStatusMessage(StatusMessage.WIN);
        } else if (model.getLives() <= 0) {
            view.displayStatusMessage(StatusMessage.LOSE);
        }
    }
}
