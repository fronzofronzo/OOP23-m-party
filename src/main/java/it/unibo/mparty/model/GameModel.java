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
}
