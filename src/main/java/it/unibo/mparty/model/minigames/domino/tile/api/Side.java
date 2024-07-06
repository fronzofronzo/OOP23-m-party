package it.unibo.mparty.model.minigames.domino.tile.api;

/**
 * Represents a side of a domino tile.
 */
public interface Side {

    /**
     * Returns the value of the side.
     *
     * @return the value of the side
     */
    int getValue();

    /**
     * Checks if the side is matched with another side.
     *
     * @return true if the side is matched, false otherwise
     */
    boolean isMatched();

    /**
     * Sets the side as matched.
     */
    void setMatched();
}
