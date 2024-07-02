package it.unibo.mparty.view;

import it.unibo.mparty.controller.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class InitialScreenImpl implements InitialScreen {

    private GameController controller;

    @FXML
    private Button addPlayers;

    @FXML
    private Button startGame;

    @FXML
    private ChoiceBox<String> playerChoiceBox;


    @Override
    public void setObserver(GameController observer) {
        this.controller = observer;
    }

    @Override
    public void setUp() {
        this.startGame.setDisable(false);
        //add to the choice box the characters with a controller method
    }

    @Override
    public void handleExitButton(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void handleDifficultyButton(ActionEvent event) {
        //set the string to mychoicebox.getValue() to get current choice so when the game start you pass that difficulty
    }

    @Override
    public void handleAddPlayerButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MiniScreen.fxml"));
        Parent root = loader.load();
        //var controller = loader.getController();
        MiniScreen controller = new MiniScreenImpl();
        controller.setUp(this.controller);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add player");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @Override
    public void handleStartButton(ActionEvent event) {

    }
}