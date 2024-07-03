package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.view.InitialScreen.api.MiniScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MiniScreenImpl implements MiniScreen {

    private GameModelBuilder builder;
    private final List<String> characterList = new ArrayList<>();

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button backButton;

    private String username;

    private String character;

    @Override
    public void handleOkButton(ActionEvent e) {
        if(this.username != null && this.character != null) {
            this.builder.addPlayer(this.username, this.character);
        }
        Stage stage = (Stage) this.okButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void handleBackButton(ActionEvent e) {
        Stage stage = (Stage) this.backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void handleCharacterChoiceBox(ActionEvent e) {
        this.character = this.choiceBox.getValue();
    }

    @Override
    public void handleUsernameTextField(ActionEvent e) {
        this.username = this.textField.getText();
    }

    @Override
    public void setUp(GameModelBuilder builder) {

        this.builder = builder;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Character character : Character.values()){
            this.characterList.add(character.getName());
        }
        this.choiceBox.getItems().addAll(this.characterList);
    }
}