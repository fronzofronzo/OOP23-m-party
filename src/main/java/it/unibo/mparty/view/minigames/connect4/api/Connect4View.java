package it.unibo.mparty.view.minigames.connect4.api;

import it.unibo.mparty.view.minigames.MinigameView;
import javafx.fxml.Initializable;

public interface Connect4View extends MinigameView, Initializable{

    /**
     * Add a circle to the gameGrid
     * @param col the column index
     * @param row the row index
     * @param color true if the first player is adding a circle, false otherwise
     */
    public void addCircle(int col, int row, boolean color);

    /**
     * Update a label to comunicate with the players playing the minigame
     * @param msg the msg to be displayed
     */
    public void updateDisplayLabel(String msg);


    /**
     * Activate the exitButton
     * @param pred true if the button has to be activated, false to disactivate it
     */
    public void activateExitButton(boolean pred);
}
