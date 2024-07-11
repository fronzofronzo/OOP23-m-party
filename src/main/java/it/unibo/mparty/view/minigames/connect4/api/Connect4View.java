package it.unibo.mparty.view.minigames.connect4.api;

import it.unibo.mparty.view.minigames.MinigameView;
import javafx.event.ActionEvent;

/**
 * This interface models the view for a connect4 minigame extending {@link MinigameView} interface.
 */
public interface Connect4View extends MinigameView {

    /**
     * Add a circle to the gameGrid.
     * @param col the column index
     * @param row the row index
     * @param color true if the first player is adding a circle, false otherwise
     */
    void addCircle(int col, int row, boolean color);

    /**
     * Update a label to comunicate with the players playing the minigame.
     * @param msg the msg to be displayed
     */
    void updateDisplayLabel(String msg);

    /**
     * Activate the exitButton.
     * @param pred true if the button has to be activated, false to disactivate it
     */
    void activateExitButton(boolean pred);

    /**
     * Select the column clicked.
     * @param e the click on the button of the relative column
     */
    void selectColumn(ActionEvent e);

    /**
     * Set visible the tutorial label teaching you how to play the game.
     */
    void viewTutorial();

    /**
     * Close the minigame view and set back to the main board.
     */
    void closeView();
}
