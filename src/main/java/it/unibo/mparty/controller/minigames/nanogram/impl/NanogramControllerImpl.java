package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.view.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;

public class NanogramControllerImpl implements NanogramController {

    private final NanogramViewImpl view;
    private final NanogramModelImpl model;

    /**
     * Constructs a new {@code NanogramControllerImpl} with the specified view.
     * Initializes the model for the Nanogram game.
     *
     * @param view the view for the Nanogram game.
     */
    public NanogramControllerImpl(final NanogramViewImpl view) {
        this.view = view;
        this.model = new NanogramModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel(final int row, final int col, final boolean state) {
        this.model.hitCell(row, col, state);
        updateView(row, col, state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final int row, final int col, final boolean state) {
        this.view.updateCell(row, col, this.model.getSolutionCellState(row, col));

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
