package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;
import it.unibo.mparty.utilities.Position;
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
    public NanogramControllerImpl(final NanogramViewImpl view) {
        this.view = view;
        this.model = new NanogramModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.model.initializeGame(Difficulty.SIMPLE);
        this.model.getLives();

        this.view.init(this.model.getBoardSize());

        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel(final int row, final int col, final boolean state) {
        final boolean isCorrect = this.model.isMoveValid(row, col, state);
        if (isCorrect) {
            this.view.setCorrectCell(row, col, state);
        } else {
            this.view.setErrorCell(row,col,state);
        }

        this.model.fillSelectedBoard(row, col, state);
        this.view.updateLives(this.model.getLives());

        if (this.model.isGameOver()) {
            this.view.disableAllCells();
            this.view.displayStatusMessage(StatusMessage.LOSE);
        } else if (this.model.isGameComplete()) {
            this.view.fillRemainingCellsWithCrosses();
            this.view.displayStatusMessage(StatusMessage.WIN);
        }
    }
}
