package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

import java.util.List;

/**
 * Interface representing a simple board for a Nanogram game.
 * This interface defines methods to retrieve the state of cells, generate hints for rows or columns,
 * and obtain the size of the board.
 */
public interface SimpleBoard {

    /**
     * Retrieves the state of the cell at the specified position.
     *
     * @param position the position of the cell.
     * @return true if the cell is filled, false otherwise.
     */
    boolean getState(Position position);

    /**
     * Generates hints for each row or column based on the board's current state.
     *
     * @param isRow true if generating hints for rows, false for columns.
     * @return a list of lists of integers representing hints for each row or column.
     */
    List<List<Integer>> generateHints(boolean isRow);

    /**
     * Retrieves the size of the board.
     *
     * @return the size of the board (number of rows/columns).
     */
    int getSize();
}
