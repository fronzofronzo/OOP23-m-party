package it.unibo.mparty.controller.minigames.nanogram.api;

/**
 * Interface representing a controller for managing game logic in a Nanogram game.
 * Implementations of this interface handle interactions with the game board and state.
 */
public interface NanogramController {

    /**
     * Checks and processes the cell at the specified coordinates in the Nanogram grid.
     *
     * @param x the x-coordinate of the cell to check.
     * @param y the y-coordinate of the cell to check.
     */
    void checkCell(int x, int y);

    /**
     * Sets the fill state for placing marks (fill or cross) on the Nanogram grid.
     *
     * @param state the fill state to set: {@code true} for filling cells, {@code false} for crossing cells.
     */
    void setFillState(boolean state);
}
