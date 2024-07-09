package it.unibo.mparty.view.InitialScreen.impl;

import it.unibo.mparty.view.InitialScreen.api.TutorialScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TutorialScreenImpl implements TutorialScreen, Initializable {

    @FXML
    private Button returnButton;

    @FXML
    private TextArea textArea;

    @Override
    public void handleButton(ActionEvent e) {
        Stage stage = (Stage) this.returnButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String text = this.readFile("path to tutorial");
        this.textArea.setText(text);
    }

    private String readFile(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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