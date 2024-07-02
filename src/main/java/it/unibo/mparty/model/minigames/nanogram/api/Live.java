package it.unibo.mparty.model.minigames.nanogram.api;

/**
 * Interface representing the lives system in a game.
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
     * Updates the number of lives based on the given value.
     *
     * @param live the value to update the lives by.
     */
    void update(int live);

    /**
     * Checks if there are no more lives left.
     *
     * @return true if there are no lives left, false otherwise.
     */
    boolean isDeath();

    void decrease();
}
