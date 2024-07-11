package it.unibo.mparty.view.minigames.nanogram.impl;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class NanogramTutorial {

    private static final String TUTORIAL_PATH = "src/main/resources/text/nanogramTutorial.txt";
    private static final double MARGIN = 30;

    @FXML
    private BorderPane pane;

    @FXML
    private void initialize() {
        Label tutorialText = new Label();
        tutorialText.setStyle("-fx-font-size: 12pt; -fx-font-style: italic;");
        tutorialText.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(tutorialText, Pos.CENTER);
        BorderPane.setMargin(tutorialText, new Insets(MARGIN));
        this.pane.setCenter(tutorialText);

        final InputStream input = getClass().getClassLoader().getResourceAsStream(TUTORIAL_PATH);
        if (input != null) {
            String text;
            try {
                text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tutorialText.setText(text);
        }
    }
}
