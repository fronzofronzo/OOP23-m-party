package it.unibo.mparty.model.shop.api;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.player.api.Player;

/**
 * This interface models the shop
 */
public interface Shop {
    /**
     * Make the player buy the item if he can
     * @param player the player who is buying the item
     * @param item the item to buy
     * @return true if the player can buy the item or false otherwise
     */
    boolean buyItem(Player player, Item item);
}
