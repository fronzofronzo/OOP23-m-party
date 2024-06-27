package it.unibo.mparty.view.minigames.memoryCard;

import it.unibo.mparty.view.SceneView;
import javafx.scene.control.Button;

public interface MemoryCardView extends SceneView {

    /**
     * set if the button can be used or not
     * @param index of the button to set
     * @param status true if button is to set enabled, false
     *                  if button is to set not enabled
     */
    void setButtonStatus(int index, boolean status);

    /**
     * Set the type of card
     * @param index of card
     * @param type to set
     */
    void setCardType(int index, String type);

    /**
     * show the results of the game
     * @param n coins earned by the player
     */
    void showResult(int n);

    /**
     * Add a card to the game table
     * @param text to write on the card
     */
    void addCard(String text);

}
