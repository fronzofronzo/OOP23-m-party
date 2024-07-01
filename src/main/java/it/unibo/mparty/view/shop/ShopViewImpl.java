package it.unibo.mparty.view.shop;



import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
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
