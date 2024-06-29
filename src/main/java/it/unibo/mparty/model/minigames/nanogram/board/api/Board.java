package it.unibo.mparty.model.minigames.nanogram.board.api;

import it.unibo.mparty.utilities.Position;

/**
 * Interface representing the board of a Nanogram game.
 */
public interface Board {

    int getSize();
    void setCellState(Position position, boolean state);
    boolean getCellState(Position position);

//    /**
//     * Gets the state of a specific cell in the grid.
//     *
//     * @param x the row index of the cell.
//     * @param y the column index of the cell.
//     * @return the current state of the cell at the specified position.
//     */
//    Boolean getCellState(int x, int y);
//
//    /**
//     * Calculates the hints for either rows or columns of the grid.
//     *
//     * @param isRow {@code true} to calculate hints for rows, {@code false} for columns.
//     * @return a list of lists of integers representing the hints for either rows or columns.
//     *         Each inner list contains the hints for a single row or column based on game logic.
//     */
//    List<List<Integer>> calculateHints(boolean isRow);
}
