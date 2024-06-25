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
     * Roll dices of the actual player
     * @return the result of the roll
     */
    int rollDice();

    /**
     * Get the name of the minigame that is active
     * @return the name of the minigame
     */
    String getActiveMinigame();

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

    /**
     * Get the username of the player that has won
     * @return the username of winner
     */
    String getWinner();

    /**
     * Checks if the game's over
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

}
