package it.unibo.mparty.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
    @SuppressFBWarnings(value = "EI_EXPOSED_REP" , justification = "An abstract scene view should be able "
            + "to provide the reference to main view ")
    public GameView getMainView() {
        return this.mainView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final GameView view, final GameController controller) {
        this.setView(view);
        this.setController(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSED_REP" , justification = "An abstract scene view should be able "
            + "to provide the reference to main controller ")
    public GameController getMainController() {
        return this.mainController;
    }

    private void setView(final GameView view) {
        this.mainView = view;
    }

    private void setController(final GameController controller) {
        this.mainController = controller;
    }
}
