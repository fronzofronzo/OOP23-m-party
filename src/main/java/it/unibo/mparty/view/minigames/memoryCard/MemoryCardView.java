package it.unibo.mparty.view.minigames.memoryCard;

import javafx.scene.control.Button;

public interface MemoryCardView {

    /**
     * Disable the button with the relative index
     * @param index of the button to disable
     */
    void disableButton(int index);

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
