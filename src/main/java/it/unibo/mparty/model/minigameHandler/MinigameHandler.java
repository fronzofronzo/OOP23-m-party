package it.unibo.mparty.model.minigameHandler;

import java.io.FileNotFoundException;

public interface MinigameHandler {

    /**
     * Start a new minigame
     */
    void startMinigame() throws Exception;

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
