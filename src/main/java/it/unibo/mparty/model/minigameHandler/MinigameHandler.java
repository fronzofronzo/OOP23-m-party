package it.unibo.mparty.model.minigameHandler;

import it.unibo.mparty.model.minigames.MinigameType;
import it.unibo.mparty.model.player.api.Player;
import java.util.List;

/**
 * This interface models an object that handles the mini-games. Its role
 * is to choose a random minigame, based on the type decided. In addition,
 * It handles the end of a mini-game and provides methods to access to information
 * about players in game and the current minigame.
 */
public interface MinigameHandler {

    /**
     * Method to start a new minigame.
     * @param players that participate to mini-game.
     * @param type - {@link MinigameType} of the mini-game.
     * @throws Exception if there are problems in finding the minigame.
     */
    void startMinigame(List<Player> players, MinigameType type) throws Exception;

    /**
     * Check if there's a minigame running.
     * @return true if there's a minigame active, false otherwise.
     */
    boolean isInGame();

    /**
     * Method to stop the current minigame.
     */
    void stopMinigame();

    /**
     * Get the name of the minigame actually played.
     * @return the mini-game's name.
     */
    String getMinigame();

    /**
     * Method to get the username of players that are playing the minigame.
     * @return {@link List} of nicknames of players.
     */
    List<String> getUsersPlaying();
}

