package it.unibo.mparty.controller.shop;

import it.unibo.mparty.model.shop.api.Shop;

public class ShopControllerImpl implements ShopController{

    private Shop shop;
    @Override
    public void setShop(Shop shop) {
        this.shop=shop;
    }

}
