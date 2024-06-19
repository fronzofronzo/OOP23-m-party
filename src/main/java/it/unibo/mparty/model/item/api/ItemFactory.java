package it.unibo.mparty.model.item.api;

import it.unibo.mparty.model.item.impl.ItemName;

public interface ItemFactory {
    /**
     * Create an item 
     * @param name the name of the item to be created
     * @return the item created
     */
    public Item createItem(ItemName name);
}
