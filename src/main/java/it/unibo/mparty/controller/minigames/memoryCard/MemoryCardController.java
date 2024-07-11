package it.unibo.mparty.controller.minigames.memoryCard;

import it.unibo.mparty.controller.minigames.MinigameController;

/**
 * Interface that models the controller of the minigame Memory card. It
 * represents the Controller of the patter MVC. It handles the request
 * made by the user during the game and modify properly the Model. In the case
 * of Memory Card, in addition to {@link MinigameController} interface methods
 * add method to handle the selection of a card during the game.
 */
public interface MemoryCardController extends MinigameController {

    /**
     * Handles the selection of a card made by the user.
     * @param index of the card to select.
     */
    void selectCard(int index);

}
