package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.MinigameModel;

import java.util.List;

/**
 * Interface representing the model for a Nanogram game.
 * This interface defines methods to interact with and retrieve the state of the Nanogram game.
 */
public interface NanogramModel extends MinigameModel {

    /**
     * Checks if the selected cell matches the expected state and updates the game state accordingly.
     *
     * @param x     the x-coordinate of the cell.
     * @param y     the y-coordinate of the cell.
     * @param state the state to check (true for filled, false for empty).
     * @return true if the selection is correct, otherwise false.
     */
    boolean checkAndSelectCell(int x, int y, boolean state);

    /**
     * Retrieves the current number of lives.
     *
     * @return the current number of lives.
     */
    int getLives();

    /**
     * Retrieves the row hints for the board.
     *
     * @return a list of lists of integers representing the row hints.
     */
    List<List<Integer>> getRowHints();

    /**
     * Retrieves the column hints for the board.
     *
     * @return a list of lists of integers representing the column hints.
     */
    List<List<Integer>> getColumnHints();

    /**
     * Retrieves the size of the board.
     *
     * @return the size of the board (number of rows/columns).
     */
    int getBoardSize();

    /**
     * Checks if the game is complete.
     *
     * @return true if the game is complete, otherwise false.
     */
    boolean isGameComplete();

    String getPlayer();
}
