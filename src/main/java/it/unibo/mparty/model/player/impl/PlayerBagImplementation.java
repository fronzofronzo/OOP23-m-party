package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.item.api.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * This class implements a {@code PlayerBag}. It also offers an implementation
 * for the methods to access and modify content of the bag
 */
public class PlayerBagImplementation implements PlayerBag {

    private final List<Item> items;
    private final int capacity;

    /**
     * Initialise a {@link PlayerBag} implementation with selected amount
     * of items
     * @param numberOfItems dimension of the bag
     */
    public PlayerBagImplementation(int numberOfItems) {
        this.items = new ArrayList<>();
        this.capacity = numberOfItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int i) {
        if(items[i].isPresent()){
            return (Item)items[i].get();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(ItemName item) {
        Optional<Item> itemOptional = Arrays.stream(this.items).filter(i -> i.isPresent()).filter(i -> i.get().)
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return items.size() == capacity;
    }
}
