package it.unibo.mparty.model.player.api;
import it.unibo.mparty.model.item.api.Item;

/**
 * This interface models the bag of each player. Each player has a bag of items
 * During the game, players can buy new items and add them to the bag or use an
 * item and remove it from the bag.
 */
public interface PlayerBag {

    /**
     * Get a certain {@link Item} from player items collection
     * @param i index of the selected {@link Item}
     * @return the selected {@link Item}
     */
    Item getItem(int i);

    /**
     * Add an item to the player bag. The item is added in the first free
     * space of player bag
     * @param item to add
     */
    void addItem(Item item);

    /**
     * Remove selected item from the player bag
     * @param i index of item to remove
     * @throws IllegalAccessException in case of trying to remove an item from
     * a position where it is not present
     */
    void removeItem(int i) throws IllegalAccessException;

    /**
     * Check if the player's bag is full
     * @return true if it's full, false otherwise
     */
    boolean isFull();

}
