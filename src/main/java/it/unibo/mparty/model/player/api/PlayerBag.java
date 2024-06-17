package it.unibo.mparty.model.player.api;

public interface PlayerBag {

    /**
     * Get a certain item from player items collection
     * @param i index of the selected item
     * @return the selected item
     */
    Item getItem(int i);
}
