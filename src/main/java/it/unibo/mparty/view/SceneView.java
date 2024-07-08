package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;

/**
 * Interface that models a general scene of the application
 */
public interface SceneView {

    /**
     * Get the main view of the application
     * @return {@link GameView} of the application
     */
    GameView getMainView();

    /**
     * Initialise the scene with the reference to main view and controller
     * @param view of the application
     * @param controller of the application
     */
    void init(GameView view, GameController controller);

    /**
     * Get the controller of the application
     * @return {@link GameController } of the application
     */
    GameController getMainController();
}
