package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.item.api.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(ItemName item) {
        final Optional<Item> itemOptional = items.stream().filter(i -> i.getName().equals(item)).findAny();
        if(itemOptional.isPresent()){
            final Item it = itemOptional.get();
            it.activate(this.owner);
            items.remove(it);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return items.size() == capacity;
    }
}
