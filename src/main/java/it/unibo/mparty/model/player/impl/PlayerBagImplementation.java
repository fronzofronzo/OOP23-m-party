package it.unibo.mparty.model.player.impl;

import it.unibo.mparty.model.player.api.PlayerBag;

import java.util.Optional;

public class PlayerBagImplementation implements PlayerBag {

    private Optional<Item>[] items;

    public PlayerBagImplementation(int numberOfItems) {
        this.item = new Optional<Item>[numberOfItems];
    }

    @Override
    public Item getItem(int i) {
        return null;
    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(int i) {

    }
}
