package it.unibo.mparty.controller.shop.impl;

import java.util.List;
import java.util.Map;

import it.unibo.mparty.controller.shop.api.ShopController;
import it.unibo.mparty.model.shop.api.Shop;
import it.unibo.mparty.model.shop.impl.ShopImpl;
import it.unibo.mparty.view.shop.ShopView;

public class ShopControllerImpl implements ShopController{

    private final Shop modelShop;
    private final ShopView view;

    public ShopControllerImpl (ShopView view)
    {
        this.modelShop=new ShopImpl();
        this.view=view;
    }

    @Override
    public void setUpShop(Map<String, Integer> itemMap, List<String> descriptionItem) {
        itemMap.entrySet().forEach(it -> view.addButton(it.getKey(), it.getValue()));
        descriptionItem.stream().forEach(it-> view.addDescription(it));
    }

    @Override
    public boolean selectItem(String item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectItem'");
    }
}
