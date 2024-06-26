package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.model.minigames.nanogram.util.CellState;
import it.unibo.mparty.utilities.Pair;

import java.util.List;
import java.util.Map;

/**
 * Interface representing the board of a Nanogram game.
 */
public interface Board {

    /**
     * Gets the complete grid of the game with all cell states.
     *
     * @return a map representing the grid, where the keys are pairs of (row, column) indices
     *         and the values are the states of the cells.
     */
    Map<Pair<Integer, Integer>, CellState> getGrid();

    /**
     * Gets the grid as it is currently shown to the player, with cell states.
     *
     * @return a map representing the current visible grid, where the keys are pairs of (row, column) indices
     *         and the values are the states of the cells.
     */
    Map<Pair<Integer, Integer>, CellState> getShowGrid();

    /**
     * Gets the hints for the rows and columns of the grid.
     *
     * @return a list of integers representing the hints.
     */
    List<Integer> getHints();

    /**
     * Gets the state of a specific cell in the grid.
     *
     * @param x the row index of the cell.
     * @param y the column index of the cell.
     * @return the current state of the cell at the specified position.
     */
    CellState getCellState(int x, int y);
}
