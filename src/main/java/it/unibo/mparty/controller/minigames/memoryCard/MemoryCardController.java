package it.unibo.mparty.controller.minigames.memoryCard;

/**
 * Interface that models the controller of the minigame Memory card
 * to
 */
public interface MemoryCardController {

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
