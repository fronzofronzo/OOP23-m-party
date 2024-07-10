package it.unibo.mparty.view.minigames.nanogram.impl;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NanogramTutorial {

    private static final String TUTORIAL_PATH = "src/main/resources/text/nanogramTutorial.txt";
    private static final double MARGIN = 30;

    @FXML
    private BorderPane pane;

    @FXML
    private void initialize() throws IOException {
        Label tutorialText = new Label();
        tutorialText.setStyle("-fx-font-size: 12pt; -fx-font-style: italic;");
        tutorialText.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(tutorialText, Pos.CENTER);
        BorderPane.setMargin(tutorialText, new Insets(MARGIN));
        this.pane.setCenter(tutorialText);

        tutorialText.setText(new String(Files.readAllBytes(Paths.get(TUTORIAL_PATH))));
    }
}