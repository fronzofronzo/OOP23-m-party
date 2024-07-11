package it.unibo.mparty.view.initialscreen.api;

import javafx.event.ActionEvent;

/**
 * interface that models the upload on a new stage of the tutorial
 */
public interface TutorialScreen {
    /**
     * method that manages the okButton in Tutorial screen.
     * @param e the action event
     */
    void handleButton(ActionEvent e);

}