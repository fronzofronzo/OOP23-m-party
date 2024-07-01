package it.unibo.mparty.controller;

import java.util.HashMap;

import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.view.SceneView;
import it.unibo.mparty.view.shop.ShopView;
import it.unibo.mparty.view.shop.ShopViewImpl;


public class GameControllerImpl implements GameController{

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
    public boolean selectItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectItem'");
    }

}
