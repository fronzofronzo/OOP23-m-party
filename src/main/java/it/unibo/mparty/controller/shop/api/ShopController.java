package it.unibo.mparty.controller.shop.api;

import java.util.List;
import java.util.Map;

public interface ShopController {
    
    public void setUpShop(Map<String,Integer> itemMap, List<String> descriptionItem);

    public boolean selectItem(String item);
}
