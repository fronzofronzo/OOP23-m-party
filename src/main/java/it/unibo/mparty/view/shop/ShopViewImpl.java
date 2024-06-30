package it.unibo.mparty.view.shop;


import java.util.List;
import java.util.Map;

import it.unibo.mparty.controller.shop.api.ShopController;
import it.unibo.mparty.controller.shop.impl.ShopControllerImpl;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopViewImpl extends AbstractSceneView implements ShopView{

    private final ShopController controller = new ShopControllerImpl(this);

    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;

    public ShopViewImpl() {
        controller.setUpShop(Map.of("Dado",20), List.of("Lanci il dado"));
    }

    public void addButton (String item, int cost)
    {
        final Button but = new Button();
        but.setText("Acquista "+ item + ": " + cost);
        but.setOnAction(this::selectItem);
        buttonPane.getChildren().add(but);
    }

    public void addDescription(String description)
    {
        final Label lb = new Label(description);
        descriptionPane.getChildren().add(lb);
    }

    private void selectItem(ActionEvent e) {

    }
    
}
