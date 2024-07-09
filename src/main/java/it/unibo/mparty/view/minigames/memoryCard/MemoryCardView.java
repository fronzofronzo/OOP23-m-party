package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.view.SceneView;
import it.unibo.mparty.view.minigames.MinigameView;
import javafx.scene.control.Button;

/**
 * This class models the View of the mini-game Memory Card. The view of this
 * minigame can add game cards to the view and can modify the type and the
 * status of the cards.
 */
public interface MemoryCardView extends MinigameView {

    /**
     * Set if the selected card can be clicked or not.
     * @param index of the card to select.
     * @param status true if it's clickable, false
     *              otherwise.
     */
    void setCardStatus(int index, boolean status);

    /**
     * Set the type of card.
     * @param index of card.
     * @param type to set.
     */
    void setCardType(int index, String type);

    /**
     * Add a card to the game table.
     * @param text to write on the card.
     */
    void addCard(String text);

    /**
     * Set the mistake number made by the player.
     * @param n number of mistakes.
     */
    void setMistakesNumber(int n);

}
