package it.unibo.mparty.view.minigames.memorySweep;

import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepController;
import it.unibo.mparty.controller.minigames.memorySweep.MemorySweepControllerImpl;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class MemorySweepViewImpl extends AbstractSceneView implements MemorySweepView, Initializable {

    private final MemorySweepController controller = new MemorySweepControllerImpl(this);
    private final static int SIZE = 8;
    private Button button;
    private List<String> players = new ArrayList<>();

    @FXML
    private Button startButton;

    @FXML
    private Label label;

    @FXML
    private GridPane memorySweepGrid;

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleStartButton(ActionEvent e) {
        this.startButton.setDisable(true);
        this.controller.setUp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp(Set<Position> randoms) {
        this.memorySweepGrid.setDisable(true);
        for (var child : this.memorySweepGrid.getChildren()) {
            var position = this.buttonPos((Button) child);
            if (randoms.contains(position)) {
                child.setStyle("-fx-background-color: #35e608;");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hit(MemorySweep.HitType type, boolean turn) {
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
                for (var child : this.memorySweepGrid.getChildren()) {
                    child.setStyle(" ");
                }
                if (!turn) {
                    this.setLabelText(this.players.get(0) + " HA FINITO IL TURNO, TOCCA A " + this.players.get(1));
                } else {
                    this.setLabelText(this.players.get(1) + " HA FINITO IL TURNO, TOCCA A " + this.players.get(0));
                }
                this.controller.setRandoms();
                this.controller.setUp();
            }
            case LOSS -> {
                for (var child : this.memorySweepGrid.getChildren()) {
                    child.setStyle(" ");
                }
                button.setText("X");
                this.memorySweepGrid.setDisable(true);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideRandoms(Set<Position> randoms) {
        for (var child : this.memorySweepGrid.getChildren()) {
            var position = this.buttonPos((Button) child);
            if (randoms.contains(position)) {
                child.setStyle(" ");
            }
        }
        this.memorySweepGrid.setDisable(false);
    }

    private final EventHandler<MouseEvent> click = event -> {
        this.button = (Button) event.getSource();
        this.controller.hit(this.buttonPos(button));
    };

    private Position buttonPos(Button button) {
        var x = GridPane.getRowIndex(button);
        var y = GridPane.getColumnIndex(button);
        return new Position(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showResult(Pair<String, Integer> result) {
        this.memorySweepGrid.setDisable(true);
        this.startButton.setDisable(false);
        this.startButton.setText("RETURN");
        this.startButton.setOnAction(event -> {
            try {
                this.getMainView().setBoardScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        this.setLabelText(result.getFirst() + " HA VINTO " + result.getSecond() + " COINS");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startMinigame(List<String> players) {
        this.controller.initGame(players);
        this.players = players;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }

    private void setLabelText(String text) {
        this.label.setText(text);
    }

}