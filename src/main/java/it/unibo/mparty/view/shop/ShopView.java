package it.unibo.mparty.view.shop;


import it.unibo.mparty.view.SceneView;
import javafx.fxml.Initializable;

/**
 * This interface models the view for the shop
 */
public interface ShopView extends SceneView,Initializable{
    
    /**
     * Add the button to the view for buying items
     * @param item the item name
     * @param cost the cost of the item
     */
    public void addButton (String item, int cost);

    /**
     * Add the description of the item to the view
     * @param description the description of the item
     */
    public void addDescription(String description);

    public void updateMoney(int money);

}
