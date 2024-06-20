package it.unibo.mparty.controller.minigames.memoryCard;

public interface MemoryCardController {

    /**
     * It selects the first card of the pair that the player is trying to guess
     */
    void selectFirst(int index);

    /**
     * It selects the second card of the pair that the player
     * is trying to guess
     */
    void selectSecond();


}
