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

    /**
     * {@inheritDoc}
     */
    @Override
    public void useItem(ItemName item, Optional<Player> target, Optional<Position> position) {
        final Optional<Item> itemOptional = items.stream().filter(i -> i.getName().equals(item)).findAny();
        if(itemOptional.isPresent()){
            final Item it = itemOptional.get();
            it.activate(this.owner, target, position);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemName> getItems() {
        return (items.stream().map(Item::getName).toList());
    }
}
