package it.unibo.mparty.view.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.api.Board;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.StatusMessage;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.minigames.MinigameView;

import java.util.List;

/**
 * Interface representing the view for a Nanogram game.
 */
public interface NanogramView extends MinigameView {

    /**
     * Gets the user's click input as a pair of coordinates.
     *
     * @return a {@link Pair} of integers where the first integer is the row index and the second integer is the column index.
     */
    Pair<Integer, Integer> userClicked();

    /**
     * Updates the state of a specific cell in the grid.
     *
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * @param cellState the new state to set for the cell, as a {@link CellState}.
     */
    void updateCell(int row, int col, CellState cellState);

    /**
     * Updates the display of the remaining lives in the game.
     *
     * @param actualLives the number of remaining lives.
     */
    void updateLives(int actualLives);

    /**
     * Displays a status message to the user.
     *
     * @param message the status message to display, encapsulated in a {@link StatusMessage} object.
     */
    void displayStatusMessage(StatusMessage message);

    /**
     * Displays the game board.
     *
     * @param board the {@link Board} to display.
     */
    void displayBoard(Board board);
}
