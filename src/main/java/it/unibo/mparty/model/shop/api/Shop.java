package it.unibo.mparty.model.shop.api;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import java.util.List;

/**
 * This interface models the shop.
 */
public interface Shop {
    /**
     * Make the player buy the item if he can.
     * @param player the player who is buying the item
     * @param itemName the {@link ItemName} of the item to buy
     * @return true if the player can buy the item or false otherwise
     */
    boolean buyItem(Player player, ItemName itemName);

    /**
     * It gets the list of {@link Item} in the shop in a list.
     * @return list of {@link Item} in the shop
     */
    List<Item> getItemList();
}
