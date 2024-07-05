package it.unibo.mparty.controller.minigames;

import java.util.List;

/**
 * This interface models a generic controller for a minigame. It has to
 * handle the start game phase and the end game phase.
 *
 */
public interface MinigameController {

    /**
     * Handles the end phase of a minigame and update the main game
     */
    void endGame();

    /**
     * Initialise the minigame
     * @param players that participate to the minigame
     */
    void initGame(List<String> players);
}
