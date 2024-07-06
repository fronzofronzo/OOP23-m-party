package it.unibo.mparty.view.shop.api;


import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.view.SceneView;

/**
 * This interface models the view for the shop
 */
public interface ShopView extends SceneView{
    
    /**
     * Add the button to the view for buying items
     * @param item the item name
     * @param cost the cost of the item
     */
    public void addButton (ItemName itemName, int cost);

    /**
     * Add the description of the item to the view
     * @param description the description of the item
     */
    public void addDescription(String description);

    public void updateMoney(int money);

    public void initShopView();

}
