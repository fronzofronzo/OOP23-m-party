package it.unibo.mparty.model.minigames.nanogram.live.api;

/**
 * Interface representing the lives system in Nanogram game.
 * This interface defines methods to retrieve, reset, update, and check the status of lives.
 */
public interface Live {

    /**
     * Gets the current number of lives.
     *
     * @return the current number of lives.
     */
    int getLive();

    /**
     * Decreases the number of lives by one.
     */
    void decrease();

    /**
     * Checks if there are no more lives left.
     *
     * @return true if there are no lives left, false otherwise.
     */
    boolean isDeath();
}
