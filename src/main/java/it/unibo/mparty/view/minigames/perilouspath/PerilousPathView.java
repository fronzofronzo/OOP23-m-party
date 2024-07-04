package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.view.SceneView;
import javafx.event.ActionEvent;

import java.util.List;

public interface PerilousPathView  extends SceneView{


    void setUpView(List<AbstractPosition> balls,List<AbstractPosition> bombs);

    void hideBombs(List<AbstractPosition> bombs);

    void hitTile(PerilousPath.Type type);

    void buttonClicked(ActionEvent e);

    void handleStartButton(ActionEvent e) throws InterruptedException;

    void showBombs(List<AbstractPosition> bombs);


    
}