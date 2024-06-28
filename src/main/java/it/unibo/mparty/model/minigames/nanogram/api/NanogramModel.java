package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.model.minigames.nanogram.util.Difficulty;

import java.util.List;

/**
 * Interface representing the model for a Nanogram game.
 * This interface defines methods to interact with the game state and rules.
 */
public interface NanogramModel {

    /**
     * Initializes the game grid based on the given difficulty.
     *
     * @param difficulty the difficulty level to set up the game.
     */
    void initializeGame(final Difficulty difficulty);

    /**
     * Gets the state of a specific cell in the grid.
     *
     * @param row    the row index of the cell.
     * @param column the column index of the cell.
     * @return the current state of the cell at the specified position.
     */
    CellState getCellState(final int row, final int column);

    /**
     * Gets the current board of the game.
     *
     * @return the current {@link Board} of the game.
     */
    Board getBoard();

    /**
     * Gets the number of lives remaining in the game.
     *
     * @return the number of remaining lives.
     */
    int getLives();

    /**
     * Updates the state of a specific cell in the grid.
     *
     * @param row   the row index of the cell.
     * @param column the column index of the cell.
     * @param state the new state to set for the cell.
     */
    void updateCellState(final int row, final int column, final CellState state);

    /**
     * Updates the number of lives remaining in the game.
     *
     * @param lives the new number of lives.
     */
    void updateLives(final int lives);

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
     * Checks if a move with the given state at the specified position is valid.
     *
     * @param row        the row index of the cell.
     * @param column     the column index of the cell.
     * @param actualState the state to be validated.
     * @return true if the move is valid, false otherwise.
     */
    boolean isMoveValid(final int row, final int column, final CellState actualState);

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
