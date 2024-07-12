package it.unibo.mparty.view.initialscreen.impl;

import it.unibo.mparty.controller.GameController;
import it.unibo.mparty.model.GameModelBuilder;
import it.unibo.mparty.model.GameModelBuilderImpl;
import it.unibo.mparty.utilities.BoardType;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.initialscreen.api.InitialScreen;
import it.unibo.mparty.view.initialscreen.api.MiniScreen;
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

/**
 * implementation of {@link InitialScreen}.
 */
public final class InitialScreenImpl extends AbstractSceneView implements InitialScreen, Initializable {


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

    @FXML
    private Label playersLabel;



    /**
     * {@inheritDoc}
     */
    @Override
    public void handleExitButton(final ActionEvent event) {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleAddPlayerButton(final ActionEvent event) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/MiniScreen.fxml"));
        final Parent root = loader.load();
        final MiniScreen miniScreenController = loader.getController();
        miniScreenController.setUpView(this);
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Aggiungi Giocatore");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        this.startGame.setDisable(!(this.builder.enoughPlayers() && !this.difficulty.isEmpty()));
        this.addPlayers.setDisable(this.builder.isFull());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStartButton(final ActionEvent event) throws IOException {
        this.builder = this.builder.difficulty(this.difficulty);
        final GameController controller = this.getMainController();
        controller.startGame(this.builder.build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.startGame.setDisable(true);
        for (final BoardType difficulty : BoardType.values()) {
            this.difficulties.add(difficulty.toString());
        }
        this.playerChoiceBox.getItems().addAll(this.difficulties);
        this.playerChoiceBox.setOnAction(e -> {
            this.difficulty = this.playerChoiceBox.getValue();
            this.startGame.setDisable(!(this.builder.enoughPlayers() && !this.difficulty.isEmpty()));
        });
        this.builder = new GameModelBuilderImpl();
        this.stackPane.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.imageView.fitWidthProperty().bind(this.stackPane.widthProperty());
                this.imageView.fitHeightProperty().bind(this.stackPane.heightProperty());
                this.imageView.setPreserveRatio(false);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNewPlayer(final String username, final String character) {
        try {
            this.builder = this.builder.addPlayer(username, character);
            this.setLabelText("giocatore correttamente aggiunto");
            this.playersLabel.setText(this.playersLabel.getText() + "\n" + username + ": " + character);
        } catch (IllegalArgumentException e) {
            this.setLabelText(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLabelText(final String text) {
        this.exceptionLabel.setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleTutorialButton(final ActionEvent event) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/TutorialScreen.fxml"));
        final Parent root = loader.load();
        final Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("TUTORIAL");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
