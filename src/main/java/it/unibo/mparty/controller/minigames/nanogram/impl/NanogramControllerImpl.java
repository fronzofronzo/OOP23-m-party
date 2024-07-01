package it.unibo.mparty.controller.minigames.nanogram.impl;

import it.unibo.mparty.controller.minigames.nanogram.api.NanogramController;
import it.unibo.mparty.model.minigames.nanogram.impl.NanogramModelImpl;
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


    private boolean fillState = true;

    /**
     * Constructs a new {@link NanogramControllerImpl} with the specified view.
     * Initializes the model for the Nanogram game.
     *
     * @param view the view for the Nanogram game.
     */
    public NanogramControllerImpl(final NanogramViewImpl view, final Difficulty difficulty) {
        this.view = view;
        this.model = new NanogramModelImpl(difficulty);
        this.initializeGame();
    }

    private void initializeGame() {
        this.view.updateLives(this.model.getLives());
        this.view.initGrid(this.model.getBoardSize());
        this.view.setRowHints(this.model.getRowHints());
        this.view.setColumnHints(this.model.getColumnHints());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateModel(final int row, final int col, final boolean state) {
//        final boolean isCorrect = this.model.isMoveValid(row, col, state);
//        if (isCorrect) {
//            this.view.setCorrectCell(row, col, state);
//        } else {
//            this.view.setErrorCell(row,col,state);
//        }
//
//        this.model.fillSelectedBoard(row, col, state);
//        this.view.updateLives(this.model.getLives());
//
//        if (this.model.isGameOver()) {
//            this.view.disableAllCells();
//            this.view.displayStatusMessage(StatusMessage.LOSE);
//        } else if (this.model.isGameComplete()) {
//            this.view.fillRemainingCellsWithCrosses();
//            this.view.displayStatusMessage(StatusMessage.WIN);
//        }
    }

    @Override
    public void checkCell(int row, int col) {
        final boolean isCorrect = this.model.checkAndSelectCell(row, col, this.fillState);
//        if (isCorrect) {


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
            this.view.fillRemainingCellsWithCrosses();
            this.view.displayStatusMessage(StatusMessage.WIN);
        }
//        }
//        } else {
//            if (this.fillState) {
//                this.view.crossCell(row, col, isCorrect);
//            } else {
//                this.view.fillCell(row, col, isCorrect);
//            }
//        }
    }

    @Override
    public void setFillState(boolean state) {
        this.fillState = state;
    }
}
