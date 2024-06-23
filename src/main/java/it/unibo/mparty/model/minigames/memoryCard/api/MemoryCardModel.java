package it.unibo.mparty.model.minigames.memoryCard.api;

import it.unibo.mparty.model.minigames.memoryCard.impl.CardType;
import it.unibo.mparty.model.player.Position;

import java.util.Map;
import java.util.Set;

public interface MemoryCardModel {

    static final int NOT_SELECTED = -1;

    /**
     * flip the card selected
     * @param card to flip
     * @return true if the card is the first selected of the couple, false
     * if it's the second
     */
    boolean flip(int card);

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

    /**
     * Get all the cards of the memory
     * @return map that contains for each card its relative type
     */
    Map<Integer, CardType> getCards();

    /**
     * Check all the card type that have been already guessed
     * @return set of card type
     */
    Set<CardType> guessedCardsType();
}
