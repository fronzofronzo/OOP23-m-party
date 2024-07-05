package it.unibo.mparty.view.shop.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.shop.api.ShopView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopViewImpl extends AbstractSceneView implements ShopView{

    private int i=1;
    private int j=1;
    private List<ItemName> itemNameList = new ArrayList<>();
    private final static double DISTANCE_FROM_BUTTON = 70.0;

    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;

    @FXML 
    private Label moneyLabel;


    public void addButton (ItemName itemName, int cost)
    {
        final Button but = new Button();
        but.setText("Acquista "+ itemName.getNameToString() + ": " + cost);
        but.setOnAction(this::selectItem);
        buttonPane.getChildren().add(but);
        AnchorPane.setTopAnchor(but, i*DISTANCE_FROM_BUTTON);
        i++;
        itemNameList.add(itemName);
    }

    public void addDescription(String description)
    {
        final Label lb = new Label(description);
        descriptionPane.getChildren().add(lb);
        AnchorPane.setTopAnchor(lb,j*DISTANCE_FROM_BUTTON);
        j++;
    }

    private void selectItem(ActionEvent e) {
        this.getMainController().buyItem(itemNameList.get(this.buttonPane.getChildren().indexOf((Button)(e.getSource()))),this);
    }

    @Override
    public void updateMoney(int money) {
        moneyLabel.setText("Monete rimaste: " + money);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.getMainController().setUpShop(this);
    }
    
}
