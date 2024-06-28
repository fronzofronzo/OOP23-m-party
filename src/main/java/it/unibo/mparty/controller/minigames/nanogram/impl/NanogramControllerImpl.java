package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.view.minigames.nanogram.util.StatusMessage;
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
        this.view.setSolutionBoard(this.model.getBoard());
        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    @Override
    public void updateModel(int row, int col, CellState state) {
        this.model.updateCellState(row, col, state);
        updateView(row, col, state);
    }

    @Override
    public void updateView(int row, int col, CellState state) {
        this.view.updateCell(row, col, this.model.getCellState(row, col));

        this.view.updateLives(this.model.getLives());
        this.view.clearMessageLabel();
        if (!this.model.isMoveValid(row, col, state)) {
            this.view.displayStatusMessage(StatusMessage.ERROR);
        }
        if (this.model.isGameOver()) {
            this.view.disableAllCells();
            this.view.displayStatusMessage(StatusMessage.LOSE);
        } else if (this.model.isGameComplete()) {
            this.view.fillRemainingCellsWithCrosses();
            this.view.displayStatusMessage(StatusMessage.WIN);
        }
    }
}
