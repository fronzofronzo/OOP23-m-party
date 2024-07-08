package it.unibo.mparty.model.player.api;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemName;

import java.util.List;

/**
 * This interface models the bag of each player. Each player has a bag of items
 * During the game, players can buy new items and add them to the bag or use an
 * item and remove it from the bag.
 */
public interface PlayerBag {

    /**
     * Add an item to the player bag. The item is added in the first free
     * space of player bag
     * @param item to add
     */
    void addItem(Item item);

    /**
     * Method to use an item that is present in the player's bag.
     * @param name - {@link ItemName} to use.
     * @return {@link Item} that player is going to use.
     */
    Item useItem(ItemName name);

    /**
     * Check if the player's bag is full.
     * @return true if it's full, false otherwise.
     */
    boolean isFull();

    /**
     * Get the set of items contained in the player's bag.
     * @return {@link List} with all the {@link ItemName} present
     * in the bag.
     */
    List<ItemName> getItems();

}
