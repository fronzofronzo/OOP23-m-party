package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;

public abstract class AbstractSceneView implements SceneView{

    private GameView mainView;
    private GameController mainController;

    @Override
    public GameView getMainView(){
        return this.mainView;
    }

    @Override
    public void init(GameView view, GameController controller){
        this.mainView = view;
        this.mainController = controller;
    }

    @Override
    public GameController getMainController(){
        return this.mainController;
    }
}