package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.view.minigames.nanogram.StatusMessage;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;

public class NanogramControllerImpl implements NanogramController {

    private final NanogramViewImpl view;
    private final NanogramModelImpl model;
    private boolean fillState = true;

    /**
     * Constructs a new {@code NanogramControllerImpl} with the specified view.
     * Initializes the model for the Nanogram game.
     *
     * @param view the view for the Nanogram game.
     */
    public NanogramControllerImpl(final NanogramViewImpl view) {
        this.view = view;
        this.model = new NanogramModelImpl();
        this.initializeGame();
    }

    private void initializeGame() {
        this.view.updateLives(this.model.getLives());
        this.view.initGrid(this.model.getBoardSize());
        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    @Override
    public void checkCell(int x, int y) {
        final boolean isCorrect = this.model.checkAndSelectCell(x, y, this.fillState);

        if (this.fillState) {
            this.view.fillCell(isCorrect);
        } else {
            this.view.crossCell(isCorrect);
        }
        this.view.updateLives(this.model.getLives());

        if (this.model.isGameOver()) {
            this.view.disableAllCells();
            this.view.displayStatusMessage(StatusMessage.LOSE);
        } else if (this.model.isGameComplete()) {
            this.view.disableAllCells();
            this.view.fillRemainingCellsWithCrosses();
            this.view.displayStatusMessage(StatusMessage.WIN);
        }
    }

    @Override
    public void setFillState(boolean state) {
        this.fillState = state;
    }
}
