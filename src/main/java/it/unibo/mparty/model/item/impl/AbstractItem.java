package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.item.api.Item;

/**
 * Abstract class for items
 */

public abstract class AbstractItem implements Item{
    private final ItemName name;
    private final int cost;

    public AbstractItem (ItemName name,int cost) {
        this.name=name;
        this.cost=cost;
    }

    @Override
    public ItemName getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }
    
    
}
