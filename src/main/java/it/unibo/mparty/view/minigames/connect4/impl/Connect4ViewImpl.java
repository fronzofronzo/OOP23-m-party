package it.unibo.mparty.view.minigames.connect4.impl;

import java.util.List;
import it.unibo.mparty.controller.minigames.connect4.api.Connect4Controller;
import it.unibo.mparty.controller.minigames.connect4.impl.Connect4ControllerImpl;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.view.AbstractSceneView;
import it.unibo.mparty.view.minigames.connect4.api.Connect4View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class implements the {@link Connect4View} interface.
 */
public class Connect4ViewImpl extends AbstractSceneView implements Connect4View {

    private final Connect4Controller controller = new Connect4ControllerImpl(this);
    private static final int CIRCLE_DIMENSION = 25;
    private static final String TUTORIAL_PATH = "src/main/resources/text/connect4Tutorial.txt";

    @FXML
    private Label displayLabel;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane gameGrid;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private Button tutorialButton;

    @FXML
    private Label tutorialLabel;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.updateDisplayLabel(result.getFirst() + " ha vinto " + result.getSecond() + " monete");
        this.activateExitButton(true);
        disableButtons(true);
    }

    private void disableButtons(final boolean pred) {
        buttonGrid.getChildren().listIterator().forEachRemaining(it -> it.setDisable(pred));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDisplayLabel(final String msg) {
        displayLabel.setText(msg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activateExitButton(final boolean pred) {
        exitButton.setDisable(!pred);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCircle(final int col, final int row, final boolean color) {
        Circle circle = new Circle(CIRCLE_DIMENSION);
        circle.setFill(color ? Color.RED : Color.YELLOW);
        StackPane stack = new StackPane();
        stack.getChildren().add(circle);
        gameGrid.add(stack, col, row);
    }

    @FXML
    private void getColumn(final ActionEvent e) {
        Button but = (Button) (e.getSource());
        var index = GridPane.getColumnIndex(but);
        if (index == null) {
            index = 0;
        }
        this.controller.check(index);
    }

    @FXML
    private void closeView() {
        this.controller.endGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(players);
        tutorialLabel.setVisible(false);
        updateTutorialLabel();
    }

    @FXML
    private void viewTutorial() {
        if (tutorialLabel.isVisible()) {
            tutorialButton.setText("Tutorial");
            tutorialLabel.setVisible(false);
            gameGrid.setVisible(true);
            disableButtons(false);
        } else {
            tutorialButton.setText("Chiudi");
            tutorialLabel.setVisible(true);
            gameGrid.setVisible(false);
            disableButtons(true);
        }
    }

    private void updateTutorialLabel() {
        try {
            this.tutorialLabel.setText(new String(Files.readAllBytes(Paths.get(TUTORIAL_PATH))));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
