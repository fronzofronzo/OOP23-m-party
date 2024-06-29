package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class PerilousPathViewImpl extends AbstractSceneView implements PerilousPathView{

    @Override
    public void setObserver(PerilousPathController controller) {

    }

    @Override
    public void setUpView() {

    }

    @Override
    public void hideBombs() {

    }

    @Override
    public void hitTile() {

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