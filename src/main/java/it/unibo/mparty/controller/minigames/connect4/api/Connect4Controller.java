package it.unibo.mparty.controller.minigames.connect4.api;

import it.unibo.mparty.controller.minigames.MinigameController;

public interface Connect4Controller extends MinigameController{

    /**
     * Manage adding a disc in the view following the model's implementation
     * @param column the column where the player wants to add a disc
     */
    public void check (int column);
}
