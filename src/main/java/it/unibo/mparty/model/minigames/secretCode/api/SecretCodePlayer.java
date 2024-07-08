package it.unibo.mparty.model.minigames.secretCode.api;

import java.util.List;

import it.unibo.mparty.model.minigames.secretCode.util.SecretCodeColors;

public interface SecretCodePlayer {

    /**
     * Add the input color at the current guess if it is possible,
     * @param color the color to be added
     * @return true if it was added correctly, false otherwisw.
     */
    boolean addColor(SecretCodeColors color);
    
    /**
     * Get the current guess
     * @return the list of the color that was added at the current guess
     */
    List<SecretCodeColors> getGuess();

    /**
     * Permits to start a new guess
     */
    void startNewGuess();
    
    /**
     * Add point at the player
     * @param points
     */
    void addPoints(int points);

    /**
     * Get the name of the player
     * @return the name of the player.
     */
    String getNamePlayer();

    /**
     * Get the player's points
     * @return the points of the player.
     */
    int getPoints();
}
