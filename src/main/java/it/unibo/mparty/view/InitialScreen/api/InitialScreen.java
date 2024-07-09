package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.view.SceneView;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * interface that model the starting screen view you see when you start playing the game
 */
public interface InitialScreen extends SceneView {

     /**
      * method that handles the event that happens when you click the exit button
      * @param event event that happens when you click the exit button
      */
     void handleExitButton(ActionEvent event);

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
     void handleStartButton(ActionEvent event) throws IOException;

     /**
      * method for adding a new player to the game
      * @param username the username the user gives from input
      * @param character the character the user chooses
      */
     void setNewPlayer(String username,String character);

     /**
      * method for setting the text inside the label in the fxml file
      * @param text the text that will be set in the label
      */
     void setLabelText(String text);

}
