package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
import it.unibo.mparty.view.minigames.nanogram.NanogramMessage;
import it.unibo.mparty.view.minigames.nanogram.impl.NanogramViewImpl;

import java.util.List;

/**
 * Implementation of the {@link NanogramController} interface for managing the Nanogram game logic.
 * This controller interacts with the game model ({@link NanogramModelImpl}) and updates the view ({@link NanogramViewImpl}).
 */
public class NanogramControllerImpl implements NanogramController {

    private final NanogramViewImpl view;
    private final NanogramModelImpl model;
    private boolean fillState = true;

    /**
     * Constructs a new {@code NanogramControllerImpl} instance with the specified view.
     *
     * @param view the view component to interact with.
     */
    public NanogramControllerImpl(final NanogramViewImpl view) {
        this.view = view;
        this.model = new NanogramModelImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(List<String> players) {
        this.model.setUpPlayers(players);
        this.view.updateLives(this.model.getLives());
        this.view.initGrid(this.model.getBoardSize());
        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCell(final int x, final int y) {
        final boolean isCorrect = this.model.checkAndSelectCell(x, y, this.fillState);

        if (this.fillState) {
            this.view.fillCell(isCorrect);
        } else {
            this.view.crossCell(isCorrect);
        }
        this.view.updateLives(this.model.getLives());
        this.endGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFillState(final boolean state) {
        this.fillState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        if (this.model.isOver()) {
            this.view.disableAllCells();
            this.view.displayStatusMessage(NanogramMessage.LOSE);
        } else if (this.model.isGameComplete()) {
            this.view.disableAllCells();
            this.view.fillRemainingCellsWithCrosses();
            this.view.displayStatusMessage(NanogramMessage.WIN);
        }
        this.view.getMainController().saveMinigameResult(this.model.getResult());
    }
}
