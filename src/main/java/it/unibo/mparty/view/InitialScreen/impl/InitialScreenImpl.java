package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.GameModelBuilderImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.InitialScreen.api.InitialScreen;
import it.unibo.mparty.view.InitialScreen.api.MiniScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InitialScreenImpl extends AbstractSceneView implements InitialScreen, Initializable {


    private GameModelBuilder builder;
    private final List<String> difficulties = new ArrayList<>();
    private String difficulty = "";

    @FXML
    private ImageView imageView;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button addPlayers;

    @FXML
    private Button startGame;

    @FXML
    private ChoiceBox<String> playerChoiceBox;

    @FXML
    private Label exceptionLabel;

    @Override
    public void handleExitButton(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void handleAddPlayerButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/MiniScreen.fxml"));
        Parent root = loader.load();
        MiniScreen miniScreenController = loader.getController();
        miniScreenController.setUp(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Aggiungi Giocatore");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        this.startGame.setDisable(!(this.builder.enoughPlayers() && !this.difficulty.isEmpty()));
        this.addPlayers.setDisable(this.builder.isFull());
    }

    @Override
    public void handleStartButton(ActionEvent event) throws IOException {
        this.builder = this.builder.difficulty(this.difficulty);
        GameController controller = this.getMainController();
        controller.startGame(this.builder.build());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.startGame.setDisable(true);
        for(BoardType difficulty: BoardType.values()){
            this.difficulties.add(difficulty.toString());
        }
        this.playerChoiceBox.getItems().addAll(this.difficulties);
        this.playerChoiceBox.setOnAction(e -> {
            this.difficulty = this.playerChoiceBox.getValue();
            this.startGame.setDisable(!(this.builder.enoughPlayers() && !this.difficulty.isEmpty()));
        });
        this.builder = new GameModelBuilderImpl();
        this.stackPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                this.imageView.fitWidthProperty().bind(this.stackPane.widthProperty());
                this.imageView.fitHeightProperty().bind(this.stackPane.heightProperty());
                this.imageView.setPreserveRatio(false);
            }
        });
    }

    @Override
    public void setNewPlayer(String username,String character){
        try{
            this.builder = this.builder.addPlayer(username,character);
            this.setLabelText("player correttamente aggiunto");
        }catch(IllegalArgumentException e){
            this.setLabelText(e.getMessage());
        }
    }

    @Override
    public void setLabelText(String text){
        this.exceptionLabel.setText(text);
    }

}
