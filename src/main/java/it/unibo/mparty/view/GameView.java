package it.unibo.mparty.view;

import java.io.IOException;

/**
 * This interface models the main View of the application. Its role
 * is to handle change between different scenes of the game and communicate
 * with the controller according to players decisions.
 */
public interface GameView {

    /**
     * Sets the next scene to show
     * @param path of the scene to show
     */
    void setScene(String path) throws IOException;

    /**
     * Switch to te board scene
     */
    void switchToBoard();

    void setUpBoard() throws IOException;
}
