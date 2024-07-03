package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.GameModelBuilderImpl;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.InitialScreen.api.InitialScreen;
import it.unibo.mparty.view.InitialScreen.api.MiniScreen;
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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class InitialScreenImpl extends AbstractSceneView implements InitialScreen {

    private GameModelBuilder builder;
    private GameController controller;
    private final Set<String> temp = Set.of("Easy","Medium","Difficult");
    private String difficulty;

    @FXML
    private Button addPlayers;

    @FXML
    private Button startGame;

    @FXML
    private ChoiceBox<String> playerChoiceBox;




    @Override
    public void handleExitButton(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void handleDifficultyButton(ActionEvent event) {

        this.playerChoiceBox.getItems().addAll(temp);
        this.difficulty = this.playerChoiceBox.getValue();
    }

    @Override
    public void handleAddPlayerButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/MiniScreen.fxml"));
        Parent root = loader.load();
        MiniScreen controller = new MiniScreenImpl();
        controller.setUp(this.builder);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add player");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        this.startGame.setDisable(!this.builder.enoughPlayers());
        this.addPlayers.setDisable(this.builder.isFull());
    }

    @Override
    public void handleStartButton(ActionEvent event) {
        this.builder.difficulty(this.difficulty);
        this.builder.build();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGame.setDisable(true);
        this.playerChoiceBox.getItems().addAll(temp);
        this.builder = new GameModelBuilderImpl();
    }
}