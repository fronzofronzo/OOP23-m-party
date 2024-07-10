package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.view.InitialScreen.api.TutorialScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class TutorialScreenImpl implements TutorialScreen, Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private Label tutorialLabel;

    private static final String TUTORIAL_PATH = "src/main/resources/Tutorial.txt";


    @Override
    public void handleButton(ActionEvent e) {
        Stage stage = (Stage) this.returnButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.tutorialLabel.setText(new String(Files.readAllBytes(Paths.get(TUTORIAL_PATH))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\LENOVO\\OneDrive\\Desktop\\oop-m-party\\src\\main\\resources\\Tutorial.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}