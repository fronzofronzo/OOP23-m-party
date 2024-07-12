package it.unibo.mparty.model.minigames.domino.tile.api;

import it.unibo.mparty.model.minigames.domino.tile.impl.SideType;

/**
 * Represents a tile in the domino game.
 */
public interface Tile {

    /**
     * Attempts to match the current tile with another tile.
     *
     * @param tile the tile to match with
     * @return true if the tiles match, false otherwise
     */
    boolean match(Tile tile);

    /**
     * Sets the specified side of the tile as matched.
     *
     * @param side the type of the side to set as matched
     */
    void setMatched(SideType side);

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

    /**
     * Gets the value of the specified side of the tile.
     *
     * @param side the type of the side to get the value of
     * @return the value of the specified side
     */
    int getSideValue(SideType side);

    /**
     * Checks if the specified side of the tile is matched.
     *
     * @param side the type of the side to check
     * @return true if the specified side is matched, false otherwise
     */
    boolean isSideMatched(SideType side);
}
