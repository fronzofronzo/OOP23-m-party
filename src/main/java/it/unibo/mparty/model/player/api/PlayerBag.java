package it.unibo.mparty.model.player.api;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.utilities.Position;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
     *
     * @param name
     * @return
     */
    Item useItem(ItemName name);

    /**
     * Check if the player's bag is full
     * @return true if it's full, false otherwise
     */
    boolean isFull();

    /**
     * Get the set of items contained in the player's bag
     * @return {@link List} with all the {@link ItemName} present
     * in the bag
     */
    List<ItemName> getItems();

}
