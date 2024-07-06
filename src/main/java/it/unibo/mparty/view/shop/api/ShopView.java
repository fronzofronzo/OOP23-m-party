package it.unibo.mparty.view.shop.api;


import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.view.SceneView;

/**
 * This interface models the view for the shop.
 */
public interface ShopView extends SceneView {
    /**
     * Add the button to the view for buying items.
     * @param itemName the {@link ItemName} of the item
     * @param cost the cost of the item
     */
    void addButton(ItemName itemName, int cost);

    /**
     * Add the description of the item to the view.
     * @param description the description of the item
     */
    void addDescription(String description);

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
