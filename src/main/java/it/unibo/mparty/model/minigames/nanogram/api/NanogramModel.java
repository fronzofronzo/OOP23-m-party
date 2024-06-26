package it.unibo.mparty.model.minigames.nanogram.api;

import java.util.List;
import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;

/**
 * Interface representing the model for a Nanogram game.
 */
public interface NanogramModel {

    /**
     * Initializes the game grid based on the given difficulty.
     *
     * @param difficulty the difficulty level to set up the game.
     */
    void initializeGame(Difficulty difficulty);

    /**
     * Gets the state of a specific cell in the grid.
     *
     * @param row the row index of the cell.
     * @param column the column index of the cell.
     * @return the current state of the cell at the specified position.
     */
    CellState getCellState(int row, int column);

    /**
     * Updates the state of a specific cell in the grid.
     *
     * @param row the row index of the cell.
     * @param column the column index of the cell.
     * @param state the new state to set for the cell.
     */
    void updateCellState(int row, int column, CellState state);

    /**
     * Gets the list of hints for each row of the grid.
     *
     * @return a list of lists of integers representing the row hints.
     */
    List<List<Integer>> getRowHints();

    /**
     * Gets the list of hints for each column of the grid.
     *
     * @return a list of lists of integers representing the column hints.
     */
    List<List<Integer>> getColumnHints();

    /**
     * Checks if the last move made by the player is valid.
     *
     * @return true if the move is valid, false otherwise.
     */
    boolean isMoveValid();

    /**
     * Checks if the game is complete.
     *
     * @return true if the player has successfully completed the game, false otherwise.
     */
    boolean isGameComplete();

    /**
     * Checks if the game is over, either by completing the game or losing all lives.
     *
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();
}
