package it.unibo.mparty.view.shop;

import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;


public class ShopViewImpl extends AbstractSceneView implements ShopView{

    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;

    @Override
    public void setUpShop() {
        
    }

    
}
