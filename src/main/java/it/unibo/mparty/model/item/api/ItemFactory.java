package it.unibo.mparty.model.item.api;

import it.unibo.mparty.model.item.impl.ItemName;

/**
 * This interface models a factory method for the creation of the items.
 */
public interface ItemFactory {
    /**
     * Create the item corrisponding at the name.
     * 
     * @param name the {@link ItemName} of the item to be created
     * @return the {@link Item} created
     */
    Item createItem(ItemName name);
}
