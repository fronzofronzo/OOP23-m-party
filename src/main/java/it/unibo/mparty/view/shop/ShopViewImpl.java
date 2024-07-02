package it.unibo.mparty.view.shop;

import java.util.ArrayList;
import java.util.List;

import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class ShopViewImpl extends AbstractSceneView implements ShopView{

    private int i=1;
    private int j=1;
    private List<String> stringList = new ArrayList<>();

    @FXML
    private AnchorPane descriptionPane;

    @FXML
    private AnchorPane buttonPane;

    @FXML
    private SplitPane principalPane;


    public void addButton (String item, int cost)
    {
        final Button but = new Button();
        but.setText("Acquista "+ item + ": " + cost);
        but.setOnAction(this::selectItem);
        buttonPane.getChildren().add(but);
        AnchorPane.setTopAnchor(but, i*50.0);
        i++;
        stringList.add(item);
    }

    public void addDescription(String description)
    {
        final Label lb = new Label(description);
        descriptionPane.getChildren().add(lb);
        AnchorPane.setTopAnchor(lb,j*50.0);
        j++;
    }

    private void selectItem(ActionEvent e) {
        this.getMainController().selectItem(stringList.get(this.buttonPane.getChildren().indexOf((Button)(e.getSource()))));
    }
    
}
