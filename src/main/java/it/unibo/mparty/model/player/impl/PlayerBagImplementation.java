package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.PlayerBag;
import it.unibo.mparty.model.item.api.Item;

import java.util.Arrays;
import java.util.Optional;

public class PlayerBagImplementation implements PlayerBag {

    private final Optional[] items;

    public PlayerBagImplementation(int numberOfItems) {
        this.items = new Optional[numberOfItems];
        for(int i = 0; i < numberOfItems; i++ ){
            items[i] = Optional.empty();
        }
    }

    @Override
    public Item getItem(int i) {
        if(items[i].isPresent()){
            return (Item)items[i].get();
        } else {
            return null;
        }
    }

    @Override
    public void addItem(Item item) {
        int i = 0;
        while(items[i].isPresent()){
            i++;
        }
        items[i] = Optional.of(item);
    }

    @Override
    public void removeItem(int i) throws IllegalAccessException {
        if(items[i].isEmpty()){
            throw new IllegalAccessException("No element is present");
        } else {
            items[i] = Optional.empty();
        }
    }

    @Override
    public boolean isFull() {
        return Arrays.stream(items).filter(o -> o.isPresent()).count() == items.length;
    }
}
