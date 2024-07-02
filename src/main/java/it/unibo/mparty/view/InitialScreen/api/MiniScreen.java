package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;

/**
 * interface that models the controller for the MiniScreen controller that opens up when you click the add player button
 */
public interface MiniScreen {
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
     * method that handles the event that happens when you click the choice box
     * @param e event that happens when you click the choice box
     */
    void handleCharacterChoiceBox(ActionEvent e);

    /**
     * method that handles the event that happens when you write in the text field area
     * @param e event that happens when you start writing in the text field area
     */
    void handleUsernameTextField(ActionEvent e);

    /**
     *  method that attaches a GameModelBuilder object to the view
     * @param builder an object that the view calls when it is needed to create the Game model
     */
    void setUp(GameModelBuilder builder);




}