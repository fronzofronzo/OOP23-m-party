package it.unibo.mparty.model.minigames.memoryCard.api;

import it.unibo.mparty.model.player.Position;

public interface memoryCardModel {

    /**
     * Select the first card of the pair
     * @param card it's the first card selected
     */
    void firstClick(Position card);
}
