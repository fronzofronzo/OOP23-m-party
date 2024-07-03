package it.unibo.mparty.model.item.api;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;

/**
 * This interface models the items in game
 */
public interface Item {
    
    /**
     * Activate the effect of the item
     * 
     * @param player the player on whom the effect is activated
     */
    void activate (Player player);

    /**
     * Get the description of the effect of the item
     * 
     * @return the description of the effect of the item
     */
    String getDescription ();

    /**
     * Get the cost of the item
     * 
     * @return the cost of the item
     */
    int getCost ();

    /**
     * Get the name of the item
     * 
     * @return the name of the item
     */
    ItemName getName();
}

