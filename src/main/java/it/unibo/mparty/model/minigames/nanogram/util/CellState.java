package it.unibo.mparty.model.minigames.nanogram.util;

/**
 * Enum representing the state of a cell in a Nanogram game.
 */
public enum CellState {

    /**
     * The cell is filled.
     */
    FILLED,

    /**
     * The cell is empty.
     */
    EMPTY,

    /**
     * The cell is marked, possibly as a tentative or temporary indication.
     */
    CROSSED,

    /**
     * The cell is marked as an error, indicating a mistake in the player's guess.
     */
    ERROR
}
