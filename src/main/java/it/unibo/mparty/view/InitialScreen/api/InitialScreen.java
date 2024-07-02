package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface InitialScreen {

     void setBuilder(GameModelBuilder builder);

     void setUp();

     void handleExitButton(ActionEvent event);

     void handleDifficultyButton(ActionEvent event);

     void handleAddPlayerButton(ActionEvent event) throws IOException;

     void handleStartButton(ActionEvent event);

}