package it.unibo.mparty.view.minigames.perilouspath;

import it.unibo.mparty.controller.minigames.perilousPath.PerilousPathController;
import it.unibo.mparty.view.SceneView;

public interface PerilousPathView  extends SceneView{

    public void setObserver(PerilousPathController controller);
    
}