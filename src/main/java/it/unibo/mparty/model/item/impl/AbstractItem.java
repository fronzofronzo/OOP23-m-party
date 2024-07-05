package it.unibo.mparty.model.item.impl;

import it.unibo.mparty.model.item.api.Item;

/**
 * This is an abstract class that represents a generical item
 */
public abstract class AbstractItem implements Item{
    
    private final ItemName name;
    private final int cost;
    private boolean target=false;

    /**
     * Constructs a new istance of AbstractItem with the specified ItemName and cost
     * @param name the name of the item
     * @param cost the cost of the item
     */
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

    @Override
    public boolean isOnOthers() {
        return this.target;
    }

    
    
    
}
