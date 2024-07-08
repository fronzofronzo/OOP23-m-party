package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.MinigameController;

/**
 * Interface that models the controller of the minigame Memory card. It
 * represents the Controller of the patter MVC. It
 */
public interface MemoryCardController extends MinigameController {

    /**
     * Select a certain card
     * @param index of the card to select
     */
    void selectCard(int index);


}
