package it.unibo.mparty.model;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.utilities.Direction;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.SlotType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface that models the model of the main game
 */
public interface GameModel {

    /**
     * Moves the current player to a new position
     *
     * @return
     */
    void movePlayer(Optional<Direction> dir);

    /**
     * Roll dices of the actual player
     * @return the result of the roll
     */
    int rollDice();

    /**
     * Activate the effect of slot where is the player that's playing its turn
     */
    void action();

    /**
     * Make the player that's playing its turn use the selected item
     * @param item {@link ItemName} to use
     */
    void useItem(ItemName itemName);

    /**
     * Buy an item from the shop if the player can
     * @param itemName name of the item
     * @return true if the player can buy the item, false otherwise
     */
    boolean buyItem (ItemName itemName);

    /**
     * Method to end the current minigame and update the model
     * with the results
     * @param result of the minigame
     */
    void endMinigame(Pair<String,Integer> result);

    /**
     * Checks if the game's over
     * @return true if the game is over, false otherwise
     */
    boolean isOver();

    /**
     * Checks if the slot of the actual player is on a SlotType.SHOP
     * @return true if the actual player is on a SlotType.SHOP, false otherwise
     */
    boolean isShop();

    /**
     * Get the username of the player that has won
     * @return the username of winner
     */
    String getWinner();

    /**
     * If is active, get the name of the minigame.
     * @return {@link Optional}: it's empty if there is no minigame running
     * at that moment. If there's a minigame playing, the Optional contains
     * its name
     */
    Optional<String> getActiveMinigame();

    /**
     * Return the message to print according to the actual game status
     * @return the message
     */
    String getMessage();

    /**
     * Get the board configuration: for each slot, returns the relative slot type
     * @return {@link Map} of {@link Position} and {@link SlotType}
     */
    Map<Position, SlotType> getBoardConfig();

    /**
     * Get the board width and height
     * @return {@link Pair} containing dimensions
     */
<<<<<<< HEAD
    Pair<Integer, Integer> getBoardDim();
=======
    Pair<Integer, Integer> getBoardDimensions();

    /**
     * Make the player that's playing its turn use the selected item
     * @param item {@link ItemName} to use
     */
    void useItem(ItemName item);

    /**
     * Method to end the current minigame and update the model
     * with the results
     * @param result of the minigame
     */
    void endMinigame(Pair<String,Integer> result);

    List<String> getPlayersNicknames();

    /**
     * Get the information of the player that's playing the actual turn
     * @return {@link Pair} containing nickname of player and it's
     * {@link Position}
     */
    Pair<String, Position> getActualPlayerInfo();

    /**
     * Buy an item from the shop if the player can
     * @param itemName {@link ItemName} of the item
     * @return true if the player can buy the item, false otherwise
     */
    boolean buyItem (ItemName itemName);
>>>>>>> develop

    /**
     * Get a list of items in the shop
     * @return a list of the items in the shop
     */
    List<Item> getItemsFromShop();

    /**
     * Get a list of items in the shop
     * @return a list of the items in the shop
     */
    List<ItemName> getItemsOfCurrentPlayer();

    /**
     * Get the players that are participating
     * @return {@link List} of {@link Player} with all players
     */
    List<Player> getPlayers();
}