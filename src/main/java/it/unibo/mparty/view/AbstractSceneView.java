package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;

/**
 * This class gives a general implementation of {@link SceneView} interface.
 * It implements methods that can be also used by other scenes and sets
 * references to view and controller and provides methods to access them.
 */
public abstract class AbstractSceneView implements SceneView {

    private GameView mainView;
    private GameController mainController;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView getMainView() {
        return this.mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final GameView view, final GameController controller) {
        this.mainView = view;
        this.mainController = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getMainController() {
        return this.mainController;
    }
}
