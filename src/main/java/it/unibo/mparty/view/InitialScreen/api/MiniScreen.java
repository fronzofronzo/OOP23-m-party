package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.player.impl.Character;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.util.List;

/**
 * interface that models the controller for the MiniScreen controller that opens up when you click the add player button
 */
public interface MiniScreen extends Initializable {
    /**
     * method that handles the event that happens when you click the ok button
     * @param e event that happens when you click the start button
     */
    void handleOkButton(ActionEvent e);

    /**
     * method that handles the event that happens when you click the back button
     * @param e event that happens when you click the back button
     */
    void handleBackButton(ActionEvent e);

    /**
     *  method that attaches a GameModelBuilder object to the view
     * @param controller an object that the view calls when it is needed to create the Game model
     */
    void setUp(InitialScreen controller);




}