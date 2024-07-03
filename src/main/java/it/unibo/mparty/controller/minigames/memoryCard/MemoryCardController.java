package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.MinigameController;

/**
 * Interface that models the controller of the minigame Memory card
 * to
 */
public interface MemoryCardController extends MinigameController {

    /**
     * Select a certain card
     * @param index of the card to select
     */
    void selectCard(int index);

    /**
     * Set up the game view and model at the game start
     */
    void setUpGame();

}
