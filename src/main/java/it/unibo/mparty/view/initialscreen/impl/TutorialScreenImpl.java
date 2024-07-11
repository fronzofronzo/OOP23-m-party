package it.unibo.mparty.view.initialscreen.impl;

import it.unibo.mparty.view.initialscreen.api.TutorialScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

/**
 * implementation of {@link TutorialScreen}.
 */
public class TutorialScreenImpl implements TutorialScreen, Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private Label tutorialLabel;

    private static final String TUTORIAL_PATH = "Tutorial.txt";

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleButton(final ActionEvent e) {
        Stage stage = (Stage) this.returnButton.getScene().getWindow();
        if (stage != null) {
            stage.close();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final InputStream input = getClass().getClassLoader().getResourceAsStream(TUTORIAL_PATH);
        if (input != null) {
            String text;
            try {
                text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.tutorialLabel.setText(text);
        }
    }

}
