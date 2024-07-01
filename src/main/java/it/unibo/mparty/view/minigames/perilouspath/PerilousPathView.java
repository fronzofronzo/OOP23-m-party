package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.model.minigames.perilouspath.api.AbstractPosition;
import it.unibo.mparty.model.minigames.perilouspath.api.PerilousPath;
import it.unibo.mparty.view.SceneView;
import javafx.event.ActionEvent;

import java.util.List;

public interface PerilousPathView  extends SceneView{

    public void setObserver(PerilousPathController controller);

    public void setUpView(List<AbstractPosition> balls,List<AbstractPosition> bombs);

    public void hideBombs(List<AbstractPosition> bombs);

    public void hitTile(PerilousPath.Type type);

    public void buttonClicked(ActionEvent e);
    
}