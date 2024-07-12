package it.unibo.mparty.model.minigames.secretcode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretcode.util.SecretCodeColors;

/**
 * This interface models a player of the game Secrete Code.
 */
public interface SecretCodePlayer {

    /**
     * Add the input color at the current guess if it is possible.
     * 
     * @param color the color to be added.
     * @return true if it was added correctly, false otherwise.
     */
    boolean addColor(SecretCodeColors color);

    /**
     * Add point at the player.
     * 
     * @param points points to be added.
     */
    void addPoints(int points);

    /**
     * Permits to the player to start a new guess.
     */
    void startNewGuess();

    /**
     * Get the name of the player.
     * 
     * @return the name of the player.
     */
    String getNamePlayer();

    /**
     * Get the current guess.
     * 
     * @return the list of the color that was added at the current guess.
     */
    List<SecretCodeColors> getGuess();

    /**
     * Get the player's points.
     * 
     * @return the points of the player.
     */
    int getPoints();
}
