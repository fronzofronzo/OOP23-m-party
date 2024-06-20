package it.unibo.mparty.view.minigames.memoryCard;

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
     * shows the results of the game
     */
    void showResult();
}
