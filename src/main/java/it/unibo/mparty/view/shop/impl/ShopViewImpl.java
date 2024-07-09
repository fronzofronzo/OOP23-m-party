package it.unibo.mparty.view.shop.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import it.unibo.mparty.model.item.impl.ItemName;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.shop.api.ShopView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Implements the view for the shop.
 */
public class ShopViewImpl extends AbstractSceneView implements ShopView {
    private int i = 1;
    private List<ItemName> itemNameList = new ArrayList<>();
    private static final double DISTANCE_FROM_BUTTON = 70.0;
    private static final double LEFT_MARGIN = 40.0;

    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;

    @FXML 
    private Label moneyLabel;

    private void selectItem(final ActionEvent e) {
        this.getMainController().buyItem(itemNameList
        .get(this.buttonPane.getChildren().indexOf((Button) (e.getSource()))), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateMoney(final int money) {
        moneyLabel.setText("Monete rimaste: " + money);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initShopView() {
        this.getMainController().setUpShop(this);
    }

    @FXML
    private void closeShop() throws IOException {
        this.getMainView().setBoardScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemView(final ItemName itemName, final int cost, final String description) {
        final Button but = new Button();
        final Label lb = new Label(description);
        but.setText("Acquista " + itemName.toString() + ": " + cost);
        but.setOnAction(this::selectItem);
        buttonPane.getChildren().add(but);
        AnchorPane.setTopAnchor(but, i * DISTANCE_FROM_BUTTON);
        AnchorPane.setLeftAnchor(but, LEFT_MARGIN);
        descriptionPane.getChildren().add(lb);
        AnchorPane.setTopAnchor(lb, i * DISTANCE_FROM_BUTTON);
        AnchorPane.setLeftAnchor(lb, LEFT_MARGIN);
        i++;
        itemNameList.add(itemName);
    }
}
