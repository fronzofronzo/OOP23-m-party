package it.unibo.mparty.model.shop.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;

public class ShopImpl implements Shop {
    ItemFactory itemFactory = new ItemFactoryImpl();
    private final List<Item> itemList = new ArrayList<>();

    public ShopImpl() {
        itemList.add(itemFactory.createItem(ItemName.CAMPANA_BOO));
        itemList.add(itemFactory.createItem(ItemName.DADO_MALEDETTO));
        itemList.add(itemFactory.createItem(ItemName.DADO_FORTUNATO));
        itemList.add(itemFactory.createItem(ItemName.DOPPIO_DADO));
        itemList.add(itemFactory.createItem(ItemName.TUBO_DORATO));
    }

    @Override
    public void buyItem(Player player, Item item) {
        if (canAfford(player, item)) {
            player.removeCoins(item.getCost());
            player.getPlayerBag().addItem(item);
        }
    }

    private boolean canAfford (Player player, Item item) {
        return player.getNumCoins()>= item.getCost();
    }

}
