package it.unibo.mparty.model.item.api;

import java.util.Optional;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.utilities.Position;

/**
 * This interface models the items in game
 */
public interface Item {
    
    /**
     * Activate the effect of the item
     * 
     * @param player the player on whom the effect is activated
     * @param target null if the item is activate on the player who is using it, otherwise the target player
     * @param position null if the item does not require a position, otherwise the position required
     */
    void activate (Player player, Optional<Player> target, Optional<Position> position);

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

    /**
     * Check if the item effect is on the player who use it or on another player
     * 
     * @return true if the effect is on another player, false otherwise
     */
    boolean isOnOthers();

    /**
     * Check if the item needs a position to activate it
     * 
     * @return true if the item requires a position for its effect, false otherwise
     */
    boolean needPosition();
}

