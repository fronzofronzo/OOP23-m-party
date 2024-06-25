package it.unibo.mparty.model.item.api;

import it.unibo.mparty.model.item.impl.ItemName;

/**
 * This interface models a factory method for the creation of the items
 */
public interface ItemFactory {
    /**
     * Create an item 
     * @param name the name of the item to be created
     * @return the item created
     * @throws IllegalArgumentException in case you try to create an item which is not implemented
     */
    public Item createItem(ItemName name) throws IllegalArgumentException;
}
