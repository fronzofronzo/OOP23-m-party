package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.utilities.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class implements a {@code PlayerBag}. It also offers an implementation
 * for the methods to access and modify content of the bag
 */
public class PlayerBagImplementation implements PlayerBag {

    private final List<Item> items;
    private final int capacity;
    private final Player owner;

    /**
     * Initialise a {@link PlayerBag} implementation with selected amount
     * of items
     * @param numberOfItems dimension of the bag
     */
    public PlayerBagImplementation(final int numberOfItems, final Player pl) {
        this.items = new ArrayList<>();
        this.capacity = numberOfItems;
        this.owner = pl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public Item useItem(ItemName name) {
        Optional<Item> output = this.items.stream().filter(i -> i.getName().equals(name)).findAny();
        if (output.isEmpty()){
            throw new IllegalStateException("Player should have the item");
        }
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
