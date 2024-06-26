package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;

/**
 * Interface that models a general scene of the application
 */
public interface SceneView {

    /**
     * gets the main view of reference
     * @return {@link GameView} of reference
     */
    GameView getMainView();

    /**
     * Initialise the scene with the reference to main view and controller
     * @param view of the application
     * @param controller of the application
     */
    void init(GameView view, GameController controller);
}
