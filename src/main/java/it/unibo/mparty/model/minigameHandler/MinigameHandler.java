package it.unibo.mparty.model.minigameHandler;

/**
 * This interface represents the concept of mini-game handler:
 * it's role is to handle the chose and the start of a mini-game
 */
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
