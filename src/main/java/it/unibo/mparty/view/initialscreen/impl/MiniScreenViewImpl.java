package it.unibo.mparty.view.initialscreen.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mparty.model.player.impl.Character;
import it.unibo.mparty.view.initialscreen.api.InitialScreenView;
import it.unibo.mparty.view.initialscreen.api.MiniScreenView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * implmentation of {@link MiniScreenView}.
 */
public class MiniScreenViewImpl implements MiniScreenView {

    private final List<String> characterList = new ArrayList<>();
    private InitialScreenView controller;
    private static final int MAX_SIZE = 10;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button okButton;

    @FXML
    private Button backButton;

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleOkButton(final ActionEvent e) {
        if (this.isShort(this.textField.getText()) && this.isChoiceSelected(this.choiceBox.getValue())) {
            if (this.controller != null) {
                this.controller.setNewPlayer(this.textField.getText(), this.choiceBox.getValue());
            }
        } else {
            if (this.controller != null) {
                this.controller.setLabelText("username o character non correttamente inseriti");
            }
        }
        if (this.okButton.getScene().getWindow() instanceof Stage stage) {
            stage.close();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleBackButton(final ActionEvent e) {
        if (this.backButton.getScene().getWindow() instanceof Stage stage) {
            stage.close();
        }

    }

    /**
     * {@inheritDoc}
     */

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "method setUpView needs a mutable object")
    public void setUpView(final InitialScreenView controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        for (final Character character : Character.values()) {
            this.characterList.add(character.getName());
        }
        this.choiceBox.getItems().addAll(this.characterList);
    }

    private boolean isShort(final String text) {
        return text.length() < MAX_SIZE && !text.isEmpty();
    }

    private boolean isChoiceSelected(final String choice) {
        return choice != null && !choice.isEmpty();
    }

}
