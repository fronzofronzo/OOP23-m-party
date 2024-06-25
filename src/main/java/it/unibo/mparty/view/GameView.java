package it.unibo.mparty.view;

import java.io.IOException;

/**
 * Models the view of the application
 */
public interface GameView {

    /**
     * Sets the next scene to show
     * @param path of the scene to show
     */
    void setScene(String path) throws IOException;
}
