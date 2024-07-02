package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.view.InitialScreen.api.MiniScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MiniScreenImpl implements MiniScreen {

    private GameModelBuilder builder;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField textField;

    private String username;

    private String character;

    @Override
    public void handleOkButton(ActionEvent e) {
        if(this.username != null && this.character != null) {
            this.builder.addPlayer(this.username, this.character);
        }
        System.exit(0);

    }

    @Override
    public void handleBackButton(ActionEvent e) {
        System.exit(0);
    }

    @Override
    public void handleCharacterChoiceBox(ActionEvent e) {
        this.character = choiceBox.getValue();
    }

    @Override
    public void handleUsernameTextField(ActionEvent e) {
        this.username = textField.getText();
    }

    @Override
    public void setUp(GameModelBuilder builder) {
        this.builder = builder;
    }
}