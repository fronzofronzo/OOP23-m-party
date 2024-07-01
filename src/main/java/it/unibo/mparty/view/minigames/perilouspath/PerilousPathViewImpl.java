package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.model.minigames.perilouspath.impl.PathPosition;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PerilousPathViewImpl extends AbstractSceneView implements PerilousPathView{

    @FXML
    private GridPane gridPane;
    private Button button;

    private PerilousPathController observer;
    @Override
    public void setObserver(PerilousPathController controller) {
        this.observer = controller;
    }

    @Override
    public void setUpView(List<AbstractPosition> balls, List<AbstractPosition> bombs) {
        for(var child: this.gridPane.getChildren()){
            var x = GridPane.getRowIndex(child);
            var y = GridPane.getColumnIndex(child);
            var pos = new PathPosition(x,y,7);
            if(balls.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText("O");
                }
            }
            if(bombs.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText("X");
                }
            }
        }
    }

    @Override
    public void hideBombs(List<AbstractPosition> bombs) {
        for(var child: this.gridPane.getChildren()){
            var x = GridPane.getRowIndex(child);
            var y = GridPane.getColumnIndex(child);
            var pos = new PathPosition(x,y,7);
            if(bombs.contains(pos)){
                if (child instanceof Button) {
                    ((Button) child).setText(" ");
                }
            }
        }
    }

    @Override
    public void hitTile(PerilousPath.Type type) {
        switch(type){
            case PATH -> this.button.setText("*");
            case BOMB -> System.out.println("perso");
            case BALL -> System.out.println("vinto");
            case WRONG -> System.out.println("sbagliato");
        }
    }

    @Override
    public void buttonClicked(ActionEvent e) {
        this.button = (Button) e.getSource();
        var x = GridPane.getRowIndex((Node) button);
        var y = GridPane.getColumnIndex((Node) button);
        var pos = new PathPosition(x,y,7);
        //this.observer.hit(pos);
    }




    @Override
    public void init(GameView view, GameController controller) {
        super.init(view, controller);
    }

    @Override
    public GameController getMainController() {
        return super.getMainController();
    }

    @Override
    public GameView getMainView() {
        return super.getMainView();
    }

}