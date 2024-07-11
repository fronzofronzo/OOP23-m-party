package it.unibo.mparty.utilities;

/**
 * Enum of the direction to check in connect4 minigame.
 */
public enum Connect4Directions {
    /**
     * Horizontal direction.
     */
    HORIZONTAL(0, 1),
    /**
     * Vertical direction.
     */
    VERTICAL(1, 0),
    /**
     * Diagonal left direction.
     */
    DIAGONAL_LEFT(1, -1),
    /**
     * Diagonal right direction.
     */
    DIAGONAL_RIGHT(1, 1);

    private final Position pos;

    Connect4Directions(final int row, final int col) {
        pos = new Position(row, col);
    }

    /**
     * Get the position in order to check the respective direction.
     * @return the {@link Position} of the direction to check in the game
     */
    public Position getPosition() {
        return this.pos;
    }
}
