package it.unibo.mparty.model.shop.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.item.api.ItemFactory;
import it.unibo.mparty.model.item.impl.ItemFactoryImpl;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;

/**
 * Implementation of the shop.
 */
public class ShopImpl implements Shop {
    private ItemFactory itemFactory = new ItemFactoryImpl();
    private final List<Item> itemList = new ArrayList<>();

    /**
     * Constructs a new istance of shopImpl.
     */
    public ShopImpl() {
        itemList.add(itemFactory.createItem(ItemName.CAMPANA_BOO));
        itemList.add(itemFactory.createItem(ItemName.DADO_MALEDETTO));
        itemList.add(itemFactory.createItem(ItemName.DADO_FORTUNATO));
        itemList.add(itemFactory.createItem(ItemName.TRIPLO_DADO));
        itemList.add(itemFactory.createItem(ItemName.TUBO_DORATO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(final Player player, final ItemName itemName) {
        Item item = itemList.stream().filter(it -> it.getName().equals(itemName)).findFirst().get();
        if (canAfford(player, item) && !player.getPlayerBag().isFull()) {
            player.removeCoins(item.getCost());
            player.getPlayerBag().addItem(item);
            return true;
        }
        return false;
    }

    private boolean canAfford(final Player player, final Item item) {
        return player.getNumCoins() >= item.getCost();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getItemList() {
        return itemList;
    }

}
