package it.unibo.mparty.model.minigames.domino.tile.api;

/**
 * Represents a tile in the domino game.
 */
public interface Tile {

    /**
     * Returns the first side of the tile.
     *
     * @return the first side of the tile
     */
    Side getSideA();

    /**
     * Returns the second side of the tile.
     *
     * @return the second side of the tile
     */
    Side getSideB();

    /**
     * Attempts to match the current tile with another tile.
     *
     * @param tile the tile to match with
     * @return true if the tiles match, false otherwise
     */
    boolean match(Tile tile);

    /**
     * Reverses the sides of the tile.
     */
    void reverse();

    /**
     * Checks if the current tile can match with another tile.
     *
     * @param tile the tile to check against
     * @return true if the tiles can match, false otherwise
     */
    boolean canMatch(Tile tile);

    /**
     * Checks if the tile has the same value on both sides.
     *
     * @return true if both sides have the same value, false otherwise
     */
    boolean isDoubleSide();
}
