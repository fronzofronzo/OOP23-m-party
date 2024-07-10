package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.item.api.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements a {@code PlayerBag}. It also offers an implementation
 * for the methods to access and modify content of the bag.
 */
public class PlayerBagImpl implements PlayerBag {

    private final List<Item> items;
    private final int capacity;

    /**
     * Initialise a {@link PlayerBag} implementation with selected amount
     * of items.
     * @param numberOfItems dimension of the bag.
     */
    public PlayerBagImpl(final int numberOfItems) {
        this.items = new ArrayList<>();
        this.capacity = numberOfItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(final Item item) {
        if (this.items.size() < this.capacity) {
            this.items.add(item);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item useItem(final ItemName name) {
        Optional<Item> output = this.items.stream().filter(i -> i.getName().equals(name)).findAny();
        if (output.isEmpty()) {
            throw new IllegalStateException("Player should have the item");
        }
        this.items.remove(output.get());
        return output.get();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return items.size() == capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemName> getItems() {
        return (items.stream().map(Item::getName).toList());
    }
}