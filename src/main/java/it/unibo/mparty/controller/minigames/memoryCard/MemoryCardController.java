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
     * Get how many cards there are in game
     * @return number of total cards in the game
     */
    int getCardsNumber();

    /**
     * Gets the name of type of selected card
     * @param i the index of the card
     * @return the type associated to the selected card
     */
    String getCardName(int i);

}
