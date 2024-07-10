package it.unibo.mparty.model.minigames.nanogram.api;

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
     * Resets the number of lives to the initial value.
     */
    void reset();

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
