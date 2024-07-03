package it.unibo.mparty.controller;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Pair;

/**
 * This interface models the Controller ( pattern MVC ) of the main game. After the game
 * initialisation, the controller starts the game and sets the model created
 * during the initialisation phase. It also handles user request to the model
 */
public interface GameController {

    /**
     * Handle the request of the user to roll dice
     * @return the result of rolling dice
     */
    int rollDice();

    /**
     * Handle the action of moving the player in the {@code GameBoard }
     */
    void movePlayer();

    /**
     * Manage the acquisition of an item of the player's playing the
     * turn
     * @param item that player wants to buy
     * @return true if the player is able to buy it, false otherwise
     */
    boolean buyItem(ItemName item);

    /**
     * Manage the start of a new game
     * @param model to set, created during the initial game phase
     */
    void startGame(GameModel model);

    /**
     * Get the result of a minigame and update the model with it
     * @param result of the game
     */
    void saveMinigameResult(Pair<String, Integer> result);

    void endGame();
}
