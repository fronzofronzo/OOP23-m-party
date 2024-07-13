package it.unibo.mparty.view.initialscreen.impl;

import it.unibo.mparty.view.initialscreen.api.TutorialScreenView;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementation of {@link TutorialScreenView}.
 */
public class TutorialScreenViewImpl implements TutorialScreenView, Initializable {

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
        if (this.returnButton.getScene().getWindow() instanceof Stage stage) {
            stage.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final InputStream input = getClass().getClassLoader().getResourceAsStream(TUTORIAL_PATH);
        if (input != null) {
            String text = "";
            try {
                text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                final Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, e.toString());
            }
            this.tutorialLabel.setText(text);
        }
    }

}
