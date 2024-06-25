package it.unibo.mparty.view;

public abstract class AbstractSceneView implements SceneView{

    private GameView mainView;

    @Override
    public GameView getMainView(){
        return this.mainView;
    }
}
