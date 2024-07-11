package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.view.InitialScreen.api.TutorialScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * implementation of {@link TutorialScreen}.
 */
public class TutorialScreenImpl implements TutorialScreen, Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private Label tutorialLabel;

    private static final String TUTORIAL_PATH = "src/main/resources/Tutorial.txt";

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleButton(final ActionEvent e) {
        Stage stage = (Stage) this.returnButton.getScene().getWindow();
        stage.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {
            this.tutorialLabel.setText(new String(Files.readAllBytes(Paths.get(TUTORIAL_PATH))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
