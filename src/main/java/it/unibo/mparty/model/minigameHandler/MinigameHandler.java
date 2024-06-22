package it.unibo.mparty.model.minigameHandler;

public interface MinigameHandler {

    /**
     * Start a new minigame
     */
    void startMinigame();

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
