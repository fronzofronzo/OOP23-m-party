package it.unibo.mparty.model.minigames.connect4.api;

import it.unibo.mparty.model.minigames.MinigameModel;

/** 
 * This interface models the model for a connect4 minigame extending {@link MinigameModel} interface.
 */
public interface Connect4Model extends MinigameModel {

    /**
     * Get the player who is playing in this turn of the minigame.
     * @return the string that identifies the player
     */
    String getTurnPlayer();

    /**
     * Add a disc in the requested column if the player can.
     * @param column the column where the players want to add a disc
     * @return true if the player can add a disc, false otherwise
     */
    boolean addDisc(int column);

    /**
     * Get the first available row in a specified column.
     * @param column the column selected
     * @return the index of the first row available in the column
     */
    int getAvailableRow(int column);

    /**
     * Get the player1 to manage the different colors for the players.
     * @return the string that identifies the player1
     */
    String getPlayer1();
}
