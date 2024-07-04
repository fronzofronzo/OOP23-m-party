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
import java.util.Optional;
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

    private Optional<String> username = Optional.empty();
    private Optional<String> character = Optional.empty();
    private static final int MAX_SIZE = 10;

    @Override
    public void handleOkButton(ActionEvent e) {
        if(this.username.isPresent() && this.character.isPresent()) {
            this.builder = this.builder.addPlayer(this.username.get(), this.character.get());
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
        this.character = Optional.of(this.choiceBox.getValue());
    }

    @Override
    public void handleUsernameTextField(ActionEvent e) {
        if(this.textField.getText().length() > MAX_SIZE){
            this.username = Optional.of(this.textField.getText());
        }
    }

    @Override
    public void setUp(GameModelBuilder builder){
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