package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

import java.util.List;

/**
 * Interface representing the model for a Nanogram game.
 * This interface defines methods to interact with the game state and rules.
 */
public interface NanogramModel {

    int getBoardSize();

    List<Position> getFilledCells();

    /**
     * Gets the number of lives remaining in the game.
     *
     * @return the number of remaining lives.
     */
    int getLives();

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
     * @param state the state to be validated.
     * @return true if the move is valid, false otherwise.
     */
    boolean checkAndSelectCell(int row, int column, boolean state);


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
