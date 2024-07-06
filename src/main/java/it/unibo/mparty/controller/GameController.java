package it.unibo.mparty.controller;

import java.io.IOException;
import java.util.Optional;

import it.unibo.mparty.model.GameModel;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.shop.api.ShopView;

/**
 * This interface models the Controller ( pattern MVC ) of the main game. After the game
 * initialisation, the controller starts the game and sets the model created
 * during the initialisation phase. It also handles user request to the model
 */
public interface GameController {

    /**
     * Manage the start of a new game
     * @param model to set, created during the initial game phase
     */
    void startGame(GameModel model) throws IOException;

    /**
     * Select an item and check if the player can add it
     * @param itemName the {@link ItemName} of the item the player wants to buy
     */
    void useItem(ItemName itemName);

    /**
     * Handle the request of the user to roll dice
     */
    void rollDice();

    /**
     * Handle the action of moving the player in the {@code GameBoard }
     */
    void movePlayer(Optional<Direction> dir);

    /**
     * handle the activation of the slot where is located the actual player
     * @throws IOException 
     */
    void action() throws IOException;

    /**
     * Set up the shop view
     */
    void setUpShop(ShopView shopView);

    /**
     * Select an item and check if the player can add it
     * @param itemString the item the player wants to buy
     */
    void buyItem(ItemName itemName, ShopView shopView);

    /**
     * Get the result of a minigame and update the model with it
     * @param result of the game
     */
    void saveMinigameResult(Pair<String, Integer> result);
}
