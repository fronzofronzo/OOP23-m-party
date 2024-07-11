package it.unibo.mparty.model.minigames.nanogram.board.api;

import it.unibo.mparty.utilities.Position;

import java.util.List;

/**
 * Interface representing a simple board for a Nanogram game.
 * This interface defines methods to retrieve the state of cells, generate hints for rows or columns,
 * and obtain the size of the board.
 */
public interface SimpleBoard extends Board {

    /**
     * Generates hints for each row or column based on the board's current state.
     *
     * @param isRow true if generating hints for rows, false for columns.
     * @return a list of lists of integers representing hints for each row or column.
     */
    List<List<Integer>> generateHints(boolean isRow);
}
