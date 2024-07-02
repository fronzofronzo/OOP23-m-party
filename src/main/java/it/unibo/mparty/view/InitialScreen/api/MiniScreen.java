package it.unibo.mparty.view.InitialScreen.api;

import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;

public interface MiniScreen {

    void handleOkButton(ActionEvent e);

    void handleBackButton(ActionEvent e);

    void handleCharacterChoiceBox(ActionEvent e);

    void handleUsernameTextField(ActionEvent e);

    void setUp(GameModelBuilder builder);




}