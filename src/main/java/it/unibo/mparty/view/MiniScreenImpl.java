package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.GameModelBuilder;
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
        //controller.addPlayers();
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
        //load the choicebox whit characters
    }
}