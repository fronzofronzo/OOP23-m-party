package it.unibo.mparty.controller;

import java.util.HashMap;

import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.model.item.api.Item;
import it.unibo.mparty.model.player.api.Player;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.view.shop.ShopView;



public class GameControllerImpl implements GameController{
    
    private Player player;
    final private Shop shop = new ShopImpl();
    private ShopView shopview;
    private HashMap<String,Integer> itemMap = new HashMap<>();


    @Override
    public void setUpShop(ShopView sceneView) {
        this.shopview=sceneView;
        shop.getItemList().stream().forEach(it -> itemMap.put(it.getName().getNametoString(), it.getCost()));
        itemMap.forEach((str, i) -> shopview.addButton(str, i));
        shop.getItemList().stream().forEach(it -> shopview.addDescription(it.getDescrition()));
    }


    @Override
    public boolean selectItem(String itemString) {
        Item item = (Item) (shop.getItemList().stream().filter(it -> itemString.equals(it.getName().getNametoString())));
        return shop.buyItem(player, item);
    }

}
