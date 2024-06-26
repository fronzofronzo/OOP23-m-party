package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;

public abstract class AbstractSceneView implements SceneView{

    private GameView mainView;
    private GameController mainController;

    @Override
    public GameView getMainView(){
        return this.mainView;
    }


}
