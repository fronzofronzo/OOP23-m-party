package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * interface that model the starting screen view you see when you start playing the game
 */
public interface InitialScreen {

     /**
      * method that attaches a GameModelBuilder object to the view
      * @param builder an object that the view calls when it is needed to create the Game model
      */
     void setBuilder(GameModelBuilder builder);

     /**
      * method for setting up the view, it disables the button Start and set up the choiceBox choices
      */
     void setUp();

     /**
      * method that handles the event that happens when you click the exit button
      * @param event event that happens when you click the exit button
      */
     void handleExitButton(ActionEvent event);

     /**
      * method that handles the event that happens when you click the difficulty choice box
      * @param event event that happens when you click the difficulty choice box
      */
     void handleDifficultyButton(ActionEvent event);

     /**
      * method that handles the event that happens when you click the add player button
      * @param event event that happens when you click the difficulty choice box
      * @throws IOException catches the exception that the method might throw
      */
     void handleAddPlayerButton(ActionEvent event) throws IOException;

     /**
      * method that handles the event that happens when you click the start button
      * @param event event that happens when you click the start button
      */
     void handleStartButton(ActionEvent event);

}