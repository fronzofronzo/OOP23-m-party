package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;
import javafx.event.ActionEvent;

public interface MiniScreen {

    void handleOkButton(ActionEvent e);

    void handleBackButton(ActionEvent e);

    void handleCharacterChoiceBox(ActionEvent e);

    void handleUsernameTextField(ActionEvent e);

    void setUp(GameController controller);




}