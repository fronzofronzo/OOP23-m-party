package it.unibo.mparty.view.shop.api;


import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.view.SceneView;

/**
 * This interface models the view for the shop.
 */
public interface ShopView extends SceneView {
    
    void addItemView(ItemName itemName, int cost, String description);

    /**
     * Update how much money the player who's buying in the shop has left.
     * @param money money left at the player
     */
    void updateMoney(int money);

    /**
     * Initialize the shop view.
     */
    void initShopView();

}
