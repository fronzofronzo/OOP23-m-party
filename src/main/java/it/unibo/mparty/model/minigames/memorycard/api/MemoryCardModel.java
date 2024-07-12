package it.unibo.mparty.model.minigames.memorycard.api;

import it.unibo.mparty.model.minigames.MinigameModel;
import it.unibo.mparty.model.minigames.memorycard.impl.CardType;
import java.util.Map;
import java.util.Set;

/**
 * This interface models the Model of the pattern "MVC" of the mini-game
 * Memory-card. It has methods to change the model status and to get game
 * information needed by the controller.
 */
public interface MemoryCardModel extends MinigameModel {

    /**
     * Flip the card selected.
     * @param card to flip.
     * @return true if the card is the first selected of the couple, false
     * if it's the second.
     */
    boolean flip(int card);

    /**
     * Get all the cards of the memory.
     * @return map that contains for each card its relative {@link CardType}.
     */
    Map<Integer, CardType> getCards();

    /**
     * Check all the card type that have been already guessed.
     * @return set of {@link CardType}.
     */
    Set<CardType> guessedCardsType();

    /**
     * Get number of mistakes made by the player.
     * @return number of mistakes.
     */
    int getMistakes();
}
