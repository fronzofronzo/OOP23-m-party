package it.unibo.mparty.controller;

import it.unibo.mparty.view.shop.ShopView;

public interface GameController {
    public void setUpShop(ShopView shopview);

    public boolean selectItem();
}
