package it.unibo.mparty.view.minigames.memorysweep;

import it.unibo.mparty.controller.minigames.memorysweep.MemorySweepController;
import it.unibo.mparty.controller.minigames.memorysweep.MemorySweepControllerImpl;
import it.unibo.mparty.model.minigames.memorysweep.api.MemorySweep;
import it.unibo.mparty.utilities.Pair;
import it.unibo.mparty.utilities.Position;
import it.unibo.mparty.view.AbstractSceneView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementation of {@link MemorySweepView}.
 */
public class MemorySweepViewImpl extends AbstractSceneView implements MemorySweepView, Initializable {

    private final MemorySweepController controller = new MemorySweepControllerImpl(this);
    private static final int SIZE = 8;
    private Button button;
    private List<String> players = new ArrayList<>();
    private static final String PATH = "text/memorySweepTutorial.txt";

    @FXML
    private Button startButton;

    @FXML
    private Label label;

    @FXML
    private GridPane memorySweepGrid;

    private final EventHandler<MouseEvent> click = event -> {
        this.button = (Button) event.getSource();
        this.controller.hit(this.buttonPos(button));
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStartButton(final ActionEvent e) {
        this.startButton.setDisable(true);
        this.controller.setUpGame();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUpGame(final Set<Position> randoms) {
        this.memorySweepGrid.setDisable(true);
        for (final var child : this.memorySweepGrid.getChildren()) {
            final var position = this.buttonPos((Button) child);
            if (randoms.contains(position)) {
                child.setStyle("-fx-background-color: #35e608;");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(final MemorySweep.HitType type, final boolean turn) {
        switch (type) {
            case RIGHT_CHOICE -> {
                if (turn) {
                    this.button.setStyle("-fx-background-color: #ef0427;");
                    this.setLabelText(this.players.get(0) + " HA FATTO SCELTA GIUSTA");
                } else {
                    this.button.setStyle("-fx-background-color: #0623af;");
                    this.setLabelText(this.players.get(1) + " HA FATTO SCELTA GIUSTA");
                }
            }
            case TURN_END -> {
                for (final var child : this.memorySweepGrid.getChildren()) {
                    child.setStyle(" ");
                }
                if (!turn) {
                    this.setLabelText(this.players.get(0) + " HA FINITO IL TURNO, TOCCA A " + this.players.get(1));
                } else {
                    this.setLabelText(this.players.get(1) + " HA FINITO IL TURNO, TOCCA A " + this.players.get(0));
                }
                this.controller.setUpGame();
            }
            case LOSS -> {
                for (final var child : this.memorySweepGrid.getChildren()) {
                    child.setStyle(" ");
                }
                button.setText("X");
                this.memorySweepGrid.setDisable(true);
            }
            default ->  button.setText("X");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideRandoms(final Set<Position> randoms) {
        for (final var child : this.memorySweepGrid.getChildren()) {
            final var position = this.buttonPos((Button) child);
            if (randoms.contains(position)) {
                child.setStyle(" ");
            }
        }
        this.memorySweepGrid.setDisable(false);
    }


    private Position buttonPos(final Button button) {
        final var x = GridPane.getRowIndex(button);
        final var y = GridPane.getColumnIndex(button);
        return new Position(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(final Pair<String, Integer> result) {
        this.memorySweepGrid.setDisable(true);
        this.startButton.setDisable(false);
        this.startButton.setText("RETURN");
        this.startButton.setOnAction(event -> {
            try {
                this.getMainView().setBoardScene();
            } catch (IOException ex) {
                final Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, ex.toString());
            }
        });
        this.setLabelText(result.getFirst() + " HA VINTO " + result.getSecond() + " COINS");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(final List<String> players) {
        this.controller.initGame(Collections.unmodifiableList(players));
        this.players = Collections.unmodifiableList(players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.gridCreation();
    }

    private void gridCreation() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final Button button = new Button();
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnMouseClicked(this.click);
                this.memorySweepGrid.add(button, i, j);
            }
        }
        this.memorySweepGrid.setDisable(true);
        this.label.setWrapText(true);
        final InputStream input = getClass().getClassLoader().getResourceAsStream(PATH);
        if (input != null) {
            String text = "";
            try {
                text = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                final Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, e.toString());
            }
            this.label.setText(text);
        }
    }

    private void setLabelText(final String text) {
        this.label.setText(text);
    }

}
