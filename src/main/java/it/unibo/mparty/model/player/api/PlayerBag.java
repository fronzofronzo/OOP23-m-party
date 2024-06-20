package it.unibo.mparty.model.player.api;
import it.unibo.mparty.model.item.api.Item;

public interface PlayerBag {

    /**
     * Get a certain item from player items collection
     * @param i index of the selected item
     * @return the selected item
     */
    Item getItem(int i);

    /**
     * Add an item to the player bag
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
}
