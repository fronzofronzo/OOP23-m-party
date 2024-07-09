package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.view.InitialScreen.api.InitialScreen;
import it.unibo.mparty.view.InitialScreen.api.MiniScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MiniScreenImpl implements MiniScreen {

    private final List<String> characterList = new ArrayList<>();
    private InitialScreen controller;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button backButton;

    private static final int MAX_SIZE = 10;


    @Override
    public void handleOkButton(ActionEvent e) {
        if(this.isShort(this.textField.getText()) && this.isChoiceSelected(this.choiceBox.getValue())) {
            this.controller.setNewPlayer(this.textField.getText(), this.choiceBox.getValue());
        }else{
            this.controller.setLabelText("username o character non correttamente inseriti");
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
    public void setUp(InitialScreen controller){
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Character character : Character.values()){
            this.characterList.add(character.getName());
        }
        this.choiceBox.getItems().addAll(this.characterList);
    }

    private boolean isShort(String text){
        return text.length() < MAX_SIZE && !text.isEmpty();
    }

    private boolean isChoiceSelected(String choice){
        return choice != null && !choice.isEmpty();
    }
}
