package it.unibo.mparty.model.minigames.memoryCard.api;

import it.unibo.mparty.model.player.Position;

public interface memoryCardModel {

    /**
     * Select the first card of the pair
     * @param card it's the first card selected
     */
    void firstClick(Position card);

    /**
     Select the second card of the pair
     * @param card it's the second card selected
     */
    void secondClick(Position card);

    /**
     * Add mistake to player's mistakes counter
     */
    void addMistake();
}
