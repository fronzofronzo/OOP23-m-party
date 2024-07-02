package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;

import java.io.IOException;

public interface InitialScreen {

    public void setBuilder(GameModelBuilder builder);

    public void setUp();

    public void handleExitButton(ActionEvent event);

    public void handleDifficultyButton(ActionEvent event);

    public void handleAddPlayerButton(ActionEvent event) throws IOException;

    public void handleStartButton(ActionEvent event);

}