package it.unibo.mparty.controller.minigames.nanogram.api;

import it.unibo.mparty.utilities.Position;

/**
 * Interface representing the controller for a Nanogram game.
 * This controller handles the interactions between the model and the view.
 */
public interface NanogramController {

    /**
     * Starts the Nanogram game.
     * This method initializes the game, setting up the model and view as needed.
     */
    void startGame();

    /**
     * Handles a click input from the user.
     * This method processes the user's click and updates the model accordingly.
     *
     * @param click the position of the click in the grid.
     */
    void getClick(Position click);

    /**
     * Updates the model based on the current game state.
     * This method updates the model with any changes resulting from user actions or game logic.
     */
    void updateModel();

    /**
     * Updates the view to reflect the current state of the model.
     * This method ensures that the view displays the latest information from the model.
     */
    void updateView();
}
