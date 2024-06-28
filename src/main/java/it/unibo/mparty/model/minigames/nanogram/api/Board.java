package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Position;

import java.util.List;
import java.util.Map;

/**
 * Interface representing the board of a Nanogram game.
 */
public interface Board {

    int getSize();

    /**
     * Gets the complete grid of the game with all cell states.
     *
     * @return a map representing the grid, where the keys are positions of type {@link Position}
     *         and the values are the states of the cells of type {@link CellState}.
     */
    Map<Position, CellState> getGrid();

    /**
     * Gets the grid as it is currently shown to the player, with cell states.
     *
     * @return a map representing the current visible grid, where the keys are positions of type {@link Position}
     *         and the values are the states of the cells of type {@link CellState}.
     */
    Map<Position, CellState> getShowGrid();

    /**
     * Gets the state of a specific cell in the grid.
     *
     * @param x the row index of the cell.
     * @param y the column index of the cell.
     * @return the current state of the cell at the specified position.
     */
    CellState getCellState(int x, int y);

    /**
     * Calculates the hints for either rows or columns of the grid.
     *
     * @param isRow {@code true} to calculate hints for rows, {@code false} for columns.
     * @return a list of lists of integers representing the hints for either rows or columns.
     *         Each inner list contains the hints for a single row or column based on game logic.
     */
    List<List<Integer>> calculateHints(boolean isRow);
}
