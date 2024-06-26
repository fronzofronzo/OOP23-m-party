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
     * Set text string to a certain button
     * @param index of the button
     * @param text to set
     */
    void setTextButton(int index, String text);

    /**
     * show the results of the game
     * @param n coins earned by the player
     */
    void showResult(int n);

    /**
     * Add a {@link Button} to the central pane
     * @param text to set on the button
     */
    void addButton(String text);

}
