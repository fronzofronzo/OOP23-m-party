package it.unibo.mparty.model;

/**
 * Interface that models the model of the main game
 */
public interface GameModel {

    /**
     * Moves the current player to a new position
     */
    void movePlayer();

    /**
     * Get the name of the minigame that is active
     * @return the name of the minigame
     */
    String activeMinigame();

    /**
     * Check if during the actual player turn, there is a minigame active
     * @return true if there is a minigame active, false otherwise
     */
    boolean isActiveMinigame();

    /**
     * End the turn of the actual player and starts the turn of the
     * next player
     */
    void nextPlayer();
}
