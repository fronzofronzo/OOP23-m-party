package it.unibo.mparty.model.shop.api;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;

/**
 * This interface models the shop
 */
public interface Shop {
    /**
     * buy an item from the shop
     * @param player the player who is buying the item
     * @param item the name of the item 
     */
    void buyItem(Player player, ItemName item);

    /**
     * get the item from the name
     * @param item the name of the item
     * @return the item considerated
     */
    Item getItem(ItemName item);
}
