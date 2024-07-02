package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.GameModelBuilder;
import javafx.event.ActionEvent;

public interface MiniScreen {

    void handleOkButton(ActionEvent e);

    void handleBackButton(ActionEvent e);

    void handleCharacterChoiceBox(ActionEvent e);

    void handleUsernameTextField(ActionEvent e);

    void setUp(GameModelBuilder builder);




}