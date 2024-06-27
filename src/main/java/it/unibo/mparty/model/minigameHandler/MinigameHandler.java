package it.unibo.mparty.model.minigameHandler;

import it.unibo.mparty.model.player.api.Player;

import java.io.FileNotFoundException;
import java.util.List;

public interface MinigameHandler {

    /**
     * Start a new minigame
     */
    void startMinigame(List<Player> players) throws Exception;

    /**
     * Get the name of the minigame actually played
     * @return the mini-game's name
     */
    String getMinigame();

    /**
     * Stop current minigame
     */
    void stopMinigame();
}
