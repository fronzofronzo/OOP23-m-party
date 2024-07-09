package it.unibo.mparty.application;

import it.unibo.mparty.view.GameViewImpl;
import javafx.application.Application;

/**
 * This is the main class to start the application.
 */
public final class Main {

    private Main() {

    }

    /**
     * Main method to start the application.
     * @param args passed to main.
     */
    public static void main(final String[] args) {
        Application.launch(GameViewImpl.class);
    }
}
