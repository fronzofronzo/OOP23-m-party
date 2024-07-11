package it.unibo.mparty.utilities;

import it.unibo.mparty.model.gameBoard.api.GameBoard;

/**
 * This enum is used by the {@link GameBoard} to indicate
 * the possible directions to move on the board.
 */
public enum Direction {
    /**
     * upward.
     */
    UP("Su"),
    /**
     * downward.
     */
    DOWN("Giu'"),
    /**
     * rightward.
     */
    RIGHT("Destra"),
    /**
     * leftward.
     */
    LEFT("Sinistra");

    private final String text;

    Direction(final String text) {
        this.text = text;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.text;
    }
}
