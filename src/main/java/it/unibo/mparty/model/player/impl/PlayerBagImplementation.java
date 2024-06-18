package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.PlayerBag;

import java.util.Optional;

public class PlayerBagImplementation implements PlayerBag {

    private Optional<Item>[] items;

    public PlayerBagImplementation(int numberOfItems) {
        this.item = new Optional[numberOfItems];
        for(int i = 0; i < numberOfItems; i++ ){
            items[i] = (Item)Optional.empty();
        }
    }

    @Override
    public Item getItem(int i) {
        if(items[i].isPresent()){
            return items[i].get();
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
    public void removeItem(int i) {

    }
}
