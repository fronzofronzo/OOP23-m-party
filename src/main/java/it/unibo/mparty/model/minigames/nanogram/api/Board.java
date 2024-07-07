package it.unibo.mparty.model.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

import java.util.Map;

/**
 * Interface representing a board for a Nanogram game.
 * This interface defines methods to manage the state of cells on the board.
 */
public interface Board {

    /**
     * Retrieves the size of the board.
     *
     * @return the size of the board (number of rows/columns).
     */
    int getSize();

    /**
     * Sets the state of the cell at the specified position.
     *
     * @param position the position of the cell.
     * @param state    the state to set for the cell (true for filled, false for empty).
     */
    void setCellState(Position position, boolean state);

    /**
     * Retrieves the state of the cell at the specified position.
     *
     * @param position the position of the cell.
     * @return true if the cell is filled, false if it is empty or the position is not found.
     */
    boolean getState(Position position);

    Map<Position, Boolean> getBoard();
}
