package it.unibo.mparty.model.minigames.memoryCard.api;

import it.unibo.mparty.model.minigames.memoryCard.impl.CardType;
import it.unibo.mparty.model.player.Position;

import java.util.Map;

public interface MemoryCardModel {

    /**
     * Flip the card
     * @param card it's the first card selected
     */
    void flip(int card);

    /**
     * Add mistake to player's mistakes counter
     */
    void addMistake();

    /**
     * Check if the game is over
     * @return true if the player has guessed all the couples
     * false if there are others couples to find
     */
    boolean isDone();

    /**
     * Get the amount of money that the player should receive at the
     * end of the game
     * @return amount of money earned by the player
     */
    int getResults();

    Map<Integer, CardType> getCards();
}
