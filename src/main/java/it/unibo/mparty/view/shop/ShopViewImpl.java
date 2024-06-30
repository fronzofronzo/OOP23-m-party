package it.unibo.mparty.view.shop;

import java.util.Map;

import it.unibo.mparty.view.AbstractSceneView;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopViewImpl extends AbstractSceneView implements ShopView{



    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;

    private void addButton (String item, int cost)
    {
        final Button but = new Button();
        but.setText("Acquista "+ item + ": " + cost);
        buttonPane.getChildren().add(but);
    }

    private void addDescription(String description)
    {
        final Label lb = new Label(description);
        descriptionPane.getChildren().add(lb);
    }

    @Override
    public void setUpShop(Map<String, Integer> itemMap, String descriptionItem) {
        itemMap.entrySet().forEach(it -> addButton(it.getKey(), it.getValue()));
        addDescription(descriptionItem);
    }
    
}
